package zadaciZaVezbanje.prvKolokvium.task3;

import zadaciZaVezbanje.prvKolokvium.task3.Exceptions.FileNameExistsException;

import java.util.ArrayList;
import java.util.Collections;

public class Folder implements IFile {
    String name;
    long size;
    ArrayList<IFile> folder;

    public Folder(String input) {
        this.name = input;
        this.folder = new ArrayList<>();
    }

    public void addFile(IFile file) {
        boolean exist = folder.stream().map(IFile::getFileName).anyMatch(name -> name.equals(file.getFileName()));
        if (exist) throw new FileNameExistsException("The file exists");
        folder.add(file);
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        long fileSize = 0;
        for (IFile element : folder) {
            fileSize += element.getFileSize();
        }
        return fileSize;
    }

    @Override
    public String getFileInfo() {
        StringBuilder s = new StringBuilder();
        for (IFile element : folder) {
            s.append(element.getFileInfo());
        }
        return s.toString();
    }

    @Override
    public void sortBySize() {
        boolean swap;
        for (int i = 0; i < folder.size()-1; i++) {
            swap = false;
            for (int j = 0; j < folder.size()-i+1; j++) {
                if (folder.get(j).getFileSize() > folder.get(j+1).getFileSize()) {
                    Collections.swap(folder, j, j + 1);
                    swap = true;
                }
            }
            if (!swap){
                break;
            }
        }

    }

    @Override
    public long findLargestFile() {
        long largest = 0;
        for (IFile element : folder) {
            if (element.getFileSize() > largest) {
                largest = element.getFileSize();
            }
        }
        return largest;
    }

    @Override
    public String toString() {
        return getFileInfo();
    }
}
