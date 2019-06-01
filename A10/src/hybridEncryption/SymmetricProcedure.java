package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class SymmetricProcedure {
    private final int BLOCKSIZE = 16;

    public void encryptMessage(String message) {
        // put the message as bytes into an array with an offset at the beginning
        byte[] messageBytesWOffset = new byte[message.length() + BLOCKSIZE];
        System.arraycopy(message.getBytes(), 0, messageBytesWOffset, BLOCKSIZE, message.length());

        // create a random session key by filling a byte array with half the blocksize as length and the put it into the beginning of the byte array with the message
        Random rnd = new SecureRandom();
        byte[] sessionKey = new byte[BLOCKSIZE/2];
        rnd.nextBytes(sessionKey);
        for(int i = 0; i < BLOCKSIZE/2; i++) {
            messageBytesWOffset[i] = sessionKey[i];
        }

        BigInteger k = new BigInteger(64, new SecureRandom());
        BigInteger l = BigInteger.ZERO;
        BigInteger r = BigInteger.ZERO;

        for(int i = 0; i < 12; i++) {
            l = r;
            //r =
        }
    }

}
