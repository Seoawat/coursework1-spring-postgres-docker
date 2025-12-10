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
 * КОНТРОЛЛЕР ДЛЯ РАБОТЫ С СОТРУДНИКАМИ
 *
 * Основные изменения:
 *
 * 1. Перешли с GET-запросов на REST-совместимые HTTP-методы:
 *    → POST /employees — добавление (через JSON)
 *    → DELETE /employees — удаление (по параметрам)
 *    → GET /employees — поиск и получение всех
 *
 * 2. Добавлена поддержка новых полей сотрудника:
 *    → salary (зарплата) и department (отдел) передаются в теле запроса
 *
 * 3. Вся работа с данными делегирована сервису,
 *    → который теперь использует EmployeeRepository и PostgreSQL
 *
 * 4. Убраны дублирующие методы и упрощена маршрутизация:
 *    → корневой путь /employees используется для основных операций
 *
 * 5. Подготовлен к работе с JSON:
 *    → ожидает @RequestBody Employee при создании
 *    → возвращает объекты в виде JSON автоматически
 */