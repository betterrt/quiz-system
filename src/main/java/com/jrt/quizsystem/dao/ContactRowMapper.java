package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ContactRowMapper implements RowMapper<Contact> {
   @Override
   public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
      Contact contact = new Contact();
      contact.setFirstname(rs.getString("firstname"));
      contact.setLastname(rs.getString("lastname"));
      contact.setSubject(rs.getString("contact_subject"));
      contact.setMessage(rs.getString("message"));
      return contact;
   }
}
