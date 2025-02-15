package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends Staff {

    private Long managerId;

    public Employee(String position,Long id, String name, Double salary,Long managerId) {
        super(position, id, name, salary);
        this.managerId=managerId;
    }
}
