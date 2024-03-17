package cr.ac.una.tareaprogra.model;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class Account {
    private static  int idNumber = 0;
    private int id;
    private StringProperty name;
    public Account() {
    }

    public Account(String name) {
        this.id = ++idNumber;
        this.name = new SimpleStringProperty(name);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getName() {
        return name.get();
    }

    public void setName(String Name) {
        this.name.set(Name);
    }
     public StringProperty nameProperty() {
        return name;
    }
    
    
}
