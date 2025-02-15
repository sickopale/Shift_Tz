package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Manager extends Staff{

    private String department;

    public Manager(String position,Long id,String name, Double salary,String department) {
        super(position, id, name, salary);
        this.department=department;
    }
}
