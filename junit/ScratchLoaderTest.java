package junit;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
    * testScratchLoaderConstructorSucess -Tests the initialization of 
    *  the ScratchLoader program.
    */
    @Test
    public void testScratchLoaderConstructorSuccess()
    {
        //get current working directory path:
        Path inputFilePath = FileSystems.getDefault().getPath(
            "scratchFiles", "Animate the Crab.sb2");
        //System.out.println(new File(".").getCanonicalPath()); 
        //String inputDir = "../scratchFiles/Animate the Crab.sb2";
        ScratchLoader loader = new ScratchLoader(inputFilePath.toString());
        assertEquals(loader.getFileInputDir(), inputFilePath);
    }
    /**
     * testScratchLoaderConstructorFailure -Tests the initialization of 
     *  the ScratchLoader program with a non-existant input directory.
     */
    @Test
    public void testScratchLoaderConstructorFailure()
    {
        //get current working directory path:
        Path inputFilePath = FileSystems.getDefault().getPath(
            "scratchFiles", "no-file.sb2");
        ScratchLoader loader = new ScratchLoader(inputFilePath.toString());
        assertEquals(loader.getFileInputDir(), null);
    }
    /**
     * testGetNumberSB2Files -Tests the functionality of the getNumberSB2Files method.
     */
    @Test
    public void testGetNumberSB2Files()
    {
        //initalization:
        Path inputFilePath = FileSystems.getDefault().getPath(
            "scratchFiles");
        ScratchLoader loader = new ScratchLoader(inputFilePath.toString());
        int numSP2Files = 1;
        assertEquals(loader.getNumberSB2Files(inputFilePath), numSP2Files);
    }
}

