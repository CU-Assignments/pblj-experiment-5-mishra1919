EXPERIMENT 4
q2-
solution-import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.ser";

    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        System.out.println("Enter Employee Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Designation:");
        String designation = scanner.nextLine();
        System.out.println("Enter Salary:");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            out.writeObject(employee);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public static void displayEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("Employee Details:");
            while (true) {
                try {
                    Employee employee = (Employee) in.readObject();
                    System.out.println(employee);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No employee data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error displaying employees: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);

        scanner.close();
    }
}

