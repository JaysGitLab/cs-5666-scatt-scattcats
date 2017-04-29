package scratchgrader;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Demo.java
 * Demo to run the app.
 *  
 * @author Chris Campell
 * @author Eric Cambel
 * @version 3/22/2017
 */
public class Demo
{

    /**
     * main - main for demo.
     * @param args - main arg
     */
    public static void main(String[] args)
    {
        // Create scratchloader object and take 
        Demo d = new Demo();
        int option = d.makeMenu();
        if (option == 2)
        {
            System.exit(1);
        } 
        else 
        {
            d.gradeScratchDir();
        }
        
    }
    
    /**
     * makeMenu - Menu for demo.
     * @return int - user selection
     */
    public int makeMenu()
    {
        System.out.println("===========================");
        System.out.println("|   Scratch Grader Demo   |");
        System.out.println("===========================");
        System.out.println("| Options:                |");
        System.out.println("|  1. Grade Scratch Dir   |");
        System.out.println("|  2. Exit                |");
        System.out.println("===========================");  
        
        Scanner reader = new Scanner(System.in);
        int n = 0;
        while (true)
        {
            System.out.print("Select option: ");

            if (!reader.hasNextInt())
            {
                System.out.println("Please enter only integers.");
                reader.next();
                continue;
            }
            else
            {
                n = reader.nextInt();
            }
            
            if (n >= 1 && n <= 2)
            {
                break;
            }
            else 
            {
                System.out.println("Please enter a valid option.");
            }
            
        }
        return n;
    }   
    
    /**
     * gradeScratchDir - Grade a folder.
     * 
     */
    public void gradeScratchDir()
    {
        Scanner reader = new Scanner(System.in);
        CategoryMap map = CategoryMap.getInstance();
        System.out.print("Please enter the name of the" 
            + " directory with the scratch files: ");  
        String dir = reader.nextLine();
        ScratchLoader loader = new ScratchLoader(dir);
        // If wrong input, it will throw an exception and crash the program
        // TODO: Have the user type in another directory again.
        List<Path> sb2Contents = loader.
            getFilePathsSB2(loader.getFileInputDir());
        loader.convertToZip(sb2Contents);
        loader.unzipFile();
        List<Path> projects = loader.
            getDirectoryContents(loader.getFileInputDir());
        
        for (int i = 0; i < projects.size(); i++)
        {
            File file = new File(projects.get(i).toString());
            if (file.isDirectory())
            {
                if (loader.checkMediaReferences(file.getAbsolutePath()))
                {
                    ScratchGrader grader = new ScratchGrader(
                            file.getAbsolutePath());
                    List<Sprite> sprites =  new ArrayList<Sprite>();
                    sprites = grader.getListOfSprites();
                    List<DataVariable> data = grader.getAllSpritesVariables(); 
                    System.out.println("Project: " + grader.getProjectName() 
                        + " | " + "Total Scripts: " 
                        + grader.getTotalScriptCount() 
                        + " | " + "Total length of the scripts: " 
                        + grader.getCombinedScriptLength()
                        + " | " + "Total Data Varaibles: " 
                        + data.size());
                    for (int j = 0; j < data.size(); j++)
                    {
                        System.out.println("Variables: " 
                            + data.get(j).getName()  
                            + " | " + "Uses: " 
                            + data.get(j).getUses() 
                            +  " | " 
                            + "Global: " 
                            + data.get(j)
                                .getGlobal().toString());
                    }
                    
                    List<String>  categoryBlocks = 
                        grader.getAllSprintcategorys();
                    
                    for (int j = 0; j < categoryBlocks.size(); j++)
                    {
                        System.out.println("Category & Uses: " 
                            + categoryBlocks.get(j));
                    }
                }
                System.out.println("");
            }
        }
              
    }
   
}




