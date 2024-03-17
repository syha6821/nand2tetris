import java.util.Map;
import java.util.HashMap;

class AsmCode {

    private static int trueLabelCount = 2;
    private static HashMap<String,Integer> returnCounter = new HashMap<>();
    private static int returnPerFunctionCounter(String functionName){
        try{
            int count = returnCounter.get(functionName) + 1;
            returnCounter.put(functionName, count);
            return count/2;
        }catch (NullPointerException e){
            returnCounter.put(functionName,2);
            return 1;
        }
    }
    private static final Map<String, String> abbr = Map.of("local", "LCL", "argument", "ARG", "this", "THIS", "that", "THAT");

    private static String trueLabelCounter() {
        int preResult = trueLabelCount / 2;
        trueLabelCount++;

        String result = Integer.toString(preResult);

        return result;
    }

    public static String bootStrapCode(String fileName) {
        //SP=256
        //Call Sys.init
        String bootStrapCode =
                // SP = 256
                "@256\n"
                        + "D=A;\n"
                        + "@0\n"
                        + "M=D;\n"
                        // LCL = -1
                        + "@1\n"
                        + "M=-A;\n"
                        // ARG = -2
                        + "@2\n"
                        + "M=-A;\n"
                        // THIS = -3
                        + "@3\n"
                        + "M=-A;\n"
                        // THAT = -4
                        + "@4\n"
                        + "M=-A;\n"
                        // Call sys.init()
                        + AsmCode.call(fileName,"Sys.init",0);
        return bootStrapCode;
    }

    public static String arithmetic(String vmCode) {
        String asmCode = "";
        // add, sub, neg, eq, gt, lt, and, or, not
        switch (vmCode) {
            case "add":
                asmCode = "@SP\n"
                        + "M=M-1;\n"
                        + "A=M;\n"
                        + "D=M;\n"
                        + "@SP\n"
                        + "M=M-1;\n"
                        + "A=M;\n"
                        + "M=M+D;\n"
                        + "@SP\n"
                        + "M=M+1;\n";
                break;
            case "sub":
                asmCode = "@SP\n"
                        + "M=M-1;\n"
                        + "A=M;\n"
                        + "D=M;\n"
                        + "@SP\n"
                        + "M=M-1;\n"
                        + "A=M;\n"
                        + "M=M-D;\n"
                        + "@SP\n"
                        + "M=M+1;\n";
                break;
            case "neg":
                asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "M=-M;\n" + "@SP\n" + "M=M+1;\n";
                break;
            case "eq":
                asmCode = "@SP\n"
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
                        + trueLabelCounter()
                        + ")\n"
                        + "@SP\n"
                        + "A=M;\n"
                        + "M=M-1;\n"
                        + "@TRUE"
                        + trueLabelCounter()
                        + "\n"
                        + "D=D-1;\n"
                        + "D;JEQ\n"
                        + "@SP\n"
                        + "M=M+1;\n";
                break;
            case "gt":
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
                                + trueLabelCounter()
                                + ")\n"
                                + "@SP\n"
                                + "A=M;\n"
                                + "M=M-1;\n"
                                + "@TRUE"
                                + trueLabelCounter()
                                + "\n"
                                + "D=-D;\n"
                                + "D;JGT\n"
                                + "@SP\n"
                                + "M=M+1;\n";
                break;
            case "lt":
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
                                + trueLabelCounter()
                                + ")\n"
                                + "@SP\n"
                                + "A=M;\n"
                                + "M=M-1;\n"
                                + "@TRUE"
                                + trueLabelCounter()
                                + "\n"
                                + "D=-D;\n"
                                + "D;JLT\n"
                                + "@SP\n"
                                + "M=M+1;\n";
                break;
            case "and":
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
                break;
            case "or":
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
                break;
            case "not":
                asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "M=!M;\n" + "@SP\n" + "M=M+1;\n";
                break;
            default:

        }
        return asmCode;
    }

