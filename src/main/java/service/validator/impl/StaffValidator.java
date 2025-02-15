package service.validator.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import service.validator.Validator;

import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
public class StaffValidator implements Validator {

    private final Set<Long> uniqueIds;
    private final Set<Long> duplicateIds;
    private final Set<String> existingPositions;

    public boolean isValid(String line) {

        String[] parts = line.split(",");

        if (parts.length != 5) {
            return false;
        }

        String position = parts[0].trim();
        String id = parts[1].trim();
        String salary = parts[3].trim();
        String departmentOrId = parts[4].trim();

        boolean isValid = Stream.of(
                validatePosition(position),
                validateStaffId(id),
                validateUniqueId(id),
                validateSalary(salary)
        ).allMatch(Boolean::booleanValue);

        if (position.equalsIgnoreCase("Employee")) {
            isValid = isValid && validateStaffId(departmentOrId);
        }

        return isValid;
    }

    private boolean validatePosition(String position) {
        return existingPositions.contains(position);
    }

    private boolean validateStaffId(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        try {
            long value = Long.parseLong(id);
            if (value < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validateUniqueId(String id) {
        try {
            long value = Long.parseLong(id);
            if (!uniqueIds.add(value)) {
                duplicateIds.add(value);
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validateSalary(String salary) {

        String[] parts = salary.split("\\.");
        String integerPart = parts[0];

        if (integerPart.length() > 20) {
            return false;
        }

        if (salary == null || salary.isEmpty()) {
            return false;
        }
        try {
            double value = Double.parseDouble(salary);
            if (value <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}