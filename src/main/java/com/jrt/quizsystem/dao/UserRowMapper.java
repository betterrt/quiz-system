package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRowMapper implements RowMapper<User> {
   @Override
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setId(rs.getInt("user_id"));
      user.setFirstname(rs.getString("firstname"));
      user.setLastname(rs.getString("lastname"));
      user.setEmail(rs.getString("user_email"));
      user.setPassword(rs.getString("user_password"));
      user.setAddress(rs.getString("address"));
      user.setPhone(rs.getString("phone"));
      user.setIsActive(rs.getBoolean("is_active"));
      user.setIsAdmin(rs.getBoolean("is_admin"));

      return user;
   }
}
