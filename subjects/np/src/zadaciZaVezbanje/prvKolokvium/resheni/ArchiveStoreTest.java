package zadaciZaVezbanje.prvKolokvium.resheni;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }

    public abstract static class Archive {

        protected int id;
        protected Date dateArchived;

        public Archive(int id) {
            this.id = id;
            dateArchived = new Date();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDateArchived() {
            return dateArchived;
        }

        public void setDateArchived(Date dateArchived) {
            this.dateArchived = dateArchived;
        }
    }

    public static class ArchiveStore {
        private List<Archive> archiveList;
        private List<String> logs;

        public ArchiveStore() {
            archiveList = new ArrayList<>();
            logs = new ArrayList<>();
        }

        public void archiveItem(Archive item, Date date) {
            item.dateArchived = date;
            archiveList.add(item);
            String line = String.format("Item %d archived at %s", item.id, date);
            logs.add(line);
        }

        void openItem(int id, Date date) throws NonExistingItemException {
            Archive openElement = archiveList
                    .stream()
                    .filter(element -> element.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (openElement == null) {
                throw new NonExistingItemException("Item with id " + id + " doesn't exist.");
            }

            if (openElement instanceof LockedArchive) {
                LockedArchive temp = (LockedArchive) openElement;

                if (date.before(temp.getDateToOpen())) {
                    logs.add(String.format(
                            "Item %d cannot be opened before %s",
                            id, temp.getDateToOpen()
                    ));
                } else {
                    logs.add(String.format("Item %d opened at %s", id, date));
                }

            } else if (openElement instanceof SpecialArchive) {

                SpecialArchive temp = (SpecialArchive) openElement;

                if (temp.getOpenCount() < temp.getMaxOpen()) {
                    temp.counterPlusPlus();
                    logs.add(String.format("Item %d opened at %s", id, date));
                } else {
                    logs.add(String.format(
                            "Item %d cannot be opened more than %d times",
                            id, temp.getMaxOpen()
                    ));
                }
            }

        }

        String getLog() {
            return logs.toString();
        }
    }

    public static class LockedArchive extends Archive {
        private Date dateToOpen;

        public LockedArchive(int id,Date dateToOpen) {
            super(id);
            this.dateToOpen = dateToOpen;
        }

        public Date getDateToOpen() {
            return dateToOpen;
        }

        public void setDateToOpen(Date dateToOpen) {
            this.dateToOpen = dateToOpen;
        }
    }

    public static class NonExistingItemException extends Exception {
        public NonExistingItemException(String message) {
            super(message);
        }
    }

    public static class SpecialArchive extends Archive {
        protected int maxOpen;
        private int openCount = 0;

        public SpecialArchive(int id, int maxOpen) {
            super(id);
            this.maxOpen = maxOpen;
            this.openCount = 0;
        }

        public int getMaxOpen() {
            return maxOpen;
        }

        public int getOpenCount() {
            return openCount;
        }

        public void counterPlusPlus() {
            openCount++;
        }
    }
}


