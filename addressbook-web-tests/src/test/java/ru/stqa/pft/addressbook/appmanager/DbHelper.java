package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.persistence.Query;
import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list(); //all possible groups
    for (GroupData group : result) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);

  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    //all available contacts
    for (ContactData contact : result) {
      System.out.println(contact);
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public int freshContact() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData outcome = (ContactData) session.createQuery("from ContactData where deprecated = '0000-00-00'").list().get(0);
    session.getTransaction().commit();
    session.close();
    return outcome.getId();
  }


  public ContactData getContactWithoutGroup(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query result = session.createQuery("from ContactData where id = :id");
    result.setParameter("id", id);
    ContactData contact = (ContactData) result.getSingleResult();
    session.close();
    return contact;
  }

  public GroupData newGroupContact(int id) { // получаем список групп через  бд
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query result = session.createQuery("from GroupData where id = :id");
    result.setParameter("id", id);
    GroupData group = (GroupData) result.getSingleResult();
    session.close();
    return group;
  }

  public Groups getGroupsFromContact(int id) { // получаем список групп в которых есть контакт
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query result = session.createQuery("from ContactData where id = :id");
    result.setParameter("id", id);
    ContactData contact = (ContactData) result.getSingleResult();
    session.close();
    return contact.getGroups();
  }

  public int contactsWithoutGroup() { // получаем список контактов у которых количество групп меньше чем есть групп в бд
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query result = session.createQuery("from ContactData where deprecated = '0000-00-00'"); // запрос к объекту, вместо запроса sql
    ContactData contact = (ContactData) result.getSingleResult();
    session.close();
    return contact.getId();
  }

  public ContactData getContactInGroup(int id) { // получаем контакт добавленный в группу, для того что бы проверить у него в списке групп вхождение новой
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query result = session.createQuery("from ContactData where id = :id");
    result.setParameter("id", id);
    ContactData contact = (ContactData) result.getSingleResult();
    session.close();
    return contact;
  }

  public GroupData idGroupWithoutContact(int id) { // получаем список групп в которых нет передаваемого айди контакта
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.getTransaction().commit();
    Query result = session.createQuery("from GroupData where group_name = :group_name");
    result.setParameter("id", id);
    GroupData group = (GroupData) result.getSingleResult();
    session.getTransaction().commit();
    session.close();
    return group;
  }


  public ContactData contactsInGroup(int id) { // получаем список контактов в группах через бд
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.getTransaction().commit();
    Query result = session.createQuery("from ContactData where id = :id");
    result.setParameter("id", id);
    ContactData contact = (ContactData) result.getSingleResult();
    session.close();
    return contact;
  }
}
