package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Department {

    private Manager manager;
    private String name;
    private List<Employee> employees;

    public double calculateAverageSalary() {
        if (employees == null || employees.isEmpty()) {
            return manager.getSalary();
        }
        double totalSalary = manager.getSalary() + employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        int totalCount = 1 + employees.size();
        return roundUp(totalSalary / totalCount);
    }

    private double roundUp(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.CEILING).doubleValue();
    }

    public int getEmployeeCount(){
        return 1 + employees.size();
    }


}
