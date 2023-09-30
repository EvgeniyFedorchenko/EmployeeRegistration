package Employee;

public class Employee {
    private String fio;
    private int department;
    private int salary;
    int id;

    Employee(String fio, int department, int salary) {
        this.fio = fio;
        this.department = department;
        this.salary = salary;
        this.id = id++;
    }

    public String getFio() {
        return fio;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        if (this.department != department) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("This department has already been installed!");
        }
    }

    public void setSalary(int salary) {
        if (this.salary != salary) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("This salary has already been installed!");
        }
    }
}
