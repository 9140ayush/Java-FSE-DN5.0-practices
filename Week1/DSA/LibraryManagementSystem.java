package Week1.DSA;

import java.util.Arrays;
import java.util.Comparator;

class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

public class LibraryManagementSystem {

    // 1. Linear Search to find book by title
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    // 2. Binary Search to find book by title (Assumes array is sorted by title)
    public static Book binarySearchByTitle(Book[] books, String targetTitle) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] library = {
            new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B002", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B003", "1984", "George Orwell"),
            new Book("B004", "Pride and Prejudice", "Jane Austen"),
            new Book("B005", "The Catcher in the Rye", "J.D. Salinger"),
            new Book("B006", "The Hobbit", "J.R.R. Tolkien")
        };

        System.out.println("=== Library Management System Demo ===");
        
        System.out.println("\nBooks in Library:");
        for (Book b : library) {
            System.out.println("  " + b);
        }

        // Test Linear Search
        String searchTitle = "1984";
        System.out.println("\n--- Testing Linear Search ---");
        System.out.println("Searching for: \"" + searchTitle + "\"");
        Book found1 = linearSearchByTitle(library, searchTitle);
        System.out.println("Result: " + (found1 != null ? found1 : "Not Found"));

        // Test Binary Search
        System.out.println("\n--- Testing Binary Search ---");
        System.out.println("Sorting books alphabetically by title first...");
        Arrays.sort(library, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        
        System.out.println("Sorted Library:");
        for (Book b : library) {
            System.out.println("  " + b);
        }

        System.out.println("Searching for: \"" + searchTitle + "\"");
        Book found2 = binarySearchByTitle(library, searchTitle);
        System.out.println("Result: " + (found2 != null ? found2 : "Not Found"));

        // Test Search for non-existent book
        String missingTitle = "The Odyssey";
        System.out.println("\nSearching for non-existent book: \"" + missingTitle + "\"");
        Book foundMissing = binarySearchByTitle(library, missingTitle);
        System.out.println("Result: " + (foundMissing != null ? foundMissing : "Not Found"));

        // Analysis
        System.out.println("\n=== Complexity Analysis and Comparison ===");
        System.out.println("1. Linear Search (Unsorted):");
        System.out.println("   - Time Complexity: O(N) worst-case, O(1) best-case. Average-case: O(N).");
        System.out.println("   - Space Complexity: O(1) auxiliary space.");
        System.out.println("   - Use Case: Highly dynamic libraries where books are constantly added/removed and search volume is low, rendering sorting overhead inefficient.");
        
        System.out.println("2. Binary Search (Sorted):");
        System.out.println("   - Time Complexity: O(log N) worst/average-case, O(1) best-case.");
        System.out.println("   - Space Complexity: O(1) auxiliary space.");
        System.out.println("   - Use Case: Large, relatively static libraries where books are searched very frequently. Sorting can be done once (or during inserts), making future searches incredibly fast.");
    }
}
