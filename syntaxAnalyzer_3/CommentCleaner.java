public class CommentCleaner {

    private static boolean isComment(String line){
        if(line.startsWith("//")) return true;
        if(line.startsWith("*")) return true;

        return false;
    }

    public static String removeComment(String line){
        if(isComment(line)) return "";

        if(line.contains("/*")){
            int indexOfStartComment = line.indexOf("/*");
            int indexOfEndComment = line.indexOf("*/");
            if(line.contains("*/")){
                line = line.substring(0,indexOfStartComment) + line.substring(indexOfEndComment + 1, line.length() - 1);
            }else{
                line = line.substring(0, indexOfStartComment);
            }
            return line;
        }

        if(line.contains("//")){
            int indexOfComment = line.indexOf("//");
            line = line.substring(0, indexOfComment);
            return line;
        }
        
        return line;
    }
}