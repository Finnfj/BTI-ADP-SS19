package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class RSA {
    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger modulus;
    private final int BLOCKSIZE;

    public RSA(final int BLOCKSIZE) {
        this.BLOCKSIZE = BLOCKSIZE;
        generateKeys(BLOCKSIZE);
    }

    private void generateKeys(int blocksize) {
        // 1
        SecureRandom sr = new SecureRandom();
        BigInteger p1 = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime
        BigInteger p2 = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime

        // 2
        BigInteger calculatedModulus = p1.multiply(p2);
        BigInteger phi = p1.subtract(BigInteger.ONE).multiply(p2.subtract(BigInteger.ONE));

        // 3
        BigInteger e = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime
        // add one to e until the greatest common divisor with phi is 1
        while(!e.gcd(phi).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        // 4
        Triple extEuklidRes = multiplicativeInverseExtendedEuclidean(e, phi);
        BigInteger d = extEuklidRes.d;

        publicKey = e;
        privateKey = d;
        modulus = calculatedModulus;
    }

    public String getPublicKeyModulusBase64() {
        byte[] publicKeyModulusBase64 = new byte[BLOCKSIZE+BLOCKSIZE/2];

        System.arraycopy(BigIntHelper.BigInt2Byte(publicKey, BLOCKSIZE/2), 0, publicKeyModulusBase64, 0, BLOCKSIZE/2);
        System.arraycopy(BigIntHelper.BigInt2Byte(modulus, BLOCKSIZE), 0, publicKeyModulusBase64, BLOCKSIZE/2, BLOCKSIZE);

        return Base64.getEncoder().encodeToString(publicKeyModulusBase64);
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public static BigInteger encryptMessage(byte[] message, BigInteger publicKey, BigInteger modulus) {
        BigInteger encryptedMessage = BigIntHelper.Byte2BigInt(message);
        encryptedMessage = encryptedMessage.modPow(publicKey, modulus);
        return encryptedMessage;
    }

    public static byte[] decryptMessage(BigInteger message, BigInteger privateKey, BigInteger modulus) {
        return message.modPow(privateKey, modulus).toByteArray();
    }

    // calculates the multiplicative inverse of a to b with the extended euclidean algorithm
    private static Triple multiplicativeInverseExtendedEuclidean(BigInteger a, BigInteger b) {
        BigInteger b0 = b;
        BigInteger y = BigInteger.ZERO;
        BigInteger d = BigInteger.ONE;

        while(a.compareTo(BigInteger.ONE) > 0) { // while a greater 1
            BigInteger q = a.divide(b);
            BigInteger t = b;
            b = a.mod(b);
            a = t;
            t = y;

            y = d.subtract(q.multiply(y));
            d = t;
        }

        if (d.compareTo(BigInteger.ZERO) < 0) { // if d is smaller than 0
            d = d.add(b0);
        }

        return new Triple(d, BigInteger.ONE, BigInteger.ZERO);
    }

    // code from Stephan Pareigis
    // TODO: doesn't work
    public static Triple extEuklid(final BigInteger a, final BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new Triple(a, BigInteger.ONE, BigInteger.ZERO);
        } else {
            final Triple extension = extEuklid(b, a.mod(b));
            return new Triple(extension.d, extension.t, extension.s.subtract(a.divide(b).multiply(extension.t)));
        }
    }

    public BigInteger getModulus() {
        return modulus;
    }

    // code from Stephan Pareigis
    private static class Triple {
        public final BigInteger d;
        public final BigInteger s;
        public final BigInteger t;
        private Triple(final BigInteger d, final BigInteger s, final BigInteger t) {
            this.d = d; this.s = s; this.t = t;
        }
    }
}
