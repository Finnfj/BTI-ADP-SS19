package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class BlockCipherFeistel {
    private final int BLOCKSIZE;
    private final int ROUNDS;
    private final byte PADDING;
    private byte[] sessionkey;

    public BlockCipherFeistel(final int BLOCKSIZE, final int ROUNDS, final byte PADDING, byte[] sessionkey) {
        this.BLOCKSIZE = BLOCKSIZE;
        this.ROUNDS = ROUNDS;
        this.PADDING = PADDING;
        this.sessionkey = sessionkey;
    }

    // TODO doesnt work?
    public byte[] encryptMessage(byte[] message) {
        // since our array has blocks, we need to add padding in case the message does not fill out the last block completely
        // find out how much extra space we need
        int messageBytesOffsetPaddingSize = message.length + BLOCKSIZE;
        while(messageBytesOffsetPaddingSize % BLOCKSIZE != 0) {
            messageBytesOffsetPaddingSize++;
        }

        // create a new byte array and copy the message bytes into it after the key offset
        byte[] messageBytesOffsetPadding = new byte[messageBytesOffsetPaddingSize];
        System.arraycopy(message, 0, messageBytesOffsetPadding, BLOCKSIZE, message.length);

        // add the padding
        for(int i = message.length + BLOCKSIZE; i < messageBytesOffsetPaddingSize; i++) {
            messageBytesOffsetPadding[i] = PADDING;
        }

        // create a random session key if necessary by filling a byte array with half the blocksize as length and the put it into the beginning of the byte array with the message
        if (sessionkey == null) {
            Random rnd = new SecureRandom();
            sessionkey = new byte[BLOCKSIZE/2];
            rnd.nextBytes(sessionkey);
        }
        System.arraycopy(sessionkey, 0, messageBytesOffsetPadding, 0, BLOCKSIZE / 2);

        // extract the left and right part of a block into arrays and feistel them ROUND times with the feistelblock
        final int amountOfMessageBlocks = (messageBytesOffsetPaddingSize-BLOCKSIZE)/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left, 0, left.length);
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right, 0, right.length);
            for (int j = 0; j < ROUNDS; j++) {
                byte[] temp = left;
                left = right;
                right = temp;

                BigInteger rightBI = BigIntHelper.Byte2BigInt(right);
                BigInteger sessionkeyBI = BigIntHelper.Byte2BigInt(sessionkey);

                // apply feistel algo to right side: F(R,K) = (R^2 + K) mod (2^blockSizeBits - 1)
                BigInteger newRight = rightBI.pow(2).add(sessionkeyBI).mod(BigInteger.TWO.pow(BLOCKSIZE*8).subtract(BigInteger.ONE));
                right = BigIntHelper.BigInt2Byte(newRight, BLOCKSIZE/2);
                for(int k = 0; k < left.length; k++) {
                    right[k] ^= left[k];
                }
            }
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(left, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left.length);
            System.arraycopy(right, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right.length);
        }

        return messageBytesOffsetPadding;
    }

    // TODO doesnt work?
    public byte[] decryptMessage(byte[] encryptedMessage) {
        byte[] decryptedMessage = new byte[encryptedMessage.length-BLOCKSIZE];

        int amountOfMessageBlocks = decryptedMessage.length/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(encryptedMessage, i*BLOCKSIZE+BLOCKSIZE, left, 0, left.length);
            System.arraycopy(encryptedMessage, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right, 0, right.length);
            byte[] temp = left;
            left = right;
            right = temp;
            for (int j = 0; j < ROUNDS; j++) {
                temp = left;
                left = right;
                right = temp;

                BigInteger rightBI = BigIntHelper.Byte2BigInt(right);
                BigInteger sessionkeyBI = BigIntHelper.Byte2BigInt(sessionkey);

                // apply feistel algo to right side: F(R,K) = (R^2 + K) mod (2^blockSizeBits - 1)
                BigInteger newRight = rightBI.pow(2).add(sessionkeyBI).mod(BigInteger.TWO.pow(BLOCKSIZE*8).subtract(BigInteger.ONE));
                right = BigIntHelper.BigInt2Byte(newRight, BLOCKSIZE/2);
                for(int k = 0; k < left.length; k++) {
                    right[k] ^= left[k];
                }
            }
            temp = left;
            left = right;
            right = temp;
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(left, 0, decryptedMessage, i*BLOCKSIZE, left.length);
            System.arraycopy(right, 0, decryptedMessage, i*BLOCKSIZE+BLOCKSIZE/2, right.length);
        }

        return decryptedMessage;
    }
}
