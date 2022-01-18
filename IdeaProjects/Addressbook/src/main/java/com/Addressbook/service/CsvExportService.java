package com.Addressbook.service;

import com.Addressbook.model.Contact;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class CsvExportService {
    public void downloadCsvFile(PrintWriter printWriter, List<Contact> contacts){
        printWriter.write("ID, Picture, Name, Address\n");
        for (Contact contact: contacts){
            printWriter.write(contact.getId() + ", " + contact.getPicture() + "," + contact.getName() + ", " + contact.getAddress() + "\n");

        }
    }
}