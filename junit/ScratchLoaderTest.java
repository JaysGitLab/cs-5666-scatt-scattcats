package junit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
     * testGetDirectoryContentsSuccess -Tests the implemntation of 
     *  getDirectoryContents; which returns the contents 
     *  of the specified directory. 
     */
    @Test
    public void testGetDirectoryContentsSuccess() 
    {
        System.out.printf(
            "\nThis test requires manual validation. " 
            + "Confirm output matches the contents of " 
            + "the directory shown below.\n"
        );
        Path inputFileDir = Paths.get(
            System.getProperty("user.dir") + "/scratchFiles");
        //System.out.printf("\nTesting Path: %s\n", inputFileDir.toString());
        List<Path> contents = ScratchLoader.getDirectoryContents(inputFileDir); 
        System.out.printf(
            "\tPrinting Contents of Directory <%s>:\n", 
            inputFileDir.toString()
        );
        int itemCounter = 0;
        for (Path fp : contents) 
        {
            System.out.printf(
                "\t\tItem: %d\tPath: %s\n", 
                itemCounter, fp.toString()
            );
            itemCounter++;
        }
        assertEquals(true, true);
    }
    /**
     * testGetDirectoryContentsFailure -Tests the throwing of the 
     *  appropriate exceptions in the event a nonexistant 
     *  directory is provided to getDirectoryContents.
     */
    @Test
    public void testGetDirectoryContentsFailure() 
    {
        //TODO: method body.
        assertEquals(true, true);
    }
    /**
     * testGetFilePathsSB2 -Tests the return of .sb2 file paths from the
     *  specified directory. 
     */
    @Test
    public void testGetFilePathsSB2()
    {
        System.out.printf(
            "\nThis test requires manual validation. "
            + "Confirm output is only .sb2 files which match the contents of "
            + "the directory shown below.\n"
        );
        Path inputFileDir = Paths.get(
            System.getProperty("user.dir") + "/scratchFiles");
        List<Path> sb2Files = ScratchLoader.getFilePathsSB2(inputFileDir);
        System.out.printf(
            "\tPrinting Contents of Directory <%s>:\n",
            inputFileDir.toString()
        );
        int itemCounter = 0;
        for (Path fp : sb2Files)
        {
            System.out.printf(
                "\t\tItem: %d\tPath: %s\n",
                itemCounter, fp.toString()
            );
            itemCounter++;
        }
        assertEquals(true, true);
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
     * testConvertToZip -Tests the ability of the convertToZip method.
     */
    @Test
    public void testConvertToZip() {
        Path inputFileDir = Paths.get(
            System.getProperty("user.dir") + "/scratchFiles");
        List<Path> sb2Files = ScratchLoader.getFilePathsSB2(inputFileDir);
        List<Path> zipFiles;
        for (Path fp : sb2Files) {
            String fname = fp.toString();
        }
    }
    //TODO: finish test.
    assertTrue(false);
}

