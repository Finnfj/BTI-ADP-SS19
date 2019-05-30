package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class UnsymmetricProcedure {
    private int numBits;
    private BigInteger publicKey;
    private BigInteger privateKey;

    public UnsymmetricProcedure(int numBits) {
        this.numBits = numBits;
        publicKey = null;
        privateKey = null;
    }

    public void generateKeys() {
        // 1
        SecureRandom sr = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(numBits, sr); // generates a prime
        BigInteger q = BigInteger.probablePrime(numBits, sr); // generates a prime

        // 2
        BigInteger N = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // 3
        BigInteger e = BigInteger.probablePrime(numBits, sr); // generates a prime
        while(!e.gcd(phi).equals(BigInteger.ONE)) {
            e.add(BigInteger.ONE);
        }

        // 4
        // TODO: implement extended euclidean algorithm

        // TODO: set publicKey and privateKey
        System.out.println("done");
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }
}
