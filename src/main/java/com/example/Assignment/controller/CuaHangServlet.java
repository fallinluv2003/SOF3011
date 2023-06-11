package com.example.Assignment.controller;

import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.entity.KhachHang;
import com.example.Assignment.service.CuaHangService;
import com.example.Assignment.service.impl.CuaHangServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CuaHangServlet", value = {
        "/assignment/cua-hang/hien-thi",
        "/assignment/cua-hang/detail",
        "/assignment/cua-hang/remove",
        "/assignment/cua-hang/update",
        "/assignment/cua-hang/add"
})
public class CuaHangServlet extends HttpServlet {

    private CuaHangService cuaHangService = new CuaHangServiceImpl();
    private List<CuaHang> list = new ArrayList<>();

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

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list = cuaHangService.getAll();

        request.setAttribute("listCH", list);
        request.getRequestDispatcher("/view/cua-hang.jsp").forward(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CuaHang ch = cuaHangService.getOne(id);

        cuaHangService.delete(ch);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/cua-hang/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CuaHang ch = cuaHangService.getOne(id);
        request.setAttribute("ch", ch);

        list = cuaHangService.getAll();
        request.setAttribute("listCV", list);
        request.getRequestDispatcher("/view/cua-hang.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CuaHang ch = new CuaHang();
            BeanUtils.populate(ch, request.getParameterMap());

            cuaHangService.update(ch);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Cập nhật thành công");
            response.sendRedirect("/assignment/cua-hang/hien-thi");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CuaHang ch = new CuaHang();
            BeanUtils.populate(ch, request.getParameterMap());

            String mess = cuaHangService.add(ch);
            if (mess.equals("true")) {
                session.setAttribute("message", "Thêm thành công");
                response.sendRedirect("/assignment/cua-hang/hien-thi");
            } else {
                session.setAttribute("message", mess);
                response.sendRedirect("/assignment/cua-hang/hien-thi");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
}
