package scratchgrader;
import java.nio.file.Path;
import java.nio.file.Paths;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
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

    /**
    * checkMediaReferences - This methos makes 
    * sure that the media references: sound, images
    * exist.
    *
    *@return boolean - True if all the refences
    * are good.
    */
    
    public 
    static 
    boolean 
    checkMediaReferences()
    {
        Boolean yes = true;
        try
        {
            String path = inputFileDirectory.toString();
            File file = new File(path + "/project.json");       
            List<String> children = JsonPath.from(file).
                get("children.costumes.baseLayerID");
            List<String> childrentype = JsonPath.from(file).
                get("children.costumes.baseLayerMD5");
            List  child = Arrays.asList(children.toArray());
            List  childType = Arrays.
                asList(childrentype.toArray());       
            List<String> sound = JsonPath.from(file).
                get("sounds.soundID");
            List<String> soundtype = JsonPath.from(file).
                get("sounds.md5");
            List<String> costume = JsonPath.from(file).
                get("costumes.baseLayerID");
            List<String> costumetype = JsonPath.from(file).
                get("costumes.baseLayerMD5");       
            String penlayer = JsonPath.from(file).
                get("penLayerID").toString();
            String penlyertype = JsonPath.from(file).
                get("penLayerMD5");
            
            if (sound != null && yes == true)
            {
                for (int i = 0; i < sound.size(); i++)
                {
                    String string = soundtype.get(i);
                    String[] parts = string.split("\\.");
                    String aux = parts[0]; 
                    String type = parts[1]; 
                    File f = new File(path + "/" + String.
                        valueOf(sound.get(i)) + "." + type);
		    if (!f.exists() && !f.isDirectory()) 
                    { 
                        yes = false;
                        break;
                    }           
                }
            }   
            if (child != null && yes == true)
            {
                for (int i = 0; i < child.size(); i++)
                {
                    List  childOfChildren =  Arrays.asList(child.get(i));
                    String array = childOfChildren.get(0).toString();
                    String[] numbers = array.split(",");             
                    int [] numbersArray = new int[numbers.length];
                    if (numbers.length > 1)
                    {
                        String[] first = numbers[0].
                            split(Pattern.quote("["));
                        String[] last = numbers[numbers.length - 1].
                            split(Pattern.quote("]"));
                        numbersArray[0] = Integer.parseInt(first[1].trim());
                        numbersArray[numbers.length - 1] = Integer.
                            parseInt(last[0].trim());
                    }
                    else
                    {
                        String[] first = numbers[0].
                            split(Pattern.quote("["));
                        String[] last = first[1].
                            split(Pattern.quote("]"));
                        numbersArray[0] = Integer.
                            parseInt(last[0].trim()); 
                    }
                    for (int k = 1; k < numbers.length - 1; k++)
                    {             
                        numbersArray[k] = Integer.
                            parseInt(numbers[k].trim());                   
                    }            
                    List  childOfChildrenType =  Arrays.
                        asList(childType.get(i));
                    String arrayType = childOfChildrenType.
                        get(0).toString();
                    String[] types = arrayType.split(",");               
                    String [] typeArray = new String[types.length];
                    if (types.length > 1)
                    {
                        String[] firstType = types[0].
                            split(Pattern.quote("["));
                        String[] lastType = types[types.
                            length - 1].split(Pattern.quote("]"));
                        typeArray[0] = firstType[1].trim();
                        typeArray[numbers.length - 1] = lastType[0].
                            trim();
                    }
                    else
                    {
                        String[] firstType = types[0].
                            split(Pattern.quote("["));
                        String[] lastType = firstType[1].
                            split(Pattern.quote("]"));
                        typeArray[0] = lastType[0].trim();
                    }
                    for (int k = 1; k < typeArray.length - 1; k++)
                    {
                        
                        typeArray[k] = types[k].trim();
                        
                    }
                
                    for (int j = 0; j < numbersArray.length; j++)
                    {    
                        String string = typeArray[j];
                        String[] parts = string.split("\\.");
                        String aux = parts[0]; 
                        String type = parts[1]; 
                        File f = new File(path + "/" + String.
                            valueOf(numbersArray[j]) + "." + type);
                        if (!f.exists() && !f.isDirectory()) 
                        { 
                            yes = false;
                            break;
                        }
                    }    
                }
            }
            if (sound != null && yes == true)
            {
                for (int i = 0; i < costume.size(); i++)
                {
                    String string = costumetype.get(i);
                    String[] parts = string.split("\\.");
                    String aux = parts[0]; 
                    String type = parts[1]; 
                    File f = new File(path + "/" + String.
                        valueOf(costume.get(i)) + "." + type);
                    if (!f.exists() && !f.isDirectory()) 
                    { 
                        yes = false;
                        break;
                    }
                }           
            }
            if (sound != null && yes == true)
            {
                String string = penlyertype;
                String[] parts = string.split("\\.");
                String aux = parts[0]; 
                String type = parts[1]; 
                File f = new File(path + "/" + penlayer + "." + type);
                if (!f.exists() && !f.isDirectory()) 
                { 
                    yes = false;                
                }
            }
	}
	catch (Exception e)
	{
            System.out.println(e);
	}        
        return yes;  
    }

    /**
    * unzipFile - This method unzip a file
    * and creates the corresponding folder.
    *
    */

    public static void unzipFile()
    {
        
        List<Path> files = getDirectoryContents
            (inputFileDirectory);       
        for (int i = 0; i < files.size(); i++)
        {
            String fileName = files.get(i)
                .getName(files.get(i).getNameCount()-1).toString();
            String[] parts = fileName.split("\\.");
            String folderName = parts[0]; 
            String type;
            if (parts.length > 1)
                type = parts[1];
            else
                type = "";
            if (type.compareTo("zip") == 0)
            {
                String source = fileup + "/" 
                    + folderName + "." + type;
                String destination = fileup 
                    + "/" + folderName;
                new File(destination).mkdir();
                try 
                {
                    ZipFile zipFile = new 
                        ZipFile(source);
                    zipFile.extractAll(destination);
                } 
                catch (ZipException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }   

}
