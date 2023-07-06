package com.jrt.quizsystem.service;

import com.jrt.quizsystem.dao.ContactDao;
import com.jrt.quizsystem.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
   private final ContactDao contactDao;
   @Autowired
   public ContactService(ContactDao contactDao) {
      this.contactDao = contactDao;
   }

   public void createNewContact(Contact contact) {
      contactDao.addContact(contact);
   }

   public List<Contact> getAllContact() {
      return contactDao.getAllContacts();
   }
}
