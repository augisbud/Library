package models.literature;

import com.google.gson.Gson;

import java.util.Date;
import java.util.regex.Pattern;

public class Publication {
    public enum Genre {
        ACTION,
        COMEDY,
        DETECTIVE
    }

    public enum Language {
        LT,
        EN,
        DK,
        DE
    }

    private static int count = 1;

    private final int id = count++;
    private final String ISBN;
    private final Genre genre;
    private final String title;
    private final int pages;
    private final Language language;
    private final String publisher;
    private final Date publicationDate;
    private int minimumAge;

    public int getId() { return this.id; }
    public String getISBN() { return this.ISBN; }
    public Genre getGenre() { return this.genre; }
    public String getTitle() { return this.title; }
    public int getPages() { return this.pages; }
    public Language getLanguage() { return this.language; }
    public String getPublisher() { return this.publisher; }
    public Date getPublicationDate() { return this.publicationDate; }
    public int getMinimumAge() { return this.minimumAge; }
    public void setMinimumAge(int minimumAge) { this.minimumAge = minimumAge; }

    public Publication(String ISBN, Genre genre, String title, int pages, Language language, String publisher, Date publicationDate, int minimumAge) {
        this.ISBN = ISBN;
        this.genre = genre;
        this.title = title;
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.minimumAge = minimumAge;
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
}
