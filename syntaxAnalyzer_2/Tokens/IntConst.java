package Tokens;
public class IntConst implements TokenType {
    IntConst(){};

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean contains(String currentToken) {
        return isNumeric(currentToken);
    }

    public String values(String currentToken){
        return currentToken;
    }
}
