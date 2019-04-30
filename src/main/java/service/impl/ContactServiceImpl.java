package service.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import constants.ResponseCode;
import service.ContactService;

import java.util.Objects;
import java.util.Scanner;

import static constants.ConstantsMessages.*;

public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public Contact addContact(Scanner scanner) throws AddressBookException {
        Contact contact = new Contact();

        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contact.setName(name);

        System.out.println("Enter please sur name of your contact person:");
        String surName = scanner.next();
        contact.setLastName(surName);

        System.out.println("Enter please phone number of your contact person");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        contactDao.saveContact(contact);

        System.out.println("Thank you for saving your contact in this contact book.");

        return contact;
    }

    @Override
    public Contact getContact(Scanner scanner) throws AddressBookException {
        System.out.println("Pleas enter number of contact in your address book");
        if (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                return contactDao.getContactById(id);
            } else {
                System.out.println("You enter not number, pleas enter number");
                scanner.next();
            }
        }
        throw new AddressBookException(ResponseCode.SERVER_ERROR, "You enter wrong data,pleas enter number.");
    }

    @Override
    public Contact updateContactById(Scanner scanner) throws AddressBookException {
        Contact contact = null;
        if (scanner.hasNextInt()) {
            contact = getContactByName(scanner);
        } else {
            System.out.println(WRONG_DATA_INPUT);
            scanner.next();
        }
        System.out.println(FIELD_CHOSEN);
        return contactDao.updateContact(modifierFields(scanner, contact));
    }

    private Contact getContactByName(Scanner scanner) throws AddressBookException {
        return contactDao.getContactByName(scanner.next());
    }


    @Override
    public void deleteContact(Scanner scanner) throws AddressBookException {
        System.out.println(NUMBER_TO_DELETE_CONTACT);
        if (scanner.hasNextInt()) {
            contactDao.deleteById(scanner.nextInt());
        } else {
            System.out.println(WRONG_DATA_INPUT);
        }
    }

    @Override
    public void showContacts() {
        contactDao.showContacts();
    }

    @Override
    public void deleteContactByEntity(Scanner scanner) throws AddressBookException {
        System.out.println(NUMBER_TO_DELETE_CONTACT);
        contactDao.deleteContactByEntity(getContact(scanner));
    }

    private Contact modifierFields(Scanner scanner, Contact contact) {
        showFieldToModifier();
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            switch (number) {
                case ContactService.NAME: {
                    return modifierOneField(ContactService.NAME, contact, scanner);
                }
                case ContactService.SUR_NAME: {
                    return modifierOneField(ContactService.SUR_NAME, contact, scanner);
                }
                case ContactService.PHONE_NUMBER: {
                    return modifierOneField(ContactService.SUR_NAME, contact, scanner);
                }
                default: {
                    System.out.println(NOTHING_TO_CHANGE);
                    return contact;
                }
            }
        } else {
            System.out.println(WRONG_DATA_INPUT);
            scanner.next();
        }
        return contact;
    }

    private Contact modifierOneField(int field, Contact contact, Scanner scanner) {
        System.out.println(VALUE_OF_CHOSEN_FIELD);
        String variable = scanner.nextLine();
        switch (field) {
            case ContactService.NAME: {
                contact.setName(variable);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setLastName(variable);
                break;
            }
            case ContactService.PHONE_NUMBER: {
                contact.setPhoneNumber(variable);
                break;
            }
        }
        System.out.println("Thank you for update your contact.");
        return contact;
    }

    private void showFieldToModifier() {
        System.out.println("1.Name");
        System.out.println("2.Sur name");
        System.out.println("3.Phone number");
    }


}
