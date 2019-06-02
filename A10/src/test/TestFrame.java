package test;

import hybridEncryption.RSAKeys;
import hybridEncryption.SymmetricProcedure;
import hybridEncryption.UnsymmetricProcedure;

import java.math.BigInteger;

public class TestFrame {
    public static void main(String... args) {
        testRSAMessageEncryption();
        //testRSAKeysNegative();
        //testFeistelEncryption();
    }

    public static void testRSAMessageEncryption() {
        RSAKeys rsaKeys = UnsymmetricProcedure.generateKeys(16);

        BigInteger[] encryptedMessage = rsaKeys.encryptMessage("Hello");
        String decrpytedMessage = rsaKeys.decryptMessage(encryptedMessage);
        System.out.println(decrpytedMessage);
    }

    public static void testRSAKeysNegative() {
        boolean pubkeyNegative = false;
        boolean privKeyNegative = false;
        for(int i = 0; i < 1000; i++) {
            RSAKeys keys = UnsymmetricProcedure.generateKeys(16);
            if(keys.getPublicKey().compareTo(BigInteger.ZERO) < 0) {
                pubkeyNegative = true;
            }
            if(keys.getPrivateKey().compareTo(BigInteger.ZERO) < 0) {
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
        SymmetricProcedure sm = new SymmetricProcedure();
        byte[] result = sm.encryptMessage("Hallo");
        System.out.println(new String(result));
    }
}
