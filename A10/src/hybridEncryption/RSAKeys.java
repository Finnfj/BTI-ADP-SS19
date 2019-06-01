package hybridEncryption;

import java.math.BigInteger;

public class RSAKeys {
    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger N;

    public RSAKeys(BigInteger publicKey, BigInteger privateKey, BigInteger N) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.N = N;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger[] encryptMessage(String message) {
        byte[] messageBytes = message.getBytes();
        BigInteger[] encryptedMessage = new BigInteger[messageBytes.length];

        for (int i = 0; i < messageBytes.length; i++) {
            encryptedMessage[i] = BigInteger.valueOf(messageBytes[i]).modPow(publicKey, N);
        }
        return encryptedMessage;
    }

    public String decryptMessage(BigInteger[] message) {
        byte[] decryptedMessageBytes = new byte[message.length];

        for (int i = 0; i < message.length; i++) {
            decryptedMessageBytes[i] = (byte) message[i].modPow(privateKey, N).intValue();
        }
        return new String(decryptedMessageBytes);
    }
}
