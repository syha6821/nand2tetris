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

  public String abbreviations(String segment) {
    String result = "";
      if (segment.equals("local")){
        result = "LCL";
      }else if(segment.equals("argument")){
        result = "ARG";
      }else if(segment.equals("this")){
        result = "THIS";
      }else if(segment.equals("that")){
        result = "THAT";
      }
      return result;
  }

  public CodeWriter(String fileName) {
    try {
      staticFileName = fileName.split("\\.")[0];
      fileWriter = new FileWriter(staticFileName + ".asm");
      bufferedWriter = new BufferedWriter(fileWriter);
      // @/Users/song-yunha/nand2tetris/projects/07/MemoryAccess/StaticTest/StaticTest
      int staticFileNameSplitedLen = staticFileName.split("/").length;
      staticFileName = staticFileName.split("/")[staticFileNameSplitedLen - 1];

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
      if (command.equals("add")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M+D;\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("sub")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M-D;\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("neg")) {
        asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "M=-M;\n" + "@SP\n" + "M=M+1;\n";
      } else if (command.equals("eq")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "D=D+1;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ")\n"
                + "@SP\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + "\n"
                +"D=D-1;\n"
                + "D;JEQ\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("gt")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ")\n"
                + "@SP\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + "\n"
                + "D=-D;\n"
                + "D;JGT\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("lt")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=D-M;\n"
                + "M=1;\n"
                + "(TRUE"
                + booleanCounter()
                + ")\n"
                + "@SP\n"
                + "A=M;\n"
                + "M=M-1;\n"
                + "@TRUE"
                + booleanCounter()
                + "\n"
                + "D=-D;\n"
                + "D;JLT\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("and")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M&D;\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("or")) {
        asmCode =
            "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "M=M|D;\n"
                + "@SP\n"
                + "M=M+1;\n";
      } else if (command.equals("not")) {
        asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "M=!M;\n" + "@SP\n" + "M=M+1;\n";
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
      bufferedWriter.write("//" + command + " " + segment + " " + index + "\n");
      if (command.equals("push")) {
        if (segment.equals("local")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@LCL\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("argument")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@ARG\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("this")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@THIS\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("that")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@THAT\n"
                  + "A=M+D;\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("constant")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("static")) {
          asmCode =
              "@"
                  + staticFileName
                  + "."
                  + Integer.toString(index)
                  + "\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("pointer")) {
          String pointerSegment = "";
          if (index == 0) {
            pointerSegment = "THIS";
          } else if (index == 1) {
            pointerSegment = "THAT";
          }
          asmCode =
              "@"
                  + pointerSegment
                  + "\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        } else if (segment.equals("temp")) {
          asmCode =
              "@"
                  + Integer.toString(index)
                  + "\n"
                  + "D=A;\n"
                  + "@5\n"
                  + "A=A+D;\n"
                  + "D=M;\n"
                  + "@SP\n"
                  + "M=M+1;\n"
                  + "A=M-1;\n"
                  + "M=D;\n";
        }
      }
      if (command.equals("pop")) {
        if (segment.equals("local")
            || segment.equals("argument")
            || segment.equals("this")
            || segment.equals("that")) {
          asmCode =
              "@"
                  + abbreviations(segment)
                  + "\n"
                  + "D=M;\n"
                  + "@"
                  + index
                  + "\n"
                  + "D=A+D;\n"
                  + "@tmpforpop\n"
                  + "M=D;\n"
                  + "@SP\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "D=M;\n"
                  + "@tmpforpop\n"
                  + "A=M;\n"
                  + "M=D;\n";
        } else if (segment.equals("static")) {
          asmCode =
              "@SP\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "D=M;\n"
                  + "@"
                  + staticFileName
                  + "."
                  + Integer.toString(index)
                      + "\n"
                  + "M=D;\n";

        } else if (segment.equals("pointer")) {
          String pointerSegment = "";
          if (index == 0) {
            pointerSegment = "THIS";
          } else if (index == 1) {
            pointerSegment = "THAT";
          }
          asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "D=M;\n" + "@" + pointerSegment +"\n" + "M=D;\n";
        } else if (segment.equals("temp")) {
          asmCode =
              "@SP\n"
                  + "M=M-1;\n"
                  + "A=M;\n"
                  + "D=M;\n"
                  + "@"
                  + Integer.toString(5 + index)
                  + "\n"
                  + "M=D;\n";
        }
      }
      System.out.println("CodeWriter " + asmCode);
      bufferedWriter.write(asmCode);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Close the output file.
  public void close() {
      String end = "(END)\n" + "@END\n" + "0;JMP\n" ;
    try {
      bufferedWriter.write(end);
      //      fileWriter.close();
      bufferedWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
