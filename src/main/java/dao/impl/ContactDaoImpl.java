package dao.impl;

import dao.ContactDao;
import entity.Contact;

import java.util.Objects;

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

    @Override
    public Contact getContactById(int contactId) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == contactId) {
                return storeContacts;
            }
        }
        return null;
    }

    @Override
    public Contact getContactByName(String name) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getName().toLowerCase().equals(name.toLowerCase())) {
                return storeContacts;
            }
        }
        return null;
    }

    @Override
    public Contact updateContact(Contact contact) {
        for (Contact storeContacts : getStore()) {
            if (Objects.equals(storeContacts.getId(), contact.getId())) {
                storeContacts = contact;
                return storeContacts;
            }
        }
        return contact;
    }

    @Override
    public void deleteById(int id) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == id) {
                storeContacts = null;
            }
        }
    }

    @Override
    public void showContacts() {
        for (Contact contact : getStore()) {
            if(Objects.nonNull(contact)){
                System.out.println(contact.toString());
            }
        }

    }

    @Override
    public void deleteContactByEntity(Contact contact) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.equals(contact)) {
                storeContacts = null;
            }
        }
    }

    private Contact[] getStore() {
        return store;
    }


}
