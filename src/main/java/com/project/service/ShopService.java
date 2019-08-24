package com.project.service;

import com.project.entity.Shop;

import java.util.List;

public interface ShopService {

    Shop findOneById(Long id);

    List<Shop> findAll();

    Shop updateCash(Long id, Double cash);

    Shop save(Shop shop);

}
