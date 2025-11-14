package zadaciZaVezbanje.Interface.Printable;

public class Photo implements Printable {
    private String fileName;

    public Photo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void print() {
        System.out.println("Photo file: " + fileName);
    }
}
