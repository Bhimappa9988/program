import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private boolean isAvailable;

    public Book(int id, String title, String author, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = true; // All books are available by default
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        this.isAvailable = false;
    }

    public void returnBook() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Publisher: %s | Available: %b",
                id, title, author, publisher, isAvailable);
    }
}

class Member {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Sorry, the book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("This book was not borrowed by you.");
        }
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return String.format("Member ID: %d | Name: %s", id, name);
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.getId() == bookId);
        System.out.println("Book removed successfully!");
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully!");
    }

    public Member getMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getPublisher().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with the keyword: " + keyword);
        }
    }

    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void listAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the library.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add Member");
            System.out.println("4. Search Book");
            System.out.println("5. List All Books");
            System.out.println("6. List All Members");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1: {
                    System.out.println("Enter Book ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.println("Enter Book Title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter Book Author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter Book Publisher:");
                    String publisher = scanner.nextLine();
                    Book book = new Book(id, title, author, publisher);
                    library.addBook(book);
                    break;
                }
                case 2: {
                    System.out.println("Enter Book ID to Remove:");
                    int id = scanner.nextInt();
                    library.removeBook(id);
                    break;
                }
                case 3: {
                    System.out.println("Enter Member ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.println("Enter Member Name:");
                    String name = scanner.nextLine();
                    Member member = new Member(id, name);
                    library.addMember(member);
                    break;
                }
                case 4: {
                    System.out.println("Enter keyword to search for a book:");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                    break;
                }
                case 5: {
                    library.listAllBooks();
                    break;
                }
                case 6: {
                    library.listAllMembers();
                    break;
                }
                case 7: {
                    System.out.println("Enter Member ID:");
                    int memberId = scanner.nextInt();
                    Member member = library.getMemberById(memberId);
                    if (member != null) {
                        System.out.println("Enter Book ID to Borrow:");
                        int bookId = scanner.nextInt();
                        Book book = library.getBookById(bookId);
                        if (book != null) {
                            member.borrowBook(book);
                        } else {
                            System.out.println("Book not found.");
                        }
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                }
                case 8: {
                    System.out.println("Enter Member ID:");
                    int memberId = scanner.nextInt();
                    Member member = library.getMemberById(memberId);
                    if (member != null) {
                        System.out.println("Enter Book ID to Return:");
                        int bookId = scanner.nextInt();
                        Book book = library.getBookById(bookId);
                        if (book != null) {
                            member.returnBook(book);
                        } else {
                            System.out.println("Book not found.");
                        }
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                }
                case 9:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
