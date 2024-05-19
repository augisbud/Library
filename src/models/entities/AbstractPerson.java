package models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Abstract class representing common properties and actions of a person.
 */
abstract class AbstractPerson implements PersonActions {
    private static int count = 0;
    protected String name;
    protected Date birthDate;
    protected final List<String> booksInPossession = new ArrayList<>();
    protected final Date createdAt = new Date();

    /**
     * Gets the total count of created persons.
     *
     * @return The total count of created persons.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the birth date of the person.
     *
     * @return The birth date of the person.
     */
    public Date getBirthDate() {
        return this.birthDate;
    }

    /**
     * Sets the birth date of the person.
     *
     * @param birthDate The birth date of the person.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the list of books in possession of the person.
     *
     * @return The list of books in possession of the person.
     */
    public List<String> getBooksInPossession() {
        return this.booksInPossession;
    }

    /**
     * Gets the creation date of the person.
     *
     * @return The creation date of the person.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Constructs a new AbstractPerson with default name and birth date.
     */
    public AbstractPerson() {
        this("Administratorius", new Date());
    }

    /**
     * Constructs a new AbstractPerson with the specified name and birth date.
     *
     * @param name      The name of the person.
     * @param birthDate The birth date of the person.
     */
    public AbstractPerson(String name, Date birthDate) {
        count++;

        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public void takeBook(String ISBN) {
        booksInPossession.add(ISBN);
    }

    @Override
    public void returnBook(String ISBN) {
        this.booksInPossession.remove(ISBN);
    }
}
