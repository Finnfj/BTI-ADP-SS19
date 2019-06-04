package hybridEncryption;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static final int BLOCKSIZE = 16;
    private static final int ROUNDS = 12;
    private static final byte PADDING = 0x20;

    public static void main(String... args) {
        RSA rsa = new RSA(BLOCKSIZE);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Our Publickey = " + rsa.getPublicKeyModulusBase64());
        System.out.println("Enter Partner Public Key:");
        String partnerPublicKeyModuloBase64 = scanner.nextLine();
        HybridProcedure hp = new HybridProcedure(partnerPublicKeyModuloBase64, BLOCKSIZE, ROUNDS, PADDING);

        while(true) {
            System.out.println("Press 'd' if you want to decrypt a message or 'e' if you want to encrypt a message: ");
            String selection = scanner.nextLine();
            if (selection.toLowerCase().equals("d")) {
                System.out.println("Enter the Message to decrypt");
                String partnerEncryptedMessageBase64= scanner.nextLine();
                String decryptedMessage = hp.decryptMessage(partnerEncryptedMessageBase64, rsa.getPrivateKey(), rsa.getModulus());
                System.out.println("Decrypted Message: " + decryptedMessage);
            } else if (selection.toLowerCase().equals("e")) {
                System.out.println("Enter message you want to encrypt: ");
                String message = scanner.nextLine();
                String encryptedMessage = hp.encryptMessage(message);
                System.out.println("Encrypted message = " + encryptedMessage);
            }
        }
    }
}
