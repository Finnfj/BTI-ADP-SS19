import java.math.BigInteger;
import java.util.Base64;

public class Main {
    final static int BLOCKSIZE = 16;
    public static void main(String... args) {
        BigInteger bt = new BigInteger("9910670438144899298");

        String encryptedMessageBase64 = "GLUxg4ARpICS4+uQFRx61I1tJZK7yIkdj1Ry25Sq/5Ws96O2CLV9qTpSujJ4U/qdoYmepBqTJ1ugoD8Aw+vrVW8byghvBDzSGaOMUO5U9FrbQAEjskcMNtW1bczvxfNWjHdlEZRVPge8KDd7eVr0hpycSb/+oVbQNTj/AP+qH6bfzRmFrtnwi1E2dyesTHOOGfONmqB2k0W7lq3p7WPmOeAKoxqi+1BQKUzvhxW5GmvlQN8aN/1K9kPoNrvC7Yph2Efw6riQZjYLFSf0qGg3oFsh14s34zaT5k+NA4XJJRupM7lJ+B/4OjKEgMlf2peg";
        BigInteger privateKey = new BigInteger("29498050312726727283617312290869885119");
        BigInteger modulus = new BigInteger("147171051373969827477617423045231021441");
        byte[] encryptedMessage = Base64.getDecoder().decode(encryptedMessageBase64);

        byte[] encryptedSessionkey = new byte[BLOCKSIZE];
        System.arraycopy(encryptedMessage, 0, encryptedSessionkey, 0, BLOCKSIZE);
        BigInteger esbi = new BigInteger(1, encryptedSessionkey);

        BigInteger sessionkey = esbi.modPow(privateKey,modulus);

        byte[] btb = bt.toByteArray();
        byte[] btbt2b = BigInt2Byte(bt, 8);
        System.out.println();
    }

    public static byte[] BigInt2Byte (BigInteger src, int bytesize) {
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
