package JackProgram;
import java.io.File;
import java.io.FilenameFilter;

public class JackProgramDirectory implements JackProgram {
    private File[] jackFilesInDirectory;
    private File jackDirectory;

    public JackProgramDirectory(File file) {
        this.jackDirectory = file;
        this.jackFilesInDirectory = jackFilesInDirectory(jackDirectory);
    }

    private FilenameFilter jackFileFilter = new FilenameFilter() {
        public boolean accept(File file, String name) {
            return name.endsWith(".jack");
        }
    };

    private File[] jackFilesInDirectory(File directory) {
        File[] fileList = directory.listFiles(jackFileFilter);
        return fileList;
    }

    public File[] jackFile(){
        return jackFilesInDirectory;
    }

}