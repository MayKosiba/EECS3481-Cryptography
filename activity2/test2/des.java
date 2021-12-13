package activity2;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import util.Binary;
import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class des {
    public static void main(String[] args) throws Exception {
        //Q4
        byte[] keyBytes = CryptoTools.hexToBytes("4F75725269676874");
        byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
        byte[] ct = CryptoTools.hexToBytes("7AA38A029E773CBBC188A9FCEADAE99DA560B784C99AFEF2");
        
        SecretKey key =  new SecretKeySpec(keyBytes, "DES");
        String algorithm = "DES/ECB/NoPadding";
        String plainText = decrypt(algorithm, ct, key, iv);

        System.out.print(plainText);
    }


    public static String decrypt(String algorithm, byte[] text, SecretKey key, byte[] iv) 
    throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException,BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] ptBlock = null;
		final byte[] ctBlock = new byte[cipher.getBlockSize()];
        final byte[] prevCTBlock = new byte[cipher.getBlockSize()];
        byte[] ptext = new byte[text.length];

        System.arraycopy(iv, 0, prevCTBlock, 0, cipher.getBlockSize());

		for (int plaintextPos = 0; plaintextPos != text.length; plaintextPos += cipher.getBlockSize()) {

            System.arraycopy(text, plaintextPos, ctBlock, 0, cipher.getBlockSize());

			ptBlock = xor(prevCTBlock,ctBlock);
			
            System.arraycopy(ptBlock, 0, ptext, plaintextPos, cipher.getBlockSize());

			System.arraycopy(ctBlock, 0, prevCTBlock, 0, cipher.getBlockSize());
		}

        byte[] plaintext = cipher.doFinal(ptext);

        return new String(plaintext);
    }

    public static byte[] xor(byte[] block_1, byte[] block_2) throws NullPointerException {
		final byte[] result = new byte[block_1.length];

		for (int i = 0; i != block_1.length; i++) {
			result[i] = (byte) (block_1[i] ^ block_2[i]);
		}

		return result;
	}
}
