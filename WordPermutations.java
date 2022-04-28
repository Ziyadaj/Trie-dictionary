package project;

import java.io.FileNotFoundException;
import java.util.List;

public class WordPermutations {
    public static void main(String[] args) throws FileNotFoundException {
        char[] chars = "stop".toCharArray();
        int len = chars.length;
        iterate(chars, len, new char[len], 0);
    }

    public static void iterate(char[] chars, int len, char[] build, int pos) throws FileNotFoundException {
        if (pos == len) {
            String word = new String(build);
            word = word.toUpperCase();
            List<String> list = Main.readDict(len);
            for (String str : list) {
                if (word.equals(str)) System.out.println(word);
                
            }
            return;

        }
            for (int i = 0; i < chars.length; i++) {
                build[pos] = chars[i];
                iterate(chars, len, build, pos + 1);
            }
    }
}
