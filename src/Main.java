import exceptions.LibraryException;
import exceptions.UnderageException;
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
        Person individual = new Person("Augustas Budnikas", new GregorianCalendar(2004, Calendar.JANUARY, 13).getTime());

        try
        {
            individual.takeAgeRestrictedBook("987654321", 20);
            individual.takeAgeRestrictedBook("123456789", 30);
        }
        catch (UnderageException e)
        {
            System.out.println(e.getMessage() + " If this is not the expected behaviour, please contact your Administrator and provide this UUID as a reference: " + e.getUuid().toString());
        }
        catch (LibraryException e)
        {
            System.out.println(e.getMessage() + " Unexpected behaviour encountered.");
        }

        RestrictedActions a = new Person();
        a.takeBook("2222222222");

        try
        {
            individual.takeAgeRestrictedBook("1234", 10);
        } catch (LibraryException e) {
            System.out.println(e.getMessage() + " Unexpected behaviour encountered.");
        }

        System.out.println("Books in possession for " + individual.getName() + ":");

        for (String book : individual.getBooksInPossession()) {
            System.out.println(book);
        }

        Publication release = new Book("978-3-16-148410-0", Publication.Genre.ACTION, "Example Book", Arrays.asList("Author1", "Author2"), "PublisherName", new Date(), 18, 300, "First Edition", Publication.Language.EN, Book.Format.PAPERBACK);

        System.out.println(release.generateDisplayTitle());
    }
}