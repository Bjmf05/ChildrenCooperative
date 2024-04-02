package cr.ac.una.tareaprogra.model;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private SimpleStringProperty invoice;
    private SimpleStringProperty movement;
    private SimpleStringProperty amount;
    private SimpleStringProperty balanceAccount;

    public Movements() {
        this.invoice = new SimpleStringProperty();
        this.accountId = new SimpleStringProperty();
        this.movement = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.balanceAccount = new SimpleStringProperty();
        this.invoice = new SimpleStringProperty();
        this.date = new SimpleObjectProperty();
        this.hour = new SimpleObjectProperty();
        
    }

    public Movements(LocalDate date, LocalTime hour, Long accountId, String invoice, String movement, Long amount, Long balanceAccount) {
        this();
        if (date != null) {
            this.date.set(date);
        } else {
            this.date.set(null);
        }
        this.date.set(date);
        this.hour.set(hour);
        this.accountId.set(accountId.toString());
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

    
}
