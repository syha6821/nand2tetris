package JackProgram;
import java.io.File;
public class JackProgramFile implements JackProgram{
    private File jackFile;

    public JackProgramFile(File file){
        this.jackFile = file;
    }
    
    public File[] jackFile(){
        return new File[] { jackFile };
    }
}