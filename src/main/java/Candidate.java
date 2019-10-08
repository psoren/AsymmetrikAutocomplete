
/**
 * This class represents a candidate suggestion
 * @author parker
 *
 */
public class Candidate {

	private String word;
	private int confidence;
	
	/**
	 * @param w the word of the candidate
	 * @param c the initial confidence level of the candidate
	 */
	public Candidate(String w, int c){
		this.word = w;
		this.confidence = c;
	}
	
	/**
	 * @return the autocomplete candidate
	 */
	public String getWord(){
		return this.word;
	}
	
	/**
	 * @return the confidence for the candidate
	 */
	public int getConfidence(){
		return this.confidence;
	}
	
	@Override 
	public String toString(){
		return "\"" + this.word + "\"" + " (" + this.confidence + ")";	
	}
}
