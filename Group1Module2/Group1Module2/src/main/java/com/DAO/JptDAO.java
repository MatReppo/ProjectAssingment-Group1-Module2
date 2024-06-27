package com.DAO;

import com.model.Jpt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JptDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/proaccreditation";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    private static final String INSERT_JPTS_SQL = "INSERT INTO jpt (doctype, file, date, pic) VALUES (?, ?, ?, ?)";
    private static final String SELECT_JPT_BY_ID = "SELECT docid, doctype, file, date, pic FROM jpt WHERE docid = ?";
    private static final String SELECT_ALL_JPTS = "SELECT * FROM jpt";
    private static final String DELETE_JPT_SQL = "DELETE FROM jpt WHERE docid = ?";
    private static final String UPDATE_JPT_SQL = "UPDATE jpt SET doctype = ?, file = ?, date = ?, pic = ? WHERE docid = ?";

    public JptDAO() {}

    protected Connection getConnection() {
    Connection connection = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return connection;
}


    public void insertJpt(Jpt jpt) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = getConnection();
        preparedStatement = connection.prepareStatement(INSERT_JPTS_SQL);

        preparedStatement.setString(1, jpt.getDoctype());
        preparedStatement.setBytes(2, jpt.getFile());
        preparedStatement.setString(3, jpt.getDate());
        preparedStatement.setString(4, jpt.getPic());

        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        printSQLException(e);
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}


    public Jpt selectJpt(int docid) {
        Jpt jpt = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JPT_BY_ID)) {
            preparedStatement.setInt(1, docid);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String doctype = rs.getString("doctype");
                byte[] file = rs.getBytes("file");
                String date = rs.getString("date");
                String pic = rs.getString("pic");
                jpt = new Jpt(docid, doctype, file, date, pic);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return jpt;
    }

    public List<Jpt> selectAllJpt() {
        List<Jpt> jpts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JPTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int docid = rs.getInt("docid");
                String doctype = rs.getString("doctype");
                byte[] file = rs.getBytes("file");
                String date = rs.getString("date");
                String pic = rs.getString("pic");
                jpts.add(new Jpt(docid, doctype, file, date, pic));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return jpts;
    }

    public boolean deleteJpt(int docid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_JPT_SQL)) {
            statement.setInt(1, docid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateJpt(Jpt jpt) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_JPT_SQL)) {
            statement.setString(1, jpt.getDoctype());
            statement.setBytes(2, jpt.getFile());
            statement.setString(3, jpt.getDate());
            statement.setString(4, jpt.getPic());
            statement.setInt(5, jpt.getDocid());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
