package com.example.Assignment.service;

import com.example.Assignment.entity.DongSanPham;

import java.util.List;

public interface DongSanPhamService {
    List<DongSanPham> getAll();

    DongSanPham getOne(String id);

    DongSanPham getName(String ten);

    String add(DongSanPham dongSanPham);

    Boolean update(DongSanPham dongSanPham);

    Boolean delete(DongSanPham dongSanPham);
}
