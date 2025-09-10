package com.org.jaytech.servletwithdatabase.dao;

import Utils.HibernateUtil;
import com.org.jaytech.servletwithdatabase.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO {
    public void save(User user){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.persist(user);
            s.getTransaction().commit();
        } finally {
            s.close();
        }
    }

    public User findByUsername(String username){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            Query<User> q = s.createQuery("from User where name = :u", User.class);
            q.setParameter("u", username);
            return q.uniqueResult();
        } finally {
            s.close();
        }
    }

    public User authenticate(String username, String password){
        User u = findByUsername(username);
        if(u != null && u.getPassword().equals(password)){
            return u;
        }
        return null;
    }
}
