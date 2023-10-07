import Employee.*;

import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {

        EmployeeBook employeeBook = new EmployeeBook();

        /* Создание новых объектов Employee возможно только через метод createEmployee. Такое ограничение сделано
           для того, чтобы невозможно было создать объект Employee вне массива Employee[] */

        employeeBook.crateEmployee("Yunusov Timur Ildarovich", 1, 60000);
        employeeBook.crateEmployee("Vysokova Klavdiya Vadimovna", 2, 30000);
        employeeBook.crateEmployee("Zhadan Marina Vadimovna", 4, 40000);
        employeeBook.crateEmployee("Vakulenko Vasily Mikhailovich", 1, 25000);
        employeeBook.crateEmployee("Abrosimova Marina Sergeevna", 3, 50000);
        employeeBook.crateEmployee("Abrosimova Anna Yurievna", 5, 45000);
        employeeBook.crateEmployee("Onanova Anna", 5, 35000);
        employeeBook.crateEmployee("Gagarina Polina Sergeevna", 3, 35000);
        employeeBook.crateEmployee("Zivert Yulia Dmitrievna", 4, 45000);
        employeeBook.crateEmployee("Dzyuba Anna Anatolyevna", 2, 35000);


           // Изменение поля department (отдел)
        System.out.println("Изменение поля department (отдел):");
        employeeBook.getEmployee("Yunusov Timur Ildarovich").setDepartment(3);
        System.out.println(employeeBook.getEmployee("Yunusov Timur Ildarovich"));

           // Изменение поля salary (зарплата)
        System.out.println("\nИзменение поля salary (зарплата):");
        employeeBook.getEmployee("Abrosimova Marina Sergeevna").setSalary(45000);
        System.out.println(employeeBook.getEmployee("Abrosimova Marina Sergeevna"));

           // Сразу удалим кого-нибудь, чтобы посмотреть, как методы работают с массивом, в котором есть null-ячейки
        employeeBook.removeEmployeeById(6);

           // Вывести список всех сотрудников со всеми имеющимися по ним данными
        System.out.println("\nВывести список всех сотрудников со всеми имеющимися по ним данными:");
        employeeBook.printAllEmployeesInfo();

           // Подсчет суммы затрат на зарплаты для всех сотрудников в месяц
        System.out.println("\nПодсчет суммы затрат на зарплаты для всех сотрудников в месяц:");
        NumberFormat NF = NumberFormat.getCurrencyInstance();
        System.out.println("Затраты на зарплаты для всех сотрудников в месяц: " +
                NF.format(employeeBook.countSalariesPerMonth(-1)[0]));

           // Поиск минимальной, максимальной и средней зарплаты по всей компании
        System.out.println("\nПоиск минимальной, максимальной и средней зарплаты по всей компании:");
        System.out.println("Минимальная зарплата среди всех сотрудников равна " +
                NF.format(employeeBook.searchExtremumSalary(-1, "min")));

        System.out.println("Максимальная зарплата среди всех сотрудников равна " +
                NF.format(employeeBook.searchExtremumSalary(-1, "max")));

        System.out.println("Средняя зарплата среди всех сотрудников равна " +
                NF.format(employeeBook.searchAvgSalary(-1)));

           // Печать ФИО всех сотрудников
        System.out.println("\nПечать ФИО всех сотрудников:");
        employeeBook.printAllEmployeesFio();




           // Индексация всех зарплат на 20%
        System.out.println("\nИндексация всех зарплат на 20%:");
        employeeBook.indexingSalaries(1.2D, -1);
        employeeBook.printAllEmployeesInfo();

        /* Поиск минимальной, максимальной и средней зарплаты по конкретному отделу
           (учтите, что зарплаты уже проиндексированы) */
        System.out.println("\nПоиск минимальной, максимальной и средней зарплаты по конкретному отделу");
        System.out.println("Минимальная зарплата среди сотрудников 2го отдела равна " +
                NF.format(employeeBook.searchExtremumSalary(2, "min")));

        System.out.println("Максимальная зарплата среди сотрудников 3го отдела равна " +
                NF.format(employeeBook.searchExtremumSalary(3, "max")));

        System.out.println("Средняя зарплата по 4му отделу равна " +
                NF.format(employeeBook.searchAvgSalary(4)));

           // Подсчет затрат на зарплаты сотрудников отдела
        System.out.println("\nПодсчет затрат на зарплаты сотрудников отдела:");
        System.out.println("Затраты на зарплаты для сотрудников 1го отдела в месяц: " +
                NF.format(employeeBook.countSalariesPerMonth(1)[0]));

           // Индексация зарплат 5го отдела на 10%:
        System.out.println();
        employeeBook.indexingSalaries(1.1D, 5);

           // Печать всех сотрудников 3го отдела:
        System.out.println("\nПечать всех сотрудников 3го отдела:");
        employeeBook.printInfoOfEmployeesOfDepartment(3);

           // Поиск сотрудников с зарплатой больше и меньше числа
        System.out.println("\nПоиск сотрудников с зарплатой больше и меньше числа:");
        employeeBook.searchEmployeesWithSalaryAboveNum(40000);
        System.out.println();
        employeeBook.searchEmployeesWithSalaryLessNum(40000);


        /* Метод добавления нового сотрудника вызван в самом начале, так как без него не получится
           соблюсти требование создавать новые объекты Employee только внутри массива */

        System.out.println("\nУдаление сотрудника:");
        employeeBook.removeEmployeeById(7);
        employeeBook.removeEmployeeById(4);
        employeeBook.removeEmployeeById(10);
        employeeBook.printAllEmployeesInfo();

           // Изменить зарплату и отдел сотрудника
        System.out.println("\nИзменить зарплату и отдел сотрудника:");
        employeeBook.toChangeEmployeeField
                (3, "department", 5);
        System.out.println(employeeBook.getEmployee("Yunusov Timur Ildarovich"));

        employeeBook.toChangeEmployeeField
                (3, "Salary", 60000);
        System.out.println(employeeBook.getEmployee("Vysokova Klavdiya Vadimovna"));

           // Получить Ф.И.О. всех сотрудников по отделам
        System.out.println();
        employeeBook.printAllEmployeesWithDepartmentGruping();
    }
}