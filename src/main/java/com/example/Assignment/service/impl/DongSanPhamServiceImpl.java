package com.example.Assignment.service.impl;

import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.repository.DongSanPhamRepository;
import com.example.Assignment.service.DongSanPhamService;

import java.util.List;

public class DongSanPhamServiceImpl implements DongSanPhamService {

    private DongSanPhamRepository dongSPRepo = new DongSanPhamRepository();

    @Override
    public List<DongSanPham> getAll() {
        return dongSPRepo.getAll();
    }

    @Override
    public DongSanPham getOne(String id) {
        return dongSPRepo.getOne(id);
    }

    @Override
    public DongSanPham getName(String ten) {
        return dongSPRepo.getName(ten);
    }

    @Override
    public String add(DongSanPham dongSanPham) {
        String ma = dongSanPham.getMa();
        List<DongSanPham> list = dongSPRepo.getAll();
        for (DongSanPham dongSP : list) {
            if (dongSP.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        dongSPRepo.add(dongSanPham);
        return "true";
    }

    @Override
    public Boolean update(DongSanPham dongSanPham) {
        if (dongSPRepo.update(dongSanPham)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(DongSanPham dongSanPham) {
        if (dongSPRepo.delete(dongSanPham)) {
            return true;
        } else {
            return false;
        }
    }
}
