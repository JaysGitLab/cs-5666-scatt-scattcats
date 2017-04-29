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
 * @author Erick Lares
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
    
    public List<DataVariable> getAllScriptVariables()
    {
        int count = 0;
        int global = 0;
        List<DataVariable> mockdataVariables = 
        	new ArrayList<DataVariable>();
        List<DataVariable> dataVariables = 
        	new ArrayList<DataVariable>();
        DataVariable data = null;
        List<String> auxdataVariables = 
        	new ArrayList<String>();
        Set<String> auxSet = new HashSet<>();
        for (int i = 0; i < this.scripts.size(); i++)
        {
            dataVariables.
            	addAll(scripts.get(i).
            			getDataVariables());
            
        }
        
        for (int j = 0; j < dataVariables.size(); j++)
        {
            auxSet.add(dataVariables.
            		get(j).getName());
            
        }
        
        auxdataVariables.addAll(auxSet);
        
        for (int i = 0; i < auxdataVariables.size(); i++)
        {
            data = new DataVariable
            	(auxdataVariables.get(i),0,false);
            mockdataVariables.add(data);
        }
        
        for (int i = 0; i < mockdataVariables.size(); i++)
        {
            for (int j = 0; j < dataVariables.size(); j++)
            {
               if (mockdataVariables.get(i).getName().compareTo(dataVariables.get(j).getName()) == 0)
               {
                   global = global + 1;
                   data = mockdataVariables.get(i);
                   data.setUses(data.getUses() + 
                   		dataVariables.get(j).getUses());
                   if (global > 1)
                   data.setGlobal(true);
                   mockdataVariables.set(i, data);
                   
               }
            }
            global = 0;
        }
        
        return mockdataVariables;
    }
    
    /**
     *  getDataVariables - Get a list that contains the 
     * data variables of each script.
     * @return List<String> - A list of string each string represent
     * a data variable of a script.
     */
    public List<String> getAllScriptcategorys()
    {
        List<String> categoryBlocks = 
        	new ArrayList<String>();
        List<String> auxcategoryBlocks = 
        	new ArrayList<String>();
      
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
            auxcategoryBlocks = scripts.
            	get(i).getCategoryBlocks();
            
            for (int j = 0; j < auxcategoryBlocks.size(); j++)
            {
                String auxTogether[] = auxcategoryBlocks.
                	get(j).split(",");
                int auxCount = Integer.
                	valueOf(auxTogether[1].trim());
                String together[] = categoryBlocks.
                	get(j).split(",");
                int Count = Integer.
                	valueOf(together[1].trim());
                categoryBlocks.set(j, together[0] + 
                		", "+ (auxCount + Count));
                
            }
        }
        return categoryBlocks;
    }
}
