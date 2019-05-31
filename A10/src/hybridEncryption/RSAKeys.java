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

    public BigInteger[] encryptMessage(char[] message) {
        BigInteger[] encryptedMessage = new BigInteger[message.length];

        for (int i = 0; i < message.length; i++) {
            encryptedMessage[i] = BigInteger.valueOf(message[i]).modPow(publicKey, N);
        }
        return encryptedMessage;
    }

    public char[] decryptMessage(BigInteger[] message) {
        char[] decryptedMessage = new char[message.length];

        for (int i = 0; i < message.length; i++) {
            decryptedMessage[i] = (char) message[i].modPow(privateKey, N).intValue();
        }
        return decryptedMessage;
    }
}
