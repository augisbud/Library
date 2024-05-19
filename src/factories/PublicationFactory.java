package factories;

import models.literature.Book;
import models.literature.Journal;
import models.literature.Publication;

import java.util.List;

/**
 * Factory class for creating instances of Publication, Book, and Journal.
 */
public class PublicationFactory {
    /**
     * Creates a Publication instance with the specified ISBN, title, number of pages, and publisher.
     *
     * @param ISBN      The ISBN of the publication.
     * @param title     The title of the publication.
     * @param pages     The number of pages of the publication.
     * @param publisher The publisher of the publication.
     * @return A new Publication instance.
     */
    public Publication createPublication(String ISBN, String title, int pages, String publisher) {
        return new Publication(ISBN, title, pages, publisher);
    }

    /**
     * Creates a Book instance with the specified ISBN, title, authors, publisher, and number of pages.
     *
     * @param ISBN      The ISBN of the book.
     * @param title     The title of the book.
     * @param authors   The authors of the book.
     * @param publisher The publisher of the book.
     * @param pages     The number of pages of the book.
     * @return A new Book instance.
     */
    public Book createBook(String ISBN, String title, List<String> authors, String publisher, int pages) {
        return new Book(ISBN, title, authors, publisher, pages);
    }

    /**
     * Creates a Journal instance with the specified ISBN, title, number of pages, editor, and publisher.
     *
     * @param ISBN      The ISBN of the journal.
     * @param title     The title of the journal.
     * @param pages     The number of pages of the journal.
     * @param editor    The editor of the journal.
     * @param publisher The publisher of the journal.
     * @return A new Journal instance.
     */
    public Journal createJournal(String ISBN, String title, int pages, String editor, String publisher) {
        return new Journal(ISBN, title, pages, editor, publisher);
    }
}