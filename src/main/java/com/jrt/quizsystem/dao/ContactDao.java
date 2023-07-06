package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao {
   private final JdbcTemplate jdbcTemplate;
   private final ContactRowMapper contactRowMapper;

   @Autowired
   public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.contactRowMapper = contactRowMapper;
   }

   /**
    * Insert a new contact into table Contact
    * @param contact
    */
   public void addContact(Contact contact){
      String query = "INSERT INTO Contact (firstname, lastname, contact_subject, message)"
            + "values (?, ?, ?, ?)";
      jdbcTemplate.update(query, contact.getFirstname(), contact.getLastname(),
            contact.getSubject(), contact.getMessage());
   }

   /**
    * Get all contact in table Contact
    * @return List of contact
    */
   public List<Contact> getAllContacts() {
      String query = "SELECT * FROM Contact";
      List<Contact> list = jdbcTemplate.query(query, contactRowMapper);
      return list;
   }
}
