package zadaciZaVezbanje.prvKolokvium.task3;

public class File implements IFile{
    String name;
    long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo() {
        return String.format("\t\tFile name:\t%s File size:\t%d\n", name,size);
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return size;
    }

    @Override
    public String toString() {
       return getFileInfo();
    }
}
