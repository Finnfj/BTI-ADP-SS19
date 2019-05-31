package test;

import hybridEncryption.RSAKeys;
import hybridEncryption.UnsymmetricProcedure;

public class TestFrame {
    public static void main(String... args) {
        RSAKeys rsaKeys = UnsymmetricProcedure.generateKeys(64);
    }
}
