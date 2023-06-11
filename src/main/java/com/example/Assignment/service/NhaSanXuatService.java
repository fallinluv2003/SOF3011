package com.example.Assignment.service;

import com.example.Assignment.entity.NhaSanXuat;

import java.util.List;

public interface NhaSanXuatService {
    List<NhaSanXuat> getAll();

    NhaSanXuat getOne(String id);

    NhaSanXuat getName(String ten);

    String add(NhaSanXuat nsx);

    Boolean update(NhaSanXuat nsx);

    Boolean delete(NhaSanXuat nsx);
}
