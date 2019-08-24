package com.project.facade;

import com.project.entity.Book;
import com.project.entity.Shop;
import com.project.entity.User;

import java.util.List;

public interface ShopFacade {

    User findOneUserById(Long userId);

    User findOneUserByName(String userName);

    Shop findOneShopById(Long shopId);

    Book findOneBookById(Long bookId);

    User saveUser(User user);

    Shop saveShop(Shop shop);

    List<Shop> findAllShops();

    List<Book> findAllShopsBooks(Long shopId);

    List<Book> findAllUsersBooks(Long userId);

    boolean saleBook(Long shopId, Long userId, Long bookId);
}
