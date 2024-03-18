package models.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.Period;
import java.util.UUID;

import com.google.gson.Gson;
import exceptions.UnderageException;

interface PersonActions {
    void takeBook(String ISBN);
    void returnBook(String ISBN);
}

interface RestrictedActions extends PersonActions {
    void takeAgeRestrictedBook(String ISBN, int minimumAge) throws UnderageException;
}

abstract class AbstractPerson implements PersonActions {
    private static int count = 0;
    protected String name;
    protected Date birthDate;
    protected final List<String> booksInPossession = new ArrayList<String>();

    protected final Date createdAt = new Date();

    public static int getCount() {
        return count;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getBooksInPossession() {
        return this.booksInPossession;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public AbstractPerson() {
        this("Administratorius", new Date());
    }

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

public class Person extends AbstractPerson implements RestrictedActions {
    public Person() {
        super();
    }

    public Person(String name, Date birthDate) {
        super(name, birthDate);
    }

    public void takeAgeRestrictedBook(String ISBN, int minimumAge) throws UnderageException {
        LocalDate today = LocalDate.now();
        var years = Period.between(this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today).getYears();

        if (years < minimumAge) {
            throw new UnderageException(minimumAge, UUID.randomUUID());
        }

        booksInPossession.add(ISBN);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}