package com.example.Assignment.service;

import com.example.Assignment.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getOne(String id);

    String add(KhachHang kh);

    Boolean update(KhachHang kh);

    Boolean delete(KhachHang kh);

}
