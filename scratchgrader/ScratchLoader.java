package scratchgrader;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        try 
        {
            this.inputFileDirectory = Paths.get(inputFileDirPath);
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(System.err);
        }
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
