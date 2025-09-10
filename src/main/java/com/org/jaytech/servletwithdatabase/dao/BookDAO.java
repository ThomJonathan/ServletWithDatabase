package com.org.jaytech.servletwithdatabase.dao;

import Utils.HibernateUtil;
import com.org.jaytech.servletwithdatabase.model.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAO {
    public void save(Book b){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            s.beginTransaction();
            s.persist(b);
            s.getTransaction().commit();
        } finally { s.close(); }
    }
    public void update(Book b){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            s.beginTransaction();
            s.merge(b);
            s.getTransaction().commit();
        } finally { s.close(); }
    }
    public void delete(int id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            s.beginTransaction();
            Book b = s.find(Book.class, id);
            if(b!=null) s.remove(b);
            s.getTransaction().commit();
        } finally { s.close(); }
    }
    public Book find(int id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{ return s.find(Book.class, id);} finally { s.close(); }
    }
    public List<Book> list(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            return s.createQuery("from Book", Book.class).list();
        } finally { s.close(); }
    }
    public List<Book> search(String q){
        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Book> query = s.createQuery("from Book where lower(title) like :q or lower(author) like :q", Book.class);
            query.setParameter("q", "%" + q.toLowerCase() + "%");
            return query.list();
        } finally { s.close(); }
    }
}
