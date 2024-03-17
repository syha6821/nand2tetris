import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
  //Use ArrayList than Array Because Its length has to be dynamic.
  private ArrayList<String> codeList = new ArrayList<>();
  private int currentLineNum = 0;
  public String currentLine = "";
  public String currentCommandType = "";

  // Arithmetic / Logical
  final ArrayList<String> arithmeticCommands =
      new ArrayList<>(Arrays.asList("add", "sub", "neg", "eq", "gt", "lt", "and", "or", "not"));

  // Memory access commands ( push , pop )
  final ArrayList<String> memoryAccessCommands = new ArrayList<>(Arrays.asList("pop", "push"));

  // Branching
  // label, goto, if-goto

  // Function
  // function, call, return

  // Opens the input file/stream and gets ready to parse it.
  // File -> List
  public Parser(File vmCode) {
    try {
      Scanner reader = new Scanner(vmCode);

      while (reader.hasNextLine()) {
        String nextLine = reader.nextLine();
        if (!nextLine.isEmpty() && nextLine.charAt(0) != '/'){
          if(nextLine.contains("/")){
            int slashIndex = nextLine.trim().indexOf("/");
            codeList.add(nextLine.substring(0,slashIndex));
          }else{
            codeList.add(nextLine.trim());
          }
        }
      }

      reader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Are there more commands in the input?
  public boolean hasMoreCommands() {
    boolean hasMoreCommands = (currentLineNum == codeList.size()) ? false : true;
    return hasMoreCommands;
  }

  // Reads the next command from the input and makes it the current command. Should be called only
  // if hasMoreCommands() is true. Initially there is no current command.
  public void advance() {
    currentLine = codeList.get(currentLineNum);
    currentLineNum++;
  }

  // Returns a constant representing the type of the current command. C_ARITHMETIC is returned for
  // all the arithmetic/logical commands.
  public String commandType() {
    String type = "";
    String firstOfCommand = currentLine.split(" ")[0];
    if (arithmeticCommands.contains(firstOfCommand)) {
      type = "C_ARITHMETIC";
    } else if (firstOfCommand.equals("if-goto")){
      type=  "C_IF";
    } else {
      type = "C_" + firstOfCommand.toUpperCase();
    }

    // C_ARITHMETIC
    // C_PUSH
    // C_POP
    // C_LABEL
    // C_GOTO
    // C_IF (if-goto)
    // C_FUNTION
    // C_RETURN
    // C_CALL

    currentCommandType = type;
    return type;
  }

  // Returns the first argument of the current command. In the case of C_ARITHMETIC, the command
  // itself(add, sub, etc.)is returned.
  // Should not be called if the current command is C_RETURN.
  public String arg1() {
    String arg1 = (currentCommandType.equals("C_ARITHMETIC")) ? currentCommandType : currentLine.split(" ")[1];

    return arg1;
  }

  // Returns the second argument of the current command. Should be called only if the current
  // command is C_PUSH, C_POP, C_FUNCTION, or C_CALL.
  public int arg2() {
      String arg2 = "";

      switch (currentCommandType){
        case("C_PUSH"):
        case("C_POP"):
        case("C_FUNCTION"):
        case("C_CALL"):
          arg2 = currentLine.split(" ")[2].trim();
          break;
        default:
          arg2 = "";
          break;
      }
    return Integer.parseInt(arg2);
  }
}
