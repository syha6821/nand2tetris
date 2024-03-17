package Tokens;
import java.util.ArrayList;
import java.util.Arrays;

public class KeyWord implements TokenType {
    KeyWord(){};

    private static final String[] keywords = {
            "class", "method", "function", "constructor", "int", "boolean", "char", "void", "var", "static", "field",
            "let", "do", "if", "else", "while", "return", "true", "false", "null", "this"
    };
    private static final ArrayList<String> keywordsList = new ArrayList<>(Arrays.asList(keywords));

    public boolean contains(String currentToken) {
        return keywordsList.contains(currentToken);
    }
    
    public String values(String currentToken){
        return currentToken;
    }

}
