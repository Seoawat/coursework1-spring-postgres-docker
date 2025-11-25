package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int MAX_EMPLOYEES = 100;
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new IllegalStateException("Невозможно добавить: достигнут лимит сотрудников (" + MAX_EMPLOYEES + ")");
        }

        Employee newEmployee = new Employee(firstName, lastName);
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee found = employees.stream()
                .filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFound(
                        "Сотрудник " + firstName + " " + lastName + " не найден."
                ));

        employees.remove(found);
        return found;
    }

    public Employee findEmployee(String firstName, String lastName) {
        return employees.stream()
                .filter(emp -> firstName.equals(emp.getFirstName()) && lastName.equals(emp.getLastName()))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees); // возвращаем копию списка
    }
}