package com.project.service.impl;

import com.project.entity.Shop;
import com.project.jpa.JpaUtil;
import com.project.service.ShopService;

import javax.persistence.EntityManager;
import java.util.List;

public class ShopServiceImpl implements ShopService {

    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Shop findOneById(Long id) {

        Shop shop = entityManager.find(Shop.class, id);
        entityManager.detach(shop);
        return shop;
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shops = entityManager.createQuery("SELECT s FROM Shop s").getResultList();
        return shops;
    }

    @Override
    public Shop updateCash(Long id, Double cash) {
        Shop shop = findOneById(id);
        entityManager.detach(shop);
        shop.setCash(shop.getCash() + cash);
        entityManager.getTransaction().begin();
        entityManager.merge(shop);
        entityManager.getTransaction().commit();

        return save(shop);
    }

    @Override
    public Shop save(Shop shop) {
        entityManager.getTransaction().begin();
        if (shop.getId() == null) {
            entityManager.persist(shop);
        } else {
            entityManager.merge(shop);
        }
        entityManager.getTransaction().commit();
        entityManager.merge(shop);

        return shop;
    }
}
