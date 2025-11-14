package zadaciZaVezbanje.Interface.Printable;

public class Document implements Printable{
    private String title;

    public Document(String title) {
        this.title = title;
    }

    @Override
    public void print(){
        System.out.println("Document: " + title);
    }
}
