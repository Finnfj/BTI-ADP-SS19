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
        //testFeistelEncryption();
        testHybridMessageEncprytionDecryption();
    }

    public static void testRSAMessageEncryptionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);

        BigInteger encryptedMessage = RSA.encryptMessage("Hallo".getBytes(), rsa.getPublicKey(), rsa.getModulus());
        byte[] decrpytedMessage = RSA.decryptMessage(encryptedMessage.toByteArray(), rsa.getPrivateKey(), rsa.getModulus());
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

    public static void testFeistelEncryption() {
        BlockCipherFeistel sm = new BlockCipherFeistel(BLOCKSIZE, ROUNDS, PADDING, null);
        byte[] result = sm.encryptMessage("Hallo".getBytes());
        System.out.println(new String(result));
    }

    public static void testHybridMessageEncprytionDecryption() {
        RSA rsa = new RSA(BLOCKSIZE);
        HybridProcedure hp = new HybridProcedure(rsa.getPublicKeyModulusBase64(), BLOCKSIZE, ROUNDS, PADDING);
        String encryptedMessage = hp.encryptMessage("Hallo");
        String decryptedMessage = hp.decryptMessage(encryptedMessage, rsa.getPrivateKey(), rsa.getModulus());
        System.out.println(decryptedMessage);
    }
}
