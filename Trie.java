package project;

public class Trie  {
    protected TrieNode root; 
    public Trie(){  
        this.root = new TrieNode();  
    }  
    public boolean isEmpty() {
        for (int i = 0; i < root.n; i++) //root.n = Alphabet amount
            if (root.children[i] != null) //check each node's children
                return false; 
        return true;
    }
    public void clear() {
        root = null;
    }
    protected int size(){ return size(root);}
    public int size(TrieNode node) {
        int result = 0;
       
        if (node.isLeaf)//base case: last node
            result++;
           
        for (int i = 0; i < node.n; i++)    
          if (node.children[i] != null )
             result += size(node.children[i]);// recursively add each non null child
          
        return result;   
    }
    public boolean containsKey(char ch) {
        return root.children[ch -'A'] != null;
    }
    public TrieNode get(char ch) {
        return root.children[ch -'A'];
    }
    public void insert(String s) {
        
        int index;
        s = s.toUpperCase(); //only insert Upper case alphabets
        TrieNode node = root;
      
        for (int level = 0; level < s.length(); level++)
        {
            index = s.charAt(level) - 'A';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
      
            node = node.children[index];
        }

        node.isLeaf = true;//leaf
    }
    public Boolean contains(String s) {
        TrieNode node = searchPrefix(s);
        return node != null &&  node.isLeaf; //check if whole word is in trie
    }
    // protected boolean search(String s) {
        
    //     int index;
    //     s = s.toUpperCase(); 
    //     TrieNode node = root; 
      
    //     for (int level = 0; level < s.length(); level++)
    //     {
    //         index = s.charAt(level) - 'A';
      
    //         if (node.children[index] == null)
    //             return false;
      
    //         node = node.children[index];
    //     }
      
    //     return (node.isLeaf);
    // }

    private TrieNode searchPrefix(String s) {
        s = s.toUpperCase(); //only insert Upper case alphabets
        TrieNode node = root; //pointer
        for (int i = 0; i < s.length(); i++) {
           char child = s.charAt(i);
           if (containsKey(child)) {//check if index is not null
               node = get(child); //assign node to index
           } else {
               return null;
           }
        }
        return node;
    }
    // public TrieNode delete(String key){ return delete(root,key,0);}
    // protected TrieNode delete(TrieNode node, String key, int depth) {
        
    //     if (node == null)//base case
    //         return null;
 
        
    //     if (depth == key.length()) {// if leaf node is reached
 

    //         if (node.isLeaf)// remove leaf indicator
    //             node.isLeaf = false;
 
    //         //delete last node
    //         if (isEmpty()) {
    //             node = null;
    //         }
 
    //         return node; //return deleted
    //     }
    //         node = delete(get(key.charAt(depth)), key, depth + 1);
     
    //         if (isEmpty() && node.isLeaf){ //if leaf node and no children
    //             node = null; //delete
    //         }
     
    //         return node;
    //     }
        public boolean delete(String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            
            // All nodes below 'deleteBelow' and on the path starting with 'deleteChar' (including itself) will be deleted if needed
            TrieNode deleteBelow = null;
            char deleteChar = '\0';
            
            // Search to ensure word is present
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char cur = word.charAt(i);
                
                TrieNode child = get(cur); // Check if having a TrieNode associated with 'cur'
                if (child == null) { // null if 'word' is way too long or its prefix doesn't appear in the Trie
                    return false;
                }
                
                if (node.children.size() > 1 || node.isLeaf) { // Update 'deleteBelow' and 'deleteChar'
                    deleteBelow = node;
                    deleteChar = cur;
                }
                
                node = child;
            }
            
            if (!node.isLeaf) { // word isn't in trie
                return false;
            }
            
            if (node.children.isEmpty()) {
                deleteBelow.children.remove(deleteChar);
            } else {
                node.isLeaf = false; // Delete word by mark it as not the end of a word
            }
            
            return true;
        }
    protected void print() {
        char[] str = new char[26];
        print(root, str, 0);
    }
    public void print(TrieNode node, char[] str, int level) {
        if (node.isLeaf){
            str[level] = '\0';
            System.out.println(str);
        }

        for (int i = 0; i < node.n; i++) {
            if (node.children[i] != null){
                str[level] = (char) (i + 'A');
                print(node.children[i], str, level + 1);

            }
        }
    }   
    public boolean isPrefix(String p) {
        TrieNode node = searchPrefix(p);
        return node != null;
    }
    public String[] allWordsPrefix(String p) {
        String[] arr = new String[size()];
        for (int i = 0; i < root.n; i++) {
            for (int j = 0; j < p.length(); j++) {
                if (root.children[i].info == p){
                    System.out.println(root.children[i].info);
                    arr[i] = String.valueOf(root.children[i].info);
                }
            }
        }
        return arr;
    }
}