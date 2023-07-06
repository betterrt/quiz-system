package com.jrt.quizsystem.service;

import com.jrt.quizsystem.dao.UserDao;
import com.jrt.quizsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) { this.userDao = userDao; }

    /**
     * @param user
     * @return false if duplication of email are found
     */
    public boolean createNewUser(User user) {
        return userDao.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public void activateUser(int id) {
        userDao.updateUser(true, id);
    }

    public void suspendUser(int id) {
        userDao.updateUser(false, id);
    }

    public Optional<User> validateLogin(String email, String password) {
        return Optional.ofNullable(userDao.findUserByNameAndPassword(email, password));
    }

    public String getUserFullName(int userId) {
        return userDao.getuserFullName(userId);
    }


}
