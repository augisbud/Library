package models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.time.Period;

import com.google.gson.Gson;

public class Person {
    private static int count = 0;
    private String name;
    private Date birthDate;
    private final List<String> booksInPossession = new ArrayList<String>();

    private final Date createdAt = new Date();

    public static int getCount() { return count; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public Date getBirthDate() { return this.birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public List<String> getBooksInPossession() { return this.booksInPossession; }
    public Date getCreatedAt() { return this.createdAt; }

    public Person() {
        this("Administratorius", new Date());
    }

    public Person(String name, Date birthDate) {
        count++;

        this.name = name;
        this.birthDate = birthDate;
    }

    public void println() {
        System.out.println(new Gson().toJson(this));
    }

    public void takeBook(String ISBN) {
        booksInPossession.add(ISBN);
    }

    public void takeAgeRestrictedBook(String ISBN, int minimumAge) {
        LocalDate today = LocalDate.now();
        var years = Period.between(this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today).getYears();
        if(years > minimumAge) {
            booksInPossession.add(ISBN);
        }
    }

    public void returnBook(String ISBN) {
        this.booksInPossession.remove(ISBN);
    }
}
