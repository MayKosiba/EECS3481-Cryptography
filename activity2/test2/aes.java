package activity2;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aes {
    public static void main(String[] args) throws Exception {

        byte[] keyBytes = CryptoTools.hexToBytes("444F204E4F542054454C4C2045564521");
        byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");
        
        SecretKey key =  new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = encryptOrDecrypt(algorithm, ct, key, ivParameterSpec, Cipher.DECRYPT_MODE);
        System.out.print(plainText);
    }


    public static String encryptOrDecrypt(String algorithm, byte[] text, SecretKey key,
    IvParameterSpec iv, int mode) throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException,BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, key, iv);
        if(mode == Cipher.DECRYPT_MODE){
            byte[] plainText = cipher.doFinal(text);
            return new String(plainText);
        } else {
            byte[] cipherText = cipher.doFinal(text);
            return Base64.getEncoder().encodeToString(cipherText);
        }    
    }
}
