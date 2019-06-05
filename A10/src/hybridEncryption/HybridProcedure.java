package hybridEncryption;

import java.math.BigInteger;
import java.util.Base64;

public class HybridProcedure {
    private final int BLOCKSIZE;
    private final int ROUNDS;
    private final byte PADDING;
    private byte[] partnerPublicKey;
    private byte[] partnerModulus;

    public HybridProcedure(String publicKeyModulusBase64, final int BLOCKSIZE, final int ROUNDS, final byte PADDING) {
        this.BLOCKSIZE = BLOCKSIZE;
        this.ROUNDS = ROUNDS;
        this.PADDING = PADDING;
        this.partnerPublicKey = new byte[BLOCKSIZE/2];
        this.partnerModulus = new byte[BLOCKSIZE];
        setPartnerPublicKeyAndPartnerModulo(publicKeyModulusBase64);
    }

    private void setPartnerPublicKeyAndPartnerModulo(String publicKeyModulusBase64) {
        // decode the base64 encoded publicKeyModulo String and put the partnerPublicKey and the Modulo each into an array
        byte[] publicKeyModulusBytes = Base64.getDecoder().decode(publicKeyModulusBase64);
        System.arraycopy(publicKeyModulusBytes, 0, partnerPublicKey, 0, partnerPublicKey.length); // partnerPublicKey is in the first 8 bytes
        System.arraycopy(publicKeyModulusBytes, BLOCKSIZE/2, partnerModulus, 0, partnerModulus.length); // partnerModulus is in the following 16 bytes
        System.out.println("Partner Data:");
        System.out.println("Public Key: " + BigIntHelper.Byte2BigInt(partnerPublicKey));
        System.out.println("Modulus: " + BigIntHelper.Byte2BigInt(partnerModulus));
    }

    public String encryptMessage(String message) {
        if (message.length() == 0) {
            return "";
        }

        // encrypt the message with BlockCipherFeistel, the sessionkey at the beginning is not encrypted
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, null);
        byte[] bcfEncryptedMessage = bcf.encryptMessage(message.getBytes());

        // put the unecrypted sessionkey into an array
        byte[] sessionkey = new byte[BLOCKSIZE/2];
        System.arraycopy(bcfEncryptedMessage, 0, sessionkey, 0, BLOCKSIZE/2);
        System.out.println("Encryption Sessionkey: " + BigIntHelper.Byte2BigInt(sessionkey));

        // encrypt the sessionkey with the public key and modulo with RSA and put at the beginning of bcfEncryptedMessage, overwriting the plain text sessionkey
        BigInteger encryptedSessionkey = RSA.encryptMessage(BigIntHelper.Byte2BigInt(sessionkey), BigIntHelper.Byte2BigInt(partnerPublicKey), BigIntHelper.Byte2BigInt(partnerModulus));
        byte[] encryptedSessionkeyBytes = BigIntHelper.BigInt2Byte(encryptedSessionkey, BLOCKSIZE);
        System.arraycopy(encryptedSessionkeyBytes, 0, bcfEncryptedMessage, 0, BLOCKSIZE);

        // encode the byte array to base 64 and return it
        return Base64.getEncoder().encodeToString(bcfEncryptedMessage);
    }

    public String decryptMessage(String encryptedMessageBase64, BigInteger privateKey, BigInteger modulus) {
        if (encryptedMessageBase64.length() == 0) {
            return "";
        }

        byte[] encryptedMessage = Base64.getDecoder().decode(encryptedMessageBase64);

        // get sessionkey from encryptedMessage
        byte[] encryptedSessionkey = new byte[BLOCKSIZE];
        System.arraycopy(encryptedMessage, 0, encryptedSessionkey, 0, BLOCKSIZE); // copy the encrypted session key to the array
        BigInteger encryptedSessionkeBI = BigIntHelper.Byte2BigInt(encryptedSessionkey);

        BigInteger sessionkey = RSA.decryptMessage(encryptedSessionkeBI, privateKey, modulus);
        System.out.println("Decryption Sessionkey: " + sessionkey);

        // decrypt the message with the decrypted sessionkey
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, BigIntHelper.BigInt2Byte(sessionkey, BLOCKSIZE/2));
        byte[] decryptedMessage = bcf.decryptMessage(encryptedMessage);

        // remove the sessionkey and the padding from the decrypted message and turn it into a string
        int cleanedDecryptedMessageSize = decryptedMessage.length-BLOCKSIZE;

        // count how often the padding occurs at the end and decrease cleanedDecryptedMessageSize by that
        while (decryptedMessage[cleanedDecryptedMessageSize+BLOCKSIZE-1] == PADDING) {
            cleanedDecryptedMessageSize--;
        }

        // copy the decrpyted message to the array
        byte[] cleanedDecryptedMessage = new byte[cleanedDecryptedMessageSize];
        System.arraycopy(decryptedMessage, BLOCKSIZE, cleanedDecryptedMessage, 0, cleanedDecryptedMessageSize);

        return new String(cleanedDecryptedMessage);
    }
}
