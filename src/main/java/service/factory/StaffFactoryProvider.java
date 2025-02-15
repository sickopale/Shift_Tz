package service.factory;

import service.factory.impl.EmployeeFactory;
import service.factory.impl.ManagerFactory;

public class StaffFactoryProvider {
        public static StaffFactory getFactory(String position) {
            return switch (position) {
                case "Employee" -> new EmployeeFactory();
                case "Manager" -> new ManagerFactory();
                default -> throw new IllegalArgumentException("Unknown position: " + position);
            };
        }
}