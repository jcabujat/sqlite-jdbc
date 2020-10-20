package com.jcabujat;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\jonat\\IdeaProjects\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACT = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {

//            conn.setAutoCommit(false);

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACT);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACT +
                    "(" + COLUMN_NAME + " TEXT, " +
                    COLUMN_PHONE + " INTEGER, " +
                    COLUMN_EMAIL + " TEXT)");
            insertContact(statement, "Tim", 6545678, "tim@email.com");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    " VALUES ('Tim', 6545678, 'tim@email.com')");

            insertContact(statement, "Joe", 45632, "joe@anywhere.com");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    " VALUES ('Joe', 45632, 'joe@anywhere.com')");

            insertContact(statement, "Jane", 4829484, "jane@somewhere.com");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    " VALUES ('Jane', 4829484, 'jane@somewhere.com')");

            insertContact(statement, "Fido", 9038, "dog@email.com");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    " VALUES ('Fido', 9038, 'dog@email.com')");

//            statement.execute("UPDATE contacts SET phone = 5566789 WHERE name = 'Jane'");
//            statement.execute("DELETE FROM contacts WHERE name = 'Joe'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet resultSet = statement.getResultSet();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACT);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " " +
                        resultSet.getInt("phone") + " " +
                        resultSet.getString("email"));
//                System.out.println(resultSet.getString(1) + " " +
//                        resultSet.getInt(2) + " " +
//                        resultSet.getString(3));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACT + " (" +
                COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL +
                ") VALUES ('" + name +  "', " + phone + ", '" + email + "')");
    }
}
