package com.example.Assignment.service;

import com.example.Assignment.entity.ChucVu;

import java.util.List;

public interface ChucVuService {
    List<ChucVu> getAll();

    ChucVu getOne(String id);

    ChucVu getName(String ten);

    String add(ChucVu cv);

    Boolean update(ChucVu cv);

    Boolean delete(ChucVu cv);
}
