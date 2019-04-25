package dao.impl;

import constants.ResponseCode;
import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactDaoImpl implements ContactDao {

    private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        contact.setId(store.size() + 1);
        store.add(contact);
        System.out.println("This contact was added in your contact book");
        System.out.println(contact.toString());
    }

    @Override
    public Contact getContactById(int contactId) throws AddressBookException {
        Contact contact = store.size() > contactId ? store.get(contactId - 1) : null;
        if (Objects.isNull(contact)) {
            throw new AddressBookException(ResponseCode.NOT_FOUND,
                    " Contact with this number not found.");
        } else {
            return contact;
        }
    }

    @Override
    public Contact getContactByName(String name) throws AddressBookException {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getName().toLowerCase().equals(name.toLowerCase())) {
                return storeContacts;
            }
        }
        throw new AddressBookException(ResponseCode.NOT_FOUND, "Not found contact");
    }

    @Override
    public Contact updateContact(Contact contact) {
        store.set(contact.getId() - 1, contact);
        return contact;
    }

    @Override
    public void deleteById(int id) throws AddressBookException {
        store.forEach(item -> {
            if (item.getId() == id) {
                store.remove(item);
            }
        });
    }

    @Override
    public void showContacts() {
        for (Contact contact : getStore()) {
            System.out.println(contact.toString());
        }

    }

    @Override
    public void deleteContactByEntity(Contact contact) throws AddressBookException {
        store.remove(getContactById(contact.getId()));
    }

    private List<Contact> getStore() {
        return store;
    }

    private void searchSameContact(Contact contact) throws AddressBookException {
        for (Contact contactFromStore : getStore()) {
            if (Objects.nonNull(contactFromStore)
                    && contact.getName().equals(contactFromStore.getName())
                    && contact.getPhoneNumber().equals(contactFromStore.getPhoneNumber())
                    && contact.getLastName().equals(contactFromStore.getLastName())) {
                throw new AddressBookException(ResponseCode.OBJECT_EXIST,
                        "This contact was added early");
            }
        }
    }

}
