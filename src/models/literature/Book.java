package models.literature;

import com.google.gson.Gson;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class Book extends Publication {
    public enum Format {
        HARDCOVER,
        PAPERBACK,
        EBOOK
    }

    private List<String> authors;
    private final String edition;
    private Format format;
    private List<Integer> ratings = new ArrayList<Integer>();
    private boolean isTaken = false;
    private Date dateOfIssue;
    private Date issuedUntil;

    public List<String> getAuthors() { return this.authors; }
    public String getEdition() { return this.edition; }
    public Format getFormat() { return this.format; }
    public boolean getIsTaken() { return this.isTaken; }
    public List<Integer> getRatings() { return this.ratings; }

    public void setFormat(Format format) { this.format = format; }

    public void addRating(int rating) { ratings.add(rating); }
    public void takeBook(Date issuedUntil) throws ExecutionControl.NotImplementedException {
        if(isTaken)
            throw new ExecutionControl.NotImplementedException("Book is already taken");

        this.isTaken = true;
        this.dateOfIssue = new Date();
        this.issuedUntil = issuedUntil;
    }
    public void returnBook() throws ExecutionControl.NotImplementedException {
        if(!isTaken)
            throw new ExecutionControl.NotImplementedException("Book is already taken");

        this.isTaken = false;
        this.dateOfIssue = null;
        this.issuedUntil = null;
    }

    public Book(
            String ISBN, Genre genre, String title, List<String> authors, String publisher, Date publicationDate, int minimumAge,
            int pages, String edition, Language language, Format format
    ) {
        super(ISBN, genre, title, pages, language, publisher, publicationDate, minimumAge);

        this.authors = authors;
        this.edition = edition;
        this.format = format;
    }

    public double calculateRating() {
        int sum = 0;

        for(int rating : this.ratings)
            sum += rating;

        return (double) sum / this.ratings.size();
    }

    public String generateDisplayTitle() {
        StringBuilder authorNames = new StringBuilder();

        for (String author : authors) {
            authorNames.append(author).append(", ");
        }

        return this.getPublisher() + " | " + authorNames.substring(0, authorNames.length() - 2) + " | " + this.getTitle();
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

        clone.ratings = new ArrayList<Integer>();
        clone.ratings.addAll(this.ratings);

        return clone;
    }
}
