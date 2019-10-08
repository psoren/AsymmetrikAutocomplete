import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AutocompleteTests {
	
	@Test
	public void testAutocomplete(){
		AutocompleteProvider testProvider = new AutocompleteProvider();
		
		//Testing to make sure that we are starting with an empty list
		assertEquals(testProvider.getWords(""), new ArrayList<Candidate>());
		
		//Train on the example sentence
		testProvider.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
		
		//Test to make sure that there were 15 unique words inserted
		assertEquals(testProvider.getWords("").size(), 15);
		
		//"thi" testing
		assertEquals(testProvider.getWords("thi").size(), 4);
		assertEquals(testProvider.getWords("thi").get(0).getConfidence(), 2);
		assertEquals(testProvider.getWords("thi").get(0).getWord(), "thing");

		assertEquals(testProvider.getWords("thi").get(1).getConfidence(), 1);
		assertEquals(testProvider.getWords("thi").get(1).getWord(), "think");
		
		assertEquals(testProvider.getWords("thi").get(2).getConfidence(), 1);
		assertEquals(testProvider.getWords("thi").get(2).getWord(), "third");
		
		assertEquals(testProvider.getWords("thi").get(3).getConfidence(), 1);
		assertEquals(testProvider.getWords("thi").get(3).getWord(), "this");

		//"nee" testing
		assertEquals(testProvider.getWords("nee").size(), 1);
		assertEquals(testProvider.getWords("nee").get(0).getConfidence(), 1);
		assertEquals(testProvider.getWords("nee").get(0).getWord(), "need");
		
		//"th" testing
		assertEquals(testProvider.getWords("th").size(), 7);		
		
		assertEquals(testProvider.getWords("th").get(0).getConfidence(), 2);
		assertEquals(testProvider.getWords("th").get(0).getWord(), "that");

		assertEquals(testProvider.getWords("th").get(1).getConfidence(), 2);
		assertEquals(testProvider.getWords("th").get(1).getWord(), "thing");
		
		assertEquals(testProvider.getWords("th").get(2).getConfidence(), 1);
		assertEquals(testProvider.getWords("th").get(2).getWord(), "the");
		
		assertEquals(testProvider.getWords("th").get(3).getConfidence(), 1);
		assertEquals(testProvider.getWords("th").get(3).getWord(), "think");
		
		assertEquals(testProvider.getWords("th").get(4).getConfidence(), 1);
		assertEquals(testProvider.getWords("th").get(4).getWord(), "third");
		
		assertEquals(testProvider.getWords("th").get(5).getConfidence(), 1);
		assertEquals(testProvider.getWords("th").get(5).getWord(), "this");
		
		assertEquals(testProvider.getWords("th").get(6).getConfidence(), 1);
		assertEquals(testProvider.getWords("th").get(6).getWord(), "thoroughly");
		
		//Test with new words		
		assertEquals(testProvider.getWords("p").size(), 0);
		testProvider.train("pork");
		assertEquals(testProvider.getWords("p").size(), 1);
		testProvider.train("plateau");
		assertEquals(testProvider.getWords("p").size(), 2);
		
		assertEquals(testProvider.getWords("x").size(), 0);
		testProvider.train("xylophone");
		assertEquals(testProvider.getWords("x").size(), 1);
		assertEquals(testProvider.getWords("xylophone").size(), 1);
		testProvider.train("xerxes");
		assertEquals(testProvider.getWords("x").size(), 2);	
		
		//Testing with null
		assertEquals(testProvider.getWords("").size(), 19);
		testProvider.train(null);
		assertEquals(testProvider.getWords("").size(), 19);
		assertEquals(testProvider.getWords(null), new ArrayList<Candidate>());

		//Testing with capital letters
		assertEquals(testProvider.getWords("r").size(), 0);
		testProvider.train("racecar");
		assertEquals(testProvider.getWords("r").size(), 1);
		assertEquals(testProvider.getWords("R").size(), 0);
		
		testProvider.train("Rambunctious");
		assertEquals(testProvider.getWords("r").size(), 2);
		
		//Test with numbers
		testProvider.train("123");
		assertEquals(testProvider.getWords("123").size(), 0);

		//Test with non-character strings
		testProvider.train("{{[][)($");
		assertEquals(testProvider.getWords("{{").size(), 0);	
	}
}
