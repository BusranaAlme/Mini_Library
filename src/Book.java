public class Book {
    private int id;
    private String title;
    private String author;
    private int yearPublished;
    private boolean isIssued;

    public Book(int id, String title, String author, int yearPublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isIssued = false;
    }

    public boolean issueBook() {
        if (!isIssued) {
            isIssued = true;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (isIssued) {
            isIssued = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d|%s|%s|%d|%s",
                id, title, author, yearPublished, isIssued);
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYearPublished() { return yearPublished; }
    public boolean isIssued() { return isIssued; }
}