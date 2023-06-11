package com.example.Assignment.service.impl;

import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.repository.CuaHangRepository;
import com.example.Assignment.service.CuaHangService;

import java.util.List;

public class CuaHangServiceImpl implements CuaHangService {

    private CuaHangRepository chRepo = new CuaHangRepository();

    @Override
    public List<CuaHang> getAll() {
        return chRepo.getAll();
    }

    @Override
    public CuaHang getOne(String id) {
        return chRepo.getOne(id);
    }

    @Override
    public CuaHang getName(String ten) {
        return chRepo.getName(ten);
    }

    @Override
    public String add(CuaHang ch) {
        String ma = ch.getMa();
        List<CuaHang> list = chRepo.getAll();
        for (CuaHang cuaHang : list) {
            if (cuaHang.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        chRepo.add(ch);
        return "true";
    }

    @Override
    public Boolean update(CuaHang ch) {
        if (chRepo.update(ch)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(CuaHang ch) {
        if (chRepo.delete(ch)) {
            return true;
        } else {
            return false;
        }
    }
}
