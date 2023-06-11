package com.example.Assignment.service;

import com.example.Assignment.entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAll();

    MauSac getOne(String id);

    MauSac getName(String ten);

    String add(MauSac ms);

    Boolean update(MauSac ms);

    Boolean delete(MauSac ms);
}
