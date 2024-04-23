package cr.ac.una.tareaprogra.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class MailBoxDeposit implements Serializable {

    private SimpleStringProperty invoice;
    public SimpleStringProperty nameAccount;
    public String amount20Thousand;
    public String amount10Thousand;
    public String amount5Thousand;
    public String amount2Thousand;
    public String amount1Thousand;
    public String amount500;
    public String amount100;
    public String amount50;
    public String amount25;
    public String amount10;
    public String amount5;

    public MailBoxDeposit() {
        this.invoice = new SimpleStringProperty();
        this.nameAccount = new SimpleStringProperty();
        this.amount20Thousand = new String();
        this.amount10Thousand = new String();
        this.amount5Thousand = new String();
        this.amount2Thousand = new String();
        this.amount1Thousand = new String();
        this.amount500 = new String();
        this.amount100 = new String();
        this.amount50 = new String();
        this.amount25 = new String();
        this.amount10 = new String();
        this.amount5 = new String();
    }

    public MailBoxDeposit(String invoice, String nameAccount, int amount20Thousand, int amount10Thousand, int amount5Thousand, int amount2Thousand, int amount1Thousand, int amount500, int amount100, int amount50, int amount25, int amount10, int amount5) {
        this();
        this.invoice.set(invoice);
        this.nameAccount.set(nameAccount);
        this.amount20Thousand = String.valueOf(amount20Thousand);
        this.amount10Thousand = String.valueOf(amount10Thousand);
        this.amount5Thousand = String.valueOf(amount5Thousand);
        this.amount2Thousand = String.valueOf(amount2Thousand);
        this.amount1Thousand = String.valueOf(amount1Thousand);
        this.amount500 = String.valueOf(amount500);
        this.amount100 = String.valueOf(amount100);
        this.amount50 = String.valueOf(amount50);
        this.amount25 = String.valueOf(amount25);
        this.amount10 = String.valueOf(amount10);
        this.amount5 = String.valueOf(amount5);
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
