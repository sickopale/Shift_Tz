package model;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
public class Manager extends Staff{

    private String department;

    public Manager(String position,Long id,String name, Double salary,String department) {
        super(position, id, name, salary);
        this.department=department;
    }
}
