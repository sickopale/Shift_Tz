package service.output;

import model.Args;
import model.Department;

import java.util.List;

public interface Writer {
    void write(List<Department> departments,List<String> incorrectData, Args arguments);
}
