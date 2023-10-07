package Employee;

public class DataOfSalaryFund {
    static int totalSalary;
    int amountEmployees;

    public DataOfSalaryFund(int totalSalary, int amountEmployees) {
        this.totalSalary = totalSalary;
        this.amountEmployees = amountEmployees;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public int getAmountEmployees() {
        return amountEmployees;
    }
}
