package com.example.Assignment.controller;

import com.example.Assignment.entity.NhaSanXuat;
import com.example.Assignment.service.NhaSanXuatService;
import com.example.Assignment.service.impl.NhaSanXuatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NhaSanXuatServlet", value = {
        "/assignment/nha-san-xuat/hien-thi",
        "/assignment/nha-san-xuat/add",
        "/assignment/nha-san-xuat/detail",
        "/assignment/nha-san-xuat/remove",
        "/assignment/nha-san-xuat/update"
})
public class NhaSanXuatServlet extends HttpServlet {

    private NhaSanXuatService nsxService = new NhaSanXuatServiceImpl();
    private List<NhaSanXuat> list = new ArrayList<>();

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
        NhaSanXuat nsx = nsxService.getOne(id);

        nsxService.delete(nsx);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/nha-san-xuat/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhaSanXuat nsx = nsxService.getOne(id);
        request.setAttribute("nsx", nsx);

        list = nsxService.getAll();
        request.setAttribute("listNSX", list);
        request.getRequestDispatcher("/view/nha-san-xuat.jsp").forward(request, response);
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

        NhaSanXuat mauSac = NhaSanXuat.builder()
                .id(id)
                .ma(ma)
                .ten(ten)
                .build();

        nsxService.update(mauSac);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Cập nhật thành công");
        response.sendRedirect("/assignment/nha-san-xuat/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        NhaSanXuat mauSac = NhaSanXuat.builder()
                .ma(ma)
                .ten(ten)
                .build();

        String mess = nsxService.add(mauSac);
        if (mess.equals("true")) {
            session.setAttribute("message", "Thêm thành công");
            response.sendRedirect("/assignment/nha-san-xuat/hien-thi");
        } else {
            session.setAttribute("message", mess);
            response.sendRedirect("/assignment/nha-san-xuat/hien-thi");
        }
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = nsxService.getAll();

        request.setAttribute("listNSX", list);
        request.getRequestDispatcher("/view/nha-san-xuat.jsp").forward(request, response);
    }
}
