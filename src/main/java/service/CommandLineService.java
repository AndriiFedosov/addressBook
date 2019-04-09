package service;

import java.util.Scanner;

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
            System.out.println("Chose your wish:");
            showMenu();
            int numberOfMenu = scanner.nextInt();
            switch (numberOfMenu) {
                case 1: {
                    service.addContact(scanner);
                    break;
                }
                case 2: {
                    System.out.println(service.updateContactById(scanner));
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
                    System.out.println("Sorry. You enter wrong number of menu.Chose another number.");
                }
            }
        } while (exit);
    }
}
