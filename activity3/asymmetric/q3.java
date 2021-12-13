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

public class q3 {
    public static void main(String[] args) throws Exception {
        BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
        BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
        BigInteger e = new BigInteger("65537");
        BigInteger ct = new BigInteger("2860343511650624855536801423229241360270155261818891412133738950638333577677444735030337910529513416732869176248688449871162754270700440751893288037477549");
        
        BigInteger q_1 = q.subtract(BigInteger.ONE);
        BigInteger p_1 = phi.divide(q_1);
        BigInteger p = p_1.add(BigInteger.ONE);
        BigInteger n = p.multiply(q);

        BigInteger d = e.modInverse(p_1.multiply(q_1));
        
        byte[] pt = ct.modPow(d,n).toByteArray();
        System.out.print(new String(pt, StandardCharsets.UTF_8));
    }
}