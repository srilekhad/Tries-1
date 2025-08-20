//Time Complexity: O(M*L + N*L) where M is the number of words in dictionary, N is number of words in sentence and L is the average word length
//Space Complexity: O(M * L + N * L) for tries and split array.

//Insert all dictionary roots into a trie, marking isEnd at each complete root.
//For each word in the sentence, walk the trie character by character and stop when you hit isEnd (use that prefix) or a missing child (keep original word).
//Build the result by joining each replaced word with spaces.

class Solution {
    TrieNode root;
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()) {
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for(String word : dictionary) {
            insert(word);
        }
        StringBuilder result = new StringBuilder();
        String[] splitArr = sentence.split(" ");
        for(int i = 0; i < splitArr.length; i++) {
            String word = splitArr[i];
            if(i > 0) result.append(" ");
            result.append(getShortestVersion(word));
        }
        return result.toString();
    }

    private String getShortestVersion(String word) {
        TrieNode curr = root;
        StringBuilder sb = new StringBuilder();
        for(char c : word.toCharArray()) { 
            if(curr.children[c - 'a'] == null || curr.isEnd) {
                break;
            }
            curr = curr.children[c - 'a'];
            sb.append(c);
        }
        if(curr.isEnd) {
            return sb.toString();
        }
        return word;
    }
}
