package com.example.Assignment.service.impl;

import com.example.Assignment.entity.ChiTietSanPham;
import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.entity.MauSac;
import com.example.Assignment.entity.NhaSanXuat;
import com.example.Assignment.entity.SanPham;
import com.example.Assignment.repository.ChiTietSanPhamRepository;
import com.example.Assignment.repository.DongSanPhamRepository;
import com.example.Assignment.repository.MauSacRepository;
import com.example.Assignment.repository.NhaSanXuatRepository;
import com.example.Assignment.repository.SanPhamRepository;
import com.example.Assignment.service.ChiTietSanPhamService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private NhaSanXuatRepository nhaSanXuatRepository = new NhaSanXuatRepository();
    private MauSacRepository mauSacRepository = new MauSacRepository();
    private DongSanPhamRepository dongSanPhamRepository = new DongSanPhamRepository();

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.getAll();
    }

    @Override
    public ChiTietSanPham getOne(String id) {
        return chiTietSanPhamRepository.getOne(id);
    }

    @Override
    public String add(HashMap<String, String> ctsp) {
        try {
            String sp = ctsp.get("sanPham");
            SanPham sanPham = sanPhamRepository.getName(sp);
            String nsx = ctsp.get("nhaSanXuat");
            NhaSanXuat nhaSanXuat = nhaSanXuatRepository.getName(nsx);
            String ms = ctsp.get("mauSac");
            MauSac mauSac = mauSacRepository.getName(ms);
            String dongSP = ctsp.get("dongSanPham");
            DongSanPham dongSanPham = dongSanPhamRepository.getName(dongSP);
            Integer namBH = Integer.valueOf(ctsp.get("namBH"));
            String moTa = ctsp.get("moTa");
            Integer soLuongTon = Integer.valueOf(ctsp.get("soLuong"));
            String giaNhap = ctsp.get("giaNhap");
            String giaBan = ctsp.get("giaBan");

            if (namBH < 1) {
               return "Năm BH phải là số nguyên dương";
            } else if (soLuongTon < 1) {
                return "Số lượng phải là số nguyên dương";
            } else if (Double.parseDouble(giaBan) < 1) {
                return "Giá bán phải lớn hơn 0";
            } else if (Double.parseDouble(giaNhap) < 1) {
                return "Giá nhập phải lớn hơn 0";
            } else if (Double.valueOf(giaBan) < Double.valueOf(giaNhap)) {
                return "Giá bán phải lớn hơn giá nhập";
            } else {

            }

            ChiTietSanPham chiTietSanPham = ChiTietSanPham.builder()
                    .sanPham(sanPham).nhaSanXuat(nhaSanXuat).mauSac(mauSac).dongSanPham(dongSanPham)
                    .namBH(namBH).moTa(moTa).soLuongTon(soLuongTon).giaNhap(BigDecimal.valueOf(Double.valueOf(giaNhap)))
                    .giaBan(BigDecimal.valueOf(Double.valueOf(giaBan))).build();
            chiTietSanPhamRepository.add(chiTietSanPham);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String update(HashMap<String, String> ctsp) {
        try {
            String id = ctsp.get("id");
            String sp = ctsp.get("sanPham");
            SanPham sanPham = sanPhamRepository.getName(sp);
            String nsx = ctsp.get("nhaSanXuat");
            NhaSanXuat nhaSanXuat = nhaSanXuatRepository.getName(nsx);
            String ms = ctsp.get("mauSac");
            MauSac mauSac = mauSacRepository.getName(ms);
            String dongSP = ctsp.get("dongSanPham");
            DongSanPham dongSanPham = dongSanPhamRepository.getName(dongSP);
            String namBH = ctsp.get("namBH");
            String moTa = ctsp.get("moTa");
            String soLuongTon = ctsp.get("soLuong");
            BigDecimal giaNhap = BigDecimal.valueOf(Double.valueOf(ctsp.get("giaNhap")));
            BigDecimal giaBan = BigDecimal.valueOf(Double.valueOf(ctsp.get("giaBan")));

            ChiTietSanPham chiTietSanPham = ChiTietSanPham.builder()
                    .id(id).sanPham(sanPham).nhaSanXuat(nhaSanXuat).mauSac(mauSac).dongSanPham(dongSanPham)
                    .namBH(Integer.valueOf(namBH)).moTa(moTa).soLuongTon(Integer.valueOf(soLuongTon)).giaNhap(giaNhap).giaBan(giaBan)
                    .build();
            chiTietSanPhamRepository.update(chiTietSanPham);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(ChiTietSanPham ctsp) {
        if (chiTietSanPhamRepository.delete(ctsp)) {
            return true;
        } else {
            return false;
        }
    }

}
