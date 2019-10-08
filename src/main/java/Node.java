/**
 * This class is used internally in the AutocompleteProvider
 * to store the nodes of the trie
 * @author parker
 *
 */
public class Node {
	public Node[] letters;
	private int numOccurrences;
	private final int ALPHABET_LENGTH = 26;
	
	/**
	 * Creates a new Trie Node
	 */
	public Node(){
		this.letters = new Node[ALPHABET_LENGTH];
		this.numOccurrences = 0;
	}
	
	/**
	 * @return True if the string at this node is a word, false if not 
	 */
	public boolean isWord(){
		return this.numOccurrences > 0;
	}
	
	/**
	 * Increment the number of times this word has been seen
	 */
	public void incrementCount(){
		this.numOccurrences++;
	}
	
	/**
	 * @return The number of occurrences of this word, 0 if this node is not a word
	 */
	public int getNumOccurrences(){
		return this.numOccurrences;
	}	
}
