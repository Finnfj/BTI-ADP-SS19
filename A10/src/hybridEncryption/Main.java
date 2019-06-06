package hybridEncryption;

import java.util.Scanner;

public class Main {
    private static final int BLOCKSIZE = 16;
    private static final int ROUNDS = 12;
    private static final byte PADDING = 0x20;
    private static boolean quitted = false;

    public static void main(String... args) {
        RSA rsa = new RSA(BLOCKSIZE);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Our PublicKeyModuloBase64: " + rsa.getPublicKeyModulusBase64());
        System.out.println("Enter partner PublicKeyModuloBase64:");
        String partnerPublicKeyModuloBase64 = scanner.nextLine();
        HybridProcedure hp = new HybridProcedure(partnerPublicKeyModuloBase64, BLOCKSIZE, ROUNDS, PADDING);

        while(!quitted) {
            System.out.println("Press 'd' to decrypt a message, 'e' to encrypt a message or 'q' to quit: ");
            String selection = scanner.nextLine();
            switch (selection.toLowerCase()) {
                case "d":
                    System.out.println("Enter the message to decrypt:");
                    String partnerEncryptedMessageBase64 = scanner.nextLine();
                    String decryptedMessage = hp.decryptMessage(partnerEncryptedMessageBase64, rsa.getPrivateKey(), rsa.getModulus());
                    System.out.println("Decrypted message: " + decryptedMessage);
                    break;
                case "e":
                    System.out.println("Enter the message to encrypt: ");
                    String message = scanner.nextLine();
                    String encryptedMessage = hp.encryptMessage(message);
                    System.out.println("Encrypted message: " + encryptedMessage);
                    break;
                case "q":
                    quitted = true;
                    break;
            }
        }
    }
}
