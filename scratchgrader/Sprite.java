package scratchgrader;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Sprite.java
 * Handle diferent operation for each sprite
 * inside the scratch project. 
 * @author Chris Campell
 * @author Eric Cambel
 * @version 3/22/2017
 */
public class Sprite {

    private String name;
    private Object[] scripts;
    
    /**
     * Sprite - Constructor for objects of type Sprite.
     * @param name - Name of the sprite object
     * @param scripts - List of scripts.
     */
    public Sprite(String name, Object[] scripts) 
    {
        this.name = name;
        this.scripts = scripts;
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
     * lenght of each script.
     * @return List<Integer> - A list of integer each integer represent
     * the lenght of a script.
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
}