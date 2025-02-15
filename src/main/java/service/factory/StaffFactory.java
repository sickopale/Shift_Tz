package service.factory;

import model.Staff;

public interface StaffFactory {
    Staff createStaff(Long id, String name, Double salary, String extraParam);
}
