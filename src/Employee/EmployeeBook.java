package Employee;

import java.text.NumberFormat;
import java.util.Arrays;

public class EmployeeBook {
    private Employee[] employees = new Employee[10];
    private static NumberFormat nf = NumberFormat.getCurrencyInstance();

    public void crateEmployee(String fio, int department, int salary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(fio, department, salary);
                return;
            }
        }
        throw new RuntimeException("Collection is full");
    }

    /* Уникальным идентификатором является не поле fio, а поле id, (чтобы два полных тески могли устроиться и
       это бы не поломало программу; ID же всегда уникален) будем удалять по ID */
    public void removeEmployeeById(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                return;
            }
        }
    }

    public void toChangeEmployeeField(int id, String field, double fieldNewValue) {
        Employee employeeForChange = null;
        for (Employee employee : employees) {
            if (employees != null && employee.getId() == id) {
                employeeForChange = employee;
                break;
            }
        }
        if (field.equalsIgnoreCase("salary")) {
            employeeForChange.setSalary(fieldNewValue);
        } else if (field.equalsIgnoreCase("department")) {
            employeeForChange.setDepartment((int) fieldNewValue);
        } else {
            System.out.println("This field can not be changed!");
        }
    }

    public Employee getEmployee(String fio) {
        for (Employee employee : employees) {
            if (employee != null && fio.equals(employee.getFio())) {
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

    /* Я попытался написать этот метод так, будто мы не знаем сколько у нас отделов
       (если компания будет разрастаться, отделы будут прибавляться) */
    public void printAllEmployeesWithDepartmentGruping() {

        // Собираем в массив номера этих отделов
        int[] numbersOfDepartment = new int[employees.length];

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            for (int j = 0; j < numbersOfDepartment.length; j++) {
                if (employee.getDepartment() == numbersOfDepartment[j]) {
                    break;
                } else if (numbersOfDepartment[j] == 0) {
                    numbersOfDepartment[j] = employee.getDepartment();
                    break;
                }
            }
        }
        // Считаем, сколько там ненулевых отделов
        int countNoZeroNumbers = 0;
        for (int number : numbersOfDepartment) {
            if (number != 0) {
                countNoZeroNumbers++;
            }
        }
        // Создаем под ненулевые элменты новый массив и складываем их туда
        int[] numberOfDepartmentsWithoutZeros = new int[countNoZeroNumbers];
        System.arraycopy(numbersOfDepartment, 0, numberOfDepartmentsWithoutZeros,0,
                countNoZeroNumbers);
        Arrays.sort(numberOfDepartmentsWithoutZeros);

        // Печатаем всех сотрудников с сортировкой по отделам
        for (int department : numberOfDepartmentsWithoutZeros) {
            System.out.println("Отдел N" + department + ": ");
            for (Employee employee : employees) {
                if (employee != null && employee.getDepartment() == department) {
                    System.out.println(employee.getFio());
                }
            }
        }
    }

    public void printAllEmployeesFio() {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getFio());
            }
        }
    }

    public void printInfoOfEmployeesOfDepartment(int department) {
        System.out.println("Отдел N" + department);
        for (Employee employee : employees) {
            if (employee != null && department == employee.getDepartment()) {
                System.out.printf("ID%d: %s, salary: %s\n",
                        employee.getId(), employee.getFio(), nf.format(employee.getSalary()));
            }
        }
    }

    public DataOfSalaryFund countSalariesPerMonth(int department) { // Ожидается номер отдела или -1 для всей компании
        int total = 0;
        int countEmployees = 0;
        for (Employee employee : employees) {

            if (employee != null && (department == -1 || department == employee.getDepartment())) {
                total += employee.getSalary();
                countEmployees++;
            }
        }
        return new DataOfSalaryFund(total, countEmployees);
    }


    /**
     * Тут объединены методы получения min/maxSalary по всем сотрудникам и получения её же по конкретному
     * отделу: Для вывода ЗП ожидается:
     * - В первом параметре: номер отдела (или -1 для поиска по всей компании)
     * - Во втором парметре: тип экстремума в виде строки "min" и "max" соответственно (хотя Enum конечно был бы лучше)
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
            } else if (employee.getSalary() > max && department == employee.getDepartment()) {
                max = employee.getSalary();
            }
        }

        if (extremum.equals("min") && min != Double.MAX_VALUE) {
            return min;
        } else if (extremum.equals("max") && max != Double.MIN_VALUE) {
            return max;
        } else {
            throw new RuntimeException("Array is empty or uncorrected extremum");
        }
    }

    public int searchAvgSalary(int department) {  // Ожидается номер отдела или -1 для всей компании
        DataOfSalaryFund data = countSalariesPerMonth(department);
        return data.getTotalSalary() / data.getAmountEmployees();
    }

    // Ожидается коэффициент индексации, а не процент повышения
    public void indexingSalaries(double index, int department) { // Во втором аргументе -1 для всех отделов сразу
        for (Employee employee : employees) {
            if (employee != null && (department == -1 || department == employee.getDepartment())) {
                employee.setSalary(employee.getSalary() * index);
            }
        }
    }

    public void searchEmployeesWithSalaryAboveNum(int num) {
        System.out.println("Сотрудники с зарплатой больше " + nf.format(num) + ":");
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() > num) {
                System.out.printf("ID%d: %s, зарплата: %s\n",
                        employee.getId(), employee.getFio(), nf.format(employee.getSalary()));
            }
        }
    }

    public void searchEmployeesWithSalaryLessNum(int num) {
        System.out.println("Сотрудники с зарплатой меньше или равной " + nf.format(num) + ":");
        for (Employee employee : employees) {

            if (employee != null && employee.getSalary() <= num) {
                System.out.printf("ID%d: %s, зарплата: %s\n",
                        employee.getId(), employee.getFio(), nf.format(employee.getSalary()));
            }
        }
    }
}
