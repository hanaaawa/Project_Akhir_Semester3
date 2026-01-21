package pertemuan_8;

// Subclass dari Person2 (Level 2)
public class Employee extends Person2 {
    int salary;

    public Employee(String name, int salary) {
        super(name); // memanggil constructor Person2
        this.salary = salary;
    }

    public void displayEmployee() {
        displayPerson();
        System.out.println("Gaji: " + salary);
    }
}
