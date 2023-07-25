package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session= Util.getSession().openSession();
        session.beginTransaction();
        session.createSQLQuery("create table users");
        session.getTransaction().commit();
        session.close();
        System.out.println("table created");

    }

    @Override
    public void dropUsersTable() {
        Session session=Util.getSession().openSession();
        session.createSQLQuery("drop table users");
        session.getTransaction().commit();
        session.close();
        System.out.println("table droped");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session=Util.getSession().openSession();
        session.beginTransaction();
        User user=new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        System.out.println(user+"Successfully saved");
    }

    @Override
    public void removeUserById(long id) {
Session session=Util.getSession().openSession();
session.beginTransaction();
User user= (User) session.get(User.class,id);
session.delete(user);
session.getTransaction().commit();
session.close();
        System.out.println(user+"is deleted");
    }

    @Override
    public List<User> getAllUsers() {
       List<User> users=new ArrayList<>();
        Session session=Util.getSession().openSession();
        session.beginTransaction();
        users=session.createQuery("select u from  User u", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session=Util.getSession().openSession();
        session.beginTransaction();
        session.createSQLQuery("delete from users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("cleaner table users");
    }
}
