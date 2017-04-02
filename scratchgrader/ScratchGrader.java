package scratchgrader;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * ScratchGrader.java
 * Analize the scratch project and gives diferent metrics.
 *  
 * @author Chris Campell
 * @author Eric Cambel
 * @version 3/22/2017
 */
public class ScratchGrader 
{
    
    private Path inputFileDirectory;
    private String projectName;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    /**
     * ScratchGrader - Constructor for objects of type ScratchGrader.
     * @param inputFileDirPath -The file path to the project folder 
     *  containing the project.json file to be analyzed.
     */
    public ScratchGrader(String inputFileDirPath) 
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
        this.projectName = inputFileDirectory.
            getName(inputFileDirectory.getNameCount() - 1).toString();        
        File file = new File(inputFileDirectory.toString() + "/project.json");
        List<String> spriteName = JsonPath.
            from(file).get("children.objName");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        for (int i = 0; i < spriteName.size(); i++)
        {
            String name = spriteName.get(i);
            List<String>  aux =  spriteScripts.get(i);
            Object[]  spriteiScripts = aux.toArray(); 
            Sprite sprite = new Sprite(name, spriteiScripts);
            this.sprites.add(sprite);
        }
        
    }  

    /**
     * getProjectName - Get the name of the scratch project.
     * 
     * @return String - name of the project.
     */
    public String getProjectName()
    {
        return this.projectName;
    }
    
    /**
     * getTotalScriptCount - Get the total amount of scripts the
     * project have.
     * @return int - total number of scripts
     */
    public int getTotalScriptCount()
    {
        int count = 0;
        for (int i = 0; i < this.sprites.size(); i++)
        {
            count = count +  this.sprites.get(i).countScripts();
        }
        return count;
    }

    /**
     * getTotalScriptLenght - Get the total lenght between \
     * all the scripts the of the project.
     * @return int - total lenght of all scripts
     */
    public int getTotalScriptLenght()
    {
        int count = 0;
        for (int i = 0; i < this.sprites.size(); i++)
        {
            List<Integer> aux = this.sprites.get(i).lengthScripts();
            for (int j = 0; j < aux.size(); j++)
            {
                count = count + aux.get(j);
            }
        }
        return count;
    }
    
}
