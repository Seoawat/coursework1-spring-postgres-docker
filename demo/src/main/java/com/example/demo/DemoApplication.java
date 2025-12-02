package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

/*
 * СЕРВИС ДЛЯ РАБОТЫ С СОТРУДНИКАМИ
 *
 * Основные изменения:
 *
 * 1. Хранилище заменено с List<Employee> на Map<String, Employee>.
 *    → Обеспечивает уникальность сотрудников и скорость операций O(1).
 *
 * 2. Используется нормализованный ключ:
 *        (firstName.trim() + " " + lastName.trim()).toLowerCase()
 *    → "Иван Иванов", "ivan ivanov" и " IVAN  IVANOV " считаются одним сотрудником.
 *
 * 3. Все операции (добавление, поиск, удаление) выполняются через методы Map:
 *        put(), get(), remove()
 *    → Убраны циклы и Stream API — повышена производительность.
 *
 * 4. Реализована валидация входных данных:
 *    → firstName и lastName не могут быть null или пустыми.
 *
 * 5. Введены кастомные исключения:
 *    → EmployeeAlreadyAdded — при попытке добавить дубликат
 *    → EmployeeNotFound   — при отсутствии сотрудника
 *
 * 6. Метод getAllEmployees() возвращает копию списка (через new ArrayList<>(...)),
 *    → чтобы внешний код не мог изменить внутреннее состояние хранилища.
 */