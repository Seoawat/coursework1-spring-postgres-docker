package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Конструктор для внедрения зависимости (dependency injection)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Пример эндпоинта — можно расширить
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees(); // нужно реализовать в сервисе
    }

    @GetMapping("/add")
    public Employee getEmployeeCount(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }




}