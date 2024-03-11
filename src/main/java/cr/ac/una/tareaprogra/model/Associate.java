/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tareaprogra.model;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Associate {
    
    private int id;
    private String name;
    private String invoice;
    private LocalDate dateOfBirth;
    private String sex;
    private String addressPhoto;
       public Associate() {
    }

    public Associate(int id, String name, String invoice, LocalDate dateOfBirth, String sex, String addressPhoto) {
        this.id = id;
        this.name = name;
        this.invoice = invoice;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.addressPhoto = addressPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth; }

    public String getSex() {
        return sex;
    }

    public void setSex(String Sex) {
        this.sex = Sex;
    }

    public String getAdressPhoto() {
        return addressPhoto;
    }

    public void setAddressPhoto(String addressPhoto) {
        this.addressPhoto = addressPhoto;
    }
    
    
}
