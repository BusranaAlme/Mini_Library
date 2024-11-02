import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String email;
    private List<Book> issuedBooks;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public void issueBook(Book book) {
        if (book != null && book.issueBook()) {
            issuedBooks.add(book);
        }
    }

    public void returnBook(Book book) {
        if (book != null && book.returnBook()) {
            issuedBooks.remove(book);
        }
    }

    @Override
    public String toString() {
        return String.format("%d|%s|%s", id, name, email);
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Book> getIssuedBooks() { return issuedBooks; }
}