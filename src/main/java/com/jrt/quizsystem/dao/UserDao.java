package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private UserRowMapper userRowMapper;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = mapper;
    }

    /**
     * Insert a new user into table User
     * @param user
     */
    public boolean addUser(User user){
        // Check if the user is already existed
        String emailQuery = "SELECT * FROM User WHERE user_email = ?";
        List<User> list = jdbcTemplate.query(emailQuery, userRowMapper, user.getEmail());
        if(list.size() != 0) {
            return false;
        }
        String query = "INSERT INTO User " +
              "(firstname, lastname, user_email, user_password, address, phone, is_active, is_admin)"
              + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getFirstname(), user.getLastname(),
              user.getEmail(), user.getPassword(), user.getAddress(), user.getPhone(),
              user.getIsActive(), user.getIsAdmin());
        return true;
    }

    /**
     * Get all users in a table User
     * @return List of users
     */
    public List<User> getAllUsers() {
        String query = "SELECT * FROM User";
        List<User> list = jdbcTemplate.query(query, userRowMapper);
        return list;
    }
    public User getUserById(int userId) {
        String query = "SELECT * FROM User WHERE user_id = ?";
        List<User> list = jdbcTemplate.query(query, userRowMapper, userId);
        if(list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public String getuserFullName(int id) {
        String query = "SELECT * FROM User WHERE user_id = ?";
        List<User> list = jdbcTemplate.query(query, userRowMapper, id);
        User user = list.get(0);
        return user.getFirstname() + " " + user.getLastname();
    }

    /**
     * Change the status of isActive of the user
     * @param isActive
     * @param id
     */
    public void updateUser(boolean isActive, int id) {
        String query = "UPDATE User SET is_active = ? WHERE user_id = ?";
        jdbcTemplate.update(query, isActive, id);
    }

    /**
     * Find if user with given email and password exists
     * @param email
     * @param password
     * @return
     */
    public User findUserByNameAndPassword(String email, String password) {
        String query = "SELECT * FROM User WHERE user_email = ? AND user_password = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(query, userRowMapper, email, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }






}
