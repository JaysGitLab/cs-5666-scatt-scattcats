/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchgrader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Script.java
 * Handle diferent operation for each Script
 * inside the scratch project. 
 * @author Chris Campell
 * @author Eric Cambel
 * @author Erick Lares
 * @version 3/22/2017
 */
public class Script {
    
    private Object  scriptContent;
    private List<String> categoryBlocks = new ArrayList<String> ();
    private List<DataVariable> dataVariables = new ArrayList<DataVariable> ();


	/**
     * getDataVaraibles - get all the 
     * data variables of the script.
     * @return List<DataVariable> - 
     * Data variables of the script
     * 
     */
    public List<DataVariable> getDataVariables() {
        return dataVariables;
    }

    /**
     * getScriptContent - get the 
     * content of the script.
     * @return Object - script content
     */
    public Object getScriptContent() {
        return scriptContent;
    }

    /**
     * getCategoryBlocks - get the 
     * categories and blocks of the script.
     * @return List<String> - categories and blocks
     */
    public List<String> getCategoryBlocks() {
        return categoryBlocks;
    }

    
    /**
     * Script - Constructor for objects of type Script.
     * @param scriptContent - Content of the script
     * 
     */
    public Script(Object  scriptContent)
    {
        this.scriptContent = scriptContent;
        this.categoryBlocks = fillCateogryBlocks();
        
        List<String> auxtocount = 
        	new ArrayList<String>();
        List<String> auxDataVaraible = 
        	new ArrayList<String>();
        List<String> changevar = 
        	getallDataVariables("changeVar");
        List<String> hidevar = 
        	getallDataVariables("hideVariable");
        List<String> showvar = 
        	getallDataVariables("showVariable");
        List<String> setvar = 
        	getallDataVariables("setVar");
        
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
        for (int j = 0; j < auxDataVaraible.size(); j++)
        {
            for (int i = 0; i < auxtocount.size(); i++)
            {
              if (auxDataVaraible.get(j).compareTo(auxtocount.get(i)) == 0)
              {
               counter = counter + 1;
               auxtocount.remove(i);
               i =  i -1;
              }
            }
           DataVariable data = new DataVariable
           	(auxDataVaraible.get(j),counter,false);
           dataVariables.add(data);
           counter = 0;
        }
    }
    
    /**
     * fillCateogryBlocks - Fill the 
     * categories and blocks of the script.
     * @return List<String> - categories and blocks
     */
    private List<String> fillCateogryBlocks()
    {
        List<String> auxcategoryBlocks = 
        	new ArrayList<String>();
        CategoryMap map = CategoryMap.getInstance();
        List<List<String>> categories = 
        	map.getCategories();
        int count = 0;
        int foundPosition = 0;
        String scriptString = 
        	this.scriptContent.toString();
        
        for (int i = 0; i < categories.size(); i++)
        {
            for (int j = 0; j < categories.get(i).size(); j++)
            {
                foundPosition = scriptString.
                	indexOf(categories.get(i).
                		get(j),foundPosition);
                
                while (foundPosition != -1)
                {
                   count = count + 1; 
                   foundPosition = scriptString.
                   	indexOf(categories.get(i).
                   		get(j),foundPosition + 1);
                }
            }
          
            
            if(i == 0)
                auxcategoryBlocks.
            	add("Control, " + count);
            else if (i == 1)
                auxcategoryBlocks.
            	add("Event, " + count);
            else if (i == 2)
               auxcategoryBlocks.
           		add("Looks, " + count);
            else if (i == 3)
                auxcategoryBlocks.
            	add("Motion, " + count);
            else if (i == 4)
                auxcategoryBlocks.
            	add("Operator, " + count);
            else if (i == 5)
                auxcategoryBlocks.
            	add("Pen, " + count);
            else if (i == 6)
                auxcategoryBlocks.
            	add("Sensing, " + count);
            else if (i == 7)
                auxcategoryBlocks.
            	add("Sound, " + count);
            count = 0;
        }
        
        return auxcategoryBlocks;
    }
    
    
     /**
     *  getDataVariables - Get a list that contains the 
     * data variables of each script.
     * @return List<String> - A list of string each string represent
     * a data variable of a script.
     */
    
    private List<String> getallDataVariables(String word)
    {
        List<String> dataVariables = 
        	new ArrayList<String>();
        Object script = this.scriptContent;
        int wordpos = 0;
        String variable = "";
        
        String scriptsString = 
        	script.toString();
        wordpos = scriptsString.
        	indexOf(word,wordpos);
            
        while (wordpos != -1)
        {
                
        char[] aux = scriptsString.
        	toCharArray();
        while (aux[wordpos] != ',')
        {
            wordpos = wordpos + 1; 
        }
        wordpos = wordpos + 1; 
        while (aux[wordpos] != ',' && aux[wordpos] != ']')
        {
            variable = variable + aux[wordpos];
            wordpos = wordpos + 1; 
        }
        dataVariables.add(variable);
        variable = "";
        wordpos = scriptsString.
        	indexOf(word,wordpos + 1);
        }
            
        
        
       
        
        return dataVariables;
    }
    
}
