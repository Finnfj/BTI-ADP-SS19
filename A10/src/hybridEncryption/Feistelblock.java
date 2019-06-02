package hybridEncryption;

import java.math.BigInteger;

public class Feistelblock {
    private byte[] right;
    private byte[] left;
    private byte[] sessionkey;
    private int blockSize;

    public Feistelblock(byte[] left, byte[] right, byte[] sessionkey, int blockSize) {
        this.left = left;
        this.right = right;
        this.sessionkey = sessionkey;
        this.blockSize = blockSize;
    }

    public void swap() {
        // swap left and right
        byte[] temp = left;
        left = right;
        right = temp;

        BigInteger rightBI = new BigInteger(1, right);
        BigInteger sessionkeyBI = new BigInteger(1, sessionkey);

        // apply feistel algo to right side: F(R,K) = (R^2 + K) mod (2^blockSizeBits - 1)
        BigInteger newRight = rightBI.pow(2).add(sessionkeyBI).mod(BigInteger.TWO.pow(blockSize*8).subtract(BigInteger.ONE));
        right = Feistelblock.BigInt2Byte(newRight, blockSize);
        for(int i = 0; i < left.length; i++) {
            right[i] ^= left[i];
        }
    }

    public byte[] getRight() {
        return right;
    }

    public byte[] getLeft() {
        return left;
    }

    // code from Stephan Pareigis
    static byte[] BigInt2Byte (BigInteger src, int bytesize){
        byte[] out = new byte[bytesize];
        BigInteger mod = new BigInteger("2");
        mod = mod.pow(bytesize*8);
        src = src.mod(mod);
        int startdst = bytesize - src.toByteArray().length ;
        int cpylength = src.toByteArray().length;
        if((src.bitLength() % 8) != 0){
            System.arraycopy(src.toByteArray(),0,out,startdst,cpylength);
        }
        else {
            System.arraycopy(src.toByteArray(),1,out,startdst+1,cpylength-1);
        }
        return out;
    }
}
