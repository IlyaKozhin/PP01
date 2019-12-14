package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;

    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(long id) {
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(User.class,id));
        transaction.commit();
        session.close();
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
}
