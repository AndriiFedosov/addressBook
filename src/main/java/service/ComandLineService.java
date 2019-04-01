package service;

import dao.ContactDao;
import entity.Contact;

import java.util.Scanner;

public class ComandLineService {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ContactService service = new ContactService(new ContactDao());

    public static void showMenu() {
        System.out.println("1.Add contact;");
        System.out.println("2.Update contact;");
        System.out.println("3.Delete contact;");
        System.out.println("4.Show all contacts");
        System.out.println("0.Exit.");
    }

    public static void run() {
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
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
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
