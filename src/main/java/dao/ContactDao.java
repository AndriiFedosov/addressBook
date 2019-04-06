package dao;

import entity.Contact;

public interface ContactDao {

    void saveContact(Contact contact);

    Contact getContactById(int contactId);

    Contact updateContactById(int contactId);

    void deleteById(int id);

    void showContacts();

    void deleteContactByEntity(Contact contact);
}
