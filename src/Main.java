import Employee.EmployeeBook;

public class Main {
    public static void main(String[] args) {

        EmployeeBook EB = new EmployeeBook();

        // Create new employee only through method "createEmployee" from class EmployeeBook
        EB.crateEmployee("123", 1, 20000);
        EB.crateEmployee("456", 2, 30000);



    }
}