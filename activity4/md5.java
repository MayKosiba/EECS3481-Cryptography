
import java.math.BigInteger;
import java.security.MessageDigest;
  
// Java program to calculate MD5 hash value
public class md5 {
    public static void main(String args[]) throws Exception{
  
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest("123.55".getBytes());
  
            BigInteger no = new BigInteger(1, messageDigest);
  
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println(hashtext.equalsIgnoreCase("2A79EA27C279E471F4D180B08D62B00A"));
  

    }
}