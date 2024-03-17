import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Main {

  private enum FileType{
    FILE,
    DIRECTORY
  }
  private static void writeCode(Parser parser,CodeWriter codeWriter,String commandType, String arg1, int arg2){
    switch (commandType){
      case "C_PUSH":
      case "C_POP":
        codeWriter.writePushPop(parser.currentLine.split(" ")[0], arg1, arg2);
        break;
      case "C_IF":
        codeWriter.writeIf(arg1);
        break;
      case "C_LABEL":
        codeWriter.writeLabel(arg1);
        break;
      case "C_GOTO":
        codeWriter.writeGoto(arg1);
        break;
      case "C_FUNCTION":
        codeWriter.writeFunction(arg1,arg2);
        break;
      case "C_RETURN":
        codeWriter.writeReturn();
        break;
      case "C_CALL":
        codeWriter.writeCall(arg1, arg2);
        break;
      default:
        codeWriter.writeArithmetic(parser.currentLine.split(" ")[0]);
        break;
    }
  }
  private static String arg1(Parser parser, String commandType){
    return (!commandType.equals("C_RETURN")) ? parser.arg1() : "";
  }
  private static int arg2(Parser parser, String commandType){
    switch (commandType){
      case "C_PUSH":
      case "C_POP":
      case "C_FUNCTION":
      case "C_CALL":
        return parser.arg2();
      default:
        return 0;
    }
  }
  private static void writeBootStrapCode(File file){
    //get File to Write
    String outputFileNameWithPath = "";

    if(file.isFile()){
      String parentDirName = file.getParentFile().getName();
      outputFileNameWithPath = file.getParentFile().getAbsolutePath() + "/" + parentDirName + ".asm";
    }else if(file.isDirectory()){
      outputFileNameWithPath = file.getAbsolutePath() + "/" + file.getName() + ".asm";
    }

    //Informs the codeWriter that the transition of new VM file has started.
    CodeWriter.setFileName(outputFileNameWithPath);
    File outputAsmFile = new File(outputFileNameWithPath);
    CodeWriter codeWriter = new CodeWriter(outputAsmFile,file.getName());

    codeWriter.writeInit();

    codeWriter.close();
  }
  private static void translate(File file){
    //Ready to parse
    Parser parser = new Parser(file);
    String outputFileNameWithPath = "";

    //get File to Write
    if(file.isFile()){
      String parentDirName = file.getParentFile().getName();
      outputFileNameWithPath = file.getParentFile().getAbsolutePath() + "/" + parentDirName + ".asm";
    }else if(file.isDirectory()){
      outputFileNameWithPath = file.getAbsolutePath() + "/" + file.getName() + ".asm";
    }

    //Informs the codeWriter that the transition of new VM file has started.
    CodeWriter.setFileName(outputFileNameWithPath);
    File outputAsmFile = new File(outputFileNameWithPath);
    CodeWriter codeWriter = new CodeWriter(outputAsmFile,file.getName());

    //Write while parser has more commands
    while (parser.hasMoreCommands()) {
      //advance
      parser.advance();
      //get commandType
      String commandType = parser.commandType();
      //get arg1
      String arg1 = arg1(parser, commandType);
      //get arg2
      int arg2 = arg2(parser, commandType);
      //write
      //codeWriter.writeInit();
      writeCode(parser,codeWriter,commandType,arg1,arg2);

    }
    codeWriter.close();
  }
  private static void removeExistingFile(File file){
    String outputFileNameWithPath = "";
    if(file.isFile()){
      String parentDirName = file.getParentFile().getName();
      outputFileNameWithPath = file.getParentFile().getAbsolutePath() + "/" + parentDirName + ".asm";
    }else if(file.isDirectory()){
      outputFileNameWithPath = file.getAbsolutePath() + "/" + file.getName() + ".asm";
    }
    File existingFile = new File(outputFileNameWithPath);
    existingFile.delete();
  }
  private static void translateSysVM(File[] fileList){
    for (File file : fileList){
      if(file.getName().equals("Sys.vm")){
        translate(file);
        break;
      }
    }
  }

  public static void main(String[] args) {
    File inputFile = new File(args[0]);
    FileType fileType;

    //reset the state of output file
    removeExistingFile(inputFile);

    //write bootstrap code
    writeBootStrapCode(inputFile);

    //write codes into a file
    if (inputFile.isFile()){
      System.out.println("This is a file");
      fileType = FileType.FILE;
      translate(inputFile);
    }else if(inputFile.isDirectory()){
      System.out.println("This is a directory");
      fileType = FileType.DIRECTORY;
      File[] fileList = inputFile.listFiles();
      translateSysVM(fileList);
      Stream<File> fileStream = Arrays.stream(fileList);
      fileStream.filter(file -> file.getName().contains(".vm")).filter(file -> !file.getName().equals("Sys.vm")).forEach(file -> translate(file));
    }

  }
}