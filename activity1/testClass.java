package activity1;

import util.CryptoTools;

public class testClass {
    public static void main(String[] args) throws Exception{
        byte[] bytes;
        bytes = CryptoTools.fileToBytes("activity1/data/test.txt");

        int[] freq = CryptoTools.getFrequencies(bytes);

        double sum = 0;
        for(int k = 0; k < freq.length; k++){
            sum += freq[k] * CryptoTools.ENGLISH[k];
        }
        sum = sum / bytes.length;
        System.out.println(sum);
    }
}
