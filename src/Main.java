import Employee.*;

import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {

        EmployeeBook EB = new EmployeeBook();

        /* Создание новых объектов Employee возможно только через метод createEmployee. Такое ограничение сделано
           для того, чтобы невозможно было создать объект Employee вне массива Employee[] */

        EB.crateEmployee("Yunusov Timur Ildarovich", 1, 60000);
        EB.crateEmployee("Vysokova Klavdiya Vadimovna", 2, 30000);
        EB.crateEmployee("Zhadan Marina Vadimovna", 4, 40000);
        EB.crateEmployee("Vakulenko Vasily Mikhailovich", 1, 25000);
        EB.crateEmployee("Abrosimova Marina Sergeevna", 3, 50000);
        EB.crateEmployee("Abrosimova Anna Yurievna", 5, 45000);
        EB.crateEmployee("Onanova Anna", 5, 35000);
        EB.crateEmployee("Gagarina Polina Sergeevna", 3, 35000);
        EB.crateEmployee("Zivert Yulia Dmitrievna", 4, 45000);
        EB.crateEmployee("Dzyuba Anna Anatolyevna", 2, 35000);

        // Изменение поля department (отдел)
        EB.getEmployee("Yunusov Timur Ildarovich").setDepartment(3);
        System.out.println(EB.getEmployee("Yunusov Timur Ildarovich"));

        // Изменение поля salary (зарплата)
        EB.getEmployee("Abrosimova Marina Sergeevna").setSalary(45000);
        System.out.println(EB.getEmployee("Abrosimova Marina Sergeevna"));

        // Сразу удалим кого-нибудь, чтобы посмотреть, как методы работают с массивом, в котором есть null-ячейки
        EB.removeEmployeeByID(6);

        // Вывести список всех сотрудников со всеми имеющимися по ним данными
        System.out.println();
        EB.printAllEmployeesInfo();

        // Подсчет суммы затрат на зарплаты для всех сотрудников в месяц
        System.out.println();
        NumberFormat NF = NumberFormat.getCurrencyInstance();
        System.out.println("Затраты на зарплаты для всех сотрудников в месяц: " +
                NF.format(EB.countSalariesPerMonth(-1)[0]));

        // Поиск минимальной, максимальной и средней зарплаты по всей компании
        System.out.println();
        System.out.println("Минимальная зарплата среди всех сотрудников равна " +
                NF.format(EB.searchExtremumSalary(-1, "min")));

        System.out.println("Максимальная зарплата среди всех сотрудников равна " +
                NF.format(EB.searchExtremumSalary(-1, "max")));

        System.out.println("Средняя зарплата среди всех сотрудников равна " +
                NF.format(EB.searchAvgSalary(-1)));

        // Печать ФИО всех сотрудников
        System.out.println();
        EB.printFioOfAllEmployees(-1);




        // Индексация всех зарплат на 20%
        EB.indexingSalaries(1.2D, -1);

        /* Поиск минимальной, максимальной и средней зарплаты по конкретному отделу
           (учтите, что зарплаты уже проиндексированы) */
        System.out.println();
        System.out.println("Минимальная зарплата среди сотрудников 2го отдела равна " +
                NF.format(EB.searchExtremumSalary(2, "min")));

        System.out.println("Максимальная зарплата среди сотрудников 3го отдела равна " +
                NF.format(EB.searchExtremumSalary(3, "max")));

        System.out.println("Средняя зарплата по 4му отделу равна " +
                NF.format(EB.searchAvgSalary(4)));

        // Подсчет затрат на зарплаты сотрудников отдела
        System.out.println();
        System.out.println("Затраты на зарплаты для сотрудников 1го отдела в месяц: " +
                NF.format(EB.countSalariesPerMonth(1)[0]));

        // Индексация зарплат 5го отдела на 10%:
        System.out.println();
        EB.indexingSalaries(1.1D, 5);

        // Печать всех сотрудников 3го отдела:
        EB.printInfoOfEmployeesOfDepartment(3);

        // Поиск сотрудников с зарплатой больше и меньше числа
        System.out.println();
        EB.searchEmployeesWithSalaryAboveNum(40000);
        System.out.println();
        EB.searchEmployeesWithSalaryLessNum(40000);

        /* Метод добавления нового сотрудника реализован в самом начале, так как без него не получится
           соблюсти требование создавать новые объекты Employee олько внутри массива */

        EB.removeEmployeeByID(4);
        EB.removeEmployeeByID(7);
        System.out.println();
        EB.printAllEmployeesInfo();
    }
}