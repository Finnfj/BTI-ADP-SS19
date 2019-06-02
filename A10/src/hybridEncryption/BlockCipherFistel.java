package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class BlockCipherFistel {
    private final int BLOCKSIZE;
    private final int ROUNDS;
    private final byte PADDING = 0x20;

    public BlockCipherFistel(final int BLOCKSIZE, final int ROUNDS) {
        this.BLOCKSIZE = BLOCKSIZE;
        this.ROUNDS = ROUNDS;
    }

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
        if (BLOCKSIZE / 2 >= 0) {
            System.arraycopy(sessionKey, 0, messageBytesOffsetPadding, 0, BLOCKSIZE / 2);
        }

        // extract the left and right part of a block into arrays and swap them ROUND times with the feistelblock
        final int amountOfMessageBlocks = (messageBytesOffsetPaddingSize-BLOCKSIZE)/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE, left, 0, left.length);
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2, right, 0, right.length);
            for (int j = 0; j < ROUNDS; j++) {
                swap(left, right, sessionKey);
            }
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(left, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left.length);
            System.arraycopy(right, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right.length);
        }

        return messageBytesOffsetPadding;
    }

    private void swap(byte[] left, byte[] right, byte[] sessionkey) {
        // swap left and right
        byte[] temp = left;
        left = right;
        right = temp;

        BigInteger rightBI = BigIntHelper.Byte2BigInt(right);
        BigInteger sessionkeyBI = BigIntHelper.Byte2BigInt(sessionkey);

        // apply feistel algo to right side: F(R,K) = (R^2 + K) mod (2^blockSizeBits - 1)
        BigInteger newRight = rightBI.pow(2).add(sessionkeyBI).mod(BigInteger.TWO.pow(BLOCKSIZE*8).subtract(BigInteger.ONE));
        right = BigIntHelper.BigInt2Byte(newRight, BLOCKSIZE);
        for(int i = 0; i < left.length; i++) {
            right[i] ^= left[i];
        }
    }
}
