package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class SymmetricProcedure {
    private final int BLOCKSIZE = 16;
    private final int ROUNDS = 12;
    private final byte PADDING = 0x20;

    public byte[] encryptMessage(String message) {
        // put the message as bytes into an array with an offset at the beginning
        byte[] messageBytes = message.getBytes();

        // since our array has blocks, we need to add padding in case the message does not fill out the last block completely
        // find out how much extra space we need
        int messageBytesOffsetPaddingSize = messageBytes.length + BLOCKSIZE;
        while(messageBytesOffsetPaddingSize % BLOCKSIZE != 0) {
            messageBytesOffsetPaddingSize++;
        }

        // create a new byte array and copy the message bytes into it after the key offset
        byte[] messageBytesOffsetPadding = new byte[messageBytesOffsetPaddingSize];
        System.arraycopy(messageBytes, 0, messageBytesOffsetPadding, BLOCKSIZE, messageBytes.length);

        // add the padding
        for(int i = messageBytes.length + BLOCKSIZE; i < messageBytesOffsetPaddingSize; i++) {
            messageBytesOffsetPadding[i] = PADDING;
        }

        // create a random session key by filling a byte array with half the blocksize as length and the put it into the beginning of the byte array with the message
        Random rnd = new SecureRandom();
        byte[] sessionKey = new byte[BLOCKSIZE/2];
        rnd.nextBytes(sessionKey);
        for(int i = 0; i < BLOCKSIZE/2; i++) {
            messageBytesOffsetPadding[i] = sessionKey[i];
        }

        // extract the left and right part of a block into arrays and swap them ROUND times with the feistelblock
        final int amountOfMessageBlocks = (messageBytesOffsetPaddingSize-BLOCKSIZE)/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE, left, 0, left.length);
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2, right, 0, right.length);
            Feistelblock fb = new Feistelblock(left, right, sessionKey, BLOCKSIZE);
            for (int j = 0; j < ROUNDS; j++) {
                fb.swap();
            }
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(fb.getLeft(), 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left.length);
            System.arraycopy(fb.getRight(), 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right.length);
        }

        return messageBytesOffsetPadding;
    }

}
