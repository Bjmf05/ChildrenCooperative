package cr.ac.una.tareaprogra.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class MailBoxDeposit {

    private SimpleStringProperty invoice;
    public SimpleStringProperty nameAccount;
    public SimpleStringProperty amount20Thousand;
    public SimpleStringProperty amount10Thousand;
    public SimpleStringProperty amount5Thousand;
    public SimpleStringProperty amount2Thousand;
    public SimpleStringProperty amount1Thousand;
    public SimpleStringProperty amount500;
    public SimpleStringProperty amount100;
    public SimpleStringProperty amount50;
    public SimpleStringProperty amount25;
    public SimpleStringProperty amount10;
    public SimpleStringProperty amount5;

    public MailBoxDeposit() {
        this.invoice = new SimpleStringProperty();
        this.nameAccount = new SimpleStringProperty();
        this.amount20Thousand = new SimpleStringProperty();
        this.amount10Thousand = new SimpleStringProperty();
        this.amount5Thousand = new SimpleStringProperty();
        this.amount2Thousand = new SimpleStringProperty();
        this.amount1Thousand = new SimpleStringProperty();
        this.amount500 = new SimpleStringProperty();
        this.amount100 = new SimpleStringProperty();
        this.amount50 = new SimpleStringProperty();
        this.amount25 = new SimpleStringProperty();
        this.amount10 = new SimpleStringProperty();
        this.amount5 = new SimpleStringProperty();
    }

    public MailBoxDeposit(String invoice, String nameAccount, String amount20Thousand, String amount10Thousand, String amount5Thousand, String amount2Thousand, String amount1Thousand, String amount500, String amount100, String amount50, String amount25, String amount10, String amount5) {
        this();
        this.invoice.set(invoice);
        this.nameAccount.set(nameAccount);
        this.amount20Thousand.set(amount20Thousand);
        this.amount10Thousand.set(amount10Thousand);
        this.amount5Thousand.set(amount5Thousand);
        this.amount2Thousand.set(amount2Thousand);
        this.amount1Thousand.set(amount1Thousand);
        this.amount500.set(amount500);
        this.amount100.set(amount100);
        this.amount50.set(amount50);
        this.amount25.set(amount25);
        this.amount10.set(amount10);
        this.amount5.set(amount5);
    }

    public String getInvoice() {
        return invoice.get();
    }

    public void setInvoice(String invoice) {
        this.invoice.set(invoice); 
    }

    public String getNameAccount() {
        return nameAccount.get();
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount.set(nameAccount);
    }

}
