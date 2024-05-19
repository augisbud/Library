package models.literature;

import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a book publication, extending the Publication class.
 * Each book has a list of authors, a flag indicating whether it is taken, and other attributes inherited from Publication.
 */
public class Book extends Publication {
    private List<String> authors;
    private boolean isTaken = false;

    /**
     * Gets the authors of the book.
     *
     * @return The list of authors of the book.
     */
    public List<String> getAuthors() { return this.authors; }

    /**
     * Checks if the book is taken.
     *
     * @return True if the book is taken, false otherwise.
     */
    public boolean getIsTaken() { return this.isTaken; }

    /**
     * Marks the book as taken.
     *
     * @throws ExecutionControl.NotImplementedException If the book is already taken.
     */
    public void takeBook() throws ExecutionControl.NotImplementedException {
        if(isTaken)
            throw new ExecutionControl.NotImplementedException("Book is already taken");

        this.isTaken = true;
    }

    /**
     * Marks the book as returned.
     *
     * @throws ExecutionControl.NotImplementedException If the book is not taken.
     */
    public void returnBook() throws ExecutionControl.NotImplementedException {
        if(!isTaken)
            throw new ExecutionControl.NotImplementedException("Book is not taken");

        this.isTaken = false;
    }

    /**
     * Constructs a new Book with the specified ISBN, title, authors, publisher, and number of pages.
     *
     * @param ISBN      The ISBN of the book.
     * @param title     The title of the book.
     * @param authors   The authors of the book.
     * @param publisher The publisher of the book.
     * @param pages     The number of pages of the book.
     */
    public Book(String ISBN, String title, List<String> authors, String publisher, int pages) {
        super(ISBN, title, pages, publisher);

        this.authors = authors;
    }

    /**
     * Generates a display title for the book combining the publisher, authors, title, and taken status.
     *
     * @return The display title of the book.
     */
    public String generateDisplayTitle() {
        StringBuilder authorNames = new StringBuilder();

        for (String author : authors) {
            authorNames.append(author).append(", ");
        }

        return this.getPublisher() + " | " + authorNames.substring(0, authorNames.length() - 2) + " | " + this.getTitle() + " | " + (this.isTaken ? "Taken" : "In Storage");
    }

    /**
     * Asynchronously saves a list of books to a file.
     *
     * @param books    The list of books to save.
     * @param filename The name of the file to save the books to.
     */
    public static void saveBooksToFileAsync(List<Book> books, String filename) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                    out.writeObject(books);
                } catch (Exception e){
                    System.err.println("Failed to save objects to file: " + e.getMessage());
                }
            }
        });
    }

    /**
     * Asynchronously loads a list of books from a file.
     *
     * @param filename The name of the file to load the books from.
     * @return A CompletableFuture containing the list of books loaded from the file.
     */
    public static CompletableFuture<List<Book>> loadBooksFromFileAsync(String filename) {
        CompletableFuture<List<Book>> future = new CompletableFuture<>();
        Thread thread = new Thread(() -> {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
                List<Book> objects = (List<Book>) in.readObject();
                future.complete(objects);
            } catch (Exception e){
                System.err.println("Failed to load objects from file: " + e.getMessage());
                future.completeExceptionally(e);
            }
        });
        thread.start();
        return future;
    }

    /**
     * Compares this book to the specified object. The result is true if and only if the argument is not null and is a Book object with the same ISBN.
     *
     * @param o The object to compare this Book against.
     * @return True if the given object represents a Book with the same ISBN as this book, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Book b))
            return false;

        if(super.isISBNFormatInvalid() && b.isISBNFormatInvalid())
            return false;

        return b.getISBN().equals(this.getISBN());
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this book.
     * @throws CloneNotSupportedException if cloning is not supported for this object.
     */
    @Override
    public Book clone() throws CloneNotSupportedException {
        var clone = (Book) super.clone();

        clone.authors = new ArrayList<String>();
        clone.authors.addAll(this.authors);

        return clone;
    }
}
