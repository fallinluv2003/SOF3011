package com.example.Assignment.service.impl;

import com.example.Assignment.entity.SanPham;
import com.example.Assignment.repository.SanPhamRepository;
import com.example.Assignment.service.SanPhamService;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository spRepo = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return spRepo.getAll();
    }

    @Override
    public SanPham getOne(String id) {
        return spRepo.getOne(id);
    }

    @Override
    public SanPham getName(String ten) {
        return spRepo.getName(ten);
    }

    @Override
    public String add(SanPham sp) {
        String ma = sp.getMa();
        List<SanPham> list = spRepo.getAll();
        for (SanPham sanPham : list) {
            if (sanPham.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        spRepo.add(sp);
        return "true";
    }

    @Override
    public Boolean update(SanPham sp) {
        if (spRepo.update(sp)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(SanPham sp) {
        if (spRepo.delete(sp)) {
            return true;
        } else {
            return false;
        }
    }
}
