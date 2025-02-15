package service.sorter;

import model.Department;
import model.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentSorter {

    public void sortDepartmentsByName(List<Department> departments) {
        Collections.sort(departments, Comparator.comparing(Department::getName));
    }

    public List<Employee> sortEmployees(List<Employee> employees, String sortField, String sortOrder) {
        Comparator<Employee> comparator;

        if (sortField.equals("name")) {
            comparator = Comparator.comparing(Employee::getName);
        } else {
            comparator = Comparator.comparing(Employee::getSalary);
        }

        if (sortOrder.equals("desc")) {
            comparator = comparator.reversed();
        }

        return employees.stream().sorted(comparator).collect(Collectors.toList());
    }
}