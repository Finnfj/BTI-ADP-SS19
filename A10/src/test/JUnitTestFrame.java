package test;

import hybridEncryption.HybridProcedure;
import hybridEncryption.RSA;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrame {
    private final static int BLOCKSIZE = 16;
    private final static int ROUNDS = 12;
    private final static byte PADDING = 0x20;

    @Test
    public void testHybridProcedureEncryption() {
        for(int i = 0; i < 10000; i++) {
            RSA rsa1 = new RSA(BLOCKSIZE);
            RSA rsa2 = new RSA(BLOCKSIZE);
            HybridProcedure hp1 = new HybridProcedure(rsa2.getPublicKeyModulusBase64(), BLOCKSIZE, ROUNDS, PADDING);
            HybridProcedure hp2 = new HybridProcedure(rsa1.getPublicKeyModulusBase64(), BLOCKSIZE, ROUNDS, PADDING);

            String message = String.valueOf(i);
            String encryptedMessage1 = hp1.encryptMessage(message);
            String decryptedMessage1 = hp2.decryptMessage(encryptedMessage1, rsa2.getPrivateKey(), rsa2.getModulus());
            String encryptedMessage2 = hp2.encryptMessage(message);
            String decryptedMessage2 = hp1.decryptMessage(encryptedMessage2, rsa1.getPrivateKey(), rsa1.getModulus());
            assertEquals(message, decryptedMessage1);
            assertEquals(message, decryptedMessage2);
        }
    }
}
