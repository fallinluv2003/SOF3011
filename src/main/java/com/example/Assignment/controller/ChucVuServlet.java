package com.example.Assignment.controller;

import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.service.ChucVuService;
import com.example.Assignment.service.impl.ChucVuServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChucVuServlet", value = {
        "/assignment/chuc-vu/hien-thi",
        "/assignment/chuc-vu/add",
        "/assignment/chuc-vu/detail",
        "/assignment/chuc-vu/remove",
        "/assignment/chuc-vu/update"
})
public class ChucVuServlet extends HttpServlet {

    private ChucVuService cvService = new ChucVuServiceImpl();
    private List<ChucVu> list = new ArrayList<>();

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
        ChucVu cv = cvService.getOne(id);

        cvService.delete(cv);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/chuc-vu/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChucVu cv = cvService.getOne(id);
        request.setAttribute("cv", cv);

        list = cvService.getAll();
        request.setAttribute("listCV", list);
        request.getRequestDispatcher("/view/chuc-vu.jsp").forward(request, response);
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

        ChucVu chucVu = ChucVu.builder()
                .id(id)
                .ma(ma)
                .ten(ten)
                .build();

        cvService.update(chucVu);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Cập nhật thành công");
        response.sendRedirect("/assignment/chuc-vu/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String maCV = request.getParameter("maCV");
        String tenCV = request.getParameter("tenCV");

        ChucVu chucVu = ChucVu.builder()
                .ma(maCV)
                .ten(tenCV)
                .build();

        String mess = cvService.add(chucVu);
        if (mess.equals("true")) {
            session.setAttribute("message", "Thêm thành công");
            response.sendRedirect("/assignment/chuc-vu/hien-thi");
        } else {
            session.setAttribute("message", mess);
            response.sendRedirect("/assignment/chuc-vu/hien-thi");
        }
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = cvService.getAll();

        request.setAttribute("listCV", list);
        request.getRequestDispatcher("/view/chuc-vu.jsp").forward(request, response);
    }
}
