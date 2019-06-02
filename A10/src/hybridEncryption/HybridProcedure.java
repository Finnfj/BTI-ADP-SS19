package hybridEncryption;

import java.math.BigInteger;
import java.util.Base64;

public class HybridProcedure {
    private final int BLOCKSIZE;
    private final int ROUNDS;
    private String publicKeyModulusBase64; // string in base64

    public HybridProcedure(String publicKeyModulusBase64, final int BLOCKSIZE, final int ROUNDS) {
        this.BLOCKSIZE = BLOCKSIZE;
        this.ROUNDS = ROUNDS;
        this.publicKeyModulusBase64 = publicKeyModulusBase64;
    }

    public String encryptMessage(String message) {
        // encrypt the message with BlockCipherFistel, the sessionkey is not encrypted
        BlockCipherFistel bcf = new BlockCipherFistel(BLOCKSIZE, ROUNDS);
        byte[] bcfEncryptedMessage = bcf.encryptMessage(message);

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

    public String decryptMessage(String encryptedMessage) {
        return encryptedMessage;
    }
}
