package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    // Максимальное число сотрудников (по условию задачи — защита от переполнения)
    private static final int MAX_EMPLOYEES = 100;

    // Хранилище: Map, где ключ — уникальный "фингерпринт" ФИО (строка), значение — сам сотрудник
    private final Map<String, Employee> employees = new HashMap<>();

    // Вспомогательный метод: создаёт уникальный ключ из имени и фамилии
    private String buildKey(String firstName, String lastName) {
        // 1. Убираем лишние пробелы по краям
        // 2. Объединяем имя и фамилию через пробел
        // 3. Приводим к нижнему регистру → "Иван Иванов" и "иван иванов" → один ключ
        return (firstName.trim() + " " + lastName.trim()).toLowerCase();
    }

    // Метод добавления сотрудника
    public Employee addEmployee(String firstName, String lastName) {
        // Проверка: имя не должно быть null или пустым
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        // Проверка: фамилия не должна быть null или пустой
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }

        // Очищаем входные данные от лишних пробелов
        firstName = firstName.trim();
        lastName = lastName.trim();

        // Проверка: не превышен ли лимит сотрудников
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new IllegalStateException("Достигнут лимит сотрудников: " + MAX_EMPLOYEES);
        }

        // Генерируем уникальный ключ для этого ФИО
        String key = buildKey(firstName, lastName);

        // Проверка: существует ли уже сотрудник с таким ФИО?
        if (employees.containsKey(key)) {
            // Если да — кидаем исключение (по ТЗ: EmployeeAlreadyAdded)
            throw new EmployeeAlreadyAdded("Сотрудник " + firstName + " " + lastName + " уже существует.");
        }

        // Создаём нового сотрудника (объект Employee)
        Employee employee = new Employee(firstName, lastName);
        // Сохраняем его в Map под уникальным ключом
        employees.put(key, employee);
        // Возвращаем созданного сотрудника
        return employee;
    }

    // Метод удаления сотрудника
    public Employee removeEmployee(String firstName, String lastName) {
        // Базовая валидация входных данных
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Имя и фамилия обязательны");
        }

        // Генерируем ключ по тому же правилу, что и при добавлении
        String key = buildKey(firstName, lastName);
        // Ищем сотрудника в Map по ключу — O(1), без циклов!
        Employee employee = employees.get(key);

        // Если сотрудник не найден → ошибка
        if (employee == null) {
            throw new EmployeeNotFound("Сотрудник " + firstName + " " + lastName + " не найден.");
        }

        // Удаляем из Map по ключу — O(1)
        employees.remove(key);
        // Возвращаем удалённого сотрудника (по ТЗ)
        return employee;
    }

    // Метод поиска сотрудника
    public Employee findEmployee(String firstName, String lastName) {
        // Базовая валидация
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Имя и фамилия обязательны");
        }

        // Генерируем ключ
        String key = buildKey(firstName, lastName);
        // Ищем по ключу — без циклов!
        Employee employee = employees.get(key);

        // Если не найден — исключение (по ТЗ)
        if (employee == null) {
            throw new EmployeeNotFound("Сотрудник " + firstName + " " + lastName + " не найден.");
        }

        // Возвращаем найденного
        return employee;
    }

    // Возвращает всех сотрудников (например, для отладки)
    public List<Employee> getAllEmployees() {
        // Возвращаем копию списка значений из Map, чтобы нельзя было сломать внутреннее состояние
        return new ArrayList<>(employees.values());
    }

    // Возвращает текущее количество сотрудников
    public int getEmployeeCount() {
        return employees.size();
    }
}