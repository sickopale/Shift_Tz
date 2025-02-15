import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import model.Department;
import service.configuration.AppConfig;
import model.Args;

import java.util.*;

public class App {
    public static void main(String[] args) {

        Args arguments=new Args();
        arguments.setOriginalArgs(args);
        JCommander jCommander=new JCommander(arguments);

        try {
            jCommander.parse(args);
            arguments.validate();

        } catch (IllegalArgumentException | ParameterException e) {
            System.out.println(e.getMessage());
            jCommander.usage();
            System.exit(0);
        }

        final String path="F:\\Shift_tz\\src\\main\\resources\\input.txt";
        runApp(arguments,path);

    }

    public static void runApp(Args arguments,String path){

        AppConfig config=new AppConfig();

        config.getReader().readFile(path);

        config.getProcessor().fixDuplicateIds(config.getDuplicateIds());

        config.getProcessor().deleteEmployeesWithoutManager(config.getEmployees(),config.getManagers());

        List<Department> departmentList = config.getDepartmentMapper().makeDepartmentList(config.getEmployees(),config.getManagers());

        config.getWriter().write(departmentList,config.getIncorrectData(),arguments);
    }
}
