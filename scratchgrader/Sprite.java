package scratchgrader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sprite.java
 * Handle diferent operation for each sprite
 * inside the scratch project. 
 * @author Chris Campell
 * @author Eric Cambel
 * @version 3/22/2017
 */
public class Sprite 
{

    private String name;
    private List<Script> scripts;
    
    
    /**
     * Sprite - Constructor for objects of type Sprite.
     * @param name - Name of the sprite object
     * @param scripts - List of scripts.
     */
    public Sprite(String name, List<Script> scripts) 
    {
        this.name = name;
        this.scripts = scripts;
                  
    }

    /**
     * getSpriteName - Get the name of the Sprite.
     *
     * @return String - name of the Sprite
     */
    public String getSpriteName()
    {
        return this.name;
    }
    
    /**
     * getScripts - Get the scripts.
     *
     * @return Object[] - Array of scripts
     */
    public List<Script> getScripts()
    {
        return this.scripts;
    }
    
    /**
     * countScripts - Get the total amount of scripts
     *  inside the Sprite.
     * @return int - total number of scripts
     */
    public int countScripts()
    {
        return this.scripts.size();
    }
    
    /**
     *  lengthScripts - Get a list that contains the 
     * length of each script.
     * @return List<Integer> - A list of integer each integer represent
     * the length of a script.
     */
    
    public List<Integer>  lengthScripts()
    {
        List<Integer> scriptsLenght = new ArrayList<Integer>();
        
        for (int i = 0; i < this.scripts.size(); i++)
        {
            String aux = this.scripts.get(i).getScriptContent().toString();
            
            String[] lines = aux.split("\r\n|\r|\n");
            scriptsLenght.add(lines.length);
        }
        return scriptsLenght;
    }
    
      
    /**
     *  getAllSpritesVaraibles - Get all data variables of
     * all the sprite.
     * @return List<String> - A list of string each string represent
     * a data variable.
     */
    
    public List<DataVariable> getAllScriptVaraibles()
    {
        int count = 0;
        int global = 0;
        List<DataVariable> mockdataVaraibles = new ArrayList<DataVariable>();
        List<DataVariable> dataVaraibles = new ArrayList<DataVariable>();
        DataVariable data = null;
        List<String> auxdataVaraibles = new ArrayList<String>();
        Set<String> auxSet = new HashSet<>();
        for (int i = 0; i < this.scripts.size(); i++)
        {
            dataVaraibles.addAll(scripts.get(i).getDataVaraibles());
            
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
    
    public List<String> getAllScriptcategorys()
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
        
        for (int i = 0; i < this.scripts.size(); i++)
        {
            auxcategoryBlocks = scripts.get(i).getCategoryBlocks();
            
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
