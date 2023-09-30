package Employee;

public class EmployeeBook {
    private static Employee[] employees = new Employee[10];

    public void crateEmployee(String fio, int department, int salary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(fio, department, salary);
                return;
            }
        }
        throw new RuntimeException("Collection is full");
    }


}
