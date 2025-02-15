package service.configuration;

import lombok.Getter;
import lombok.Setter;
import model.Employee;
import model.Manager;
import service.output.mapper.DepartmentMapper;
import service.output.StaffWriter;
import service.output.Writer;
import service.processor.StaffProcessor;
import service.processor.impl.StaffProcessorImpl;
import service.reader.Reader;
import service.reader.impl.StaffReader;
import service.sorter.DepartmentSorter;
import service.validator.Validator;
import service.validator.impl.StaffValidator;

import java.util.*;

@Getter
@Setter
public class AppConfig {

    Set<Long> uniqueIds = new HashSet<>();
    Set<Long> duplicateIds=new HashSet<>();
    Set<String> existingPositions = Set.of("Manager", "Employee");

    Map<Manager,String> managers = new LinkedHashMap<>();
    Map<Employee,String> employees = new LinkedHashMap<>();
    List<String> incorrectData = new ArrayList<>();

    Validator validator = new StaffValidator(uniqueIds,duplicateIds,existingPositions);
    StaffProcessor processor = new StaffProcessorImpl(validator,managers,employees,incorrectData);
    Reader reader = new StaffReader(processor);

    DepartmentMapper departmentMapper = new DepartmentMapper();
    DepartmentSorter departmentSorter=new DepartmentSorter();
    Writer writer=new StaffWriter(departmentSorter);
}
