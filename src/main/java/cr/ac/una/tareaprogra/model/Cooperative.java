package cr.ac.una.tareaprogra.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marconi
 */
public class Cooperative {
    
    private static int id = 0;
    private SimpleStringProperty idOfChange;
    private SimpleStringProperty nameOfCooperative;
    private SimpleStringProperty logoPath;
    

    public Cooperative() {
        this.nameOfCooperative = new SimpleStringProperty();
        this.logoPath = new SimpleStringProperty();
    }

    public Cooperative(String nameOfCooperative) {
        this();
        //this.idOfChange.set(Integer.toString(id++));
        this.nameOfCooperative.set(nameOfCooperative);
    }

    public Cooperative(String nameOfCoperative, String logoPath) {
        this();
        this.nameOfCooperative.set(nameOfCoperative); 
        this.logoPath.set(logoPath);
    }
    
    public String getNameOfCooperative() {
        return nameOfCooperative.get();
    }

    public String getLogoPath() {
        return logoPath.get();
    }
    
    public void setNameOfCooperative(String nameOfCooperative) {
        this.nameOfCooperative.set(nameOfCooperative);;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath.set(logoPath);
    }
    
}
