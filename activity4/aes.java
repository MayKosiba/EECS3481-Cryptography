

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
//import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aes {
    public static void main(String[] args) throws Exception {
        //Parameters
        byte[] keyBytes = CryptoTools.hexToBytes("34567abcdef0321134567abcdef03211".toUpperCase());
        byte[] iv = CryptoTools.hexToBytes("44668abddef1321134568abcdef13221".toUpperCase());
        byte[] ct = "Why do we tell actors to break a leg? because every play has a cast".getBytes();
        
        //AES encyption
        SecretKey key =  new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        String algorithm = "AES/CBC/PKCS5Padding";
        byte[] cipherText = encryptOrDecrypt(algorithm, ct, key, ivParameterSpec, Cipher.ENCRYPT_MODE);

        //Compute MAC
        SecretKey macKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        Mac hmac = Mac.getInstance("HmacSHA1");
        hmac.init(macKey);
        hmac.update(iv);
        hmac.update(cipherText);
        byte[] mac = hmac.doFinal();

        //Computer blocks
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + iv.length + 1 + mac.length + cipherText.length);
        byteBuffer.put((byte) iv.length);
        byteBuffer.put(iv);
        byteBuffer.put((byte) mac.length);
        byteBuffer.put(mac);
        byteBuffer.put(cipherText);

        //print output
        byte[] cipherMessage = byteBuffer.array();
        System.out.println(Base64.getEncoder().encodeToString(cipherMessage));
    }


    public static byte[] encryptOrDecrypt(String algorithm, byte[] text, SecretKey key,
    IvParameterSpec iv, int mode) throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException,BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, key, iv);
        if(mode == Cipher.DECRYPT_MODE){
            return cipher.doFinal(text);
        } else {
            return cipher.doFinal(text);
        }    
    }
}
