package hybridEncryption;

import java.math.BigInteger;
import java.util.Base64;

public class HybridProcedure {
    private final int BLOCKSIZE = 16;
    private final int ROUNDS = 12;
    private String publicKeyModulusBase64; // string in base64

    public HybridProcedure(String publicKeyModulusBase64) {
        this.publicKeyModulusBase64 = publicKeyModulusBase64;
    }

    public Base64 encryptMessage(String message) {
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
        BigInteger[] encryptedSessionkey = RSA.encryptMessage(sessionKey, new BigInteger(1, publicKey), new BigInteger(1, modulus));
        byte[] encryptedSessionkeyBytes = BigIntHelper.BigInt2Byte(encryptedSessionkey, BLOCKSIZE);

        return null;
    }
}
