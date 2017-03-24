package junit;
import org.junit.Test;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotSame;
import scratchgrader.ScratchLoader;

/**
 * ScratchLoaderTest.java
 * Tests the functionality of loading and reading scratch files.
 * @author Chris Campell
 * @version 3/22/2017
 **/
public class ScratchLoaderTest 
{
   /**
    * testCommandLineParse -Tests the ability of the program to parse 
    *  command line arguments.
    *
    */
    @Test
    public void testCommandLineParse() 
    {
        String cmdArg = "scratchFiles/my-scratch-file";
        ScratchLoader loader = new ScratchLoader(cmdArg);
        //assertEquals(loader.get_file_input_dir(), cmd_arg);
    }
    /**
     * testGetFileLocationSP2
     * Tests the ability of the program to find a single SP2 file.
    */
    @Test
    public void testGetFileLocationSP2() 
    {
        //TODO: Write test method for single .sp2 file in container dir.
    }

    /**
    * Test to determine whether a file has a .sb2 extension.
    */
    @Test
    public void testSB2Extension()
    {
        String cmdArg = "scratchFiles/Animate the Crab.sb2";
        ScratchLoader loader = new ScratchLoader(cmdArg);
        assertTrue(loader.checkSB2Extension());
    }
}
