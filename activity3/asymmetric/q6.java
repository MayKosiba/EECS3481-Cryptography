package asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class q6 {
    public static void main(String[] args) throws Exception {
        BigInteger aP_hex = new BigInteger("3059301306072A8648CE3D020106082A8648CE3D0301070342000450C35C2FB11926C2C91E089CFC743F9D942EE14B8D42E25AE6588C4F93DDFF6ACDF520F74AF3E2500EF2A5E2C346D4DA7E92C1F89AD9FD4F3ED1B97DC3F39DC8",16);
        BigInteger aS_hex = new BigInteger("3041020100301306072A8648CE3D020106082A8648CE3D0301070427302502010104200FE89D3070EECF985F971851B088EC97605A08D037F3CF3463FED25BCE0037B5",16);
        BigInteger bP_hex = new BigInteger("3059301306072A8648CE3D020106082A8648CE3D03010703420004678DF0E72D7FC86006174E506B1729081E5D1201936EBA8A39E8741E4F713F8C29AE2E62038D95B36A585E2A87FEA73BE482611115457A3D3823EA5D79E31154",16);
        BigInteger bS_hex = new BigInteger("3041020100301306072A8648CE3D020106082A8648CE3D030107042730250201010420090145EB296FD96158EDF5E59D20EBB8E7332BBE150784D91900DB2006980127",16);
        byte[] ct = hexStringToByteArray("B1803ED24B595CCB11AA39473DC7B10B");
        byte[] iv = hexStringToByteArray("4000000001000000000C00000001000C");

        KeyFactory kf = KeyFactory.getInstance("EC");

        PrivateKey aS_key = kf.generatePrivate(new PKCS8EncodedKeySpec(aS_hex.toByteArray()));
        PrivateKey bS_key = kf.generatePrivate(new PKCS8EncodedKeySpec(bS_hex.toByteArray()));
        PublicKey  aP_key = kf.generatePublic(new X509EncodedKeySpec(aP_hex.toByteArray()));
        PublicKey  bP_key = kf.generatePublic(new X509EncodedKeySpec(bP_hex.toByteArray()));

        KeyAgreement ka = KeyAgreement.getInstance("ECDH");
        ka.init(aS_key);
        ka.doPhase(bP_key, true);
        byte[] sharedSecret = ka.generateSecret();

        byte[] keyBytes = sharedSecret;
        
        SecretKey key =  new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = encryptOrDecrypt(algorithm, ct, key, ivParameterSpec, Cipher.DECRYPT_MODE);

        System.out.print(plainText);
    }
  
  public static String encryptOrDecrypt(String algorithm, byte[] text, SecretKey key, IvParameterSpec iv, int mode) 
  throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException,BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, key, iv);
        if(mode == Cipher.DECRYPT_MODE){
            byte[] plainText = cipher.doFinal(text);
            return new String(plainText);
        } else {
            byte[] cipherText = cipher.doFinal(text);
            return new String(cipherText);
        }    
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}