package Tokens;
import java.util.Map;
import java.util.HashMap;

public class Token {

    private static enum TokenTypeEnum{
        KEYWORD,
        SYMBOL,
        IDENTIFIER,
        INT_CONST,
        STRING_CONST;
                
        @Override
        public String toString() {
            if (this == STRING_CONST) {
                return "stringConstant";
            } else if (this == INT_CONST) {
                return "integerConstant";
            }
            return this.name().toLowerCase();
        }
    }

    private static Map<TokenType, TokenTypeEnum> tokenTypeEnumDictionary = new HashMap<>() {{
        put(new Symbol(), TokenTypeEnum.SYMBOL);
        put(new KeyWord(), TokenTypeEnum.KEYWORD);
        put(new IntConst(), TokenTypeEnum.INT_CONST);
        put(new StringConst(), TokenTypeEnum.STRING_CONST);
    }};   

    private static Map<TokenTypeEnum, TokenType> tokenTypeDictionary = new HashMap<>() {{
        put(TokenTypeEnum.SYMBOL, new Symbol());
        put(TokenTypeEnum.KEYWORD, new KeyWord());
        put(TokenTypeEnum.INT_CONST, new IntConst());
        put(TokenTypeEnum.STRING_CONST, new StringConst());
        put(TokenTypeEnum.IDENTIFIER, new Identifier());
    }};   

    public static TokenTypeEnum tokenType(String currentToken) {
        for (TokenType tokenType : tokenTypeEnumDictionary.keySet()) {
            if(tokenType.contains(currentToken)){
                return tokenTypeEnumDictionary.get(tokenType);
            }
        }
        return TokenTypeEnum.IDENTIFIER;
    }

    public static String tokenTypeString(String currentToken) {
        for (TokenType tokenType : tokenTypeEnumDictionary.keySet()) {
            if(tokenType.contains(currentToken)){
                return tokenTypeEnumDictionary.get(tokenType).toString();
            }
        }
        return TokenTypeEnum.IDENTIFIER.toString();
    }
    
    public static String values(String currentToken) {
        TokenTypeEnum tokenTypeEnum = tokenType(currentToken);
        TokenType tokenType = tokenTypeDictionary.get(tokenTypeEnum);
        String tokenValue = tokenType.values(currentToken);

        return tokenValue;
    }
}
