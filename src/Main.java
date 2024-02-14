import models.Person;

import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        var people = new Person[] {
                new Person("Augustas", new GregorianCalendar(2003, GregorianCalendar.JANUARY, 13).getTime()),
                new Person()
        };

        // Atspausdiname static klasės lauką (šiuo metu sukurtų asmenų skaičių)
        System.out.println(Person.getCount());

        // Išbandome setBirthDate(Date) ir getBirthDate() funkcijas
        people[0].setBirthDate(new GregorianCalendar(2004, GregorianCalendar.JANUARY, 13).getTime());
        System.out.println(people[0].getBirthDate());

        // Išbandome getCreatedAt() funkciją
        System.out.println(people[0].getCreatedAt());

        // Išbandome takeBook(string) funkciją
        people[0].takeBook("123-4567-890");
        System.out.println(people[0].getBooksInPossession().getFirst());

        // Išbandome setName(String) ir getName() funkcijas
        people[1].setName("Algirdas");
        System.out.println(people[1].getName());

        // Išbandome println() funkciją
        people[1].println();

        // Išbandome takeAgeRestrictedBook(String, int) funkcija
        people[0].takeAgeRestrictedBook("987-6543-210", 15);
        people[0].takeAgeRestrictedBook("111-2222-333", 50);
        people[0].println();

        // Išbandome returnBook(String) funkciją
        people[0].returnBook("987-6543-210");
        people[0].println();
    }
}