package activity3.asymmetric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class q5 {
    public static void main(String[] args) throws Exception {
        BigInteger p = new BigInteger("341769842231234673709819975074677605139");
        BigInteger g = new BigInteger("37186859139075205179672162892481226795");
        BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
        BigInteger bX = new BigInteger("140479748264028247931575653178988397140");
        String r = "12345";

        BigInteger B = g.modPow(bX, p);

        BigInteger s_1 = B.modPow(aX, p);

        s_1 = s_1.add(new BigInteger(r));

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        BigInteger bi = new BigInteger(1,md.digest(s_1.toByteArray()));

        System.out.println(bi.toString(16).toUpperCase());
    }
}