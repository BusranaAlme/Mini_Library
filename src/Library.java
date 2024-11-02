import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Book, Member> issuedBooks;
    private static final String BOOKS_FILE = "books_db.txt";
    private static final String MEMBERS_FILE = "members_db.txt";

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        issuedBooks = new HashMap<>();
        initializeFiles();
    }

    private void initializeFiles() {
        try {
            // Initialize books file
            File booksFile = new File(BOOKS_FILE);
            if (!booksFile.exists()) {
                booksFile.createNewFile();
            }

            // Initialize members file
            File membersFile = new File(MEMBERS_FILE);
            if (!membersFile.exists()) {
                membersFile.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing files: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void addMember(Member member) {
        members.add(member);
        saveMembers();
    }

    private void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            writer.println("ID | Title | Author | Year | Status");
            writer.println("----------------------------------------");
            for (Book book : books) {
                writer.println(formatBookData(book));
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            writer.println("ID | Name | Email");
            writer.println("----------------------------------------");
            for (Member member : members) {
                writer.println(formatMemberData(member));
            }
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    private String formatBookData(Book book) {
        return String.format("%d | %s | %s | %d | %s",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearPublished(),
                book.isIssued() ? "Issued" : "Available");
    }

    private String formatMemberData(Member member) {
        return String.format("%d | %s | %s",
                member.getId(),
                member.getName(),
                member.getEmail());
    }

    public String loadFileContent(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}