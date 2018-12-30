package my.java.vlong.homework2.application.controller;

import my.java.vlong.homework2.domain.repository.IEmployeeRepository;
import my.java.vlong.homework2.domain.repository.IMenuRepository;
import my.java.vlong.homework2.domain.service.EmployeeService;
import my.java.vlong.homework2.domain.service.MenuService;
import my.java.vlong.homework2.infrastructure.repository.EmployeeRepositoryImpl;
import my.java.vlong.homework2.infrastructure.repository.MenuRepositoryImpl;

public class MenuController {
    public void run() {
        IMenuRepository iMenuRepository = new MenuRepositoryImpl();

        IEmployeeRepository iEmployeeRepository = new EmployeeRepositoryImpl();
        EmployeeService employeeService = new EmployeeService(iEmployeeRepository);

        MenuService menuService = new MenuService(iMenuRepository, employeeService);
        menuService.executeMenu();
    }
}
