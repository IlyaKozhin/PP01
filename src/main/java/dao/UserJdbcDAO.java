package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection mysqlConnection) {
        connection = mysqlConnection;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists user (id bigint auto_increment, name varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS user");
        stmt.close();
    }

    @Override
    public List<User> getUsers() {
        Statement stmt = null;
        List<User> list = new ArrayList<>();
        try {
            stmt = connection.createStatement();

            stmt.execute("select * from user");
            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                list.add(new User(result.getLong(1), result.getString(2), result.getString(3)));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getUserById(long id) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = connection.createStatement();

            stmt.execute("select * from user where id='" + id + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            user = new User(result.getLong(1), result.getString(2), result.getString(3));
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            stmt.execute("replace into user (id,name,password) values (" + user.getId() + ",'" + user.getName() + "','" + user.getPassword() + "')");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("insert into user (name,password) values ('" + user.getName() + "','" + user.getPassword() + "')");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(long id) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("delete from user where id='" + id + "'");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
