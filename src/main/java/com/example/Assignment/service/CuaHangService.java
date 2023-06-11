package com.example.Assignment.service;

import com.example.Assignment.entity.CuaHang;

import java.util.List;

public interface CuaHangService {
    List<CuaHang> getAll();

    CuaHang getOne(String id);

    CuaHang getName(String ten);

    String add(CuaHang ch);

    Boolean update(CuaHang ch);

    Boolean delete(CuaHang ch);
}
