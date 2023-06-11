package com.example.Assignment.controller;

import com.example.Assignment.entity.ChiTietSanPham;
import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.entity.MauSac;
import com.example.Assignment.entity.NhaSanXuat;
import com.example.Assignment.entity.SanPham;
import com.example.Assignment.service.ChiTietSanPhamService;
import com.example.Assignment.service.DongSanPhamService;
import com.example.Assignment.service.MauSacService;
import com.example.Assignment.service.NhaSanXuatService;
import com.example.Assignment.service.SanPhamService;
import com.example.Assignment.service.impl.ChiTietSanPhamServiceImpl;
import com.example.Assignment.service.impl.DongSanPhamServiceImpl;
import com.example.Assignment.service.impl.MauSacServiceImpl;
import com.example.Assignment.service.impl.NhaSanXuatServiceImpl;
import com.example.Assignment.service.impl.SanPhamServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ChiTietSanPhamServlet", value = {
        "/assignment/chi-tiet-san-pham/hien-thi",
        "/assignment/chi-tiet-san-pham/detail",
        "/assignment/chi-tiet-san-pham/remove",
        "/assignment/chi-tiet-san-pham/add",
        "/assignment/chi-tiet-san-pham/update"
})
public class ChiTietSanPhamServlet extends HttpServlet {

    private ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamServiceImpl();
    private DongSanPhamService dongSanPhamService = new DongSanPhamServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private NhaSanXuatService nhaSanXuatService = new NhaSanXuatServiceImpl();
    private SanPhamService sanPhamService = new SanPhamServiceImpl();

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
        ChiTietSanPham nv = chiTietSanPhamService.getOne(id);

        chiTietSanPhamService.delete(nv);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Xóa thành công");
        response.sendRedirect("/assignment/chi-tiet-san-pham/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        request.setAttribute("ctsp", ctsp);

        this.loadTable(request, response);
    }

    private void loadTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChiTietSanPham> listCTSP = chiTietSanPhamService.getAll();
        List<DongSanPham> listDongSanPham = dongSanPhamService.getAll();
        List<MauSac> listMS = mauSacService.getAll();
        List<NhaSanXuat> listNSX = nhaSanXuatService.getAll();
        List<SanPham> listSP = sanPhamService.getAll();

        request.setAttribute("listCTSP", listCTSP);
        request.setAttribute("listDongSanPham", listDongSanPham);
        request.setAttribute("listMS", listMS);
        request.setAttribute("listNSX", listNSX);
        request.setAttribute("listSP", listSP);

        request.getRequestDispatcher("/view/chi-tiet-san-pham.jsp").forward(request, response);
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
            HashMap<String, String> ctsp = new HashMap<>();
            while (para.hasMoreElements()) {
                String paraName = (String) para.nextElement();
                String[] paraValue = request.getParameterValues(paraName);
                if (paraValue.length == 1) {
                    ctsp.put(paraName, paraValue[0]);
                }
            }
            chiTietSanPhamService.update(ctsp);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Cập nhật thành công");
            response.sendRedirect("/assignment/chi-tiet-san-pham/hien-thi");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Enumeration para = request.getParameterNames();
            HashMap<String, String> ctsp = new HashMap<>();
            while (para.hasMoreElements()) {
                String paraName = (String) para.nextElement();
                String[] paraValue = request.getParameterValues(paraName);
                if (paraValue.length == 1) {
                    ctsp.put(paraName, paraValue[0]);
                }
            }
            String mess = chiTietSanPhamService.add(ctsp);
            if (mess.equals("true")) {
                session.setAttribute("message", "Thêm thành công");
                response.sendRedirect("/assignment/chi-tiet-san-pham/hien-thi");
            } else {
                session.setAttribute("message", mess);
                response.sendRedirect("/assignment/chi-tiet-san-pham/hien-thi");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
