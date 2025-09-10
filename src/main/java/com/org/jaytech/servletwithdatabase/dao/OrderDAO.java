package com.org.jaytech.servletwithdatabase.dao;

import Utils.HibernateUtil;
import com.org.jaytech.servletwithdatabase.User;
import com.org.jaytech.servletwithdatabase.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO {
    public void save(Order o){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            s.beginTransaction();
            s.persist(o);
            s.getTransaction().commit();
        } finally { s.close(); }
    }

    public List<Order> listAll(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            // Use fetch joins to initialize items and books to avoid LazyInitializationException in JSP
            String hql = "select distinct o from Order o " +
                    "left join fetch o.items i " +
                    "left join fetch i.book b " +
                    "left join fetch o.user u " +
                    "order by o.id desc";
            return s.createQuery(hql, Order.class).list();
        } finally { s.close(); }
    }

    public List<Order> listByUser(User u){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "select distinct o from Order o " +
                    "left join fetch o.items i " +
                    "left join fetch i.book b " +
                    "where o.user = :u order by o.id desc";
            Query<Order> q = s.createQuery(hql, Order.class);
            q.setParameter("u", u);
            return q.list();
        } finally { s.close(); }
    }
}
