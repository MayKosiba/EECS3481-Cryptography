import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

import util.CryptoTools;

public class test {
   public static void main(String args[]) throws Exception{
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = CryptoTools.hexToBytes("34567abcdef0321134567abcdef03211");
        byte[] iv = CryptoTools.hexToBytes("44668abddef1321134568abcdef13221");
        secureRandom.nextBytes(key);    
        secureRandom.nextBytes(iv);
        byte[] encKey = HKDF.fromHmacSha256().expand(key, "encKey".getBytes(StandardCharsets.UTF_8), 16);
        byte[] authKey = HKDF.fromHmacSha256().expand(key, "authKey".getBytes(StandardCharsets.UTF_8), 32);

            
   }
}