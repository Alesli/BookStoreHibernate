package com.project;

import com.project.entity.Shop;
import com.project.entity.User;
import com.project.service.ShopService;
import com.project.service.UserService;
import com.project.service.impl.ShopServiceImpl;
import com.project.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        App app = new App();
        try {
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        ShopService shopService = new ShopServiceImpl();
//
//        System.out.println("--- by id ---");
//
//        User user1 = userService.findOneById(1L);
//        System.out.println(user1.getName());
//
//        System.out.println("--- by name ---");
//
//        User user2 = userService.findOneByName("Ivan");
//        System.out.println(user2.getName());
//
//        System.out.println("--- all ---");

//        List<User> userList = userService.findAll();
//        for (User user3 : userList) {
//            System.out.println(user3.getId() + " - " + user3.getName());
//        }


//        System.out.println("--- save ---");
//        Shop shop1 = new Shop();
//        shop1.setName("Belkniga");
//        shop1.setCash(100.00);
//        Shop shop = shopService.save(shop1);
//        System.out.println(shop.getId() + " " + shop.getName() + " " + shop.getCash());
    }
}
