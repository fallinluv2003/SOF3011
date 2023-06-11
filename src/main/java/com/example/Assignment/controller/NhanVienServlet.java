package com.example.Assignment.controller;

import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.entity.NhanVien;
import com.example.Assignment.service.ChucVuService;
import com.example.Assignment.service.CuaHangService;
import com.example.Assignment.service.NhanVienService;
import com.example.Assignment.service.impl.ChucVuServiceImpl;
import com.example.Assignment.service.impl.CuaHangServiceImpl;
import com.example.Assignment.service.impl.NhanVienServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "NhanVienServlet", value = {
        "/assignment/nhan-vien/hien-thi",
        "/assignment/nhan-vien/add",
        "/assignment/nhan-vien/detail",
        "/assignment/nhan-vien/remove",
        "/assignment/nhan-vien/update"
})
public class NhanVienServlet extends HttpServlet {

    private NhanVienService nvService = new NhanVienServiceImpl();
    private CuaHangService cuaHangService = new CuaHangServiceImpl();
    private ChucVuService chucVuService = new ChucVuServiceImpl();
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private List<CuaHang> listCuaHang = new ArrayList<>();
    private List<ChucVu> listChucVu = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("hien-thi")) {
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
        NhanVien nv = nvService.getOne(id);

        nvService.delete(nv);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/nhan-vien/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhanVien nv = nvService.getOne(id);
        request.setAttribute("nv", nv);

        this.loadTable(request, response);
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listNhanVien = nvService.getAll();
        listCuaHang = cuaHangService.getAll();
        listChucVu = chucVuService.getAll();

        request.setAttribute("listNV", listNhanVien);
        request.setAttribute("listCH", listCuaHang);
        request.setAttribute("listCV", listChucVu);
        request.getRequestDispatcher("/view/nhan-vien.jsp").forward(request, response);
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
        try {
            Enumeration para = request.getParameterNames();
            HashMap<String, String> nhanVien = new HashMap<>();
            while (para.hasMoreElements()) {
                String paraName = (String) para.nextElement();
                String[] paraValue = request.getParameterValues(paraName);
                if (paraValue.length == 1) {
                    nhanVien.put(paraName, paraValue[0]);
                }
            }
            nvService.update(nhanVien);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Cập nhật thành công");
            response.sendRedirect("/assignment/nhan-vien/hien-thi");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Enumeration para = request.getParameterNames();
            HashMap<String, String> nhanVien = new HashMap<>();
            while (para.hasMoreElements()) {
                String paraName = (String) para.nextElement();
                String[] paraValue = request.getParameterValues(paraName);
                if (paraValue.length == 1) {
                    nhanVien.put(paraName, paraValue[0]);
                }
            }
            String mess = nvService.add(nhanVien);
            if (mess.equals("true")) {
                session.setAttribute("message", "Thêm thành công");
                response.sendRedirect("/assignment/nhan-vien/hien-thi");
            } else {
                session.setAttribute("message", mess);
                response.sendRedirect("/assignment/nhan-vien/hien-thi");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
