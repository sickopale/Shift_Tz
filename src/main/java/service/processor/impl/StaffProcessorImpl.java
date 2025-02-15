package service.processor.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Employee;
import model.Manager;
import model.Staff;
import service.factory.StaffFactory;
import service.factory.StaffFactoryProvider;
import service.processor.StaffProcessor;
import service.validator.Validator;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class StaffProcessorImpl implements StaffProcessor {

    private final Validator staffValidator;
    private final Map<Manager,String> managers;
    private final Map<Employee,String> employees;
    private final List<String> incorrectData;
    public void processDataLine(String line){

        if(!staffValidator.isValid(line)) {
            incorrectData.add(line);
            return;
        }

        createStaff(line);
    }

    public void fixDuplicateIds(Set<Long> duplicateIds){

        employees.entrySet().removeIf(entry -> {
            if (duplicateIds.contains(entry.getKey().getId())) {
                incorrectData.add(entry.getValue());
                return true;
            }
            return false;
        });

        managers.entrySet().removeIf(entry -> {
            if (duplicateIds.contains(entry.getKey().getId())) {
                incorrectData.add(entry.getValue());
                return true;
            }
            return false;
        });
    }

    public void deleteEmployeesWithoutManager(Map<Employee,String> employees,Map<Manager,String> managers){
        Set<Long> managerIds = managers.keySet().stream()
                .map(Manager::getId)
                .collect(Collectors.toSet());

        employees.entrySet().removeIf(entry -> {
            if (!managerIds.contains(entry.getKey().getManagerId())) {
                incorrectData.add(entry.getValue());
                return true;
            }
            return false;
        });
    }
    private void createStaff(String line) {

        String[] parts = line.split(",");

        String position=parts[0].trim();
        String id=parts[1].trim();
        String name=parts[2].trim();
        String salary=parts[3].trim();
        String departmentOrId=parts[4].trim();

        StaffFactory factory = StaffFactoryProvider.getFactory(position);

        Staff staff = factory.createStaff(
                Long.valueOf(id),
                name,
                Double.valueOf(salary),
                departmentOrId
        );

        if (staff instanceof Employee) {
            employees.put((Employee) staff,line);
        } else if (staff instanceof Manager) {
            managers.put((Manager) staff,line);
        }
    }
}
