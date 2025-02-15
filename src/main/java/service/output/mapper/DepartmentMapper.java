package service.output.mapper;

import model.Department;
import model.Employee;
import model.Manager;

import java.util.*;
public class DepartmentMapper {
    public List<Department> makeDepartmentList(Map<Employee,String> employeeMap, Map<Manager,String> managerMap){

        Map<Long, List<Employee>> employeesByManagerId = new HashMap<>();
        for (Employee employee : employeeMap.keySet()) {
            Long managerId = employee.getManagerId();
            employeesByManagerId.computeIfAbsent(managerId, k -> new ArrayList<>()).add(employee);
        }

        List<Department> departments = new ArrayList<>();
        for (Manager manager : managerMap.keySet()) {
            List<Employee> deptEmployees = employeesByManagerId.getOrDefault(manager.getId(), Collections.emptyList());
            departments.add(new Department(manager, manager.getDepartment(), deptEmployees));
        }
        return departments;
    }
}
