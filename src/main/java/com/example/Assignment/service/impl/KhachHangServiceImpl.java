package com.example.Assignment.service.impl;

import com.example.Assignment.entity.KhachHang;
import com.example.Assignment.repository.KhachHangRepository;
import com.example.Assignment.service.KhachHangService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khRepo = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return khRepo.getAll();
    }

    @Override
    public KhachHang getOne(String id) {
        return khRepo.getOne(id);
    }

    @Override
    public String add(KhachHang kh) {
        String ma = kh.getMa();
        String sdt = kh.getSdt();
        Pattern sodienthoai = Pattern.compile("^0[1-9]{9}$");
        Matcher matcherFirst = sodienthoai.matcher(sdt);

        List<KhachHang> list = khRepo.getAll();
        for (KhachHang khachHang : list) {
            if (khachHang.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }

        if (!matcherFirst.matches()) {
            return "Số điện thoại phải bắt đầu là 0 và 10 số";
        }

        khRepo.add(kh);
        return "true";
    }

    @Override
    public Boolean update(KhachHang kh) {
        if (khRepo.update(kh)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(KhachHang kh) {
        if (khRepo.delete(kh)) {
            return true;
        } else {
            return false;
        }
    }
}
