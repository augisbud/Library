package models;

import java.util.Date;
import com.google.gson.Gson;

public class Person {
    public static int count = 0;
    String name;
    int age;
    final Date createdAt = new Date();

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return this.age; }
    public void setAge(int age) { this.age = age; }
    public Date getCreatedAt() { return this.createdAt; }

    public Person() {
        this("Administratorius", 99);
    }

    public Person(String name, int age) {
        count++;

        this.name = name;
        this.age = age;
    }

    public void println() {
        System.out.println(new Gson().toJson(this));
    }

    public void contact() {
        System.out.println("Asmuo gauna žinutę, kuri prašo susisiekti su biblioteka.");
    }

    public void contact(String message) {
        System.out.println("Asmuo gauna žinutę, kuri prašo susisiekti su biblioteka ir pateikia papildomą informacija: " + message);
    }
}
