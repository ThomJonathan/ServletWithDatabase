package Utils;

import com.org.jaytech.servletwithdatabase.User;
import com.org.jaytech.servletwithdatabase.model.Book;
import com.org.jaytech.servletwithdatabase.model.Order;
import com.org.jaytech.servletwithdatabase.model.OrderItem;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
           Configuration configuration = new Configuration().configure();

            // Register all your entity classes here
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(OrderItem.class);

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
