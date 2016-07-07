import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class DocumentationVerifier {
	
  static Pattern codeBlock = Pattern.compile("\\s*`{3}\\s*");
  static Pattern action = Pattern.compile("\\[(.+)\\]\\:\\s+#{1}\\s+\\((.+)\\)\\s*");
  
	public static void main(String[] args) {
	  String currentFileName = "";
	  if (args.length == 0) {
		  currentFileName = "./test.txt";
		}
		  
		prereq();
			
		try {
			File currentFile = new File(currentFileName);
			LinkedList<ActionSet> actions = getActionsFromFile(currentFile);
			for (ActionSet actionSet : actions) {
			  System.out.println(actionSet);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file: " + currentFileName );
		}
	}
	
	/**
	* A series of steps to be taken before verifying each file.
	*/
	public static void prereq() {
		//Download latest release, etc
	  // This may be a script that the user writes and is executed
	}
	
	public static LinkedList<ActionSet> getActionsFromFile(File file) throws FileNotFoundException {
	  Scanner input = new Scanner(file);
    String nextLine;
    Matcher verificationMatcher = action.matcher("");
    Matcher codeBlockMatcher = codeBlock.matcher("");
    LinkedList<ActionSet> actions = new LinkedList<ActionSet>();
	  
    while(input.hasNextLine()) {
      nextLine = input.nextLine();
      verificationMatcher.reset(nextLine);
      if (verificationMatcher.matches()) {
        int actionsFound = 1;
        ActionSet as = new ActionSet(verificationMatcher.group(1));
        as.addActionOptionsFromString(verificationMatcher.group(2));
        
        while (input.hasNextLine()) { //Check for subsequent action statements 
          nextLine = input.nextLine();
          verificationMatcher.reset(nextLine);
          codeBlockMatcher.reset(nextLine);
          if (verificationMatcher.matches()) { // Found another action
            actionsFound++;
            
          } else if (codeBlockMatcher.matches()) { // Found a code block, which we will pull commands from
            while (input.hasNextLine()) {
              break;
            }
          } else {
            break; //Stop checking for action or 
          }
        }
        
        
        actions.add(as);
      }
    } 
    input.close();
    
    return actions;
	}
}

class ActionSet {
  private String action;  //What to do (execute a command, stop all running commands)
  private LinkedList<String> actionOptions; //Options for the action (timeout, etc)
  private LinkedList<String> commands; //The command to be executed
  
  public ActionSet(String action) {
    this.action = action;
    actionOptions = new LinkedList<String>();
    commands = new LinkedList<String>();
  }
  
  public ActionSet(String action, LinkedList<String> actionOptions) {
    this(action, actionOptions, new LinkedList<String>());
  }
  
  public ActionSet(String action, LinkedList<String> actionOptions, LinkedList<String> commands) {
    this.action = action;
    this.actionOptions = actionOptions;
    this.commands = commands;
  }
  
  public String getAction() {
    return action;
  }
  
  public LinkedList<String> getActionOptions() {
    return actionOptions;
  }
  
  public  LinkedList<String> getCommands() {
    return commands;
  }
  
  public void addActionOptionsFromString(String actionOptionsString) {
    String[] options = actionOptionsString.split(" ");
    for (String option : options) {
      if (option.length() > 0) {
        actionOptions.add(option);
      } 
    }
  }

  //TODO remove stray space at end
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[" + action + "]: # (");
    for (String option: actionOptions) {
      sb.append(option + " ");
    }
    sb.append(")");
    return sb.toString();
  }
}
