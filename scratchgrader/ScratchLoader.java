package scratchgrader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
/**
 * ScratchLoader.java
 * Loads all scratch files in the specified directory 
 *  into memory and verifies files have proper file extensions.
 * @author Chris Campell
 * @author Eric Cambel
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
     * @source www.adam-bien.com/roller/abien/
     *  entry/listing_directory_contents_with_jdk 
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
                    System.err.format("%s\n", ioe);
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
            System.err.format("%s\n", ioe);
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
    public static List<Path> getFilePathsSB2(Path verifiedInputFileDirectory) 
    {
        List<Path> sb2Files = new ArrayList();
        List<Path> dirContents = getDirectoryContents(
            verifiedInputFileDirectory
        );
        for (Path path : dirContents) 
        {
            // get the file extension of the file:
            int extensionIndex = path.toString().indexOf('.');
            // if it is a file and not a directory...
            if (extensionIndex > 0) 
            {
                String fileExt = path.toString().substring(extensionIndex);
                if (fileExt.equals(".sb2")) 
                {
                    sb2Files.add(path);
                }
            }
        }
        return sb2Files;
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
    /**
     * identifySP2Locations -Returns the file paths 
     *  associated with every .sp2 file in the 
     *  specified input directory.
     * @return sp2_paths -The list of file paths
     *  where every .sp2 file can be found. 
    **/
    private Path[] identifySP2Locations()
    {
        //TODO: write method body. 
        return null;
    }

    /**
    *   Method to aid in determining whether a file from
    *   the program is a .sb2 file extension.
    *   @return boolean the analysis of the .sb2 file.
    */
    public boolean checkSB2Extension()
    {
        String scratchFile = inputFileDirectory.toString();
        // If the file name is less than 5, then it isn't a valid file
        // since .sb2 is already four characters long.
        if (scratchFile.length() < 5)
        {
            return false;
        } 
        
        // Check to see if the file name equals .sb2
        if (scratchFile.substring(scratchFile.length() - 4).equals(".sb2")) 
        {
            return true;
        }
        
        return false;
    }
}
