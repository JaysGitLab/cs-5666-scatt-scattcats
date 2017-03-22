package scratchGrader;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * ScratchLoader.java
 * Loads all scratch files in the specified directory into memory and verifies files 
 *  have proper file extensions.
 * @Author: Chris Campell
 * @Version: 3/22/2017
 **/
 public class ScratchLoader {
    private static Path inputFileDirectory;

    public ScratchLoader(String inputFileDirPath) {
        try {
            this.inputFileDirectory = Paths.get(inputFileDirPath);
        } catch (Exception e) { 
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * getFileInputDir -Returns the directory where the scratch files to be processed
     *  are housed. This directory was specified during initialization of this class.
     * @return inputFileDirectory: The directory specified during initalization where all
     *  scratch files to be processed are stored.
    **/
    public Path getFileInputDir(){
        return inputFileDirectory;    
    }
    /**
     * identifySP2Locations -Returns the file paths associated with every .sp2 file in
     *  the specified input directory.
    **/
    private Path identifySP2Locations() {
       //TODO: write method body. 
       return null;
    }
 }
