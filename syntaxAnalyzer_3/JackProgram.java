import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class JackProgram {
    String inputArg;

    JackProgram(String inputArg){
        this.inputArg = inputArg;
    }
    
    private boolean isJackFile(File file){
        return file.isFile() && file.getName().endsWith(".jack");
    }

    private FilenameFilter jackFileFilter = new FilenameFilter() {
        public boolean accept(File file, String name) {
            return name.endsWith(".jack");
        }
    };

    public ArrayList<File> sourcesFiles(){
        ArrayList<File> sourceFiles = new ArrayList<File>(); 
        File file = new File(inputArg);

        if(isJackFile(file)){
            sourceFiles.add(file);
        }else if(file.isDirectory()){
            sourceFiles.addAll(Arrays.asList(file.listFiles(jackFileFilter)));
        }

        return sourceFiles;
    }
}