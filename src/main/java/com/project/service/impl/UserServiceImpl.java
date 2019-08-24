package com.project.service.impl;

import com.project.entity.User;
import com.project.jpa.JpaUtil;
import com.project.service.UserService;

import javax.persistence.EntityManager;

public class UserServiceImpl implements UserService {

    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public User findOneById(Long id) {

        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }

    @Override
    public User findOneByName(String name) {
        return (User) entityManager
                .createQuery("SELECT u FROM User u WHERE u.name = ?1")
                .setParameter(1, name)
                .getSingleResult();
    }

    @Override
    public boolean isCashEnough(Long id, Double cash) {
        User user = findOneById(id);
        return user.getCash() - cash >= 0;
    }

    @Override
    public User updateCash(Long id, Double cash) {

        if (isCashEnough(id, cash)) {
            User user = findOneById(id);
            entityManager.detach(user);
            user.setCash(user.getCash() - cash);
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return save(user);
        } else {
            return null;
        }
    }

    @Override
    public User save(User user) {
        entityManager.getTransaction().begin();
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        entityManager.getTransaction().commit();
        return user;
    }
}
