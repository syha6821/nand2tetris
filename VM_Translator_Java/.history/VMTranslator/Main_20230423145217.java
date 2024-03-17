public class Main {
  public static void main(String[] args) {
    String fileName = args[0];
    Parser parser = new Parser(fileName);
    String arg1 = "";


    int arg2 = 0;
    CodeWriter codeWriter = new CodeWriter(fileName);
    while (parser.hasMoreCommands()) {
      parser.advance();
      String commandType = parser.commandType();
      if (commandType != "C_RETURN") {
        arg1 = parser.arg1();
      }
      if (commandType.equals("C_PUSH")
          || commandType.equals("C_POP")
          || commandType.equals("C_FUNCTION")
          || commandType.equals("C_CALL")) {
        arg2 = parser.arg2();
      }
      if (commandType.equals("C_PUSH") || commandType.equals("C_POP")) {
        codeWriter.writePushPop(parser.currentLine.split(" ")[0], arg1, arg2);
      } else {
        codeWriter.writeArithmetic(parser.currentLine.split(" ")[0]);
      }
      System.out.println("Main : advanced");
    }
    codeWriter.close();
    // parser.printFile();
  }
}
