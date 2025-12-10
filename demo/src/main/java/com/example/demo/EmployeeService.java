package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        if (salary <= 0) {
            throw new IllegalArgumentException("Зарплата должна быть положительной");
        }
        if (department <= 0) {
            throw new IllegalArgumentException("Отдел должен быть положительным числом");
        }

        if (employeeRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new EmployeeAlreadyAdded("Сотрудник " + firstName + " " + lastName + " уже существует.");
        }

        Employee employee = new Employee(firstName, lastName, salary, department);
        return employeeRepository.save(employee);
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        if (employee == null) {
            throw new EmployeeNotFound("Сотрудник " + firstName + " " + lastName + " не найден.");
        }
        employeeRepository.delete(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        if (employee == null) {
            throw new EmployeeNotFound("Сотрудник " + firstName + " " + lastName + " не найден.");
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public int getEmployeeCount() {
        return (int) employeeRepository.count();
    }
}