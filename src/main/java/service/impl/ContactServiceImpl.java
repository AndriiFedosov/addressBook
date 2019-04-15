package service.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import service.ContactService;

import java.util.Objects;
import java.util.Scanner;

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
        contact.setSurName(surName);

        System.out.println("Enter please phone number of your contact person");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        contactDao.saveContact(contact);

        System.out.println("Thank you for saving your contact in this contact book.");

        return contact;
    }

    @Override
    public Contact getContact(Scanner scanner) {
        System.out.println("Pleas enter number of contact in your address book");
        int id = scanner.nextInt();
        return contactDao.getContactById(id);
    }

    @Override
    public Contact updateContactById(Scanner scanner) throws AddressBookException {
        Contact contact = new Contact();
        if (Objects.isNull(getContact(scanner))) {
            contact = getContactByName(scanner);
            if (Objects.isNull(contact)) {
                System.out.println("Contact not found;");
                System.out.println("Maybe you want to create this contact?\n1.Yes\n2.No");
                int chosing = scanner.nextInt();
                if (chosing == 1) {
                    return addContact(scanner);
                } else {
                    return null;
                }
            }
        }
        System.out.println("Enter Pleas field that you want to change:");
        return contactDao.updateContact(modifierFields(scanner, contact));
    }

    private Contact getContactByName(Scanner scanner) {
        return contactDao.getContactByName(scanner.next());
    }


    @Override
    public void deleteContact(Scanner scanner) {
        System.out.println("Enter number of contact that will be deleted:");
        contactDao.deleteById(scanner.nextInt());
    }

    @Override
    public void showContacts() {
        contactDao.showContacts();
    }

    @Override
    public void deleteContactByEntity(Scanner scanner) {
        System.out.println("Enter number of contact for deleting:");
        contactDao.deleteContactByEntity(getContact(scanner));

    }

    private Contact modifierFields(Scanner scanner, Contact contact) {
        showFieldToModifier();
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
                System.out.println("Sorry, nothing to change");
                return contact;
            }
        }
    }

    private Contact modifierOneField(int field, Contact contact, Scanner scanner) {
        System.out.println("Enter new value of chosen field:");
        String variable = scanner.nextLine();
        switch (field) {
            case ContactService.NAME: {
                contact.setName(variable);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setSurName(variable);
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
