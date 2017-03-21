// Massive list of junit imports that I'm too lazy to sort through
// but I'm sure will come in handy.
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Before;

/**
* Test for the ScratchGrader Class. 
*@author Erick Lares
*@author Eric Cambel
*@author Chris Campell
*@version Mar 2017 
*/

public class ScratchGraderTest
{
	private ScratchGrader grader;
	/**
    * Test to determine whether a file has a .sb2 extension.
    */
    @Test
    public void sb2Test()
    {
		ScratchGrader grader = new ScratchGrader();
		String file = "test.sb2";
		assertTrue(grader.deterScratch(file));
    }
}
