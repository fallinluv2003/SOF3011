package com.example.Assignment.service;

import com.example.Assignment.entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAll();

    SanPham getOne(String id);

    SanPham getName(String ten);

    String add(SanPham sp);

    Boolean update(SanPham sp);

    Boolean delete(SanPham sp);
}
