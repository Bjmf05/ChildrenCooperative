package cr.ac.una.tareaprogra.model;

import cr.ac.una.tareaprogra.util.AppContext;
import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class Account implements Serializable {

    private SimpleStringProperty id;
    private SimpleStringProperty name;

    public Account() {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    public Account(String name) {
        this();
        this.id.set(Integer.toString(createIdAccount()));
        this.name.set(name);
    }

    public Account(Account account) {
        this();
        this.id.set(account.getId().toString());
        this.name.set(account.getName());

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

    public void setName(String Name) {
        this.name.set(Name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name.get();
    }
//Funcion para crear un nuevo id
    private Integer createIdAccount() {
        ObservableList<Account> accountList = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
        if (accountList.isEmpty()) {
            return 1;
        } else {
            Account account = accountList.get(accountList.size() - 1);
            Long newIdL = account.getId();
            int newIdI = (int) (newIdL + 1);
            return newIdI;
        }
    }

}
