package test;

import hybridEncryption.BlockCipherFeistel;
import hybridEncryption.HybridProcedure;
import hybridEncryption.RSA;

import java.math.BigInteger;

public class TestFrame {
    private static final int BLOCKSIZE = 16;
    private static final int ROUNDS = 12;
    private static final byte PADDING = 0x20;

    public static void main(String... args) {
        //testRSAMessageEncryptionDecryption();
        //testRSAKeysNegative();
        //testFeistel();
        testFeistelEncryptionDecryption();
        //testHybridMessageEncryptionDecryption();
    }

    public static void testRSAMessageEncryptionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);

        BigInteger encryptedMessage = RSA.encryptMessage("HalloA".getBytes(), rsa.getPublicKey(), rsa.getModulus());
        byte[] decrpytedMessage = RSA.decryptMessage(encryptedMessage.toByteArray(), rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(new String(encryptedMessage.toByteArray()));
        System.out.println(new String(decrpytedMessage));
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

    public static void testFeistel() {
        byte[] sessionkey = {1, 1, 1, 1, 1, 1, 1, 1};
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, sessionkey);
        byte[] left  = {1, 1, 1, 1, 1, 1, 1, 1};
        byte[] right  = {2, 2, 2, 2, 2, 2, 2, 2};
        for(int i = 0; i < 2; i++) {
            bcf.feistel(left, right); // encrypt
        }
        for(int i = 0; i < 2; i++) {
            BlockCipherFeistel.swap(left, right);
            bcf.feistel(left, right); // decrypt
            BlockCipherFeistel.swap(left, right);
        }
        System.out.println(new String(left));
        System.out.println(new String(right));
    }

    public static void testFeistelEncryptionDecryption() {
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, null);
        byte[] encryptedMessage = bcf.encryptMessage("HalloBHalloBHallHalloBHalloBHall".getBytes());
        System.out.println(new String(encryptedMessage));
        byte[] decryptedMessage = bcf.decryptMessage(encryptedMessage);
        System.out.println(new String(decryptedMessage));
    }

    public static void testHybridMessageEncryptionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);
        HybridProcedure hp = new HybridProcedure(rsa.getPublicKeyModulusBase64(), BLOCKSIZE, ROUNDS, PADDING);
        String encryptedMessage = hp.encryptMessage("HalloC");
        System.out.println(encryptedMessage);
        String decryptedMessage = hp.decryptMessage(encryptedMessage, rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(decryptedMessage);
    }
}
