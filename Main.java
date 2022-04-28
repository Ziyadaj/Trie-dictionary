package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("TRIE: Enter your choice?");
        System.out.println("1) Create an empty trie");
        System.out.println("2) Create a trie with initial letters");
        System.out.println("3) Insert a word");
        System.out.println("4) Delete a word");
        System.out.println("5) List all words that begin with a prefix");
        System.out.println("6) Size of the trie");
        System.out.println("7) End");
        String choice = sc.next();
        if (choice.equals("1")){
            System.out.println("Creating a trie...");
            Trie trie = new Trie(); //create empty trie
            System.out.println("\nTrie initilized");
            do {
                System.out.println("TRIE: Enter your choice?");
                System.out.println("1) Create an empty trie");
                System.out.println("2) Create a trie with initial letters");
                System.out.println("3) Insert a word");
                System.out.println("4) Delete a word");
                System.out.println("5) List all words that begin with a prefix");
                System.out.println("6) Size of the trie");
                System.out.println("7) End");
                choice = sc.next();
                switch(choice) {
                    case ("2"):
                        System.out.println("Enter your list of letters: ");
                        ArrayList<String> keys = new ArrayList<>();
                        String key = sc.next();
                        String sum = "";
                        while (!key.equals(";")) {
                            System.out.println("Enter ; to quit: ");
                            keys.add(key);
                            sum += key;
                            key = sc.next();
                        }
                        System.out.println("Creating trie with possible permutations...");
                        int len = sum.length();
                        permutation(sum.toCharArray(), len, new char[len], 0,trie);
                        System.out.println("Trie created");

                        trie.print();
                        
                        // System.out.println("trie: ");
                        break;
                    case ("3"):
                        System.out.println("Enter a word: ");
                        String word = sc.next();
                        insertDictWord(word, word.length(), trie);
                        if (trie.contains(word))
                            System.out.println("Word successfully inserted");
                        trie.print();
                        break;

                    case ("4"):
                        System.out.println("Enter a word: ");
                        String c= sc.next();
                        if (trie.contains(c)){
                            trie.delete(c);
                            System.out.println(c + " deleted");
                        }
                        else{
                            System.out.println(c + " Not in trie");
                        }
                        break;

                    case ("5"):
                        System.out.println("Enter prefix: ");
                        String prefix = sc.next();
                        System.out.println("Words are: " + trie.allWordsPrefix(prefix));
                        break;

                    case ("6"):
                        System.out.println("Trie size: " + trie.size());
                        System.out.println();
                        break;

                    default: break;
                } 
            } while (!choice.equals("7")); 
            System.out.println("Final trie: ");
            trie.print();
        }
        else {
            System.out.println("Trie not initilized");
        }

        sc.close();
    }
    //method to only insert dictionary words in trie 
    public static void insertDictWord(String word, int len, Trie trie) throws FileNotFoundException { 
        List<String> list = Main.readDict(len);
        word = word.toUpperCase();
        boolean flag = false;
        for (String str : list) {
            if (word.equals(str)) {
                trie.insert(str);
                flag = true; //word found check
                System.out.println(trie.contains(str));
            }
        }
        if (flag) System.out.println("Word entered");   
        else System.out.println("word not in dictionary");
    }
    public static List<String> readDict(int len) throws FileNotFoundException {
        Scanner s = new Scanner(new File("UNI/lib/project/dictionary.txt"));
        List<String> dict = new ArrayList<>();
        String ss;
        while (s.hasNext()){
            ss = s.next();
            if (ss.length() <= len) //only word that are smaller than word length
                dict.add(ss);
        }
        s.close();
        return dict;
    }
    public static void permutation(char[] chars, int len, char[] build, int pos, Trie trie) throws FileNotFoundException {
        if (pos == len) {
            String word = new String(build);
            word = word.toUpperCase();
            List<String> list = Main.readDict(len);
            for (String str : list) {
                if (word.equals(str)) {
                    trie.insert(str);
                    System.out.println(trie.contains(str));
                }
            }
            return;
        }
        for (int i = 0; i < chars.length; i++) {//loop through all permutations for a given pos
            build[pos] = chars[i];
            permutation(chars, len, build, pos + 1, trie);//recursively find each permutation for each position
        }
    }
}


