package models.literature;

import com.google.gson.Gson;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Represents a publication in the literature models package.
 * Each publication has a unique ID, ISBN, title, number of pages, and publisher.
 */
public class Publication implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int count = 1;

    private int id = count++;
    private final String ISBN;
    private final String title;
    private final int pages;
    private final String publisher;

    /**
     * Gets the unique ID of the publication.
     *
     * @return The ID of the publication.
     */
    public int getId() { return this.id; }

    /**
     * Gets the ISBN of the publication.
     *
     * @return The ISBN of the publication.
     */
    public String getISBN() { return this.ISBN; }

    /**
     * Gets the title of the publication.
     *
     * @return The title of the publication.
     */
    public String getTitle() { return this.title; }

    /**
     * Gets the number of pages of the publication.
     *
     * @return The number of pages of the publication.
     */
    public int getPages() { return this.pages; }

    /**
     * Gets the publisher of the publication.
     *
     * @return The publisher of the publication.
     */
    public String getPublisher() { return this.publisher; }

    /**
     * Constructs a new Publication with the specified ISBN, title, number of pages, and publisher.
     *
     * @param ISBN      The ISBN of the publication.
     * @param title     The title of the publication.
     * @param pages     The number of pages of the publication.
     * @param publisher The publisher of the publication.
     */
    public Publication(String ISBN, String title, int pages, String publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }

    /**
     * Generates a display title for the publication combining the publisher and title.
     *
     * @return The display title of the publication.
     */
    public String generateDisplayTitle() {
        return this.publisher + " | " + this.title;
    }

    /**
     * Checks if the ISBN format is invalid.
     *
     * @return True if the ISBN format is invalid, false otherwise.
     */
    public final boolean isISBNFormatInvalid() {
        // https://howtodoinjava.com/java/regex/java-regex-validate-international-standard-book-number-isbns/
        String regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(regex);

        return !pattern.matcher(this.ISBN).matches();
    }

    /**
     * Returns a JSON representation of the publication.
     *
     * @return The JSON representation of the publication.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this publication.
     * @throws CloneNotSupportedException if cloning is not supported for this object.
     */
    @Override
    public Publication clone() throws CloneNotSupportedException {
        var clone = (Publication) super.clone();
        clone.id = count++;

        return clone;
    }
}
