package addressbook.appmanager;

import addressbook.model.Contact;
import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper() {

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();


    }

    public GroupSet groups() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Group> result = session.createQuery( "from Group").list();
        session.getTransaction().commit();
        session.close();
        return new GroupSet(result);

    }
}
