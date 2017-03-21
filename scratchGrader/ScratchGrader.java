/**
*The ScratchGrader class provides 
*a user the ability to grade
*scratch files.
*
*@author Erick Lares
*@author Eric Cambel
*@author Chris Campell
*
*@version Mar 21, 2017
 */
public class ScratchGrader
{
	/**
	* Empty constructor that does nothing at
	* this point in time.
	*/
	public ScratchGrader()
	{
		
	}
	
	/**
	* Main method that will help aid in testing
	* and setting up the files.
	* @param args is an array of arguments.
	*/
	public static void main(String[] args)
	{
		ScratchGrader grader = new ScratchGrader();
		
		// If the args array is empty, exit the program.
		if (args.length == 0)
		{
			System.out.println("Please enter a file on the command line.");
			System.exit(0);
		}
		
		// If multiple files are in the argument array, loop through them.
		for(int i = 0; i < args.length; i++)
		{
			// Determines whether the file name has a .sb2 file. 
			// DOES NOT CHECK IF IT IS A REAL FILE
			if(grader.deterScratch(args[i]))
			{
				System.out.println(args[i] + " is a .sb2 file, continue...");
			} else {
				System.out.println(args[i] + " is not a .sb2 file, please check again.");
				System.exit(0);
			}		
		}
	}
	
	/**
	*	Method to aid in determining whether a file from
	*   The program is a .sb2 file extension.
	*	@param scratchFile string.
	* 	@return boolean the analysis of the .sb2 file.
	*/
	public boolean deterScratch(String scratchFile)
	{
		// If the file name is less than 5, then it isn't a valid file
		// since .sb2 is already four characters long.
		if (scratchFile.length() < 5)
		{
			return false;
		} 
		
		// Check to see if the file name equals .sb2
		if (scratchFile.substring(scratchFile.length() - 4).equals(".sb2")) 
		{
			return true;
		}
		
		return false;
	}
}