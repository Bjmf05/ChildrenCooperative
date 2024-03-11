/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tareaprogra.model;

/**
 *
 * @author PC
 */
public class Associate {
    
    private int id;
    private String name;
    private String invoice;
    private String dateOfBirth;
    private String Sex;

       public Associate() {
    }

    public Associate(int id, String name, String invoice, String dateOfBirth, String Sex) {
        this.id = id;
        this.name = name;
        this.invoice = invoice;
        this.dateOfBirth = dateOfBirth;
        this.Sex = Sex;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }
    
    
}
