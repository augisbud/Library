package factories;

import models.literature.Book;
import models.literature.Journal;
import models.literature.Publication;

import java.util.List;

public class PublicationFactory {
    public Publication createPublication(String ISBN, String title, int pages, String publisher) {
        return new Publication(ISBN, title, pages, publisher);
    }

    public Book createBook(String ISBN, String title, List<String> authors, String publisher, int pages) {
        return new Book(ISBN, title, authors, publisher, pages);
    }

    public Journal createJournal(String ISBN, String title, int pages, String editor, String publisher) {
        return new Journal(ISBN, title, pages, editor, publisher);
    }
}