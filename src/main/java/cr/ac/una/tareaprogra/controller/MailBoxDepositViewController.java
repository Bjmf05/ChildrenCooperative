package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.model.MailBoxDeposit;
import cr.ac.una.tareaprogra.model.Movements;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import cr.ac.una.tareaprogra.util.Formato;
import cr.ac.una.tareaprogra.util.Mensaje;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MailBoxDepositViewController extends Controller implements Initializable {

    @FXML
    private TextField txfInvoice;
    @FXML
    private Button btnVerify;
    @FXML
    private Label lblNameAccount;
    @FXML
    private TextField txf20ThousandAmount;
    @FXML
    private TextField txf10ThousandAmount;
    @FXML
    private TextField txf5ThousandAmount;
    @FXML
    private TextField txf2ThousandAmount;
    @FXML
    private TextField txf1ThousandAmount;
    @FXML
    private TextField txf500Amount;
    @FXML
    private TextField txf100Amount;
    @FXML
    private TextField txf50Amount;
    @FXML
    private TextField txf25Amount;
    @FXML
    private TextField txf5Amount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txf10Amount;
    private ObservableList<MailBoxDeposit> mailBoxDeposi = (ObservableList<MailBoxDeposit>) AppContext.getInstance().get("newMailBoxDeposit");
    private ObservableList<AccountAssociate> accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    MailBoxDeposit mailBoxDeposit;
    @FXML
    private Label lblNameInvoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txf20ThousandAmount.setTextFormatter(Formato.getInstance().integerFormat());
        txf10ThousandAmount.setTextFormatter(Formato.getInstance().integerFormat());
        txf5ThousandAmount.setTextFormatter(Formato.getInstance().integerFormat());
        txf2ThousandAmount.setTextFormatter(Formato.getInstance().integerFormat());
        txf1ThousandAmount.setTextFormatter(Formato.getInstance().integerFormat());
        txf500Amount.setTextFormatter(Formato.getInstance().integerFormat());
        txf100Amount.setTextFormatter(Formato.getInstance().integerFormat());
        txf50Amount.setTextFormatter(Formato.getInstance().integerFormat());
        txf25Amount.setTextFormatter(Formato.getInstance().integerFormat());
        txf10Amount.setTextFormatter(Formato.getInstance().integerFormat());
        txf5Amount.setTextFormatter(Formato.getInstance().integerFormat());
    }

    @Override
    public void initialize() {
        clear();
    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        boolean foundUser = false;
        if (!txfInvoice.getText().isEmpty()) {
            for (MailBoxDeposit mailboxDeposit : mailBoxDeposi) {
                if (Objects.equals(mailboxDeposit.getInvoice(), txfInvoice.getText())) {
                    foundUser = true;
                    txfInvoice.setEditable(false);
                    mailBoxDeposit = mailboxDeposit;
                    bindMailboxDeposit();
                    enableData();
                    showNameAssociate(txfInvoice.getText());
                    break;
                }
            }
            if (!foundUser) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Deposito Buzon", getStage(), "No se encontro ningun asociado con ese folio que tenga depositos de buzon.");
            }
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Deposito", getStage(), invalid);
            } else {
                safeDeposit();
                FlowController.getInstance().goViewInWindowModal("CheckDepositView", getStage(), true);
                mailBoxDeposi.remove(mailBoxDeposit);
                clear();
            }
        } catch (Exception e) {
            Logger.getLogger(DepositFunctionaryViewController.class.getName()).log(Level.SEVERE, "Error realizando el deposito.", e);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Deposito", getStage(), "Ocurrio un error realizando el deposito.");
        }
    }

    private void disableData() {
        txf20ThousandAmount.setEditable(false);
        txf10ThousandAmount.setEditable(false);
        txf5ThousandAmount.setEditable(false);
        txf2ThousandAmount.setEditable(false);
        txf1ThousandAmount.setEditable(false);
        txf500Amount.setEditable(false);
        txf100Amount.setEditable(false);
        txf50Amount.setEditable(false);
        txf25Amount.setEditable(false);
        txf10Amount.setEditable(false);
        txf5Amount.setEditable(false);

    }

    private void enableData() {
        txf20ThousandAmount.setEditable(true);
        txf10ThousandAmount.setEditable(true);
        txf5ThousandAmount.setEditable(true);
        txf2ThousandAmount.setEditable(true);
        txf1ThousandAmount.setEditable(true);
        txf500Amount.setEditable(true);
        txf100Amount.setEditable(true);
        txf50Amount.setEditable(true);
        txf25Amount.setEditable(true);
        txf10Amount.setEditable(true);
        txf5Amount.setEditable(true);
    }

    private void clear() {
        disableData();
        txfInvoice.setEditable(true);
        lblNameInvoice.setText("");
        txfInvoice.clear();
        txf20ThousandAmount.clear();
        txf10ThousandAmount.clear();
        txf5ThousandAmount.clear();
        txf2ThousandAmount.clear();
        txf1ThousandAmount.clear();
        txf500Amount.clear();
        txf100Amount.clear();
        txf50Amount.clear();
        txf25Amount.clear();
        txf10Amount.clear();
        txf5Amount.clear();
    }

    private void bindMailboxDeposit() {
        lblNameAccount.textProperty().bindBidirectional(mailBoxDeposit.nameAccount);
        txf20ThousandAmount.textProperty().bindBidirectional(mailBoxDeposit.amount20Thousand);
        txf10ThousandAmount.textProperty().bindBidirectional(mailBoxDeposit.amount10Thousand);
        txf5ThousandAmount.textProperty().bindBidirectional(mailBoxDeposit.amount5Thousand);
        txf2ThousandAmount.textProperty().bindBidirectional(mailBoxDeposit.amount2Thousand);
        txf1ThousandAmount.textProperty().bindBidirectional(mailBoxDeposit.amount1Thousand);
        txf500Amount.textProperty().bindBidirectional(mailBoxDeposit.amount500);
        txf100Amount.textProperty().bindBidirectional(mailBoxDeposit.amount100);
        txf50Amount.textProperty().bindBidirectional(mailBoxDeposit.amount50);
        txf25Amount.textProperty().bindBidirectional(mailBoxDeposit.amount25);
        txf10Amount.textProperty().bindBidirectional(mailBoxDeposit.amount10);
        txf5Amount.textProperty().bindBidirectional(mailBoxDeposit.amount5);
    }

    private void safeDeposit() {

        String account = lblNameAccount.getText();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText())
                    && Objects.equals(accountAssociate.getName(), account)) {
                String amountAccount = "0";
                if (accountAssociate.getBalanceAccount() == null) {
                    amountAccount = "0";
                } else {
                    amountAccount = accountAssociate.getBalanceAccount();
                }

                int amountAccoun = Integer.parseInt(amountAccount);
                amountAccoun += totalDeposit();
                accountAssociate.setBalanceAccount(String.valueOf(amountAccoun));
                safeMovement(accountAssociate, totalDeposit(), amountAccoun);

            }
        }
    }

    private int totalDeposit() {
        int total = (20000 * stringToInt(txf20ThousandAmount))
                + (10000 * stringToInt(txf10ThousandAmount))
                + (5000 * stringToInt(txf5ThousandAmount))
                + (2000 * stringToInt(txf2ThousandAmount))
                + (1000 * stringToInt(txf1ThousandAmount))
                + (500 * stringToInt(txf500Amount))
                + (100 * stringToInt(txf100Amount))
                + (50 * stringToInt(txf50Amount))
                + (25 * stringToInt(txf25Amount))
                + (10 * stringToInt(txf10Amount))
                + (5 * stringToInt(txf5Amount));

        return total;
    }

    private int stringToInt(TextField textField) {
        String text = textField.getText();
        return text != null && !text.isEmpty() ? Integer.parseInt(text) : 0;
    }

    private void safeMovement(AccountAssociate accountAssociate, int amount, int balanceAccount) {
        ObservableList<Movements> movement = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");
        long idAccount = accountAssociate.getId();
        String invoice = accountAssociate.getInvoice();
        String accountname = accountAssociate.getName();
        long amoun = amount;
        long balanceAccoun = balanceAccount;
        movement.add(new Movements(idAccount, accountname, invoice, "Deposito", amoun, balanceAccoun));
    }

    private String validateRequired() {
        TextField[] textFields = {txf20ThousandAmount, txf10ThousandAmount, txf5ThousandAmount, txf2ThousandAmount,
            txf1ThousandAmount, txf500Amount, txf100Amount, txf50Amount, txf25Amount, txf10Amount, txf5Amount};
        boolean atLeastOneHas = false;
        for (TextField textField : textFields) {
            if (!textField.getText().isEmpty()) {
                atLeastOneHas = true;
                break;
            }
        }
        if (atLeastOneHas) {
            return "";
        } else {
            return "No has dijitado ninguna cantidad. Hazlo he intenta de nuevo.";
        }
    }

    private void showNameAssociate(String invoice) {
        ObservableList<Associate> associateList = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
        ObservableList<Associate> filterAsscociateList = associateList.filtered(associate -> Objects.equals(invoice, associate.getInvoice()));
        Associate associate = filterAsscociateList.get(0);
        lblNameInvoice.setText(associate.getName() + " " + associate.getLastName1());
    }
}
