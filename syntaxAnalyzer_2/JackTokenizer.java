import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JackTokenizer {
    private List<String> codeLines;

    JackTokenizer(File jackFile) throws IOException{
        this.codeLines = Trimmer.removeCommentLines(Files.readAllLines(Paths.get(jackFile.getPath())));
    }
    
    public ArrayList<String> tokenizeLines(){
        ArrayList<String> tokenList = new ArrayList<>();
        for (String line : codeLines) {
            for (String token : LineTokenizer.tokenize(line)){
                tokenList.add(token);
            }
        }
        return tokenList;
    }
}