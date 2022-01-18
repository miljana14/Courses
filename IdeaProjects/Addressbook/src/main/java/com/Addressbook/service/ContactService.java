package com.Addressbook.service;


import com.Addressbook.model.Contact;
import com.Addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Optional<Contact> findOne(Integer id) {
        return contactRepository.findById(id);
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }

    public void createContact(Contact newContact) {
         contactRepository.save(newContact);
    }

    public void update(Contact contact, Integer id){
        for (var c: contactRepository.findAll()){
            if (c.getId().equals(id)){
                contact.setId(id);
                contactRepository.save(contact);
            }
        }

    }

    public List<Contact> findByName(String name){
        return contactRepository.findByName(name);
    }

}

