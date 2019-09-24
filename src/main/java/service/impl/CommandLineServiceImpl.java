package service.impl;

import dao.impl.ConnectionDB;
import dao.impl.ContactDaoImpl;
import service.CommandLineService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineServiceImpl implements CommandLineService {

    private static final BufferedReader readerKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void start() {
        ConnectionDB.connectAndCreateDataBase();
        CommandLineService.run(readerKeyboard, service);
    }
}