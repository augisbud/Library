import exceptions.LibraryException;
import exceptions.UnderageException;
import factories.PublicationFactory;
import jdk.jshell.spi.ExecutionControl;
import models.entities.Person;
import models.entities.RestrictedActions;
import models.literature.Book;
import models.literature.Publication;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        // Cloning
        System.out.println("Deep Clone: ");
        var book = new Book("978-3-16-148410-0", Publication.Genre.ACTION, "Example Book", Arrays.asList("Author1", "Author2"), "PublisherName", new Date(), 18, 300, "First Edition", Publication.Language.EN, Book.Format.PAPERBACK);

        try {
            var clonedBook = book.clone();

            book.setMinimumAge(50);
            book.setFormat(Book.Format.EBOOK);
            book.addRating(5);
            book.takeBook(new Date());

            System.out.println("    Original Book: " + book);
            System.out.println("      Cloned Book: " + clonedBook);
        } catch (CloneNotSupportedException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
        }


        // Object Creation Method (Abstract Factories)
        var factory = new PublicationFactory();

        var release = factory.createBook("978-3-16-148410-0", Publication.Genre.ACTION, "Example Book", Arrays.asList("Author1", "Author2"), "PublisherName", new Date(), 18, 300, "First Edition", Publication.Language.EN, Book.Format.PAPERBACK);
        System.out.println("\n\nObject Creation Method (Abstract Factories)\n    Book Created via Factory: " + release);
    }
}