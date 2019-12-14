package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    void updateUser(User user);
    void deleteUser(long id);
    void addUser(User user);
}
