package activity1;

import util.CryptoTools;

class CExhaustive {
    public static void main(String[] args) throws Exception {
        final char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        byte[] bytes;
        byte[] decrypt;
        int[] freq;
        bytes = CryptoTools.fileToBytes("activity1/data/MSG2.txt");
        decrypt = bytes;
        double total = 0;
        int shift = 0;
        for(int i = 1; i <= 26; i++){
            for(int k = 0; k < bytes.length; k++){
                char c = (char) (bytes[k] & ~32);
                int index = new String(letters).indexOf(c);
                int newIndex = (index + 1) % 26;
                bytes[k] = (byte) letters[newIndex];
            }
            freq = CryptoTools.getFrequencies(bytes);
            double sum = 0;
            for(int k = 0; k < freq.length; k++){
                sum += freq[k] * CryptoTools.ENGLISH[k];
            }
            if(sum > total){
                total = sum;
                shift = i;
            }
            System.out.println("Shift: " + i);
            System.out.println("frequency: " + sum);
        }

        for(int i = 0; i < decrypt.length; i++){
                char c = (char) (decrypt[i] & ~32);
                int index = new String(letters).indexOf(c);
                int newIndex = (index + shift) % 26;
                decrypt[i] = (byte) letters[newIndex];
        }
        CryptoTools.bytesToFile(decrypt, "activity1/data/MSG2.clean");
    }
}
