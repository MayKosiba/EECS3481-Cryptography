package activity4;

import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class q3 {
    public static void main(String args[]) throws Exception{
        String m = "Mainly cloudy with 40 percent chance of showers" ;
        String K = "This is my secret key";

        SecretKeySpec signingKey = new SecretKeySpec(K.getBytes(), "HmacSHA512");
		Mac mac = Mac.getInstance("HmacSHA512");
		mac.init(signingKey);
		byte[] hmac = mac.doFinal(m.getBytes());

        
        Formatter formatter = new Formatter();
		for (byte b : hmac) {
			formatter.format("%02x", b);
		}

        System.out.println(formatter.toString().toUpperCase());
    }
}
