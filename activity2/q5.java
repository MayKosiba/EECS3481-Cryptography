package activity2;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class q5 {
    public static void main(String[] args) throws Exception {
        byte[] keyBytes = "FACEBOOK".getBytes();
        byte[] ct = CryptoTools.hexToBytes("8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284");
        byte[] keyNegBytes = new byte[keyBytes.length];

        for(int i = 0; i < keyBytes.length; i++){
            keyNegBytes[i] = (byte) ~keyBytes[i];
        }

        SecretKey key =  new SecretKeySpec(keyBytes, "DES");
        SecretKey keyNeg = new SecretKeySpec(keyNegBytes, "DES");

        String algorithm = "DES/ECB/NoPadding";
        String plainText = decrypt(algorithm, ct, key, keyNeg);

        System.out.print(plainText);
    }


    public static String decrypt(String algorithm, byte[] text, SecretKey key, SecretKey keyNeg) throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException,BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, keyNeg);

        byte[] newText = cipher.doFinal(text);

        Cipher cipher2 = Cipher.getInstance(algorithm);
        cipher2.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = cipher2.doFinal(newText);
        return new String(plainText);
    }
}
