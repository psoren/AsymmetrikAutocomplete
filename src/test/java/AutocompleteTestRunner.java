import org.junit.runner.*;
import org.junit.runner.notification.*;

/**
 * This class runs the tests in AutocompleteTests
 * @author parker
 *
 */
public class AutocompleteTestRunner {

	public static void main(String[] args){
		Result res = JUnitCore.runClasses(AutocompleteTests.class);
		for(Failure failure: res.getFailures())
			System.out.println(failure);
		System.out.println("All of the tests passed: " + res.wasSuccessful());
	}
	
}
