/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.DAO.JptDAO;
import com.model.Jpt;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
@MultipartConfig
@WebServlet(name = "JptServlet", urlPatterns = {"/jpt/*"})
public class JptServlet extends HttpServlet {
    private JptDAO jptDAO;

    @Override
    public void init() {
        jptDAO = new JptDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertJpt(request, response);
                    break;
                case "/delete":
                    deleteJpt(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateJpt(request, response);
                    break;
                case "/download":
                    downloadJpt(request, response);
                    break;
                default:
                    listJpt(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listJpt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Jpt> listJpt = jptDAO.selectAllJpt();
        request.setAttribute("jptList", listJpt);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JptList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Jpt.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        Jpt existingJpt = jptDAO.selectJpt(docid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Jpt.jsp");
        request.setAttribute("jpt", existingJpt);
        dispatcher.forward(request, response);
    }

    private void insertJpt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String doctype = request.getParameter("doctype");
        Part filePart = request.getPart("file");

        InputStream fileInputStream = null;
        try {
            fileInputStream = filePart.getInputStream();
            byte[] fileBytes = fileInputStream.readAllBytes();

            String date = request.getParameter("date");
            String pic = request.getParameter("pic");

            Jpt jpt = new Jpt(doctype, fileBytes, date, pic);
            jptDAO.insertJpt(jpt);
            response.sendRedirect(request.getContextPath() + "/jpt/list");
        } catch (IOException e) {
            throw new ServletException("File upload failed", e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    private void updateJpt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        String doctype = request.getParameter("doctype");
        Part filePart = request.getPart("file");

        byte[] fileBytes = null;
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileInputStream = filePart.getInputStream();
            fileBytes = fileInputStream.readAllBytes();
        }

        String date = request.getParameter("date");
        String pic = request.getParameter("pic");

        Jpt jpt = new Jpt(docid, doctype, fileBytes, date, pic);
        jptDAO.updateJpt(jpt);
        response.sendRedirect(request.getContextPath() + "/jpt/list");
    }

    private void deleteJpt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        jptDAO.deleteJpt(docid);
        response.sendRedirect(request.getContextPath() + "/jpt/list");
    }

    private void downloadJpt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        Jpt jpt = jptDAO.selectJpt(docid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + jpt.getFilename()+ "\"");

        OutputStream out = response.getOutputStream();
        out.write(jpt.getFile());
        out.close();
    }
}
