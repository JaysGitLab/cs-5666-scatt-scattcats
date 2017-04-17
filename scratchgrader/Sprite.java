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
    private Object[] scripts;
    private List<DataVariable>  dataVaraibles = new ArrayList<DataVariable>();
    
    /**
     * Sprite - Constructor for objects of type Sprite.
     * @param name - Name of the sprite object
     * @param scripts - List of scripts.
     */
    public Sprite(String name, Object[] scripts) 
    {
        this.name = name;
        this.scripts = scripts;
        List<String> auxtocount = new ArrayList<String>();
        List<String> auxDataVaraible = new ArrayList<String>();
        List<String> changevar = getDataVariables("changeVar");
        List<String> hidevar = getDataVariables("hideVariable");
        List<String> showvar = getDataVariables("showVariable");
        List<String> setvar = getDataVariables("setVar");
        
        Set<String> auxSet = new HashSet<>();
        auxSet.addAll(changevar); 
        auxSet.addAll(hidevar);
        auxSet.addAll(showvar);
        auxSet.addAll(setvar);
        auxDataVaraible.addAll(auxSet);
        
        
        auxtocount.addAll(changevar); 
        auxtocount.addAll(hidevar);
        auxtocount.addAll(showvar);
        auxtocount.addAll(setvar);
        
        int counter = 0;
        for(int j = 0; j < auxDataVaraible.size(); j++)
        {
            for(int i = 0; i < auxtocount.size(); i++)
            {
              if (auxDataVaraible.get(j).compareTo(auxtocount.get(i)) == 0)
              {
               counter = counter + 1;
               auxtocount.remove(i);
              }
            }
           DataVariable data = new DataVariable(auxDataVaraible.get(j),counter,false);
           dataVaraibles.add(data);
           counter = 0;
        }
         
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
    public Object[] getScripts()
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
        return this.scripts.length;
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
        
        for (int i = 0; i < this.scripts.length; i++)
        {
            String aux = this.scripts[i].toString();
            scriptsLenght.add(aux.length());
        }
        return scriptsLenght;
    }
    
    
    /**
     *  getAllVaraibles - Get all data variables of
     * the sprite.
     * @return List<String> - A list of string each string represent
     * a data variable of the sprite.
     */
    public List<DataVariable> getAllVaraibles()
    {
        return this.dataVaraibles;
    }
    
    /**
     *  getDataVariables - Get a list that contains the 
     * data variables of each script.
     * @return List<String> - A list of string each string represent
     * a data variable of a script.
     */
    private List<String> getDataVariables(String word)
    {
        List<String> dataVaraibles = new ArrayList<String>();
        Object[] scripts = this.scripts;
        int wordpos = 0;
        String variable = "";
        for(int i = 0; i < scripts.length; i++)
        {
            String scriptsString = scripts[i].toString();
            wordpos = scriptsString.indexOf(word,wordpos);
            while(wordpos != -1)
            {
                
                char[] aux = scriptsString.toCharArray();
                while(aux[wordpos] != ',')
                {
                    wordpos = wordpos + 1; 
                }
                wordpos = wordpos + 1; 
                while(aux[wordpos] != ',' && aux[wordpos] != ']')
                {
                    variable = variable + aux[wordpos];
                    wordpos = wordpos + 1; 
                }
                dataVaraibles.add(variable);
                variable = "";
                wordpos = scriptsString.indexOf(word,wordpos + 1);
            }
            
        }
        
       
        
        return dataVaraibles;
    }
}
