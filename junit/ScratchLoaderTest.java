package junit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import org.junit.Test;
import java.util.List;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import io.restassured.path.json.JsonPath;
import org.junit.After;
//import static org.junit.Assert.assertNotSame;
import scratchgrader.ScratchLoader;
import scratchgrader.Sprite;
import scratchgrader.ScratchGrader;
/**
 * ScratchLoaderTest.java
 * Tests the functionality of loading and reading scratch files.
 * @author Chris Campell
 * @version 3/22/2017
 **/
public class ScratchLoaderTest 
{
    private ScratchGrader project;

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
    public void testConvertToZip() 
    {
        // Makes directory and contents.
        int cZip = 0;
        int cSb2 = 0;
        String dirName  = "testScratchFiles";

        makeTempDirectory(dirName);

        Path inputFileDir = Paths.get(
            System.getProperty("user.dir") + "/" + dirName);

        List<Path> sb2Files = ScratchLoader.getFilePathsSB2(inputFileDir);
        ScratchLoader.convertToZip(sb2Files);

        List<Path> dirContents = ScratchLoader
        .getDirectoryContents(inputFileDir);

        for (Path fp : dirContents) 
        {
            String fname = fp.toString();
            for (Path sb2Fp : sb2Files)
            {
                if (fname.equals(sb2Fp.toString()))
                {
                    cSb2++;
                }
            }
            String zname = fname.substring(0, fname.length() - 3) + "zip";

            if (zname.equals(fp.toString()))
            {
                cZip++;
            }
        }

        //TODO: finish test.
        assertTrue(cSb2 == cZip);

        // Deletes both directory and contents.
        File dir = new File(dirName);
        deleteDirectory(dir);
    }

	
    /**
    *	Helper method to make a temp directory with fake files.
    *	@param dirName is the name of the directory to be made.
    **/
    public static void makeTempDirectory(String dirName)
    {
        // Makes a new temp directory
        new File(dirName).mkdir();
        File dir = new File(dirName);
        //TODO: Make more dynamic for use in other methods.
        try 
        {
            File a = File.createTempFile("test1", ".sb2", dir);
            File b = File.createTempFile("test2", ".sb2", dir);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
	
    /**
    * Helper method to delete temp directories.
    * @param dir is a file directory.
    * @return boolean value of deleting something.
    */
    public static boolean deleteDirectory(File dir) 
    {
        if (dir.isDirectory()) 
        {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) 
            {
                boolean success = deleteDirectory(children[i]);
                if (!success) 
                {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    /**
    * Test to create a sprite Object.
    *
    */
    @Test
    public void testSpriteConstructor()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        File file = new File(cmdArg + "/project.json");       
        List<String> spriteName = JsonPath.from(file).get("children.objName");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        String name = spriteName.get(0);
        List<String>  aux =  spriteScripts.get(0);
        Object[]  spriteiScripts = aux.toArray(); 
        Sprite sprite = new Sprite(name, spriteiScripts);
        assertEquals(sprite.getClass() , Sprite.class);
    }

    /**
    * Test to get the Sprite name.
    *
    */
    @Test
    public void testGetSpriteName()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        File file = new File(cmdArg + "/project.json");       
        List<String> spriteName = JsonPath.from(file).get("children.objName");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        String name = spriteName.get(0);
        List<String>  aux =  spriteScripts.get(0);
        Object[]  spriteiScripts = aux.toArray(); 
        Sprite sprite = new Sprite(name, spriteiScripts);
        assertEquals("Choose", sprite.getSpriteName());
    }

    /**
    * Test to count scripts inside the sprite.
    *
    */
    @Test
    public void testCountScripts()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        File file = new File(cmdArg + "/project.json");       
        List<String> spriteName = JsonPath.from(file).get("children.objName");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        String name = spriteName.get(0);
        List<String>  aux =  spriteScripts.get(0);
        Object[]  spriteiScripts = aux.toArray(); 
        Sprite sprite = new Sprite(name, spriteiScripts);
        assertEquals(2, sprite.countScripts());
    }

     /**
    * Test to get the length of the scripts inside the sprite.
    *
    */
    @Test
    public void testGetCombinedScriptLength()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        File file = new File(cmdArg + "/project.json");       
        List<String> spriteName = JsonPath.from(file).get("children.objName");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        String name = spriteName.get(0);
        List<String>  aux =  spriteScripts.get(0);
        Object[]  spriteiScripts = aux.toArray(); 
        Sprite sprite = new Sprite(name, spriteiScripts);
        List<Integer> scriptsLength = sprite.lengthScripts();
        int length = 229;
	    int scriptLength =  scriptsLength.get(0);
	    assertEquals(length, scriptLength);
    }

    /**
    * Test to create a ScratchGrader Object.
    *
    */
    @Test
    public void testScratchGraderConstructor()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        ScratchGrader project = new ScratchGrader(cmdArg);
        assertEquals(project.getClass() , ScratchGrader.class);
    }

    /**
    * Test to get the scratch project name.
    *
    */
    @Test
    public void testgetProjectName()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        ScratchGrader project = new ScratchGrader(cmdArg);
        assertEquals("Paint with Gobo" , project.getProjectName());
    }

    /**
    * Test to count scripts inside the scratch project.
    *
    */
    @Test
    public void testgetTotalScriptCount()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        ScratchGrader project = new ScratchGrader(cmdArg);
        int count = project.getTotalScriptCount();
	project = null;
        assertEquals(4 , count);
    }

     /**
    * Test to get the length of the scripts inside the scratch project.
    *
    */
    @Test
    public void testgetTotalScriptLength()
    {
        String cmdArg = "scratchFiles/Paint with Gobo";
        ScratchGrader project = new ScratchGrader(cmdArg);
        int len = project.getCombinedScriptLength();
	project = null;
        assertEquals(406 , len);
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
	    assertFalse(loader.checkMediaReferences(cmdArg));
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



    /**
    *Clean up for the test.
    */
    @After
    public void cleanUp()
    {
    	project = null;   
    }
}

