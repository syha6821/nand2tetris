package Tokens;
public class StringConst implements TokenType{
    StringConst(){};

    public boolean contains(String currentToken) {
        return (currentToken.charAt(0) == '\"');
    }

    public String values(String currentToken){
        return currentToken.replaceAll("\"", "");
    }
}
