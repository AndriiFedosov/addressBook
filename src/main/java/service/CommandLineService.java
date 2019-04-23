package service;

import entity.Contact;
import exception.AddressBookException;
import constants.ResponseCode;

import java.util.Objects;
import java.util.Scanner;

import static constants.ConstantsMessages.NOTHING_TO_UPDATE;

public interface CommandLineService {
    /**
     * Method that show items of menu.
     */
    static void showMenu() {
        System.out.println("1.Add contact;");
        System.out.println("2.Update contact;");
        System.out.println("3.Delete contact;");
        System.out.println("4.Show all contacts");
        System.out.println("5.Show contact by id");
        System.out.println("0.Exit.");
    }

    /**
     * Endpoint for begin our program.
     *
     * @param scanner scanner of console input
     * @param service service that work with dao layer.
     */
    static void run(Scanner scanner, ContactService service) {
        boolean exit = true;
        do {
            try {
                System.out.println("Chose your wish:");
                showMenu();
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1: {
                            service.addContact(scanner);
                            break;
                        }
                        case 2: {
                            Contact contact = service.updateContactById(scanner);
                            if(Objects.nonNull(contact.getId())){
                                System.out.println(contact);
                            }else {
                                System.out.println(NOTHING_TO_UPDATE);
                            }

                            break;
                        }
                        case 3: {
                            service.deleteContact(scanner);
                            break;
                        }
                        case 4: {
                            service.showContacts();
                            break;
                        }
                        case 5: {
                            System.out.println(service.getContact(scanner));
                            break;
                        }
                        case 0: {
                            System.out.println("Thank you that use our app. Good bye.");
                            exit = false;
                            break;
                        }
                        default: {
                            throw new AddressBookException(ResponseCode.WRONG_DATA_TYPE,
                                    "You enter wrong number of operation.");
                        }
                    }
                } else {
                    System.out.println("you enter wrong number");
                    scanner.next();
                }
            } catch (AddressBookException e) {
                System.out.println(e.getMessage());
            }
        } while (exit);
    }
}
