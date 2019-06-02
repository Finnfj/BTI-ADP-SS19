package test;

import hybridEncryption.BlockCipherFistel;
import hybridEncryption.RSA;

import java.math.BigInteger;

public class TestFrame {
    private static final int BLOCKSIZE = 16;
    private static final int ROUNDS = 12;

    public static void main(String... args) {
        testRSAMessageEncryption();
        //testRSAKeysNegative();
        //testFeistelEncryption();
    }

    public static void testRSAMessageEncryption() {
        RSA rsa = new RSA(BLOCKSIZE);

        BigInteger[] encryptedMessage = rsa.encryptMessage("Hello", rsa.getPublicKey(), rsa.getModulus());
        String decrpytedMessage = rsa.decryptMessage(encryptedMessage, rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(decrpytedMessage);
    }

    public static void testRSAKeysNegative() {
        boolean pubkeyNegative = false;
        boolean privKeyNegative = false;
        for(int i = 0; i < 1000; i++) {
            RSA rsa = new RSA(BLOCKSIZE);
            if(rsa.getPublicKey().compareTo(BigInteger.ZERO) < 0) {
                pubkeyNegative = true;
            }
            if(rsa.getPrivateKey().compareTo(BigInteger.ZERO) < 0) {
                privKeyNegative = true;
            }
        }
        if (pubkeyNegative) {
            System.out.println("pubkey was atleast once negative");
        }
        if (privKeyNegative) {
            System.out.println("privatekey was atleast once negative");
        }
        if (!pubkeyNegative && !privKeyNegative) {
            System.out.println("Both Keys are fine");
        }
    }

    public static void testFeistelEncryption() {
        BlockCipherFistel sm = new BlockCipherFistel(BLOCKSIZE, ROUNDS);
        byte[] result = sm.encryptMessage("Hallo");
        System.out.println(new String(result));
    }
}
