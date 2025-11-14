package zadaciZaVezbanje.prvKolokvium.task3;

public interface IFile {
    String getFileName();

    long getFileSize();

    String getFileInfo();

    void sortBySize();

    long findLargestFile();
}
