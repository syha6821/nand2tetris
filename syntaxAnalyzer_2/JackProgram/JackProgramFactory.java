package JackProgram;
import java.io.File;

public class JackProgramFactory {
    public static JackProgram getJackProgram(File file){
        if(file.isFile()){
            return new JackProgramFile(file);
        }
        else if(file.isDirectory()){
            return new JackProgramDirectory(file);
        }
        return null;
    }
}
