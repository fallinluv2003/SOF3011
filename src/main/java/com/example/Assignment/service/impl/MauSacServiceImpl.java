package com.example.Assignment.service.impl;

import com.example.Assignment.entity.MauSac;
import com.example.Assignment.repository.MauSacRepository;
import com.example.Assignment.service.MauSacService;

import java.util.List;

public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository msRepo = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return msRepo.getAll();
    }

    @Override
    public MauSac getOne(String id) {
        return msRepo.getOne(id);
    }

    @Override
    public MauSac getName(String ten) {
        return msRepo.getName(ten);
    }

    @Override
    public String add(MauSac ms) {
        String ma = ms.getMa();
        List<MauSac> list = msRepo.getAll();
        for (MauSac mauSac : list) {
            if (mauSac.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        msRepo.add(ms);
        return "true";
    }

    @Override
    public Boolean update(MauSac ms) {
        if (msRepo.update(ms)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(MauSac ms) {
        if (msRepo.delete(ms)) {
            return true;
        } else {
            return false;
        }
    }
}
