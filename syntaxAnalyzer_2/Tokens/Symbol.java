package Tokens;
import java.util.ArrayList;
import java.util.Arrays;

public class Symbol implements TokenType{
    Symbol(){};

    private static final Character[] symbols = {
            '(', ')', '{', '}', '[', ']', '.', ',', ';', '+', '-', '*', '/', '|', '&', '<', '>', '=', '~'
    };
    private static final ArrayList<Character> symbolList = new ArrayList<>(Arrays.asList(symbols));

    public static boolean contains(char currentChar) { return symbolList.contains(currentChar);}

    public boolean contains(String currentChar) { return symbolList.contains(currentChar.charAt(0));}

    public String values(String currentToken){
        if (currentToken.equals("\"")) { return "&quot;";}
        if (currentToken.equals("<")) { return "&lt;"; }
        if (currentToken.equals(">")) { return "&gt;"; }
        if (currentToken.equals("&")) { return "&amp;"; }

        return currentToken;
    }
}
