package models.literature;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class Publication implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int count = 1;

    private int id = count++;
    private final String ISBN;
    private final String title;
    private final int pages;
    private final String publisher;

    public int getId() { return this.id; }
    public String getISBN() { return this.ISBN; }
    public String getTitle() { return this.title; }
    public int getPages() { return this.pages; }
    public String getPublisher() { return this.publisher; }

    public Publication(String ISBN, String title, int pages, String publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }

    public String generateDisplayTitle() {
        return this.publisher + " | " + this.title;
    }

    public final boolean isISBNFormatValid() {
        // https://howtodoinjava.com/java/regex/java-regex-validate-international-standard-book-number-isbns/
        String regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(this.ISBN).matches();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public Publication clone() throws CloneNotSupportedException {
        var clone = (Publication) super.clone();
        clone.id = count++;

        return clone;
    }
}
