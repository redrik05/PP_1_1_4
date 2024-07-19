package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.rmi.registry.Registry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE user("
                + "id bigint(9) auto_increment not null , "
                + "name varchar(50) not null , "
                + "last_name varchar(50) not null , "
                + "age smallint(3) not null , "
                + "PRIMARY KEY (id) "
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        try (Connection connection = util.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException s) {
            System.out.println("Проблема с createUsersTable" + s);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user";

        try (Connection connection = util.getConnection();
             Statement st = connection.createStatement()) {
            st.execute(sql);

        } catch (SQLException s) {
            System.out.println("Проблема с dropUsersTable" + s);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into user(name, last_name, age) values(?, ?, ?)";

        try (Connection connection = util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

        } catch (SQLException s) {
            System.out.println("Проблема с saveUser" + s);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE from user where id = ?";

        try (Connection connection = util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException s) {
            System.out.println("Проблема с removeUserById" + s);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * from user";
        List<User> users = new ArrayList<>();

        try (Connection connection = util.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException s) {
            System.out.println("Проблема с dropUsersTable" + s);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user";

        try (Connection con = util.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException s) {
            System.out.println("Проблема с dropUsersTable" + s);
        }
    }
}
