package com.example.Assignment.service.impl;

import com.example.Assignment.entity.NhaSanXuat;
import com.example.Assignment.repository.NhaSanXuatRepository;
import com.example.Assignment.service.NhaSanXuatService;

import java.util.List;

public class NhaSanXuatServiceImpl implements NhaSanXuatService {

    private NhaSanXuatRepository nsxRepo = new NhaSanXuatRepository();

    @Override
    public List<NhaSanXuat> getAll() {
        return nsxRepo.getAll();
    }

    @Override
    public NhaSanXuat getOne(String id) {
        return nsxRepo.getOne(id);
    }

    @Override
    public NhaSanXuat getName(String ten) {
        return nsxRepo.getName(ten);
    }

    @Override
    public String add(NhaSanXuat nsx) {
        String ma = nsx.getMa();
        List<NhaSanXuat> list = nsxRepo.getAll();
        for (NhaSanXuat nhaSanXuat : list) {
            if (nhaSanXuat.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        nsxRepo.add(nsx);
        return "true";
    }

    @Override
    public Boolean update(NhaSanXuat nsx) {
        if (nsxRepo.update(nsx)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(NhaSanXuat nsx) {
        if (nsxRepo.delete(nsx)) {
            return true;
        } else {
            return false;
        }
    }
}
