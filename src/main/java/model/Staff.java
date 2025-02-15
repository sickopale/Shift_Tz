package model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
public class Staff {

    private String position;

    private Long id;

    private String name;

    private Double salary;

    public double roundUpSalary() {
        return new BigDecimal(salary).setScale(2, RoundingMode.CEILING).doubleValue();
    }

}
