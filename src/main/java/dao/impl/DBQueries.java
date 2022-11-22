package dao.impl;

public class DBQueries {
    public static final String INSERT_CONTACT = "insert into contacts " +
            "(name, last_name, phone_number, age,  married, create_data_time, update_data_time) " +
            "VALUES (?,?,?,?,?,?,?)";

    public static final String UPDATE_CONTACT_WHERE_ID = "UPDATE contacts " +
            "SET name = ?, last_name = ?, phone_number = ?, age = ?, married = ? ,update_data_time=? " +
            "WHERE id = ?";
    public static final String DELETE_CONTACT_BY_ID = "DELETE FROM contacts WHERE id = ?";

    public static final String GET_CONTACT_WHERE_ID = "SELECT FROM contacts WHERE id = ?";

    public static final String SELECT_ALL = "select * from contacts";
}
