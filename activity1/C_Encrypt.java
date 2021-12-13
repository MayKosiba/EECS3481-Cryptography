package activity1;

import util.CryptoTools;

class CEncryption {
    public static void main(String[] args) throws Exception {
        final char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        byte[] bytes;
        byte[] clean;
        bytes = CryptoTools.fileToBytes("activity1/data/text.txt");
        clean = CryptoTools.clean(bytes);
        CryptoTools.bytesToFile(clean, "activity1/data/MSG1.clean");
        for(int i = 0; i < clean.length;i++){
            char c = (char) (clean[i] & ~32);
            int index = new String(letters).indexOf(c);
            int newIndex = (index + 19) % 26;
            clean[i] = (byte) letters[newIndex];
        }
        CryptoTools.bytesToFile(clean, "activity1/data/MSG1.ct");
    }
}