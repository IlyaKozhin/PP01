package service;

import dao.UserDAOFactory;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserService {
    private static UserService userService;

    private DBHelper dbHelper;

    private UserService(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getInstance());
        }
        return userService;
    }
    private UserService() {
    }
    public List<User> getAllUsers()  {
        return  UserDAOFactory.getDAO().getUsers();
    }
    public void addUser(User user)  {
        UserDAOFactory.getDAO().addUser(user);
    }
    public void deleteUser(Long id) {
        UserDAOFactory.getDAO().deleteUser(id);
    }
    public void updateUser(User user) {
        UserDAOFactory.getDAO().updateUser(user);
    }
}