    //fileName is where to write
    //currentReadingFile is the file name which now codeWriter is reading.
    public static String pushPop(String command, String segment, int index, String fileName, String currentReadingFile) {
        String asmCode = "";

        if (command.equals("push")) {
            switch (segment) {
                case "local":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@LCL\n"
                                    + "A=M+D;\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "argument":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@ARG\n"
                                    + "A=M+D;\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "this":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@THIS\n"
                                    + "A=M+D;\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "that":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@THAT\n"
                                    + "A=M+D;\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "constant":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "static":
                    asmCode =
                            "@"
                                    + currentReadingFile
                                    + "."
                                    + index
                                    + "\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
                case "pointer":
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
                    break;
                case "temp":
                    asmCode =
                            "@"
                                    + index
                                    + "\n"
                                    + "D=A;\n"
                                    + "@5\n"
                                    + "A=A+D;\n"
                                    + "D=M;\n"
                                    + "@SP\n"
                                    + "M=M+1;\n"
                                    + "A=M-1;\n"
                                    + "M=D;\n";
                    break;
            }
        }
        if (command.equals("pop")) {
            switch (segment) {
                case "local":
                case "argument":
                case "this":
                case "that":
                    asmCode =
                            "@"
                                    + abbr.get(segment)
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
                    break;
                case "static":
                    asmCode =
                            "@SP\n"
                                    + "M=M-1;\n"
                                    + "A=M;\n"
                                    + "D=M;\n"
                                    + "@"
                                    + currentReadingFile
                                    + "."
                                    + index
                                    + "\n"
                                    + "M=D;\n";
                    break;
                case "pointer":
                    String pointerSegment = "";
                    if (index == 0) {
                        pointerSegment = "THIS";
                    } else if (index == 1) {
                        pointerSegment = "THAT";
                    }
                    asmCode = "@SP\n" + "M=M-1;\n" + "A=M;\n" + "D=M;\n" + "@" + pointerSegment + "\n" + "M=D;\n";
                    break;
                case "temp":
                    asmCode =
                            "@SP\n"
                                    + "M=M-1;\n"
                                    + "A=M;\n"
                                    + "D=M;\n"
                                    + "@"
                                    + Integer.toString(5 + index)
                                    + "\n"
                                    + "M=D;\n";
                    break;
            }
        }

        return asmCode;
    }

    public static String label(String fileName,String functionName,String label) {
        return String.format("(%s.%s$%s)\n", fileName,functionName,label);
    }

    public static String ifGoto(String fileName, String functionName, String label) {
        String asmCode = "@SP\n"
                + "M=M-1;\n"
                + "A=M;\n"
                + "D=M;\n"
                + String.format("@%s.%s$%s\n", fileName,functionName,label)
                + "D;JNE\n";
        return asmCode;
    }

    public static String goTo(String fileName, String functionName,String label) {
        String asmCode = String.format("@%s.%s$%s\n", fileName,functionName,label)
                + "0;JMP\n";
        return asmCode;
    }

    public static String function(String fileName, String functionName, int numVars) {
        String pushConstant0 = "@SP\n"
                + "M=M+1;\n"
                + "A=M-1;\n"
                + "M=0;\n";
        String pushNVars = pushConstant0.repeat(numVars);
        String asmCode = String.format("(%s.%s)\n", fileName,functionName) + pushNVars;
        return asmCode;
    }

