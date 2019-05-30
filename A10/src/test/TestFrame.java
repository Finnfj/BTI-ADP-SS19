package test;

import hybridEncryption.UnsymmetricProcedure;

public class TestFrame {
    public static void main(String... args) {
        UnsymmetricProcedure up = new UnsymmetricProcedure(64);
        up.generateKeys();
    }
}
