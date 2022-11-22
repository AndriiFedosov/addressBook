package dao.impl;

import constants.MassageApp;
import constants.ResponseCode;
import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import service.CommandLineService;

import java.sql.*;
import java.time.LocalDateTime;

import static dao.impl.DBQueries.*;

public class ContactDaoImpl extends ConnectionDB implements ContactDao, CommandLineService {

    private static final Connection connection = ConnectionDB.getConnect();

    public void saveContact(Contact contact) throws AddressBookException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setBoolean(5, contact.isMarried());
            preparedStatement.setString(6, contact.getCreateDate().toString());
            preparedStatement.setString(7, contact.getUpdateTime().toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new AddressBookException(ResponseCode.FAILED_SAVE_DB, MassageApp.FIELD_SAVE_DB);
        }
    }

    @Override
    public void deleteContactById(int contactId) throws AddressBookException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT_BY_ID)) {
            preparedStatement.setInt(1, contactId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AddressBookException(ResponseCode.FAILED_DELETE_DB, MassageApp.FIELD_DELETE_DB);
        }
    }

    @Override
    public Contact getContactById(int contactId) throws AddressBookException {
        Contact contactForUpdata = null;
        try (Statement statement = connection.createStatement()) {
            statement.execute(GET_CONTACT_WHERE_ID + contactId);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                contactForUpdata = new Contact();
                contactForUpdata.setId(resultSet.getInt(1));
                contactForUpdata.setName(resultSet.getString(2));
                contactForUpdata.setLastName(resultSet.getString(3));
                contactForUpdata.setPhoneNumber(resultSet.getString(4));
                contactForUpdata.setAge(resultSet.getInt(5));
                contactForUpdata.setMarried(resultSet.getBoolean(6));
                contactForUpdata.setCreateDate(LocalDateTime.parse(resultSet.getString(7)));
                contactForUpdata.setUpdateTime(LocalDateTime.parse(resultSet.getString(8)));
            }
            if (contactForUpdata == null) {
                throw new AddressBookException(ResponseCode.NOT_FOUND, MassageApp.THERE_IS_NOT_ID);
            } else {
                return contactForUpdata;
            }
        } catch (SQLException e) {
            throw new AddressBookException(ResponseCode.FAILED_GET_DB, MassageApp.FIELD_GET_DB);
        }
    }

    public void showContacts() throws AddressBookException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(SELECT_ALL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(
                        "ID: " + resultSet.getInt(1) + "  " +
                                "NAME: " + resultSet.getString(2) + "  " +
                                "SURNAME: " + resultSet.getString(3) + "  " +
                                "PHONE NUMBER: " + resultSet.getString(4) + "  " +
                                "AGE: " + resultSet.getInt(5) + "  " +
                                "HEIGHT: " + resultSet.getDouble(6) + "  " +
                                "MARRIED: " + resultSet.getBoolean(7) + "  " +
                                "CREATE DATE: " + resultSet.getString(8));
            }
            System.out.println("That's ALL!");
        } catch (SQLException e) {
            throw new AddressBookException(ResponseCode.FAILED_GET_DATA, MassageApp.FIELD_GET_DATA);
        }
    }

    @Override
    public void updateContactById(Contact contact) throws AddressBookException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT_WHERE_ID)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setBoolean(5, contact.isMarried());
            preparedStatement.setString(6, contact.getUpdateTime().toString());
            preparedStatement.setInt(7, contact.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new AddressBookException(ResponseCode.FAILED_UPDATE_DB, MassageApp.FIELD_UPDATE_DB);
        }
    }
}
