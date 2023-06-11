package com.example.Assignment.service.impl;

import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.repository.ChucVuRepository;
import com.example.Assignment.service.ChucVuService;

import java.util.List;

public class ChucVuServiceImpl implements ChucVuService {

    private ChucVuRepository cvRepo = new ChucVuRepository();

    @Override
    public List<ChucVu> getAll() {
        return cvRepo.getAll();
    }

    @Override
    public ChucVu getOne(String id) {
        return cvRepo.getOne(id);
    }

    @Override
    public ChucVu getName(String ten) {
        return cvRepo.getName(ten);
    }

    @Override
    public String add(ChucVu cv) {
        String ma = cv.getMa();
        List<ChucVu> list = cvRepo.getAll();
        for (ChucVu chucVu : list) {
            if (chucVu.getMa().equals(ma)) {
                return "Mã đã tồn tại";
            }
        }
        cvRepo.add(cv);
        return "true";
    }

    @Override
    public Boolean update(ChucVu cv) {
        if (cvRepo.update(cv)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(ChucVu cv) {
        if (cvRepo.delete(cv)) {
            return true;
        } else {
            return false;
        }
    }
}
