package com.Addressbook.repository;

import com.Addressbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    public List<Contact> findByName(String name);
}

