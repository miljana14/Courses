package com.Addressbook.controller;

import com.Addressbook.model.Contact;
import com.Addressbook.model.ContactResponse;
import com.Addressbook.service.ContactService;
import com.Addressbook.service.CsvExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private CsvExportService csv;

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.findAll();
    }

    @CrossOrigin(origins="http://localhost:8080/contacts/getOne/{id}")
    @GetMapping(path = "/getOne/{id}")
    public Optional<Contact> findOne(@PathVariable Integer id) {
        return contactService.findOne(id);
    }

    @CrossOrigin(origins="http://localhost:8080/contacts/add")
    @PostMapping(path = "/add")
    public ContactResponse create(@RequestBody Contact contact) {

        if (contactService.findAll().contains(contact)){
            return new ContactResponse("Contact already exists!");
        }
        contactService.createContact(contact);
        return new ContactResponse("Contact successfully created!");
    }


    @PutMapping(path = "/update/{id}")
    public ContactResponse update(@PathVariable Integer id, @RequestBody Contact contact) {
            contactService.update(contact,id);
            return new ContactResponse("Contact successfully updated");
    }

    @CrossOrigin(origins="http://localhost:8080/contacts/{id}")
    @DeleteMapping(path = "/{id}")
    public ContactResponse delete(@PathVariable Integer id) {
        if (contactService.findOne(id).isEmpty()) {
            return new ContactResponse("Contact doesn`t exist!");
        }
        contactService.deleteContact(id);
        return new ContactResponse("Contact successfully deleted");
    }

    @GetMapping(path="/{name}")
    public List<Contact> findByName(@PathVariable String name){
        return contactService.findByName(name);
    }

    private final CsvExportService csvExportService;

    public ContactController(CsvExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/download/contacts.csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; file=contacts.csv");
        csv.downloadCsvFile(response.getWriter(), getAllContacts());
    }
}

