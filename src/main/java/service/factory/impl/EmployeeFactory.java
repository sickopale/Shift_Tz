package service.factory.impl;

import model.Employee;
import model.Staff;
import service.factory.StaffFactory;

public class EmployeeFactory implements StaffFactory {
    @Override
    public Staff createStaff(Long id, String name, Double salary, String extraParam) {
        return new Employee("Employee", id, name, salary, Long.valueOf(extraParam));
    }
}