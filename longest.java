//Time Complexity: O(N * L), N is number of words, L is average word length.
//Space Complexity: O(N * L), for storing the Trie and the BFS queues.

//Insert all words into a trie, marking isEnd at terminal nodes.
//Run DFS from the root, only descending into children whose isEnd is true, appending/removing chars on a shared StringBuilder.
//Keep a global result and update it whenever the current path becomes longer than the best seen.

class Solution {
    TrieNode root;
    String result;

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }

    private void insert(String word){
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public String longestWord(String[] words) {
        this.root = new TrieNode();
        this.result = "";

        for(String word : words){
            insert(word);
        }

        dfs(root, new StringBuilder());

        return result;
    }

    private void dfs(TrieNode curr, StringBuilder path){

        if(result.length() < path.length()){
            result = path.toString();
        }

        for(int i=0; i<26; i++){
            if(curr.children[i] != null && curr.children[i].isEnd){

                int le = path.length();
                path.append((char)(i+'a'));
                dfs(curr.children[i], path);
                path.setLength(le);

            }
        }
    }
}
