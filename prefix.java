//Time Complexity: O(L) for insert, search, and startsWith, where L is the length of the word
//Space Complexity: O(N * L) where N is number of words and L is average word length (due to storing nodes in the Trie).

//Maintain a TrieNode with children: Map<Character, TrieNode> and an isEnd flag.
//insert: walk the characters, create missing child nodes, and mark isEnd at the last node.
//search/startsWith: walk the characters; search also requires the final node’s isEnd = true.

class Trie {
    TrieNode root;

    class TrieNode{
        HashMap<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode(){
            this.children = new HashMap<>();
        }
    }

    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) { 
        TrieNode curr = root;
        for(char c: word.toCharArray()){ 
            if(curr.children.get(c) == null){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) { 
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            if(curr.children.get(c) == null){
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) { //O(L)
        TrieNode curr = root;
        for(char c: prefix.toCharArray()){
            if(curr.children.get(c) == null){
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
