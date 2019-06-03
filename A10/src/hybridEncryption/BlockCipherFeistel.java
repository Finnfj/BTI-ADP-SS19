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

    public byte[] encryptMessage(byte[] message) {
        System.out.println("Encrypting message");
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

        for(byte b : messageBytesOffsetPadding) {
            System.out.print(b + ", ");
        }
        System.out.println();

        // extract the left and right part of a block into arrays and feistel them ROUND times with the feistelblock
        final int amountOfMessageBlocks = (messageBytesOffsetPaddingSize-BLOCKSIZE)/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left, 0, left.length);
            System.arraycopy(messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right, 0, right.length);
            for (int j = 0; j < ROUNDS; j++) {
                feistel(left, right);
            }
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(left, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE, left.length);
            System.arraycopy(right, 0, messageBytesOffsetPadding, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right.length);
            System.out.print("Round: " + i + " = ");
            for(byte b : messageBytesOffsetPadding) {
                System.out.print(b + ", ");
            }
            System.out.println();
        }

        return messageBytesOffsetPadding;
    }

    public byte[] decryptMessage(byte[] encryptedMessage) {
        System.out.println("Decrypting message");
        byte[] decryptedMessage = new byte[encryptedMessage.length];
        System.arraycopy(encryptedMessage, 0, decryptedMessage, 0, BLOCKSIZE); // copy the session key over

        int amountOfMessageBlocks = (decryptedMessage.length-BLOCKSIZE)/BLOCKSIZE;
        for(int i = 0; i < amountOfMessageBlocks; i++) {
            byte[] left = new byte[BLOCKSIZE/2];
            byte[] right = new byte[BLOCKSIZE/2];
            System.arraycopy(encryptedMessage, i*BLOCKSIZE+BLOCKSIZE, left, 0, left.length);
            System.arraycopy(encryptedMessage, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right, 0, right.length);
            swap(left, right);
            for (int j = 0; j < ROUNDS; j++) {
                feistel(left, right);
            }
            swap(left, right);
            // copy the feistel swapped arrays back into the main array
            System.arraycopy(left, 0, decryptedMessage, i*BLOCKSIZE+BLOCKSIZE, left.length);
            System.arraycopy(right, 0, decryptedMessage, i*BLOCKSIZE+BLOCKSIZE/2+BLOCKSIZE, right.length);
            System.out.print("Round: " + i + " = ");
            for(byte b : decryptedMessage) {
                System.out.print(b + ", ");
            }
            System.out.println();
        }
        return decryptedMessage;
    }

    public static void swap(byte[] a, byte[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException();
        }
        byte[] temp = new byte[a.length];
        System.arraycopy(a, 0, temp, 0, a.length);
        System.arraycopy(b, 0, a, 0, a.length);
        System.arraycopy(temp, 0, b, 0, a.length);
    }

    // TODO: check this
    public void feistel(byte[] left, byte[] right) {
        swap(left, right);

        BigInteger rightBI = BigIntHelper.Byte2BigInt(right);
        BigInteger sessionkeyBI = BigIntHelper.Byte2BigInt(sessionkey);

        // apply feistel algo to right side: F(R,K) = (R^2 + K) mod (2^blockSizeBits - 1)
        BigInteger newRight = rightBI.pow(2);
        newRight = newRight.add(sessionkeyBI);

        BigInteger modulo = BigInteger.TWO;
        modulo = modulo.pow(BLOCKSIZE*8);
        modulo = modulo.subtract(BigInteger.ONE);

        newRight = newRight.mod(modulo);

        byte[] newRightBytes = BigIntHelper.BigInt2Byte(newRight, BLOCKSIZE/2);
        for(int k = 0; k < left.length; k++) {
            newRightBytes[k] ^= left[k];
            //left[k] = (byte) (newRightBytes[k] ^ left[k]);
        }
        System.arraycopy(newRightBytes, 0, right, 0, newRightBytes.length);
    }
}
