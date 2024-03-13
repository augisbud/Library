package models.literature;

import com.google.gson.Gson;

import java.util.Date;

public class Journal extends Publication {
    private final String editor;

    public String getEditor() { return this.editor; }

    public Journal(String ISBN, Genre genre, String title, int pages, Language language, String editor, String publisher, Date publicationDate, int minimumAge) {
        super(ISBN, genre, title, pages, language, publisher, publicationDate, minimumAge);

        this.editor = editor;
    }

    public String generateDisplayTitle() {
        return getPublisher() + " | " + editor + " | " + super.getTitle();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
