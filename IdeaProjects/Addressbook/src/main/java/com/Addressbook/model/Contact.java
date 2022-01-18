package com.Addressbook.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
//    @SequenceGenerator(name = "id_sequence",
//                        sequenceName = "id_sequence",
//                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String picture;
    @Column
    private String name;
    @Column
    private String address;

    public Contact(String picture, String name, String address) {
        this.picture = picture;
        this.name = name;
        this.address = address;
    }

    public Contact() {
    }

    public Contact(Contact contact) {
        this.picture = contact.getPicture();
        this.name = contact.getName();
        this.address = contact.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
