package com.project;

import com.project.entity.Book;
import com.project.entity.Shop;
import com.project.entity.User;
import com.project.facade.ShopFacade;
import com.project.facade.impl.ShopFacadeImpl;
import com.project.menu.MainMenu;

import java.util.List;

public class App {

    private ShopFacade shopFacade = new ShopFacadeImpl();

    private Shop shop;
    private User user;
    private boolean signIn = false;

    public void run(String... args) throws Exception {

        System.out.println("\n\n\n\n\n\n\n\n\nWelcome!!!\n\n");

        MainMenu mainMenu = new MainMenu();

        for (int n = 0; n < 3; n++) {
            String name = mainMenu.getName();
            if (name.length() == 0) {
                exit();
            } else {
                User user = shopFacade.findOneUserByName(name);
                if (user != null) {
                    setUser(user);
                    break;
                } else {
                    System.out.println((n + 1) + " - Sorry, your name is not correct");
                }
            }
        }

        if (getUser() == null) {
            exit();
        }

        for (int p = 0; p < 3; p++) {
            String pass = mainMenu.checkPassword(getUser().getName());
            if (pass.length() == 0) {
                exit();
            } else {
                if (getUser().getPass().equals(pass)) {
                    setSignIn(true);
                    break;
                } else {
                    System.out.println((p + 1) + " - Sorry, your pass is not correct");
                }
            }
        }

        if (isSignIn()) {

            List<Shop> shopList = shopFacade.findAllShops();
            if (shopList.isEmpty()) {
                System.out.println("Sorry, all shops is not available");
                exit();
            }
            if (shopList.size() > 1) {
                Long shopId = mainMenu.getShop(shopList);
                Shop shop = shopFacade.findOneShopById(shopId);
                setShop(shop);
            } else {
                setShop(shopList.get(0));
            }
            // todo: попробовать обернуть в цикл, чтобы юзер мог постоянно книги покупать

            while (true) {
                int menuItem = mainMenu.getMenuItems();
                switch (menuItem) {
                    case 0:
                        exit();
                        break;
                    case 1:
                        List<Book> bookShopList = shopFacade.findAllShopsBooks(getShop().getId());
                        mainMenu.showAllShopBooks(bookShopList);
                        if (mainMenu.doYouWannaBuy()) {
                            mainMenu.showAllShopBooks(bookShopList);
                            Long bookId = mainMenu.chooseBook();
                            Book book = shopFacade.findOneBookById(bookId);
                            if (book != null) {
                                shopFacade.saleBook(getShop().getId(), getUser().getId(), book.getId());
                                List<Book> basket = shopFacade.findAllUsersBooks(getUser().getId());
                                mainMenu.showAllUserBooks(basket);
                            }
                        }
                        break;
                    case 2:
                        List<Book> bookUserList = shopFacade.findAllUsersBooks(getUser().getId());
                        mainMenu.showAllUserBooks(bookUserList);
                        break;
                    case 3:
                        User user = shopFacade.findOneUserById(getUser().getId());
                        setUser(user);
                        System.out.println("Your balance: " + getUser().getCash());
                        break;
                    default:
                        System.out.println("Please, enter digits only...");
                        break;
                }
            }
        } else {
            exit();
        }
    }

    private void exit() {
        System.out.println("\n\nBay bay!!!\n\n\n\n\n\n\n\n\n");
        System.exit(0);
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean isSignIn() {
        return signIn;
    }

    private void setSignIn(boolean signIn) {
        this.signIn = signIn;
    }
}
