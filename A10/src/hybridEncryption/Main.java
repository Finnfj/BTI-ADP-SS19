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

        try {
            FileWriter out = new FileWriter("ourPublicKeyModuloBase64.txt");
            out.write(rsa.getPublicKeyModulusBase64());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Wrote ourPublicKeyModuloBase64. Copy the PublicKeyModulo of your partner into the partnerPublicKeyModuloBase64.txt and press Enter to continue.");
        scanner.nextLine();

        byte[] partnerPublicKeyModuloBase64 = new byte[0];
        try {
            partnerPublicKeyModuloBase64 = Files.readAllBytes(Paths.get("partnerPublicKeyModuloBase64.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HybridProcedure hp = new HybridProcedure(new String(partnerPublicKeyModuloBase64), BLOCKSIZE, ROUNDS, PADDING);

        while(true) {
            System.out.println("Press 'd' if you want to decrypt a message or 'e' if you want to encrypt a message: ");
            String selection = scanner.nextLine();
            if (selection.toLowerCase().equals("d")) {
                byte[] partnerEncryptedMessageBase64 = new byte[0];
                try {
                    partnerEncryptedMessageBase64 = Files.readAllBytes(Paths.get("partnerEncryptedMessageBase64.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String decryptedMessage = hp.decryptMessage(new String(partnerEncryptedMessageBase64), rsa.getPrivateKey(), rsa.getModulus());
                System.out.println(decryptedMessage);
            } else if (selection.toLowerCase().equals("e")) {
                System.out.println("Enter message you want to encrypt: ");
                String message = scanner.nextLine();
                String encryptedMessage = hp.encryptMessage(message);
                try {
                    FileWriter out = new FileWriter("ourEncryptedMessageBase64.txt");
                    out.write(encryptedMessage);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
