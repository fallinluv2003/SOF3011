package com.example.Assignment.controller;

import com.example.Assignment.entity.SanPham;
import com.example.Assignment.service.SanPhamService;
import com.example.Assignment.service.impl.SanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SanPhamServlet", value = {
        "/assignment/san-pham/hien-thi",
        "/assignment/san-pham/add",
        "/assignment/san-pham/detail",
        "/assignment/san-pham/remove",
        "/assignment/san-pham/update"
})
public class SanPhamServlet extends HttpServlet {

    private SanPhamService spService = new SanPhamServiceImpl();
    private List<SanPham> list = new ArrayList<>();

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
        SanPham sp = spService.getOne(id);

        spService.delete(sp);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/san-pham/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        SanPham sp = spService.getOne(id);
        request.setAttribute("sp", sp);

        list = spService.getAll();
        request.setAttribute("listSP", list);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
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

        SanPham mauSac = SanPham.builder()
                .id(id)
                .ma(ma)
                .ten(ten)
                .build();

        spService.update(mauSac);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Cập nhật thành công");
        response.sendRedirect("/assignment/san-pham/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        SanPham mauSac = SanPham.builder()
                .ma(ma)
                .ten(ten)
                .build();

        String mess = spService.add(mauSac);
        if (mess.equals("true")) {
            session.setAttribute("message", "Thêm thành công");
            response.sendRedirect("/assignment/san-pham/hien-thi");
        } else {
            session.setAttribute("message", mess);
            response.sendRedirect("/assignment/san-pham/hien-thi");
        }
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = spService.getAll();

        request.setAttribute("listSP", list);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }
}
