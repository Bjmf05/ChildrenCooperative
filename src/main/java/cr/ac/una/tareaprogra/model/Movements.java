package cr.ac.una.tareaprogra.model;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Movements {

    private LocalDate date;
    private String hour;
    private int accountId;
    private String invoice;
    private String movement;
    private String amount;
    private Double balanceAccounnt;

    public Movements() {
    }

    public Movements(LocalDate date, String hour, int accountId, String invoice, String movement, String amount, Double balanceAccounnt) {
        this.date = date;
        this.hour = hour;
        this.accountId = accountId;
        this.invoice = invoice;
        this.movement = movement;
        this.amount = amount;
        this.balanceAccounnt = balanceAccounnt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Double getBalanceAccounnt() {
        return balanceAccounnt;
    }

    public void setBalanceAccounnt(Double balanceAccounnt) {
        this.balanceAccounnt = balanceAccounnt;
    }

    
}
