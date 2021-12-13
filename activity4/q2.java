import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class q2 {
    public static void main(String args[]) throws Exception{
        BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
        BigInteger eA = new BigInteger("1571");

        BigInteger pB = new BigInteger("98763457697834568934613");
        BigInteger qB = new BigInteger("8495789457893457345793");
        BigInteger eB = new BigInteger("87697");
        
        //Calculating Bobs missing parameters
        BigInteger nB = pB.multiply(qB);
        BigInteger p_1 = pB.subtract(BigInteger.ONE);
        BigInteger q_1 = qB.subtract(BigInteger.ONE);
        BigInteger dB = eB.modInverse(p_1.multiply(q_1));

        BigInteger m_ = new BigInteger("418726553997094258577980055061305150940547956");
        BigInteger s_ = new BigInteger("749142649641548101520133634736865752883277237");

        //decrypt
        BigInteger m = m_.modPow(dB,nB);
        
        //verify
        BigInteger s = s_.modPow(dB,nB);
        s = s.modPow(eA,nA);
        System.out.println(s.equals(m));
    }
}
