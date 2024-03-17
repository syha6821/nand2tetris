import java.util.ArrayList;
import java.util.List;

public class CodeCleaner {

    private static String removeWhiteSpace(String line){
        return line.trim();
    }

    private static String deleteComment(String line){
        return CommentCleaner.removeComment(line);
    }
    
    public static ArrayList<String> filteredCode(List<String> codeLines){
        ArrayList<String> filteredCode = new ArrayList<>();
        for(String line : codeLines){
            line = removeWhiteSpace(line);
            line = deleteComment(line);
            
            if(line.isBlank()){
                continue;
            }
            filteredCode.add(line);
        }
        return filteredCode;
    }
}
