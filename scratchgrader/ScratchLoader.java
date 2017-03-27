package scratchgrader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.ArrayList;
/**
 * ScratchLoader.java
 * Loads all scratch files in the specified directory 
 *  into memory and verifies files have proper file extensions.
 * @author Chris Campell
 * @version 3/22/2017
 */
public class ScratchLoader 
{
    private static Path inputFileDirectory;

    /**
     * ScratchLoader -Constructor for objects of type ScratchLoader.
     * @param inputFileDirPath -The file path to the input directory 
     *  containing the scratch files to be analyzed.
     */
    public ScratchLoader(String inputFileDirPath) 
    {
        // Ensure input file directory exists:
        if (Files.exists(Paths.get(inputFileDirPath))) 
        {
            this.inputFileDirectory = Paths.get(inputFileDirPath);
        } 
        else 
        {
            //System.out.printf(
            //  "ERROR: Can't find file path <%s> on machine.", 
            //  inputFileDirPath
            //);
            this.inputFileDirectory = null;
        }
        // Read all files in input directory:
        //Path[] sb2FilePaths = getFilePathsSB2(this.inputFileDirectory);
    }
    /**
     * getDirectoryContents -Returns the contents of the specified directory 
     *  as a list of Path objects.
     * @param inputFileDir -The input directory to list contents of.
     * @return fileNames -The list of files in the specified 
     *  directory as Path objects. 
     */
    public static List<Path> getDirectoryContents(Path inputFileDir)
    {
        //Create container for path objects:
        List<Path> fileNames = new ArrayList();
        //Verify path existance:
        Path cwd;
        try 
        {
            cwd = inputFileDir.toRealPath();
            // Check to see if absolute path is readable:
            if (Files.isReadable(cwd)) 
            {
                // Iterate every file in dir and record path objects:
                try (DirectoryStream<Path> 
                    dirStream = Files.newDirectoryStream(
                            Paths.get(cwd.toString()))) 
                {
                    for (Path path : dirStream) 
                    {
                        fileNames.add(path);
                    }
                } 
                catch (IOException ioe) 
                {
                    System.err.format("%s%n", ioe);
                }
            } 
            else 
            {
                System.err.format("Unreadable Path %s", cwd);
            }
        } 
        catch (NoSuchFileException nsfe) 
        {
            System.err.format("%s: no such" + "file or directory%n", nsfe);
        } 
        catch (IOException ioe) 
        {
            System.err.format("%s%n", ioe);
        }
        return fileNames;
    }

    /**
     * getFilePathsSB2 -Returns an array of file paths pointing to the .sb2 
     *  files in the provided input file directory.
     * @param verifiedInputFileDirectory -The file path to the input 
     *  directory that has already been validated for existance.
     * @return sb2FilePaths -An array of file paths pointing to the 
     *  .sb2 files in the provided existing directory.
     */
    public Path[] getFilePathsSB2(Path verifiedInputFileDirectory) 
    {
        return null;
    }
    /**
     * getFileInputDir -Returns the directory where the scratch 
     *  files to be processed are housed. 
     * @return inputFileDirectory: The directory specified during 
     *  initalization where all scratch files to be processed are stored.
    **/
    public Path getFileInputDir() 
    {
        return inputFileDirectory;    
    }
}
