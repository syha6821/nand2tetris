import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import JackProgram.JackProgram;
import JackProgram.JackProgramFactory;

class JackAnalyzer {
  public static void main(String[] args) {
    File inputFile = new File(args[0]);
    JackProgram jackProgram = JackProgramFactory.getJackProgram(inputFile);

    for (File file : jackProgram.jackFile()){
      try {
        //Jack File -> Token list
        JackTokenizer jackTokenizer = new JackTokenizer(file);
        ArrayList<String> tokens = jackTokenizer.tokenizeLines();

        //TokenList to -> Xml File
        XmlFile xmlFile = new XmlFile(file.getPath());
        xmlFile.generateTXmlFile(tokens);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}