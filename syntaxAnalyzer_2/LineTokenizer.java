import java.util.ArrayList;

import Tokens.Symbol;

public class LineTokenizer {
    private static boolean progressInQuotationMark = false;
    private static String tempWord = "";
    private static boolean isOpeningQuotationMark(char currentChar) {
        return (currentChar == '\"' && progressInQuotationMark == false);
    }

    private static boolean isClosingQuotationMark(char currentChar) {
        return (currentChar == '\"' && progressInQuotationMark == true);
    }

    private static boolean isTailWhiteSpace(char currentChar) {
        return Character.isWhitespace(currentChar) && !tempWord.isEmpty();
    }

    private static boolean isHeadWhiteSpace(char currentChar) {
        return (Character.isWhitespace(currentChar) && tempWord.isEmpty());
    }
    
    private static boolean isSymbol(char currentChar){
        return Symbol.contains(currentChar);
    }

    private static boolean metSymbolAtEmpty(char currentChar) {
        return Symbol.contains(currentChar) && tempWord.isEmpty();
    }

    private static boolean metSymbolAtFull(char currentChar) {
        return Symbol.contains(currentChar) && !tempWord.isEmpty();
    }
    
    public static ArrayList<String> tokenize(String line){
        ArrayList<String> tokenizedLine = new ArrayList<>();

        for (char currentChar : line.toCharArray()) {
            if (isOpeningQuotationMark(currentChar)) {
                progressInQuotationMark = true;
            } else if (isClosingQuotationMark(currentChar)) {
                tokenizedLine.add('\"' + tempWord + '\"');
                tempWord = "";
                progressInQuotationMark = false;
            } else if (progressInQuotationMark) {
                tempWord += currentChar;
            } else if (isTailWhiteSpace(currentChar)) {
                tokenizedLine.add(tempWord);
                tempWord = "";
            } else if (isHeadWhiteSpace(currentChar)) {
                ;
            } else if (!isSymbol(currentChar)) {
                tempWord += currentChar;
            } else if (metSymbolAtFull(currentChar)) {
                tokenizedLine.add(tempWord);
                tokenizedLine.add(String.valueOf(currentChar));
                tempWord = "";
            } else if (metSymbolAtEmpty(currentChar)) {
                tokenizedLine.add(String.valueOf(currentChar));
                tempWord = "";
            }
        }
        return tokenizedLine;
    }

}