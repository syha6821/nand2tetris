import java.io.*;

public class CodeWriter {
  private FileWriter fileWriter;
  private BufferedWriter bufferedWriter;
  private int booleanCount = 2;
  private String staticFileName;
  private String asmCode;
  // Opens the output file/stream and gets ready to write into it.
  public String booleanCounter() {
    int preResult = booleanCount / 2;
    booleanCount++;

    String result = Integer.toString(preResult);

    return result;
  }

  public CodeWriter(String fileName) {
    try {
      staticFileName = fileName.split(".")[0];
      fileWriter = new FileWriter(fileName);
      bufferedWriter = new BufferedWriter(fileWriter);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Writes to the output file the assembly code that implements the given arithmetic command.
  public void writeArithmetic(String command) {
    try {
      bufferedWriter.write("//" + command + "\n");
      // add, sub, neg, eq, gt, lt, and, or, not
      if (command == "add") {
        asmCode =
            "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M+D;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "sub") {
        asmCode =
            "@sp\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M-D;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "neg") {
        asmCode = "@sp;\n" + "M=M-1;\n" + "A=M;\n" + "M=-M;\n" + "@sp;\n" + "M=M+1;\n";
      } else if (command == "eq") {
        asmCode =
            "@sp\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ");\n"
                + "@sp;\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + ";\n"
                + "D;JEQ;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "gt") {
        asmCode =
            "@sp\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ");\n"
                + "@sp;\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + ";\n"
                + "D;JGT;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "lt") {
        asmCode =
            "@sp\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ");\n"
                + "@sp;\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + ";\n"
                + "D;JLT;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "and") {
        asmCode =
            "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M&D;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "or") {
        asmCode =
            "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@sp;\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M|D;\n"
                + "@sp;\n"
                + "M=M+1;\n";
      } else if (command == "not") {
        asmCode = "@sp;\n" + "M=M-1;\n" + "A=M;\n" + "M=!M;\n" + "@sp;\n" + "M=M+1;\n";
      }
      bufferedWriter.write(asmCode);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Writes to the output file the assembly code that implements the given command, where command is
  // either C_PUSH or C_POP.
  public void writePushPop(String command, String segment, int index) {
    try {
      bufferedWriter.write("//" + command + "\n");
      if (command == "C_PUSH") {
        if (segment == "local") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@local;\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "argument") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@argument;\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "this") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@this;\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "that") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@that;\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "constant") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@sp;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "static") {
          asmCode =
              "@"
                  + staticFileName
                  + "."
                  + Integer.toString(index)
                  + ";\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment == "pointer") {
            String pointerSegment = "";
            if (index == 0){
                pointerSegment = "this";
            }else if (index == 1){
                pointerSegment = "that";
            }
          asmCode = "@"+pointerSegment+";\n" + "D=M;\n" + "@sp;\n" + "M=M+1;\n" + "A=M-1;\n" + "M=D;\n";
        } else if (segment == "temp") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "D=A;\n"
                  + "@5;\n"
                  + "A=A+D;\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
      } else if (command == "C_POP") {
        if (segment == "local") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "@local;\n"
                  + "D=D+M;\n"
                  + "A=D;\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "M=D;\n";
        } else if (segment == "argument") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "@argument;\n"
                  + "D=D+M;\n"
                  + "A=D;\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "M=D;\n";
        } else if (segment == "this") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "@this;\n"
                  + "D=D+M;\n"
                  + "A=D;\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "M=D;\n";
        } else if (segment == "that") {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + ";\n"
                  + "@that;\n"
                  + "D=D+M;\n"
                  + "A=D;\n"
                  + "D=M;\n"
                  + "@sp;\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "M=D;\n";
        } else if (segment == "static") {
          asmCode =
              "@sp;\n"
              +"M=M-1;\n"
              +"A=M;\n"
              +"D=M;\n"
              +"@"
                  + staticFileName
                  + "."
                  + Integer.toString(index)
            +"M=D;\n";
        }

        } else if (segment == "pointer") {

        } else if (segment == "temp") {

        }
        bufferedWriter.write(asmCode);
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Close the output file.
  public void close() {
    try {
      fileWriter.close();
      bufferedWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
