package models.literature;

import com.google.gson.Gson;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Book extends Publication {
    private List<String> authors;
    private boolean isTaken = false;

    public List<String> getAuthors() { return this.authors; }
    public boolean getIsTaken() { return this.isTaken; }

    public void takeBook() throws ExecutionControl.NotImplementedException {
        if(isTaken)
            throw new ExecutionControl.NotImplementedException("Book is already taken");

        this.isTaken = true;
    }
    public void returnBook() throws ExecutionControl.NotImplementedException {
        if(!isTaken)
            throw new ExecutionControl.NotImplementedException("Book is not taken");

        this.isTaken = false;
    }

    public Book(String ISBN, String title, List<String> authors, String publisher, int pages) {
        super(ISBN, title, pages, publisher);

        this.authors = authors;
    }

    public String generateDisplayTitle() {
        StringBuilder authorNames = new StringBuilder();

        for (String author : authors) {
            authorNames.append(author).append(", ");
        }

        return this.getPublisher() + " | " + authorNames.substring(0, authorNames.length() - 2) + " | " + this.getTitle() + " | " + (this.isTaken ? "Taken" : "In Storage");
    }

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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Book b))
            return false;

        if(!super.isISBNFormatValid() && !b.isISBNFormatValid())
            return false;

        return b.getISBN().equals(this.getISBN());
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        var clone = (Book) super.clone();

        clone.authors = new ArrayList<String>();
        clone.authors.addAll(this.authors);

        return clone;
    }
}
