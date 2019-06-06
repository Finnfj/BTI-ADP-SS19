package test;

import hybridEncryption.BigIntHelper;
import hybridEncryption.BlockCipherFeistel;
import hybridEncryption.HybridProcedure;
import hybridEncryption.RSA;

import java.math.BigInteger;

public class TestFrame {
    private static final int BLOCKSIZE = 16;
    private static final int ROUNDS = 12;
    private static final byte PADDING = 0x20;

    public static void main(String... args) {
        testRSAMessageEncryptionDecryption();
        testRSAKeysNegative();
        testFeistelround();
        testFeistelEncryptionDecryption();
        testHybridMessageEncryptionDecryption();
    }

    private static void testRSAMessageEncryptionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);

        BigInteger encryptedMessage = RSA.encryptMessage(BigIntHelper.Byte2BigInt("HalloA".getBytes()), rsa.getPublicKey(), rsa.getModulus());
        BigInteger decrpytedMessage = RSA.decryptMessage(encryptedMessage, rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(new String(encryptedMessage.toByteArray()));
        System.out.println(new String(decrpytedMessage.toByteArray()));
    }

    private static void testRSAKeysNegative() {
        boolean pubkeyNegative = false;
        boolean privKeyNegative = false;
        for(int i = 0; i < 100000; i++) {
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

    private static void testFeistelround() {
        byte[] sessionkey = {1, 1, 1, 1, 1, 1, 1, 1};
        BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, sessionkey);
        byte[] left  = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'};
        byte[] right  = {'b', 'b', 'b','b', 'b', 'b', 'b', 'b'};
        for(int i = 0; i < 2; i++) {
            bcf.feistelround(left, right); // encrypt
        }
        for(int i = 0; i < 2; i++) {
            BlockCipherFeistel.swap(left, right);
            bcf.feistelround(left, right); // decrypt
            BlockCipherFeistel.swap(left, right);
        }
        System.out.println(new String(left));
        System.out.println(new String(right));
    }

    private static void testFeistelEncryptionDecryption() {
            BlockCipherFeistel bcf = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, null);
            byte[] encryptedMessage = bcf.encryptMessage("AAAAAAAAAAAAAAAA".getBytes());
            System.out.println(new String(encryptedMessage));
            byte[] decryptedMessage = bcf.decryptMessage(encryptedMessage);
            System.out.println(new String(decryptedMessage));
    }

    private static void testHybridMessageEncryptionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);
        HybridProcedure hp = new HybridProcedure(rsa.getPublicKeyModulusBase64(), BLOCKSIZE, ROUNDS, PADDING);
        String encryptedMessage = hp.encryptMessage("HalloC");
        System.out.println(encryptedMessage);
        String decryptedMessage = hp.decryptMessage(encryptedMessage, rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(decryptedMessage);
    }
}
