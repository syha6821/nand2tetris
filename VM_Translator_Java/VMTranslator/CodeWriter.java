import java.io.*;
import java.util.ArrayList;

public class CodeWriter {

    private String commentize(String code) {
        return "//" + code + "\n";
    }
    private File file;
    private static String fileNameWithPath;
    private BufferedWriter bufferedWriter;
    private String currentFunctionName;

    private static String currentFileNameWithoutExtension;
    private String fileNameWithoutExtension(){
        return file.getName().split("\\.")[0];
    }

    // Opens the output file/stream and gets ready to write into it.
    public CodeWriter(File file, String inputFileName) {
        this.file = file;
        currentFileNameWithoutExtension = inputFileName;
        try {
            FileWriter fileWriter = new FileWriter(CodeWriter.fileNameWithPath,true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void setFileName(String fileName){
        fileNameWithPath = fileName;
    }

    public void writeInit(){
        try {
            bufferedWriter.write(commentize("bootstrap code"));
            bufferedWriter.write(AsmCode.bootStrapCode(fileNameWithoutExtension()));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeLabel(String label){
        try {
            bufferedWriter.write(commentize(String.format("label %s",label)));
            bufferedWriter.write(AsmCode.label(fileNameWithoutExtension(),currentFunctionName,label));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeIf(String label){
        try {
            bufferedWriter.write(commentize(String.format("if go-to (%s)",label)));
            bufferedWriter.write(AsmCode.ifGoto(fileNameWithoutExtension(),currentFunctionName,label));
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void writeGoto(String label){
        try {
            bufferedWriter.write(commentize(String.format("goto (%s)",label)));
            bufferedWriter.write(AsmCode.goTo(fileNameWithoutExtension(),currentFunctionName,label));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeFunction(String functionName, int numVars){
        try {
            currentFunctionName = functionName;
            bufferedWriter.write(commentize(String.format("function %s %d",functionName,numVars)));
            bufferedWriter.write(AsmCode.function(fileNameWithoutExtension() ,functionName, numVars));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeCall(String functionName, int numArgs){
        try {
            bufferedWriter.write(commentize(String.format("call %s %d",functionName,numArgs)));
            bufferedWriter.write(AsmCode.call(fileNameWithoutExtension(),functionName, numArgs));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void writeReturn(){
        try {
            bufferedWriter.write(commentize("return"));
            bufferedWriter.write(AsmCode.reTurn());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Writes to the output file the assembly code that implements the given arithmetic command.
    // add, sub, neg, eq, gt, lt, and, or, not
    public void writeArithmetic(String command) {
        try {
            bufferedWriter.write(commentize(command));
            bufferedWriter.write(AsmCode.arithmetic(command));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Writes to the output file the assembly code that implements the given command, where command is
    // either C_PUSH or C_POP.
    public void writePushPop(String command, String segment, int index) {
        try {
            String comment = commentize(command + " " + segment + " " + index);
            bufferedWriter.write(comment);
            String asmCode = AsmCode.pushPop(command,segment,index,fileNameWithoutExtension(),currentFileNameWithoutExtension);
            bufferedWriter.write(asmCode);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Close the output file.
    public void close() {
//        String endLine = "(END)\n" + "@END\n" + "0;JMP\n";
        try {
//            bufferedWriter.write(endLine);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
