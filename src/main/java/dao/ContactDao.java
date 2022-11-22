package dao;

import entity.Contact;
import exception.AddressBookException;
import exception.ApplicationException;

public interface ContactDao {

    /**
     * This method save new contact in address book.
     *
     * @param contact new contact of address book
     */
    void saveContact(Contact contact) throws ApplicationException, AddressBookException;

    /**
     * This method delete contact of address book by id of contact.
     *
     * @param id id of contact
     */
    void deleteContactById(int id) throws ApplicationException, AddressBookException;

    /**
     * This method print all contacts of address book.
     */
    void showContacts() throws ApplicationException, AddressBookException;

    /**
     * This method update all fields the contact.
     *
     * @param contact
     * @return contact of address book
     */
    void updateContactById(Contact contact) throws ApplicationException, AddressBookException;

    /**
     * This method looking for contact by id.
     *
     * @param contactId id of contact
     * @return contact of address book
     */
    Contact getContactById(int contactId) throws ApplicationException, AddressBookException;
}
