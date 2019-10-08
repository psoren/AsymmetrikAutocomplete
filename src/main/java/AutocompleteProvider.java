import java.util.*;

/**
 * This class uses a trie (prefix tree) to store the words that are passed to it.
 * This helps to save space because for words with common prefixes, we are not
 * storing duplicate strings.
 * 
 * To insert a word, we only need to traverse the trie until we get to the correct node.
 * For a string of length n, this takes n operations, so insertion is O(n).
 * 
 * @author parker
 */
public class AutocompleteProvider {
	
	private Node root;
	
	public AutocompleteProvider(){
		this.root = new Node();
	}
	
	/**
	 * @param fragment The fragment of a string used to get suggestions
	 * @return The list of candidates ordered by confidence
	 */
	public List<Candidate> getWords(String fragment){
		//If a null string was passed or if fragment is not a lowercase string,
		//return an empty list
		if(fragment == null)
			return new ArrayList<Candidate>();
		for(char c: fragment.toCharArray()){
			if(!Character.isLowerCase(c))
				return new ArrayList<Candidate>();
		}
		Node curr = this.root;
		for(char c: fragment.toCharArray()){
			//If the trie does not contain the fragment, return an empty list
			if(curr.letters[c-'a'] == null)
				return new ArrayList<Candidate>();
			curr = curr.letters[c-'a'];
		}
		//Recursively explore all of the nodes and then sort by confidence
		List<Candidate> words = getWordsWithPrefix(curr, fragment, new ArrayList<>());
		words.sort((c1, c2) -> c2.getConfidence() - c1.getConfidence());
		return words;
	}
	
	/**
	 * Find all of the nodes in the trie that contain the given fragment
	 * @param curr The node to explore from
	 * @param fragment The current string
	 * @param candidates The list of candidates so far
	 * @return The list of candidates that contain the given fragment
	 */
	private List<Candidate> getWordsWithPrefix(Node curr, String fragment, List<Candidate> candidates){
		if(curr.isWord())
			candidates.add(new Candidate(fragment, curr.getNumOccurrences()));
		for(int i = 0; i < curr.letters.length; i++){			
			if(curr.letters[i] != null)
				getWordsWithPrefix(curr.letters[i], fragment + (char)('a' + i), candidates);
		}
		return candidates;
	}

	/**
	 * This method trains the algorithm with the given passage
	 * @param passage The passage of words to train the algorithm with 
	 */
	public void train(String passage){
		//Check to make sure that passage is not null
		if(passage == null) return;
		//Remove non-letters and spaces
		passage = passage.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		String[] words = passage.split(" ");
		for(String word: words)
			insertWord(word);
	}
	
	/**
	 * Inserts a word into the trie
	 * @param word The string to insert into the trie
	 */
	private void insertWord(String word){
		//Traverse the trie until we reach the end of the string,
		//incrementing the number of occurrences of the word
		Node curr = this.root;
		for(char c: word.toCharArray()){
			if(curr.letters[c-'a'] == null)
				curr.letters[c-'a'] = new Node();
			curr = curr.letters[c-'a'];
		}
		curr.incrementCount();
	}
}
