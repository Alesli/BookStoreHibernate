package com.project.facade.impl;

import com.project.entity.Book;
import com.project.entity.Shop;
import com.project.entity.User;
import com.project.facade.ShopFacade;
import com.project.service.BookService;
import com.project.service.ShopService;
import com.project.service.UserService;
import com.project.service.impl.BookServiceImpl;
import com.project.service.impl.ShopServiceImpl;
import com.project.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class ShopFacadeImpl implements ShopFacade {

    private ShopService shopService = new ShopServiceImpl();

    private BookService bookService = new BookServiceImpl();

    private UserService userService = new UserServiceImpl();

    @Override
    public User findOneUserById(Long userId) {
        return userService.findOneById(userId);
    }

    @Override
    public User findOneUserByName(String userName) {
        return userService.findOneByName(userName);
    }

    @Override
    public Shop findOneShopById(Long shopId) {
        return shopService.findOneById(shopId);
    }

    @Override
    public Book findOneBookById(Long bookId) {
        return bookService.findOneById(bookId);
    }

    @Override
    public User saveUser(User user) {
        return userService.save(user);
    }

    @Override
    public Shop saveShop(Shop shop) {
        return shopService.save(shop);
    }

    @Override
    public List<Shop> findAllShops() {
        return shopService.findAll();
    }

    @Override
    public List<Book> findAllShopsBooks(Long shopId) {
        Shop shop = shopService.findOneById(shopId);
        List<Book> bookList = shop.getBooks();
        return bookList.isEmpty() ? new ArrayList<>(0) : bookList;
    }

    @Override
    public List<Book> findAllUsersBooks(Long userId) {
        User user = userService.findOneById(userId);
        List<Book> bookList = user.getBooks();
        return bookList.isEmpty() ? new ArrayList<>(0) : bookList;
    }

    @Override
    public boolean saleBook(Long shopId, Long userId, Long bookId) {

        User user = userService.findOneById(userId);
        Book book = bookService.findOneById(bookId);
        if ((user.getCash() - book.getCost()) >= 0) {

            moneyFromUser(userId, book.getCost());
            moneyToShop(shopId, book.getCost());
            bookFromShop(shopId, bookId);
            bookToUser(userId, bookId);
        } else {
            System.out.println("Unfortunately, you don't have enough money");
        }
        return true;
    }

    private Double moneyFromUser(Long userId, Double money) {
        User user = userService.findOneById(userId);
        user.setCash(getDouble(user.getCash(), money, false));
        return saveUser(user).getCash();
    }

    private Double moneyToShop(Long shopId, Double money) {
        Shop shop = shopService.findOneById(shopId);
        shop.setCash(getDouble(shop.getCash(), money, true));
        return saveShop(shop).getCash();
    }

    private Double getDouble(Double param1, Double param2, boolean isPlus) {
        String p1Str = String.valueOf(param1);
        String p2Str = String.valueOf(param2);
        if (p1Str.substring(p1Str.lastIndexOf(".") + 1).length() == 1) {
            p1Str += "0";
        }
        if (p2Str.substring(p2Str.lastIndexOf(".") + 1).length() == 1) {
            p2Str += "0";
        }
        p1Str = p1Str.replace(".", "");
        p2Str = p2Str.replace(".", "");
        System.out.println(p1Str);
        System.out.println(p2Str);
        int p1Int = Integer.parseInt(p1Str);
        int p2Int = Integer.parseInt(p2Str);
        p1Int = isPlus ? p1Int + p2Int : p1Int - p2Int;
        return (double) p1Int / 100;
    }

    private boolean bookFromShop(Long shopId, Long bookId) {
        Shop shop = shopService.findOneById(shopId);
        Book book = bookService.findOneById(bookId);
        List<Book> bookShopList = shop.getBooks();
        int size = bookShopList.size();
        Book bookInShop = null;
        for (int b = 0; b < bookShopList.size(); b++) {
            if (bookShopList.get(b).getId().equals(book.getId())) {
                bookInShop = bookShopList.get(b);
                break;
            }
        }
        if (bookInShop.getCount() == 1) {
            for (int b = 0; b < bookShopList.size(); b++) {
                if (bookShopList.get(b).getId().equals(bookInShop.getId())) {
                    bookShopList.remove(b);
                    break;
                }
            }
        } else if (bookInShop.getCount() > 1) {
            for (int b = 0; b < bookShopList.size(); b++) {
                if (bookShopList.get(b).getId().equals(bookInShop.getId())) {
                    bookShopList.remove(b);
                    break;
                }
            }
            bookInShop.setCount(bookInShop.getCount() - 1);
            bookShopList.add(bookInShop);
        }
        shop.setBooks(bookShopList);
        Shop shopSaved = saveShop(shop);
        return ((size - 1) - shopSaved.getBooks().size()) == 0;
    }

    private boolean bookToUser(Long userId, Long bookId) {
        User user = userService.findOneById(userId);
        Book book = bookService.findOneById(bookId);
        List<Book> bookUserList = user.getBooks();
        int size = bookUserList.size();
        bookUserList.add(book);
        user.setBooks(bookUserList);
        User userSaved = saveUser(user);
        return ((size + 1) - userSaved.getBooks().size()) == 0;
    }
}
