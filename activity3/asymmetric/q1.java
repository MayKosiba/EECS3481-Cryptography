package asymmetric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class q1 {
    public static void main(String[] args) throws Exception {
        BigInteger n = new BigInteger("40057384521392344387295509139");
        BigInteger e = new BigInteger("101");
        BigInteger d = new BigInteger("21416819447080840026842414141");
        BigInteger ct = new BigInteger("159911625443136560226876180");
        byte[] pt = ct.modPow(d,n).toByteArray();
        System.out.print(new String(pt, StandardCharsets.UTF_8));
    }
}