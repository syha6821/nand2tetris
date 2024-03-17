package Tokens;
public class Identifier implements TokenType{
    Identifier(){};

    public boolean contains(String currentToken){
        return true;
    };
    public String values(String currentToken){
        return currentToken;
    };
}
