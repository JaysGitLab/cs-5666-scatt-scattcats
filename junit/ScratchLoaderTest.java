package junit;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import scratchGrader.ScratchLoader;

/**
 * ScratchLoaderTest.java
 * Tests the functionality of loading and reading scratch files.
 * @Author: Chris Campell
 * @Version: 3/22/2017
 **/
 public class ScratchLoaderTest {
    /**
     * testCommandLineParse -Tests the ability of the program to parse 
     *  command line arguments.
     *
     **/
     @Test
     public void testCommandLineParse() {
         String cmd_arg = "scratchFiles/my-scratch-file";
         ScratchLoader loader = new ScratchLoader(cmd_arg);
         //assertEquals(loader.get_file_input_dir(), cmd_arg);
     }
     /**
      * testGetFileLocationSP2 -Tests the ability of the program to find a single SP2 file.
     **/
     @Test
     public void testGetFileLocationSP2() {
         //TODO: Write test method for single .sp2 file in container dir.
     }
 }