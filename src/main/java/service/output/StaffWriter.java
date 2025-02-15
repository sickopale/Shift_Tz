package service.output;

import lombok.AllArgsConstructor;
import model.Args;
import model.Department;
import model.Employee;
import service.sorter.DepartmentSorter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class StaffWriter implements Writer {
    private final DepartmentSorter departmentSorter;

    @Override
    public void write(List<Department> departments, List<String> incorrectData, Args arguments) {
        String sortField = arguments.getSort();
        String sortOrder = arguments.getOrder();
        String outputType = arguments.getOutput();
        String outputPath = arguments.getPath();

        StringBuilder output = new StringBuilder();

        departmentSorter.sortDepartmentsByName(departments);

        for (Department department : departments) {
                    output.append(department.getName()).append("\n");
                    output.append(department.getManager().getPosition()).append(",")
                            .append(department.getManager().getId()).append(",")
                            .append(department.getManager().getName()).append(",")
                            .append(department.getManager().roundUpSalary()).append("\n");

            List<Employee> employees = department.getEmployees();

            if (sortField != null && sortOrder!=null) {
                employees = departmentSorter.sortEmployees(employees, sortField, sortOrder);
            }

            for (Employee employee : employees) {
                output.append(employee.getPosition()).append(",")
                        .append(employee.getId()).append(",")
                        .append(employee.getName()).append(",")
                        .append(employee.roundUpSalary()).append("\n");
            }

            output.append(department.getEmployeeCount()).append(", ")
                    .append(department.calculateAverageSalary()).append("\n");
        }

        if (!incorrectData.isEmpty()) {
            output.append("Некорректные данные:\n");
            for (String data : incorrectData) {
                output.append(data).append("\n");
            }
        }

        if (outputType == null || outputType.equals("console")) {
            System.out.print(output);
        } else if (outputType.equals("file")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write(output.toString());
            } catch (IOException e) {
                System.err.println(": " + e.getMessage());
            }
        }
    }
}
