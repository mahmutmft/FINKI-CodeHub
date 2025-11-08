package zadaciZaVezbanje.prvKolokvium.task3.Exceptions;

public class FileNameExistsException extends RuntimeException {
    public FileNameExistsException(String message) {
        super(message);
    }
}
