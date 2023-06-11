package com.example.Assignment.controller;

import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.service.DongSanPhamService;
import com.example.Assignment.service.impl.DongSanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DongSPServlet", value = {
        "/assignment/dong-san-pham/hien-thi",
        "/assignment/dong-san-pham/add",
        "/assignment/dong-san-pham/detail",
        "/assignment/dong-san-pham/remove",
        "/assignment/dong-san-pham/update"
})
public class DongSanPhamServlet extends HttpServlet {

    private DongSanPhamService dongSanPhamService = new DongSanPhamServiceImpl();
    private List<DongSanPham> list = new ArrayList<>();

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
        DongSanPham dongSanPham = dongSanPhamService.getOne(id);

        dongSanPhamService.delete(dongSanPham);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/dong-san-pham/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        DongSanPham dongSanPham = dongSanPhamService.getOne(id);
        request.setAttribute("dongSP", dongSanPham);

        list = dongSanPhamService.getAll();
        request.setAttribute("listDongSanPham", list);
        request.getRequestDispatcher("/view/dong-san-pham.jsp").forward(request, response);
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

        DongSanPham dongSanPham = DongSanPham.builder()
                .id(id)
                .ma(ma)
                .ten(ten)
                .build();

        dongSanPhamService.update(dongSanPham);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Cập nhật thành công");
        response.sendRedirect("/assignment/dong-san-pham/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        DongSanPham dongSanPham = DongSanPham.builder()
                .ma(ma)
                .ten(ten)
                .build();

        String mess = dongSanPhamService.add(dongSanPham);
        if (mess.equals("true")) {
            session.setAttribute("message", "Thêm thành công");
            response.sendRedirect("/assignment/dong-san-pham/hien-thi");
        } else {
            session.setAttribute("message", mess);
            response.sendRedirect("/assignment/dong-san-pham/hien-thi");
        }
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = dongSanPhamService.getAll();

        request.setAttribute("listDongSanPham", list);
        request.getRequestDispatcher("/view/dong-san-pham.jsp").forward(request, response);
    }
}
