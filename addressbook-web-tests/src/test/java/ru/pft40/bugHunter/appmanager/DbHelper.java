package ru.pft40.bugHunter.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public GroupData getGroup(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData group = (GroupData) session.createQuery(String.format("from GroupData where id = %s", id))
                .getSingleResult();
        return group;
    }

    public ContactData getContact(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session
                .createQuery(String.format("from ContactData where id = %s", id)).getSingleResult();
        return contact;
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
}
