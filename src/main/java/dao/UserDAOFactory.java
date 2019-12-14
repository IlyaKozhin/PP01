package dao;

import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {
    public static UserDAO getDAO() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            InputStream inputStream = UserDAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            property.load(inputStream);

            String daoType = property.getProperty("daoType");
            if (daoType.equals("UserHibernateDAO")) {
                return new UserHibernateDAO((DBHelper.getInstance()).getSessionFactory().openSession());
            } else if (daoType.equals("UserJdbcDAOp")) {
                return  new UserJdbcDAO(DBHelper.getInstance().getMysqlConnection());
            }
        } catch (IOException e) {
            System.err.println("Missing properties file");
        }
        return null;
    }
}
