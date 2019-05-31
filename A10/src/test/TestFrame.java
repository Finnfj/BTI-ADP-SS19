package test;

import hybridEncryption.RSAKeys;
import hybridEncryption.UnsymmetricProcedure;

import java.math.BigInteger;

public class TestFrame {
    public static void main(String... args) {
        RSAKeys rsaKeys = UnsymmetricProcedure.generateKeys(64);

        char[] message = "Hallo".toCharArray();
        BigInteger[] encryptedMessage = rsaKeys.encryptMessage(message);
        char[] encrpytedMessage = rsaKeys.decryptMessage(encryptedMessage);

        System.out.println();

        for(int i = 0; i < 10000; i++) {
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
