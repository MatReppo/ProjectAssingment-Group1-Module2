package com.controller;

import com.model.Profile;
import com.DAO.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login/*"})
@MultipartConfig
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM profile WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // Retrieve user details from the database
                String email = result.getString("email"); // Example retrieval, adjust as per your schema

                // Create a new object
                Profile user = new Profile(username, email, password);

                // Store the user object in session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirect to appropriate page
                String destinationUrl = "Home.jsp";
                response.sendRedirect(destinationUrl);
            } else {
                // Show error message if login fails
                String errorMessage = "Invalid username or password";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

            // Close JDBC objects
            result.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().println("Error: " + ex.getMessage());
        }
    }
}
