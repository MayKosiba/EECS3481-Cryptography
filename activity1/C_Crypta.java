package activity1;

import util.CryptoTools;

class CCrypta {
    public static void main(String[] args) throws Exception {
        final char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        byte[] bytes;
        int[] freq;
        bytes = CryptoTools.fileToBytes("activity1/data/MSG2.txt");
        freq = CryptoTools.getFrequencies(bytes);
        for(int i = 0; i < freq.length; i++){
            double tmp = (double) freq[i] / (double) bytes.length;
            System.out.println("Letter: " + letters[i] + " freq:" + tmp);
        }
    }
}
