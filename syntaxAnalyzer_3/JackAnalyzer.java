import java.io.File;

public class JackAnalyzer{
    public static void main(String[] args) {
        JackProgram jackProgram = new JackProgram(args[0]);
        
        for(File source : jackProgram.sourcesFiles()){
            System.out.println(JackTokenizer.tokenList(source));
        }
    }
}