    public static String reTurn() {
        String asmCode =
                //endFrame = LCL
                "@LCL\n"
                        + "D=M;\n"
                        + "@endFrame\n"
                        + "M=D;\n"
                        //retAddr = *(endFrame - 5)
                        + "@5\n"
                        + "D=A;\n"
                        + "@endFrame\n"
                        + "A=M-D;\n"
                        + "D=M;\n"
                        + "@retAddr\n"
                        + "M=D;\n"
                        //*ARG = pop()
                        + "@SP\n"
                        + "M=M-1;\n"
                        + "A=M;\n"
                        + "D=M;\n"
                        + "@ARG\n"
                        + "A=M;\n"
                        + "M=D;\n"
                        //SP=ARG+1
                        + "@ARG\n"
                        + "D=M+1;\n"
                        + "@SP\n"
                        + "M=D;\n"
                        //THAT = *(endFrame - 1)
                        + "@endFrame\n"
                        + "A=M-1;\n"
                        + "D=M;\n"
                        + "@THAT\n"
                        + "M=D;\n"
                        //THIS = *(endFrame - 2)
                        + "@2\n"
                        + "D=A;\n"
                        + "@endFrame\n"
                        + "A=M-D;\n"
                        + "D=M;\n"
                        + "@THIS\n"
                        + "M=D;\n"
                        //ARG = *(endFrame - 3)
                        + "@3\n"
                        + "D=A;\n"
                        + "@endFrame\n"
                        + "A=M-D;\n"
                        + "D=M;\n"
                        + "@ARG\n"
                        + "M=D;\n"
                        //LCL = *(endFrame - 4)
                        + "@4\n"
                        + "D=A;\n"
                        + "@endFrame\n"
                        + "A=M-D;\n"
                        + "D=M;\n"
                        + "@LCL\n"
                        + "M=D;\n"
                        //goto retAddr
                        + "@retAddr\n"
                        + "A=M;\n"
                        + "0;JMP\n";

        return asmCode;
    }

    public static String call(String fileName,String functionName, int numArgs) {
        //push returnAddress (Using the label declared below)
        //push LCL (Saves LCL of the caller)
        //push ARG (Saves ARG of the caller)
        //push THIS (Saves THIS of the caller)
        //push THAT (Saves THAT of the caller)
        //ARG = SP-5-nArgs (Repositions ARG)
        //LCL = SP (Repositions LCL)
        //goto functionName (Transfer control to the called function)
        //(returnAddress) (Declares a label for the return-address)
        String asmCode =
                //push returnAddress (Using the label declared below)
                String.format("@%s.%s$ret.%d\n",fileName,functionName,returnPerFunctionCounter(functionName))
                        + "D=A;\n"
                        + "@SP\n"
                        + "M=M+1;\n"
                        + "A=M-1;\n"
                        + "M=D;\n"
                        //push LCL (Saves LCL of the caller)
                        + "@LCL\n"
                        + "D=M\n"
                        + "@SP\n"
                        + "M=M+1;\n"
                        + "A=M-1;\n"
                        + "M=D;\n"
                        //push ARG (Saves ARG of the caller)
                        + "@ARG\n"
                        + "D=M\n"
                        + "@SP\n"
                        + "M=M+1;\n"
                        + "A=M-1;\n"
                        + "M=D;\n"
                        //push THIS (Saves THIS of the caller)
                        + "@THIS\n"
                        + "D=M\n"
                        + "@SP\n"
                        + "M=M+1;\n"
                        + "A=M-1;\n"
                        + "M=D;\n"
                        //push THAT (Saves THAT of the caller)
                        + "@THAT\n"
                        + "D=M\n"
                        + "@SP\n"
                        + "M=M+1;\n"
                        + "A=M-1;\n"
                        + "M=D;\n"
                        //ARG = SP-5-nArgs (Repositions ARG)
                        + "@SP\n"
                        + "D=M;\n"
                        + "@5\n"
                        + "D=D-A;\n"
                        + String.format("@%d\n",numArgs)
                        + "D=D-A;\n"
                        + "@ARG\n"
                        + "M=D\n"
                        //LCL = SP (Repositions LCL)
                        + "@SP\n"
                        + "D=M;\n"
                        + "@LCL\n"
                        + "M=D;\n"
                        //goto functionName (Transfer control to the called function)
                        + String.format("@%s.%s\n",fileName,functionName)
                        + "0;JMP\n"
                        //(returnAddress) (Declares a label for the return-address)
                        + String.format("(%s.%s$ret.%d)\n",fileName,functionName,returnPerFunctionCounter(functionName));
        return asmCode;
    }
}