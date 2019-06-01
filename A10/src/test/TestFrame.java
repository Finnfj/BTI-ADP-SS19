package test;

import hybridEncryption.RSAKeys;
import hybridEncryption.UnsymmetricProcedure;

import java.math.BigInteger;

public class TestFrame {
    public static void main(String... args) {
        testRSAMessageEncryption();
        testRSAKeysNegative();
    }

    public static void testRSAMessageEncryption() {
        RSAKeys rsaKeys = UnsymmetricProcedure.generateKeys(64);

        BigInteger[] encryptedMessage = rsaKeys.encryptMessage("Hello");
        String decrpytedMessage = rsaKeys.decryptMessage(encryptedMessage);
    }

    public static void testRSAKeysNegative() {
        for(int i = 0; i < 1000; i++) {
            RSAKeys keys = UnsymmetricProcedure.generateKeys(64);
            if(keys.getPublicKey().compareTo(BigInteger.ZERO) < 0) {
                System.out.println("pubKey is negative");
            }
            if(keys.getPrivateKey().compareTo(BigInteger.ZERO) < 0) {
                System.out.println("pubKey is negative");
            }
        }
    }
}
