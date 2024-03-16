package cr.ac.una.tareaprogra.model;

import java.util.List;

/**
 *
 * @author PC
 */
public class Account {
    private static  int idNumber = 0;
    private int id;
    private String name;
    
    public Account() {
    }

    public Account(String name) {
        this.id = ++idNumber;
        this.name = name;
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

    public void setName(String Name) {
        this.name = Name;
    }

    
    
}
