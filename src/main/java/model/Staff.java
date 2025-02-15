package model;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Staff {

    private String position;

    private Long id;

    private String name;

    private Double salary;

    public double roundUpSalary() {
        return new BigDecimal(salary).setScale(2, RoundingMode.CEILING).doubleValue();
    }

}
