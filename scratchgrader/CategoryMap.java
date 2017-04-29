/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchgrader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author erick
 */
public class CategoryMap{
    
    
    private List<List<String>> categories = new ArrayList<List<String>>();
    private static CategoryMap categoryMap = null;
    
    private void CategoryMap()
    {   
      
         
    }
    
    private void loadMap()
    {
      categories.add(createCategory("Control"));
      categories.add(createCategory("Event"));
      categories.add(createCategory("Looks"));
      categories.add(createCategory("Motion"));
      categories.add(createCategory("Operator"));
      categories.add(createCategory("Pen"));
      categories.add(createCategory("Sensing"));
      categories.add(createCategory("Sound"));
      categories.add(createCategory("DataVariables"));
    }
   
    private List<String> createCategory(String category)
    {    
        List<String> blocks = new ArrayList<String>();
        File file = new File("MapFolder/" + category);
        ScratchGrader grader = new ScratchGrader(file.getAbsolutePath());
        List<Sprite> sprites =  grader.getListOfSprites();
        List<Script> scripts = sprites.get(0).getScripts();
    
        for (int i = 0; i < scripts.size(); i++)
        {
            blocks.add(scripts.get(i).getScriptContent().toString());
        }    
        return blocks;
    }

    public static CategoryMap getInstance()
    {
        if(categoryMap == null){
          categoryMap = new  CategoryMap();
          categoryMap.loadMap();
        }
        
        return categoryMap;
    }
   
   
    protected List<List<String>>  getCategories()
    {
        return categories; 
    }
    
}
