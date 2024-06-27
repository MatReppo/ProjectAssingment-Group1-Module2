package com.DAO;

import com.model.Mqa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MqaDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/proaccreditation";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    private static final String INSERT_MQAS_SQL = "INSERT INTO mqa (doctype, file, date, pic) VALUES (?, ?, ?, ?)";
    private static final String SELECT_MQA_BY_ID = "SELECT docid, doctype, file, date, pic FROM mqa WHERE docid = ?";
    private static final String SELECT_ALL_MQAS = "SELECT * FROM mqa";
    private static final String DELETE_MQA_SQL = "DELETE FROM mqa WHERE docid = ?";
    private static final String UPDATE_MQA_SQL = "UPDATE mqa SET doctype = ?, file = ?, date = ?, pic = ? WHERE docid = ?";

    public MqaDAO() {}

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


    public void insertMqa(Mqa mqa) throws SQLException {
    Connection connection = null;
    PreparedStatement st = null;

    try {
        connection = getConnection();
        st = connection.prepareStatement(INSERT_MQAS_SQL);

        st.setString(1, mqa.getDoctype());
        st.setBytes(2, mqa.getFile());
        st.setString(3, mqa.getDate());
        st.setString(4, mqa.getPic());

        st.executeUpdate();
    } catch (SQLException e) {
        printSQLException(e);
    } finally {
        if (st != null) {
            st.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}


    public Mqa selectMqa(int docid) {
        Mqa mqa = null;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_MQA_BY_ID)) {
            st.setInt(1, docid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String doctype = rs.getString("doctype");
                byte[] file = rs.getBytes("file");
                String date = rs.getString("date");
                String pic = rs.getString("pic");
                mqa = new Mqa(docid, doctype, file, date, pic);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mqa;
    }

    public List<Mqa> selectAllMqa() {
        List<Mqa> mqas = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_ALL_MQAS)) {
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int docid = rs.getInt("docid");
                String doctype = rs.getString("doctype");
                byte[] file = rs.getBytes("file");
                String date = rs.getString("date");
                String pic = rs.getString("pic");
                mqas.add(new Mqa(docid, doctype, file, date, pic));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mqas;
    }

    public boolean deleteMqa(int docid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(DELETE_MQA_SQL)) {
            st.setInt(1, docid);
            rowDeleted = st.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateMqa(Mqa mqa) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_MQA_SQL)) {
            st.setString(1, mqa.getDoctype());
            st.setBytes(2, mqa.getFile());
            st.setString(3, mqa.getDate());
            st.setString(4, mqa.getPic());
            st.setInt(5, mqa.getDocid());
            rowUpdated = st.executeUpdate() > 0;
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
