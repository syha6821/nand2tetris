import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Tokens.Token;

public class XmlFile {
    private File xmlFile; 
    private File tXmlFile; 

    private String tokenWithTag(String token) {
        String tokenType = Token.tokenTypeString(token);
        String tagLine = String.format("<%s> %s </%s>", tokenType, Token.values(token), tokenType);

        return tagLine;
    }

    XmlFile(String inputFilePath){
        String tXmlFilePath = inputFilePath.substring(0,inputFilePath.lastIndexOf(".")) + "generatedT.xml";
        String xmlFilePath = inputFilePath.substring(0,inputFilePath.lastIndexOf(".")) + "generated.xml";

        this.xmlFile = new File(xmlFilePath);
        this.tXmlFile = new File(tXmlFilePath);
    }
    
    public void generateTXmlFile(ArrayList<String> tokens){
        try {
            FileWriter fileWriter = new FileWriter(tXmlFile);
            fileWriter.write("<tokens>\n");

            System.out.println(tokens.size());
            for (String token : tokens) {
                String tagLine = tokenWithTag(token);
                fileWriter.write(tagLine+"\n");
            }

            fileWriter.write("</tokens>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateXmlFile(ArrayList<String> tokens){
    }

}
