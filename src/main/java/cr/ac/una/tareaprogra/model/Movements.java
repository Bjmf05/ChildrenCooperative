package cr.ac.una.tareaprogra.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class Movements {
    
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> hour;
    private SimpleStringProperty accountId;
    private SimpleStringProperty accountName;
    private SimpleStringProperty invoice;
    private SimpleStringProperty movement;
    private SimpleStringProperty amount;
    private SimpleStringProperty balanceAccount;
    
    public Movements() {
        this.invoice = new SimpleStringProperty();
        this.accountId = new SimpleStringProperty();
        this.accountName = new SimpleStringProperty();
        this.movement = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.balanceAccount = new SimpleStringProperty();
        this.date = new SimpleObjectProperty();
        this.hour = new SimpleObjectProperty();
        
    }

    public Movements(Long accountId, String accountName, String invoice, String movement, Long amount, Long balanceAccount) {
        LocalDate dateNow = LocalDate.now();
        LocalTime hourNow = LocalTime.now();
        LocalTime hourAndMinutes = LocalTime.of(hourNow.getHour(), hourNow.getMinute());
        this.invoice = new SimpleStringProperty(invoice);
        this.accountId = new SimpleStringProperty(accountId.toString());
        this.accountName = new SimpleStringProperty(accountName);
        this.movement = new SimpleStringProperty(movement);
        this.amount = new SimpleStringProperty(amount.toString());
        this.balanceAccount = new SimpleStringProperty(balanceAccount.toString());
        this.date = new SimpleObjectProperty(dateNow);
        this.hour = new SimpleObjectProperty(hourAndMinutes);
    }

    public Movements(LocalDate date, LocalTime hour, Long accountId, String accountName, String invoice, String movement, Long amount, Long balanceAccount) {
        this();
        if (date != null) {
            this.date.set(date);
        } else {
            this.date.set(null);
        }
        this.date.set(date);
        this.hour.set(hour);
        this.accountId.set(accountId.toString());
        this.accountName.set(accountName);
        this.invoice.set(invoice);
        this.movement.set(movement);
        this.amount.set(amount.toString());
        this.balanceAccount.set(balanceAccount.toString());
    }
    
    public LocalDate getDate() {
        return date.get();
    }
    
    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    
    public LocalTime getHour() {
        return hour.get();
    }
    
    public void setHour(LocalTime hour) {
        this.hour.set(hour);
    }
    
    public Long getAccountId() {
        if (accountId.get() != null && !accountId.get().isEmpty()) {
            return Long.valueOf(accountId.get());
        } else {
            return null;
        }
    }
    
    public void setAccountId(Long accountId) {
        this.accountId.set(accountId.toString());
    }
    
    public String getInvoice() {
        return invoice.get();
    }
    
    public void setInvoice(String invoice) {
        this.invoice.set(invoice);
    }
    
    public String getMovement() {
        return movement.get();
    }
    
    public void setMovement(String movement) {
        this.movement.set(movement);
    }
    
    public Long getAmount() {
        return Long.valueOf(amount.get());
    }
    
    public void setAmount(Long amount) {
        this.amount.set(amount.toString());
    }
    
    public Long getBalanceAccount() {
        return Long.valueOf(balanceAccount.get());
    }
    
    public void setBalanceAccount(Long balanceAccount) {
        this.balanceAccount.set(balanceAccount.toString());
    }
    
    public String getAccountName() {
        return accountName.get();
    }
    
    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }
    
    @Override
    public String toString() {
        return balanceAccount.get() + date.get() + hour.get() + movement.get() + amount.get();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.date);
        hash = 31 * hash + Objects.hashCode(this.hour);
        hash = 31 * hash + Objects.hashCode(this.accountId);
        hash = 31 * hash + Objects.hashCode(this.invoice);
        hash = 31 * hash + Objects.hashCode(this.movement);
        hash = 31 * hash + Objects.hashCode(this.amount);
        hash = 31 * hash + Objects.hashCode(this.balanceAccount);
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
        final Movements other = (Movements) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.hour, other.hour)) {
            return false;
        }
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        if (!Objects.equals(this.invoice, other.invoice)) {
            return false;
        }
        if (!Objects.equals(this.movement, other.movement)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.balanceAccount, other.balanceAccount);
    }
    
}
