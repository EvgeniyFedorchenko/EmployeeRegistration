package Employee;

import java.text.NumberFormat;
import java.util.Objects;

public class Employee {
    private String fio;
    private int department;
    private double salary;
    private final int ID;
    private static int counter = 1;

    Employee(String fio, int department, int salary) {
        this.fio = fio;
        this.department = department;
        this.salary = salary;
        this.ID = counter++;
    }

    public String getFio() {
        return fio;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getID() {
        return ID;
    }

    public void setDepartment(int department) {
        if (this.department != department) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("This department has already been installed!");
        }
    }

    public void setSalary(double salary) {
        if (this.salary != salary) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("This salary has already been installed!");
        }
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return "ID" + ID + ": " + fio + ", " + department + " dep., salary: " + numberFormat.format(salary);
    }

    /* Если ID - это уникальный идентификатор каждого объекта,
       можно прописать equals и hashCode, освновываясь только на одном этом поле */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return ID == employee.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
