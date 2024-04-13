package cr.ac.una.tareaprogra.model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class Associate implements Serializable  {

    public SimpleStringProperty id;
    public SimpleStringProperty name;
    public SimpleStringProperty lastName1;
    public SimpleStringProperty lastName2;
    public SimpleStringProperty invoice;
    public ObjectProperty<LocalDate> dateOfBirth;
    public SimpleStringProperty sex;
    public SimpleStringProperty addressPhoto;

    public Associate() {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.lastName1 = new SimpleStringProperty();
        this.lastName2 = new SimpleStringProperty();
        this.invoice = new SimpleStringProperty();
        this.dateOfBirth = new SimpleObjectProperty();
        this.sex = new SimpleStringProperty();
        this.addressPhoto = new SimpleStringProperty();

    }

    public Associate(Long id, String name, String lastName1, String lastName2, String invoice, LocalDate dateOfBirth, String sex, String addressPhoto) {
        this();
        this.id.set(id.toString());
        this.name.set(name);
        this.lastName1.set(lastName1);
        this.lastName2.set(lastName2);
        this.invoice.set(invoice);
        this.sex.set(sex);
        this.addressPhoto.set(addressPhoto);
        if (dateOfBirth != null) {
            this.dateOfBirth.set(dateOfBirth);
        } else {
            this.dateOfBirth.set(null);
        }
    }

    public Associate(Associate associate) {
        this();
        this.id.set(associate.getId().toString());
        this.name.set(associate.getName());
        this.lastName1.set(associate.getLastName1());
        this.lastName2.set(associate.getLastName2());
        this.invoice.set(associate.getInvoice());
        this.sex.set(associate.getSex());
        this.addressPhoto.set(associate.getAddressPhoto());
        if (dateOfBirth != null) {
            this.dateOfBirth.set(associate.getDateOfBirth());
        } else {
            this.dateOfBirth.set(null);
        }
    }

    public void setAssociate(Associate associate) {

        this.id.set(associate.getId().toString());
        this.name.set(associate.getName());
        this.lastName1.set(associate.getLastName1());
        this.lastName2.set(associate.getLastName2());
        this.sex.set(associate.getSex());
        this.addressPhoto.set(associate.getAddressPhoto());
        if (dateOfBirth != null) {
            this.dateOfBirth.set(associate.getDateOfBirth());
        } else {
            this.dateOfBirth.set(null);
        }
    }

    public Long getId() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setId(Long id) {
        this.id.set(id.toString());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastName1() {
        return lastName1.get();
    }

    public void setLastName1(String lastName1) {
        this.lastName1.set(lastName1);
    }

    public String getLastName2() {
        return lastName2.get();
    }

    public void setLastName2(String lastName2) {
        this.lastName2.set(lastName2);
    }

    public String getInvoice() {
        return invoice.get();
    }

    public void setInvoice(String invoice) {
        this.invoice.set(invoice);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getAddressPhoto() {
        return addressPhoto.get();
    }

    public void setAddressPhoto(String addressPhoto) {
        this.addressPhoto.set(addressPhoto);
    }

}
