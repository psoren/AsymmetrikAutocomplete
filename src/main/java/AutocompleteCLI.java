import java.util.*;

/**
 * A simple command line interface to interact with the AutocompleteProvider
 * @author parker
 *
 */
public class AutocompleteCLI {
	
	private AutocompleteProvider provider;
	private Scanner input;
	private final String GET_TRAINING_DATA = "1";
	private final String GET_PREFIX_DATA = "2";
	private final String END_PROGRAM = "3";
	
	public AutocompleteCLI(){
		this.provider = new AutocompleteProvider();
		input = new Scanner(System.in);
	}
	
	/**
	 * Prints a simple menu
	 */
	private void printMenu(){
		System.out.println("\nPress 1 to enter training data.");
		System.out.println("Press 2 to enter a prefix.");
		System.out.println("Press 3 to quit the program.");
	}
	
	/**
	 * Prints a welcome message
	 */
	private void printWelcomeMessage(){
		System.out.println("Welcome to the Autocomplete CLI.");
		System.out.println("This program stores strings containing lowercase letters");
		System.out.println("and outputs recommendations.");
	}	
	
	/**
	 * Gets training data from the user and passes it to the AutocompleteProvider
	 */
	private void inputTrainingData(){
		System.out.println("Please enter your training data:");
		String passage = input.nextLine();
		this.provider.train(passage);
	}
	
	/**
	 * Gets a prefix from the user and returns the word suggestions from the AutocompleteProvider
	 */
	private void getPrefixData(){
		System.out.println("Please enter a prefix:");
		String fragment = input.nextLine();
		List<Candidate> candidates = this.provider.getWords(fragment);
		if(candidates.size() == 0)
			System.out.println("There are no words with that prefix.");
		else {
			for(int i = 0; i < candidates.size(); i++){
				String separator = i == candidates.size()-1 ? "" : ", ";
				System.out.print(candidates.get(i) + separator);
			}
			System.out.println();
		}	
	}
	
	/**
	 * This method starts the command line interface
	 */
	public void start(){
		printWelcomeMessage();
		String option = "";
		while(!option.equals(END_PROGRAM)){
			printMenu();
			option = input.nextLine();
			switch(option){
				case GET_TRAINING_DATA:
					inputTrainingData();
					continue;
				case GET_PREFIX_DATA:
					getPrefixData();
					continue;	
				case END_PROGRAM:
					continue;
				default:
					System.out.println("Please enter a valid option.");
					continue;
			}	
		}	
	}
	
	public static void main(String[] args){
		AutocompleteCLI cli = new AutocompleteCLI();
		cli.start();
	}
}
