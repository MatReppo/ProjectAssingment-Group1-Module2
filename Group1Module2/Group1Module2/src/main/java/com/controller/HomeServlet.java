package com.controller;

import com.DAO.HomeDAO;
import com.model.Home;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home/*"})
@MultipartConfig
public class HomeServlet extends HttpServlet {
    private HomeDAO homeDAO;

    @Override
    public void init() {
        homeDAO = new HomeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertHome(request, response);
                    break;
                case "/delete":
                    deleteHome(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateHome(request, response);
                    break;
                default:
                    listHome(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    List<Home> listHome = homeDAO.selectAllHome();
    request.setAttribute("listHome", listHome);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/List.jsp");
    dispatcher.forward(request, response);
}


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Home existingHome = homeDAO.selectHome(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
    request.setAttribute("home", existingHome);
    dispatcher.forward(request, response);
}


    private void insertHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    String ncr = request.getParameter("ncr");
    String courseid = request.getParameter("courseid");
    String pic = request.getParameter("pic");
    String date = request.getParameter("date");

    Home home = new Home(ncr, courseid, pic, date);
    homeDAO.insertHome(home);
    response.sendRedirect(request.getContextPath() + "/home/list");
}


    private void updateHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/home/list");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    String ncr = request.getParameter("ncr");
    String courseid = request.getParameter("courseid");
    String pic = request.getParameter("pic");
    String date = request.getParameter("date");

    Home home = new Home(id, ncr, courseid, pic, date);
    homeDAO.updateHome(home);
    response.sendRedirect(request.getContextPath() + "/home/list");
}


    private void deleteHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        homeDAO.deleteHome(id);
        response.sendRedirect(request.getContextPath() + "/home/list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
