import models.Person;

public class Main {
    public static void main(String[] args) {
        var people = new Person[] {
                new Person("Augustas", 19),
                new Person()
        };

        // Atspausdiname static klasės lauką (šiuo metu sukurtų asmenų skaičių)
        System.out.println(Person.count);

        // Išbandome setAge(int) ir getAge() funkcijas
        people[0].setAge(20);
        System.out.println(people[0].getAge());

        // Išbandome getCreatedAt() funkciją
        System.out.println(people[0].getCreatedAt());

        // Išbandome contact() funkciją
        people[0].contact();

        // Išbandome setName(String) ir getName() funkcijas
        people[1].setName("Algirdas");
        System.out.println(people[1].getName());

        // Išbandome println() funkciją
        people[1].println();

        // Išbandome contact(String) funkcija
        people[1].contact("Negrąžinote knygos jau vienerius metus.");
    }
}