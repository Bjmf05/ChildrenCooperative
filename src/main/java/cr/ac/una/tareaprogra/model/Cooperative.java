package cr.ac.una.tareaprogra.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marconi
 */
public class Cooperative implements Serializable {
    
    private SimpleStringProperty nameOfCooperative = new SimpleStringProperty("COOPETOY");
    private SimpleStringProperty logoPath = new SimpleStringProperty("cr/ac/una/tareaprogra/resources/logo.png");
    

    public Cooperative() {
        this.nameOfCooperative = new SimpleStringProperty();
        this.logoPath = new SimpleStringProperty();
    }

    public Cooperative(String nameOfCooperative) {
        this();
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
        this.nameOfCooperative.set(nameOfCooperative);
    }

    public void setLogoPath(String logoPath) {
        this.logoPath.set(logoPath);
    }
    
}
