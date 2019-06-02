package hybridEncryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class UnsymmetricProcedure {
    public static RSAKeys generateKeys(int blocksize) {
        // 1
        SecureRandom sr = new SecureRandom();
        BigInteger p1 = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime
        BigInteger p2 = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime

        // 2
        BigInteger modulus = p1.multiply(p2);
        BigInteger phi = p1.subtract(BigInteger.ONE).multiply(p2.subtract(BigInteger.ONE));

        // 3
        BigInteger e = BigInteger.probablePrime(blocksize/2*8, sr); // generates a prime
        // add one to e until the greatest common divisor with phi is 1
        while(!e.gcd(phi).equals(BigInteger.ONE)) {
            e.add(BigInteger.ONE);
        }

        // 4
        Triple extEuklidRes = multiplicativeInverseExtendedEuclidean(e, phi);
        BigInteger d = extEuklidRes.d;
        System.out.println(e.multiply(d).mod(phi));

        return new RSAKeys(e, d, modulus);
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
