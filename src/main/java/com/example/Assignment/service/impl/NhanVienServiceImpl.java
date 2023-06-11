package com.example.Assignment.service.impl;

import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.entity.NhanVien;
import com.example.Assignment.repository.ChucVuRepository;
import com.example.Assignment.repository.CuaHangRepository;
import com.example.Assignment.repository.NhanVienRepository;
import com.example.Assignment.service.NhanVienService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository nvRepo = new NhanVienRepository();
    private CuaHangRepository chRepo = new CuaHangRepository();
    private ChucVuRepository cvRepo = new ChucVuRepository();

    @Override
    public List<NhanVien> getAll() {
        return nvRepo.getAll();
    }

    @Override
    public NhanVien getOne(String id) {
        return nvRepo.getOne(id);
    }

    @Override
    public String add(HashMap<String, String> nv) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            String ma = nv.get("ma");
            String ten = nv.get("ten");
            String temDem = nv.get("tenDem");
            String ho = nv.get("ho");
            Boolean gioiTinh = nv.get("gioiTinh").equals("Nam") ? true : false;
            String ngaySinh = nv.get("ngaySinh");
            date = sdf.parse(ngaySinh);
            String diaChi = nv.get("diaChi");
            String sdt = nv.get("sdt");
            String matKhau = nv.get("matKhau");
            String cuaHang = nv.get("cuaHang");
            CuaHang ch = chRepo.getName(cuaHang);
            String chucVu = nv.get("chucVu");
            ChucVu cv = cvRepo.getName(chucVu);

            List<NhanVien> list = nvRepo.getAll();
            for (NhanVien nhanVien : list)  {
                if (nhanVien.getMa().equals(ma)) {
                    return "Mã đã tồn tại";
                }
            }

            Pattern sodienthoai = Pattern.compile("^0[1-9]{9}$");
            Matcher matcherFirst = sodienthoai.matcher(sdt);
            if (!matcherFirst.matches()) {
                return "Số điện thoại phải là đầu 0 và 10 số";
            }

            NhanVien nhanVien = NhanVien.builder()
                    .ma(ma).ten(ten).tenDem(temDem).ho(ho).gioiTinh(gioiTinh).ngaySinh(date).diaChi(diaChi)
                    .sdt(sdt).matKhau(matKhau).cuaHang(ch).chucVu(cv)
                    .build();
            nvRepo.add(nhanVien);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String update(HashMap<String, String> nv) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            String id = nv.get("id");
            String ma = nv.get("ma");
            String ten = nv.get("ten");
            String temDem = nv.get("tenDem");
            String ho = nv.get("ho");
            Boolean gioiTinh = nv.get("gioiTinh").equals("Nam") ? true : false;
            String ngaySinh = nv.get("ngaySinh");
            date = sdf.parse(ngaySinh);
            String diaChi = nv.get("diaChi");
            String sdt = nv.get("sdt");
            String matKhau = nv.get("matKhau");
            String cuaHang = nv.get("cuaHang");
            CuaHang ch = chRepo.getName(cuaHang);
            String chucVu = nv.get("chucVu");
            ChucVu cv = cvRepo.getName(chucVu);
            Integer trangThai = nv.get("trangThai").equals("1") ? 1 : 0;

            NhanVien nhanVien = NhanVien.builder()
                    .id(id).ma(ma).ten(ten).tenDem(temDem).ho(ho).gioiTinh(gioiTinh).ngaySinh(date).diaChi(diaChi)
                    .sdt(sdt).matKhau(matKhau).cuaHang(ch).chucVu(cv).trangThai(trangThai)
                    .build();
            nvRepo.update(nhanVien);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(NhanVien nv) {
        if (nvRepo.delete(nv)) {
            return true;
        } else {
            return false;
        }
    }

}
