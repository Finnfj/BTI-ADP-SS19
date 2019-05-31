package hybridEncryption;

import java.math.BigInteger;

public class RSAKeys {
    private BigInteger publicKey;
    private BigInteger privateKey;

    public RSAKeys(BigInteger publicKey, BigInteger privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }
}
