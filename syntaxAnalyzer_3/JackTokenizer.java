import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JackTokenizer {
    public static ArrayList<String> tokenList(File sourceFile){
       ArrayList<String> tokenList = new ArrayList<>();
        try {
            List<String> codeLines = Files.readAllLines(Paths.get(sourceFile.getPath()));
            ArrayList<String> filteredCodeLines = CodeCleaner.filteredCode(codeLines);

            for(String line : filteredCodeLines){
                for(String token : LineTokenizer.tokenize(line)){
                    tokenList.add(token);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tokenList;
    }
}
