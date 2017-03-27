package junit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
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
     * testGetDirectoryContentsSuccess -Tests the implemntation of getDirectoryContents;
     *  which returns the contents of the specified directory. 
     */
    @Test
    public void testGetDirectoryContentsSuccess() {
        System.out.printf("This test requires manual validation. Confirm output matches contents of taret directory.");
        Path inputFileDir = Paths.get(System.getProperty("user.dir") + "/scratchFiles");
        System.out.printf("\nTesting Path: %s\n", inputFileDir.toString());
        List<Path> contents = ScratchLoader.getDirectoryContents(inputFileDir); 
        System.out.println("Printing Directory Contents:");
        int itemCounter = 0;
        for (Path fp : contents) {
            System.out.printf("Item: %d\tPath: %s\n", itemCounter, fp.toString());
            itemCounter++;
        }
        assertEquals(true, true);
    }
}

