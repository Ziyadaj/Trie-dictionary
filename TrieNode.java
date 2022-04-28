package project;

class TrieNode {
    protected int n = 26;
    protected String info;
    TrieNode[] children = new TrieNode[n];
    boolean isLeaf;
    TrieNode() {
        isLeaf = false;
        for (int i = 0; i < n; i++)
            children[i] = null;
    }

}