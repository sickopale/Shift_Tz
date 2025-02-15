package service.processor;

import model.Employee;
import model.Manager;

import java.util.Map;
import java.util.Set;

public interface StaffProcessor {
    void processDataLine(String line);
    void fixDuplicateIds(Set<Long> duplicateIds);
    void deleteEmployeesWithoutManager(Map<Employee,String> employees,Map<Manager,String> managers);
}
