package dao.impl;

import dao.ContactDao;
import entity.Contact;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] == null) {
                generator = argument;
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added in your contact book");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public Contact getContactById(int contactId) {

        return null;
    }

    public Contact updateContactById(int contactId) {
        return null;
    }

    public void deleteById(int id) {

    }

    public void showContacts() {

    }

    public void deleteContactByEntity(Contact contact) {

    }

    public Contact[] getStore() {
        return store;
    }
}
