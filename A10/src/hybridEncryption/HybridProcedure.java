package hybridEncryption;

import java.math.BigInteger;
import java.util.Base64;

public class HybridProcedure {
    private String publicKeyModulusBase64; // string in base64
    private final int BLOCKSIZE;
    private final int ROUNDS;
    private final byte PADDING;

    public HybridProcedure(String publicKeyModulusBase64, final int BLOCKSIZE, final int ROUNDS, final byte PADDING) {
        this.publicKeyModulusBase64 = publicKeyModulusBase64;
        this.BLOCKSIZE = BLOCKSIZE;
        this.ROUNDS = ROUNDS;
        this.PADDING = PADDING;
    }

    public String encryptMessage(String message) {
        if (message.length() == 0) {
            return "";
        }

        // encrypt the message with BlockCipherFeistel, the sessionkey is not encrypted
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, null);
        byte[] bcfEncryptedMessage = bcf.encryptMessage(message.getBytes());

        // decode the base64 encoded publicKeyModulo String and put the publicKey and the Modulo each into an array
        byte[] publicKeyModulusBytes = Base64.getDecoder().decode(publicKeyModulusBase64);
        byte[] publicKey = new byte[BLOCKSIZE/2];
        byte[] modulus = new byte[BLOCKSIZE];
        System.arraycopy(publicKeyModulusBytes, 0, publicKey, 0, publicKey.length); // publicKey is in the first 8 bytes
        System.arraycopy(publicKeyModulusBytes, BLOCKSIZE/2, modulus, 0, modulus.length); // modulus is in the following 16 bytes

        // encrypt the sessionkey with the public key and modulo with RSA and put it into the array
        byte[] sessionKey = new byte[BLOCKSIZE];
        System.arraycopy(bcfEncryptedMessage, 0, sessionKey, 0, sessionKey.length);
        BigInteger encryptedSessionkey = RSA.encryptMessage(sessionKey, BigIntHelper.Byte2BigInt(publicKey), BigIntHelper.Byte2BigInt(modulus));
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

        // get session key from encryptedMessage
        byte[] sessionkey = new byte[BLOCKSIZE];
        System.arraycopy(RSA.decryptMessage(encryptedMessage, privateKey, modulus), 0, sessionkey, 0, BLOCKSIZE);

        // decrypt the message with the sessionkey
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, sessionkey);
        byte[] decryptedMessage = bcf.decryptMessage(encryptedMessage);

        // remove the sessionkey and the padding from the decrypted message and turn it into a string
        int cleanedDecryptedMessageSize = decryptedMessage.length-BLOCKSIZE;

        // count how often the padding occurs at the end and decrease cleanedDecryptedMessageSize by that
        while (decryptedMessage[cleanedDecryptedMessageSize-1] == PADDING) {
            cleanedDecryptedMessageSize--;
        }

        // copy the decrpyted message to the array
        byte[] cleanedDecryptedMessage = new byte[cleanedDecryptedMessageSize];
        System.arraycopy(decryptedMessage, BLOCKSIZE, cleanedDecryptedMessage, 0, cleanedDecryptedMessageSize);

        return new String(cleanedDecryptedMessage);
    }
}
