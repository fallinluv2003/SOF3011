package com.example.Assignment.controller;

import com.example.Assignment.entity.MauSac;
import com.example.Assignment.service.MauSacService;
import com.example.Assignment.service.impl.MauSacServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MauSacServlet", value = {
        "/assignment/mau-sac/hien-thi",
        "/assignment/mau-sac/add",
        "/assignment/mau-sac/detail",
        "/assignment/mau-sac/remove",
        "/assignment/mau-sac/update"
})
public class MauSacServlet extends HttpServlet {

    private MauSacService msService = new MauSacServiceImpl();
    private List<MauSac> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.loadTable(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("remove")) {
            this.remove(request, response);
        } else {
            this.loadTable(request, response);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MauSac ms = msService.getOne(id);

        msService.delete(ms);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/mau-sac/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MauSac ms = msService.getOne(id);
        request.setAttribute("ms", ms);

        list = msService.getAll();
        request.setAttribute("listMS", list);
        request.getRequestDispatcher("/view/mau-sac.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.add(request, response);
        } else {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        MauSac mauSac = MauSac.builder()
                .id(id)
                .ma(ma)
                .ten(ten)
                .build();

        msService.update(mauSac);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Cập nhật thành công");
        response.sendRedirect("/assignment/mau-sac/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        MauSac mauSac = MauSac.builder()
                .ma(ma)
                .ten(ten)
                .build();

        String mess = msService.add(mauSac);
        if (mess.equals("true")) {
            session.setAttribute("message", "Thêm thành công");
            response.sendRedirect("/assignment/mau-sac/hien-thi");
        } else {
            session.setAttribute("message", mess);
            response.sendRedirect("/assignment/mau-sac/hien-thi");
        }
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = msService.getAll();
        request.setAttribute("listMS", list);
        request.getRequestDispatcher("/view/mau-sac.jsp").forward(request, response);
    }
}
