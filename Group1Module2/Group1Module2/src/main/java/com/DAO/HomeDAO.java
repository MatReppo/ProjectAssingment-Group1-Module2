package com.DAO;

import java.sql.*;
import java.util.*;
import com.model.Home;

public class HomeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/proaccreditation";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    private static final String INSERT_HOME_SQL = "INSERT INTO home (ncr, courseid, pic, date) VALUES (?, ?, ?, ?)";
    private static final String SELECT_HOME_BY_ID = "SELECT id, ncr, courseid, pic, date FROM home WHERE id = ?";
    private static final String SELECT_ALL_HOME = "SELECT * FROM home";
    private static final String DELETE_HOME_SQL = "DELETE FROM home WHERE id = ?";
    private static final String UPDATE_HOME_SQL = "UPDATE home SET ncr = ?, courseid = ?, pic = ?, date = ? WHERE id = ?";

    public HomeDAO() {}

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

    public void insertHome(Home home) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOME_SQL)) {
            preparedStatement.setString(1, home.getNcr());
            preparedStatement.setString(2, home.getCourseid());
            preparedStatement.setString(3, home.getPic());
            preparedStatement.setString(4, home.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Home selectHome(int id) {
        Home home = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ncr = rs.getString("ncr");
                String courseid = rs.getString("courseid");
                String pic = rs.getString("pic");
                String date = rs.getString("date");
                home = new Home(id, ncr, courseid, pic, date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return home;
    }

    public List<Home> selectAllHome() {
        List<Home> homes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HOME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ncr = rs.getString("ncr");
                String courseid = rs.getString("courseid");
                String pic = rs.getString("pic");
                String date = rs.getString("date");
                homes.add(new Home(id, ncr, courseid, pic, date));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return homes;
    }

    public boolean deleteHome(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_HOME_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateHome(Home home) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_HOME_SQL)) {
            statement.setString(1, home.getNcr());
            statement.setString(2, home.getCourseid());
            statement.setString(3, home.getPic());
            statement.setString(4, home.getDate());
            statement.setInt(5, home.getId());
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
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
