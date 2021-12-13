package asymmetric;

import java.math.BigDecimal;
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

public class q4 {
    public static void main(String[] args) throws Exception {
        BigInteger n = new BigInteger("1033931178476059651954862004553");
        BigInteger base = new BigInteger("2");
        BigInteger m = null;
        BigInteger k = null;

        boolean flag = true;
        int i = 1;
        while(flag){
            BigDecimal n_1 = new BigDecimal(n.subtract(BigInteger.ONE));
            BigDecimal pow = new BigDecimal("2");
            pow = pow.pow(i);
            BigDecimal m_tmp = n_1.divide(pow);

            if(m_tmp.stripTrailingZeros().scale() <= 0){
                k = new BigInteger(String.valueOf(i));
                m = m_tmp.toBigInteger();
            } else {
                flag = false;
            }
            i++;
        }


        BigInteger b = base.modPow(m, n);
        BigInteger one = new BigInteger("1");
        BigInteger neg_one = new BigInteger("-1");
        if(b.compareTo(one) == 0 || b.compareTo(neg_one) == 0){
            System.out.println("Inconclusive");
        } else {
            while(!(b.compareTo(one) == 0 || b.compareTo(neg_one) == 0)){
                b = b.modPow(new BigInteger("2"), n);
                if(b.subtract(n).compareTo(neg_one) == 0){
                    b = neg_one;
                }
            }
            if(b.equals(one)){
                System.out.println("Composite");
            } else{
                System.out.println("Inconclusive");
            }
        }
    }
}