import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static Library library;

    public static void main(String[] args) {
        library = new Library();

        // Add some initial test data
        addTestData();

        // Create and set up the main window
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        // Create buttons
        JPanel viewPanel = new JPanel();
        JButton viewMembersButton = new JButton("View Members");
        JButton viewBooksButton = new JButton("View Books");
        viewPanel.add(viewMembersButton);
        viewPanel.add(viewBooksButton);

        JPanel addPanel = new JPanel();
        JButton addMemberButton = new JButton("Add Member");
        JButton addBookButton = new JButton("Add Book");
        addPanel.add(addMemberButton);
        addPanel.add(addBookButton);

        // Add action listeners
        viewMembersButton.addActionListener(e -> showFileContent("members_db.txt", "Members List"));
        viewBooksButton.addActionListener(e -> showFileContent("books_db.txt", "Books List"));
        addMemberButton.addActionListener(e -> showAddMemberDialog());
        addBookButton.addActionListener(e -> showAddBookDialog());

        // Add components to frame
        frame.add(new JLabel("Library Management System", SwingConstants.CENTER));
        frame.add(viewPanel);
        frame.add(addPanel);

        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showAddMemberDialog() {
        JDialog dialog = new JDialog((Frame)null, "Add New Member", true);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));
        dialog.setSize(400, 200);

        // Create form fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        // Add components to dialog
        dialog.add(new JLabel("Member ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);

        // Add buttons
        JButton submitButton = new JButton("Add Member");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please fill in all fields",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Member newMember = new Member(id, name, email);
                library.addMember(newMember);
                JOptionPane.showMessageDialog(dialog,
                        "Member added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Please enter a valid ID number",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private static void showAddBookDialog() {
        JDialog dialog = new JDialog((Frame)null, "Add New Book", true);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));
        dialog.setSize(400, 250);

        // Create form fields
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();

        // Add components to dialog
        dialog.add(new JLabel("Book ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Author:"));
        dialog.add(authorField);
        dialog.add(new JLabel("Year Published:"));
        dialog.add(yearField);

        // Add buttons
        JButton submitButton = new JButton("Add Book");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());

                if (title.isEmpty() || author.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please fill in all fields",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Book newBook = new Book(id, title, author, year);
                library.addBook(newBook);
                JOptionPane.showMessageDialog(dialog,
                        "Book added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Please enter valid numbers for ID and Year",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private static void showFileContent(String filename, String title) {
        JFrame contentFrame = new JFrame(title);
        contentFrame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        try {
            String content = library.loadFileContent(filename);
            if (content.trim().isEmpty()) {
                textArea.setText("No data available in " + filename);
            } else {
                textArea.setText(content);
            }
        } catch (Exception e) {
            textArea.setText("Error loading file: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        contentFrame.add(scrollPane);

        contentFrame.setLocationRelativeTo(null);
        contentFrame.setVisible(true);
    }

    private static void addTestData() {
        // Add test books
        library.addBook(new Book(1, "Java Programming", "John Smith", 2020));
        library.addBook(new Book(2, "Python Basics", "Jane Doe", 2021));

        // Add test members
        library.addMember(new Member(101, "Alice Johnson", "alice.johnson@example.com"));
        library.addMember(new Member(102, "Bob Smith", "bob.smith@example.com"));
    }
}
//Updated