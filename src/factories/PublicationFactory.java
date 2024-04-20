package factories;

import models.literature.Book;
import models.literature.Journal;
import models.literature.Publication;

import java.util.Date;
import java.util.List;

public class PublicationFactory {
    public Publication createPublication(String ISBN, Publication.Genre genre, String title, int pages, Publication.Language language, String publisher, Date publicationDate, int minimumAge) {
        return new Publication(ISBN, genre, title, pages, language, publisher, publicationDate, minimumAge);
    }

    public Book createBook(
            String ISBN, Publication.Genre genre, String title, List<String> authors, String publisher, Date publicationDate, int minimumAge,
            int pages, String edition, Publication.Language language, Book.Format format
    ) {
        return new Book(ISBN, genre, title, authors, publisher, publicationDate, minimumAge, pages, edition, language, format);
    }

    public Journal createJournal(String ISBN, Publication.Genre genre, String title, int pages, Publication.Language language, String editor, String publisher, Date publicationDate, int minimumAge) {
        return new Journal(ISBN, genre, title, pages, language, editor, publisher, publicationDate, minimumAge);
    }
}