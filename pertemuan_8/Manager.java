package pertemuan_8;

// Subclass dari Employee (Level 3)
public class Manager extends Employee {
    String department;

    public Manager(String name, int salary, String department) {
        super(name, salary);
        this.department = department;
    }

    public void displayManager() {
        displayEmployee();
        System.out.println("Departemen: " + department);
    }
}
