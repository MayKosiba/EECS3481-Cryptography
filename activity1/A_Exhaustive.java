package activity1;

import util.CryptoTools;

public class A_Exhaustive {
    public static void main(String[] args) throws Exception {
        final char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        byte[] bytes;
        bytes = CryptoTools.fileToBytes("activity1/data/MSG3.txt");
        byte[] test = bytes;

        int key_a = 0, key_b = 0;
        double dot = 0.0;
        for(int a = 1; a < 26; a++){
            for(int b = 0; b < 26; b++){
                double tmp = testCypher(testKey(bytes, a, b));
                if(tmp > dot){
                    dot = tmp;
                    key_a = a;
                    key_b = b;
                    break;
                }
            }
        }
        System.out.println("a: " + key_a + " b: " + key_b + " dot: " + dot);
        test = testKey(test, key_a, key_b);
        CryptoTools.bytesToFile(test, "activity1/data/MSG4.clean");
    }

    public static byte[] testKey(byte[] text, int a,  int b){
        byte[] msg = new byte[text.length];
        int a_inv = 0;
        int flag = 0;
        for (int i = 0; i < 26; i++){
            flag = (a * i) % 26;
            if (flag == 1){
                a_inv = i;
            }
        }
        if(flag == 1){
            for (int i = 0; i < text.length; i++){
                char c = (char) (text[i] & ~32);
                msg[i] = (byte) ((char) (a_inv * (c - 'A' + b) % 26 + 'A'));
            }
        } else {
            msg = text;
        }
        return msg;
    }

    public static double testCypher(byte[] text){
        int[] freq = CryptoTools.getFrequencies(text);
        double sum = 0;
        for(int k = 0; k < freq.length; k++){
            sum += freq[k] * CryptoTools.ENGLISH[k];
        }
        return sum;
    }
}
