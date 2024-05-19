package models.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import com.google.gson.Gson;

import exceptions.LibraryException;
import exceptions.UnderageException;

/**
 * Represents a person in the library system.
 * Inherits from AbstractPerson and implements RestrictedActions.
 */
public class Person extends AbstractPerson implements RestrictedActions {

    /**
     * Constructs a new Person with default name and birth date.
     */
    public Person() {
        super();
    }

    /**
     * Constructs a new Person with the specified name and birth date.
     *
     * @param name      The name of the person.
     * @param birthDate The birth date of the person.
     */
    public Person(String name, Date birthDate) {
        super(name, birthDate);
    }

    /**
     * Allows the person to take an age-restricted book.
     *
     * @param ISBN        The ISBN of the book.
     * @param minimumAge  The minimum age required to take the book.
     * @throws LibraryException    If the book is blacklisted.
     * @throws UnderageException   If the person is underage for taking the book.
     */
    public void takeAgeRestrictedBook(String ISBN, int minimumAge) throws LibraryException {
        if(Objects.equals(ISBN, "1234"))
            throw new LibraryException("Books is blacklisted.");

        LocalDate today = LocalDate.now();
        var years = Period.between(this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today).getYears();

        if (years < minimumAge) {
            throw new UnderageException(minimumAge, UUID.randomUUID());
        }

        booksInPossession.add(ISBN);
    }

    /**
     * Returns a JSON representation of the person.
     *
     * @return The JSON representation of the person.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}