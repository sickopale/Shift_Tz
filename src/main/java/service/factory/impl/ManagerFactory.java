package service.factory.impl;

import model.Manager;
import model.Staff;
import service.factory.StaffFactory;

public class ManagerFactory implements StaffFactory {
    @Override
    public Staff createStaff(Long id, String name, Double salary, String extraParam) {
        return new Manager("Manager", id, name, salary, extraParam);
    }
}