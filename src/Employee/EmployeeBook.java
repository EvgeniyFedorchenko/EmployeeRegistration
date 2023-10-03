package Employee;

import java.text.NumberFormat;

public class EmployeeBook {
    private static Employee[] employees = new Employee[10];
    static NumberFormat NF = NumberFormat.getCurrencyInstance();

    public void crateEmployee(String fio, int department, int salary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(fio, department, salary);
                return;
            }
        }
        throw new RuntimeException("Collection is full");
    }

    public Employee getEmployee(String fio) {
        for (Employee employee : employees) {
            if (fio.equals(employee.getFio())) {
                return employee;
            }
        }
        throw new RuntimeException("This employee isn't found!");
    }

    public void printAllEmployeesInfo() {
        for (Employee employee : employees) {
            if (employee == null) {
                System.out.println("null");
            } else {
                System.out.println(employee);
            }
        }
    }

    public int[] countSalariesPerMonth(int department) {
        int total = 0;
        int countEmployees = 0;
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
                if (department == -1 || department == employee.getDepartment()) {
                    total += employee.getSalary();
                    countEmployees++;
                }

        }   // Чтобы потом посчитать avgSalary по отделу нужно знать еще и количество учтенных сотрудников
        int[] results = new int[]{total, countEmployees};
        return results;
    }

    /**
     * Тут объединены методы получения min/maxSalary по всем сотрудникам и получения её же по конкретному
     * отделу: Для вывода ЗП ожидается:
     * - В первом параметре: номер отдела (или -1 для поиска по всей компании)
     * - Во втором парметре: тип экстремума в виде строки "min" и "max" соответственно
     **/

    /* Предупреждая замечание, что каждый метод должен заниматься только одним конкретным делом, скажу, что этот метод
        так и делает: выводит необходимый экстремум по зарплате в заданной выборке объектов Employee */
    public double searchExtremumSalary(int department, String extremum) {

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (employee.getSalary() < min && department == -1) {
                min = employee.getSalary();
            } else if (employee.getSalary() < min && department == employee.getDepartment()) {
                min = employee.getSalary();
            }
            if (employee.getSalary() > max && department == -1) {
                max = employee.getSalary();
            } else if (employee.getSalary() > max && department == employee.getDepartment())
                max = employee.getSalary();
        }

        if (extremum.equals("min") && min != Double.MAX_VALUE) {
            return min;
        } else if (extremum.equals("max") && max != Double.MIN_VALUE) {
            return max;
        } else {
            throw new RuntimeException("Array is empty or uncorrected extremum");
        }
    }


    public int searchAvgSalary(int department) {
        int[] input = countSalariesPerMonth(department);
        return input[0] / input[1];
    }

    public void printFioOfAllEmployees(int department) {
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (department == -1 || department == employee.getDepartment()) {
                System.out.println(employee.getFio());
            }
        }
    }

    // Ожидается коэффициент индексации, а не процент повышения
    public void indexingSalaries(double index, int department) {
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (department == -1 || department == employee.getDepartment()) {
                employee.setSalary(employee.getSalary() * index);
            }
        }
    }


    public void printInfoOfEmployeesOfDepartment(int department) {

        System.out.println("Отдел N" + department);
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (department == employee.getDepartment()) {
                System.out.printf("ID%d: %s, salary: %s\n",
                        employee.getID(), employee.getFio(), NF.format(employee.getSalary()));
            }
        }
    }

    public void searchEmployeesWithSalaryAboveNum(int num) {
        System.out.println("Сотрудники с зарплатой больше " + NF.format(num) + ":");
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (employee.getSalary() > num) {
                System.out.printf("ID%d: %s, зарплата: %s\n",
                        employee.getID(), employee.getFio(), NF.format(employee.getSalary()));
            }
        }
    }

    public void searchEmployeesWithSalaryLessNum(int num) {
        System.out.println("Сотрудники с зарплатой меньше или равной " + NF.format(num) + ":");
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (employee.getSalary() <= num) {
                System.out.printf("ID%d: %s, зарплата: %s\n",
                        employee.getID(), employee.getFio(), NF.format(employee.getSalary()));
            }
        }
    }

    /* Уникальным идентификатором является не поле fio, а поле ID, (чтобы два полных тески могли устроиться и
       это бы не поломало программу; ID же всегда уникален) будем удалять по ID */
    public void removeEmployeeByID(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }
            if (employees[i].getID() == id) {
                employees[i] = null;
                return;
            }
        }
    }
}