package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService kek = new UserServiceImpl();
        // создание таблицы
        kek.createUsersTable();

        kek.saveUser("Джон", "Дориан", (byte) 23);
        kek.saveUser("Персиваль", "Кокс", (byte) 46);
        kek.saveUser("Роберт", "Келсо", (byte) 65);
        kek.saveUser("Убо", "Рщик", (byte) 45);
        System.out.println("добавление 4-х пользователей");
        // получение списка пользователей и вывод в консоль
        System.out.println("получение списка пользователей и вывод в консоль");
        kek.getAllUsers().stream().forEach(User::printInf);
        // очищение списка пользователей
        kek.cleanUsersTable();
        // удаление таблицы пользователей
        kek.dropUsersTable();
    }
}
