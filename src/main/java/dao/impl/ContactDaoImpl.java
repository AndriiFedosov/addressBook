package dao.impl;

import constants.ResponseCode;
import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;

import java.util.*;
import java.util.stream.Collectors;

import static constants.ConstantsMessages.OBJECT_EXIST_MESSAGE;

public class ContactDaoImpl implements ContactDao {

    private Set<Contact> store = new TreeSet<>();

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        contact.setId(store.size() + 1);
        store.add(contact);
        System.out.println("This contact was added in your contact book");
        System.out.println(contact.toString());
    }

    @Override
    public Contact getContactById(int contactId) throws AddressBookException {
        return getStore()
                .stream()
                .filter(item -> item.getId() == contactId)
                .findFirst()
                .orElseThrow(AddressBookException::new);
    }

    @Override
    public Contact getContactByName(String name) throws AddressBookException {
        return getStore().stream()
                .filter(streamContact -> streamContact.getName().equals(name))
                .findFirst()
                .orElseThrow(AddressBookException::new);
    }

    @Override
    public Contact updateContact(Contact contact) {
        store = getStore()
                .stream()
                .peek(updatableContact -> {
                    if (Objects.equals(contact.getId(), updatableContact.getId())) {
                        updatableContact = contact;
                    }
                })
                .collect(Collectors.toCollection(TreeSet::new));
        return contact;
    }

    @Override
    public void deleteById(int id) throws AddressBookException {
        Contact deletedContact = getStore().stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(AddressBookException::new);
        store.remove(deletedContact);
    }

    @Override
    public void showContacts() {
        getStore().forEach(System.out::println);
    }

    @Override
    public void deleteContactByEntity(Contact contact) throws AddressBookException {
        store.remove(getContactById(contact.getId()));
    }

    private TreeSet<Contact> getStore() {
        return (TreeSet<Contact>) store;
    }

    private void searchSameContact(Contact contact) throws AddressBookException {
        Optional<Contact> sameContact = getStore().stream()
                .filter(streamContact -> streamContact.equals(contact))
                .findFirst();
        if (sameContact.isPresent()) {
            throw new AddressBookException(ResponseCode.OBJECT_EXIST, OBJECT_EXIST_MESSAGE);
        }
    }

}
