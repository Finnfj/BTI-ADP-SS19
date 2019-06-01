package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class UnsymmetricProcedure {
    public static RSAKeys generateKeys(int numBits) {
        // 1
        SecureRandom sr = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(numBits, sr); // generates a prime
        BigInteger q = BigInteger.probablePrime(numBits, sr); // generates a prime

        // 2
        BigInteger N = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // 3
        BigInteger e = BigInteger.probablePrime(numBits, sr); // generates a prime
        // add one to e until the greatest common divisor with phi is 1
        while(!e.gcd(phi).equals(BigInteger.ONE)) {
            e.add(BigInteger.ONE);
        }

        // 4
        BigInteger d = multiplicativeInverseExtendedEuclidean(e, phi);

        return new RSAKeys(e, d, N);
    }

    // calculates the multiplicative inverse of a to b with the extended euclidean algorithm
    private static BigInteger multiplicativeInverseExtendedEuclidean(BigInteger a, BigInteger b) {
        BigInteger b0 = b;
        BigInteger y = BigInteger.ZERO;
        BigInteger x = BigInteger.ONE;

        while(a.compareTo(BigInteger.ONE) > 0) { // while a greater 1
            BigInteger q = a.divide(b);
            BigInteger t = b;
            b = a.mod(b);
            a = t;
            t = y;

            y = x.subtract(q.multiply(y));
            x = t;
        }

        if (x.compareTo(BigInteger.ZERO) < 0) { // if x is smaller than 0
            x = x.add(b0);
        }

        return x;
    }
}
