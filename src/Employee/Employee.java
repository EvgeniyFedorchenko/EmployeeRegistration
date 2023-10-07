package Employee;

import java.text.NumberFormat;
import java.util.Objects;

public class Employee {
    private String fio;
    private int department;
    private double salary;
    private final int id;
    private static int counter = 1;

    Employee(String fio, int department, int salary) {
        this.fio = fio;
        this.department = department;
        this.salary = salary;
        this.id = counter++;
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

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        if (this.department > 0) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("This number of department is uncorrected!");
        }
    }

    public void setSalary(double salary) {
        if (this.salary > 0) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("Can not set the salary below zero!");
        }
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return "id" + id + ": " + fio + ", " + department + " dep., salary: " + numberFormat.format(salary);
    }

    /* Если id - это уникальный идентификатор каждого объекта,
       можно прописать equals и hashCode, освновываясь только на одном этом поле */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
