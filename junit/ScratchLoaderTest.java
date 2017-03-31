package junit;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    
    /**
    *Test to check is media files are there.
    *
    */	
    @Test
    public void testMediaCheck()
    {
	try 
        {
	    String cmdArg = "scratchFiles/Paint with Gobo";
            ScratchLoader loader = new ScratchLoader(cmdArg);
	    assertTrue(loader.checkMediaReferences());
	}
	catch (Exception e)
	{
            System.out.println(e);
	}
    }

    /**
    * Fail test to check is media files are there.
    *
    */	
    @Test
    public void testMediaCheckFail()
    {
	try
        {
	    String cmdArg = "scratchFiles/Paint with Gobo Fail";
            ScratchLoader loader = new ScratchLoader(cmdArg);
	    assertFalse(loader.checkMediaReferences());
	}
	catch (Exception e)
	{
            System.out.println(e);
	}
    }

    /**
    * Test to unzip a file.
    *
    */	
    @Test
    public void testUnzip()
    {
        String cmdArg = "scratchFiles/Animate the Crab";
        ScratchLoader loader = new ScratchLoader(cmdArg);
        File file = new File(loader.toString());
        if (file.exists())
        {
            List<Path> files = loader.
                getDirectoryContents(loader.getFileInputDir());
            for (int i = 0; i < files.size(); i++)
            {
                File tempFile = new 
                    File(files.get(i).toString());
                tempFile.delete();
            }
            file.delete();
        }
        cmdArg = "scratchFiles";
        loader = new ScratchLoader(cmdArg);
        loader.unzipFile();
	file = new File("scratchFiles/Animate the Crab");
	assertTrue(file.exists());
    }
}
