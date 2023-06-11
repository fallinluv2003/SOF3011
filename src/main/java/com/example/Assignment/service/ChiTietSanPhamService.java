package com.example.Assignment.service;

import com.example.Assignment.entity.ChiTietSanPham;

import java.util.HashMap;
import java.util.List;

public interface ChiTietSanPhamService {
    
    List<ChiTietSanPham> getAll();

    ChiTietSanPham getOne(String id);

    String add(HashMap<String, String> ctsp);

    String update(HashMap<String, String> ctsp);

    Boolean delete(ChiTietSanPham ctsp);

}
