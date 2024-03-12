package cr.ac.una.tareaprogra.model;

/**
 *
 * @author PC
 */
public class AccountAssociate {
    private int id;
    private String name;
    private String invoice;
    private Double balanceAccount;

    public AccountAssociate(int id, String name, String invoice) {
        this.id = id;
        this.name = name;
        this.invoice = invoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Double getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(Double balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public int getId() {
        return id;
    }
    
    
}
