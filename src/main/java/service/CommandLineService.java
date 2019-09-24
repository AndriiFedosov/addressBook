package service;

import constants.MassageApp;
import constants.ResponseCode;
import dao.impl.ConnectionDB;
import exception.AddressBookException;
import service.impl.ContactServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

public interface CommandLineService {

    /**
     * Method displayed actions menu.
     */
    static void showMenu() {
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact. ");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contact.");
        System.out.println("0.Exit.;");
    }

    /**
     * Method is start work application
     *
     * @param readerKeyboard takes keyboard input
     * @param service        pass readerKeyboard for implementation
     */
    static void run(BufferedReader readerKeyboard, ContactServiceImpl service) {

        int numberOfMenu;
        boolean exit = true;
        do {
            System.out.println("Choose action: ");
            showMenu();

            try {
                String stringTemp = readerKeyboard.readLine();
                if (isCorrectInteger(stringTemp)) {
                    numberOfMenu = Integer.parseInt(stringTemp);
                    switch (numberOfMenu) {
                        case 1: {
                            service.addContact(readerKeyboard);
                            break;
                        }
                        case 2: {
                            service.updateContact(readerKeyboard);
                            break;
                        }
                        case 3: {
                            service.deleteContact(readerKeyboard);
                            break;
                        }
                        case 4: {
                            service.showAllContacts(readerKeyboard);
                            break;
                        }
                        case 0: {
                            System.out.println("Thank you that used our app. Good bay.");
                            exit = false;
                            ConnectionDB.closeConection();
                            break;
                        }
                        default: {
                            System.out.println("You entered invalid number. Repeat please.");
                        }
                    }
                }
            } catch (AddressBookException | IOException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while (exit);
    }

    static boolean isCorrectInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE + " " + MassageApp.DATA_TYPE_IS_NOT_NUMBER);
            return false;
        }
        return true;
    }

    static boolean isCorrectDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE + " " + MassageApp.DATA_TYPE_IS_NOT_NUMBER);
            return false;
        }
        return true;
    }
}

