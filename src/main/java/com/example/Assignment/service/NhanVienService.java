package com.example.Assignment.service;

import com.example.Assignment.entity.NhanVien;

import java.util.HashMap;
import java.util.List;

public interface NhanVienService {

    List<NhanVien> getAll();

    NhanVien getOne(String id);

    String add(HashMap<String, String> nv);

    String update(HashMap<String, String> nv);

    Boolean delete(NhanVien nv);
    
}
