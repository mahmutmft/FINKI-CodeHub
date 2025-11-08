package zadaciZaVezbanje.prvKolokvium.task3;

public class FileSystem {
    Folder root;

    public FileSystem() {
        this.root = new Folder("root");
    }

    public void addFile (IFile file){
        root.addFile(file);
    }

    long findLargestFile (){
        return root.findLargestFile();
    }
    void sortBySize(){
        root.sortBySize();
    }

    @Override
    public String toString() {
        return String.format("Folder name:\t%s Folder size:\t%d\n%s", root.getFileName() , root.getFileSize(), root.getFileInfo());

    }
}
