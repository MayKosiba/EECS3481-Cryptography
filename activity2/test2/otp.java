package activity2;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Arrays;
import util.Binary;
import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class otp {
    public static void main(String[] args) throws Exception {
        byte[] ct_1 = CryptoTools.hexToBytes("3D48044D421349564A1541054204131C");
        byte[] ct_2 = CryptoTools.hexToBytes("3D54024D531442454C0941175404150A");
        byte[] word = "bridge".getBytes();

        byte[] text = xor(ct_1,ct_2);
        for(int i = 0; i < text.length; i++){
            byte[] block = Arrays.copyOfRange(text, i, i + word.length);
            byte[] new_text = xor(block,word);
            String pt = new String(new_text);
            System.out.println(pt);
        }
    }

    public static byte[] xor(byte[] block_1, byte[] block_2) throws NullPointerException {
		final byte[] result = new byte[block_1.length];

		for (int i = 0; i != block_1.length; i++) {
			result[i] = (byte) (block_1[i] ^ block_2[i]);
		}

		return result;
	}
}
