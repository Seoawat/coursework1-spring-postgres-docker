package com.example.demo;

// Импортируем стандартный утилитарный класс Objects для безопасной работы с null
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    // Конструктор
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    // Переопределяем метод equals из класса Object для сравнения двух объектов Employee
    @Override
    public boolean equals(Object o) {
        // Если сравниваем объект с самим собой — они равны
        if (this == o) return true;
        // Если переданный объект null или имеет другой класс — не равны
        if (o == null || getClass() != o.getClass()) return false;
        // Приводим объект к типу Employee
        Employee employee = (Employee) o;
        // Сравниваем firstName и lastName с использованием Objects.equals (безопасно при null)
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    // Переопределяем метод hashCode, чтобы соблюдался контракт: 
    // равные объекты должны иметь одинаковый hashCode
    @Override
    public int hashCode() {
        // Генерируем хеш-код на основе firstName и lastName с помощью Objects.hash
        return Objects.hash(firstName, lastName);
    }

    // Переопределяем метод toString для удобного текстового представления объекта
    @Override
    public String toString() {
        // Возвращаем строку вида: Employee{firstName='...', lastName='...'}
        return "Employee{firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}