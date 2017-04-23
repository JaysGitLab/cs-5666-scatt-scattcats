package scratchgrader;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private List<String> globalVariables;
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
        globalVariables = JsonPath.
            from(file).get("variables");
        List<List<String>>  spriteScripts = 
            JsonPath.from(file).get("children.scripts");
        spriteName.removeAll(Collections.singleton(null));
        spriteScripts.removeAll(Collections.singleton(null));
        for (int i = 0; i < spriteName.size(); i++)
        {
            String name = spriteName.get(i);
            List<String>  aux =  spriteScripts.get(i);
            Object[]  spriteiScripts = aux.toArray(); 
            List <Script> scripts = new ArrayList<Script>();
            for (int j = 0; j < spriteiScripts.length; j++)
            {
                Script script = new Script(spriteiScripts[j]);
                scripts.add(script);
            }
            Sprite sprite = new Sprite(name, scripts);
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
    
    /**
     * getListOfSprites - Get the total list of \
     * sprites.
     * @return List<Sprite> - List of all sprites
     */
    public List<Sprite> getListOfSprites()
    {
        return this.sprites;
    }
    
    /**
     *  getAllSpritesVaraibles - Get all data variables of
     * all the sprite.
     * @return List<String> - A list of string each string represent
     * a data variable.
     */
    
    public List<DataVariable> getAllSpritesVaraibles()
    {
        int count = 0;
        int global = 0;
        List<DataVariable> mockdataVaraibles = new ArrayList<DataVariable>();
        List<DataVariable> dataVaraibles = new ArrayList<DataVariable>();
        DataVariable data = null;
        List<String> auxdataVaraibles = new ArrayList<String>();
        Set<String> auxSet = new HashSet<>();
        for (int i = 0; i < this.sprites.size(); i++)
        {
            dataVaraibles.addAll(sprites.get(i).getAllScriptVaraibles());
            
        }
        
        for (int j = 0; j < dataVaraibles.size(); j++)
        {
            auxSet.add(dataVaraibles.get(j).getName());
            
        }
        
        auxdataVaraibles.addAll(auxSet);
        
        for (int i = 0; i < auxdataVaraibles.size(); i++)
        {
            data = new DataVariable(auxdataVaraibles.get(i),0,false);
            mockdataVaraibles.add(data);
        }
        
        for (int i = 0; i < mockdataVaraibles.size(); i++)
        {
            for (int j = 0; j < dataVaraibles.size(); j++)
            {
               if (mockdataVaraibles.get(i).getName().compareTo(dataVaraibles.get(j).getName()) == 0)
               {
                   global = global + 1;
                   data = mockdataVaraibles.get(i);
                   data.setUses(data.getUses() + dataVaraibles.get(j).getUses());
                   if (global > 1)
                   data.setGlobal(true);
                   mockdataVaraibles.set(i, data);
                   
               }
            }
            global = 0;
        }
        
        return mockdataVaraibles;
    }
    
    public List<String> getAllSprintcategorys()
    {
        List<String> categoryBlocks = new ArrayList<String>();
        List<String> auxcategoryBlocks = new ArrayList<String>();
      
        categoryBlocks.add("Control, " + 0);         
        categoryBlocks.add("Event, " + 0);           
        categoryBlocks.add("Looks, " + 0);           
        categoryBlocks.add("Motion, " + 0);          
        categoryBlocks.add("Operator, " + 0);           
        categoryBlocks.add("Pen, " + 0);           
        categoryBlocks.add("Sensing, " + 0);  
        categoryBlocks.add("Sound, " + 0);
        
        for (int i = 0; i < this.sprites.size(); i++)
        {
            
            
            auxcategoryBlocks = sprites.get(i).getAllScriptcategorys();
            
            for (int j = 0; j < auxcategoryBlocks.size(); j++)
            {
                String auxTogether[] = auxcategoryBlocks.get(j).split(",");
                int auxCount = Integer.valueOf(auxTogether[1].trim());
                String together[] = categoryBlocks.get(j).split(",");
                int Count = Integer.valueOf(together[1].trim());
                categoryBlocks.set(j, together[0] + ", "+ (auxCount + Count));
                
            }
        }
        
        
        return categoryBlocks;
    }
    
}
