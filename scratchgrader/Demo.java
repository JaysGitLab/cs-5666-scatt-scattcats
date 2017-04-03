package scratchgrader;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Demo
{
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
        while(true)
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
    
    public void gradeScratchDir()
    {
        Scanner reader = new Scanner(System.in);
       
        System.out.print("Please enter the name of the" +
            " directory with the scratch files: ");  
        String dir = reader.nextLine();
        
        Path inputFileDir = Paths.get(dir);
        ScratchLoader loader = new ScratchLoader(inputFileDir.toString());
        
        // If wrong input, it will throw an exception and crash the program
        // TODO: Have the user type in another directory again.
        List<Path> sb2Contents = loader.getFilePathsSB2(inputFileDir);
        loader.convertToZip(sb2Contents);
        loader.unzipFile();
        List<Path> projects = loader.getDirectoryContents(loader.getFileInputDir());
        
        for (int i = 0; i < projects.size(); i++)
        {
            File file = new File(projects.get(i).toString());
            if (file.isDirectory())
            {
                if(loader.checkMediaReferences(file.getAbsolutePath()))
                {
                   ScratchGrader grader = new ScratchGrader(file.getAbsolutePath());
                   System.out.println("Project: " + grader.getProjectName() + " | " + "Total Scripts: " + grader.getTotalScriptCount() + " | " + "Total length of the scripts: " + grader.getTotalScriptLenght());
                }
            }
        }
        
    }
}