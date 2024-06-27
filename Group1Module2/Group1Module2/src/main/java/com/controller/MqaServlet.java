/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.DAO.MqaDAO;
import com.model.Mqa;
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
@WebServlet(name = "MqaServlet", urlPatterns = {"/mqa/*"})
public class MqaServlet extends HttpServlet {
    private MqaDAO mqaDAO;

    @Override
    public void init() {
        mqaDAO = new MqaDAO();
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
                    insertMqa(request, response);
                    break;
                case "/delete":
                    deleteMqa(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMqa(request, response);
                    break;
                case "/download":
                    downloadMqa(request, response);
                    break;
                default:
                    listMqa(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMqa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Mqa> listMqa = mqaDAO.selectAllMqa();
        request.setAttribute("mqaList", listMqa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa01List.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa01.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        Mqa existingMqa = mqaDAO.selectMqa(docid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa01.jsp");
        request.setAttribute("mqa", existingMqa);
        dispatcher.forward(request, response);
    }

    private void insertMqa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String doctype = request.getParameter("doctype");
        Part filePart = request.getPart("file");

        InputStream fileInputStream = null;
        try {
            fileInputStream = filePart.getInputStream();
            byte[] fileBytes = fileInputStream.readAllBytes();

            String date = request.getParameter("date");
            String pic = request.getParameter("pic");

            Mqa mqa = new Mqa(doctype, fileBytes, date, pic);
            mqaDAO.insertMqa(mqa);
            response.sendRedirect(request.getContextPath() + "/mqa/list");
        } catch (IOException e) {
            throw new ServletException("File upload failed", e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    private void updateMqa(HttpServletRequest request, HttpServletResponse response)
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

        Mqa mqa = new Mqa(docid, doctype, fileBytes, date, pic);
        mqaDAO.updateMqa(mqa);
        response.sendRedirect(request.getContextPath() + "/mqa/list");
    }

    private void deleteMqa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        mqaDAO.deleteMqa(docid);
        response.sendRedirect(request.getContextPath() + "/mqa/list");
    }

    private void downloadMqa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int docid = Integer.parseInt(request.getParameter("docid"));
        Mqa mqa = mqaDAO.selectMqa(docid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + mqa.getFilename() + "\"");

        OutputStream out = response.getOutputStream();
        out.write(mqa.getFile());
        out.close();
    }
}
