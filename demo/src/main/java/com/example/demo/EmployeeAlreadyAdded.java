package com.example.demo;

public class EmployeeAlreadyAdded extends RuntimeException {
    public EmployeeAlreadyAdded(String message) {
        super(message);
    }
}