package cr.ac.una.tareaprogra.model;

import java.util.Objects;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class AccountAssociate {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty invoice;
    private SimpleStringProperty balanceAccount;

    public AccountAssociate(Long id, String name, String invoice) {
        this.id = new SimpleStringProperty(id.toString());
        this.name=new SimpleStringProperty(name);
        this.invoice = new SimpleStringProperty(invoice);
    }

    public AccountAssociate() {
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.invoice = new SimpleStringProperty();
        this.balanceAccount = new SimpleStringProperty();
    }

    public AccountAssociate(AccountAssociate accountassociate) {
        this();
        this.id.set(accountassociate.getId().toString());
        this.name.set(accountassociate.getName());
        this.invoice.set(accountassociate.getInvoice());
        this.balanceAccount.set(accountassociate.getBalanceAccount());
    }
    
    public AccountAssociate(Account account, String additionalData){
     this.id = new SimpleStringProperty(account.getId().toString());
        this.name = new SimpleStringProperty(account.getName());
        this.invoice = new SimpleStringProperty(additionalData);
        this.balanceAccount = new SimpleStringProperty("0");
    }   
    
    public Long getId() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setId(Long Id) {
        this.id.set(Id.toString());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getInvoice() {
        return invoice.get();
    }

    public void setInvoice(String invoice) {
        this.invoice.set(invoice);
    }

    public String getBalanceAccount() {
        return balanceAccount.get();
    }

    public void setBalanceAccount(String balanceAccount) {
        this.balanceAccount.set(balanceAccount);
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final AccountAssociate other = (AccountAssociate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.invoice, other.invoice)) {
            return false;
        }
        return Objects.equals(this.balanceAccount, other.balanceAccount);
    }

    @Override
    public String toString() {
        return  id.get()  + name.get();
    }
 
}
