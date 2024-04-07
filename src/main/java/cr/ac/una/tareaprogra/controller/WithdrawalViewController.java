package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class WithdrawalViewController extends Controller implements Initializable {

    @FXML
    private Button btnVerify;
    @FXML
    private TextField txfAmountToWithdraw;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnWithdrawal;
    @FXML
    private Label lblNameAssociate;
    @FXML
    private Button btnSearch;
    @FXML
    private Label lblBalanceAccount;
    @FXML
    private TextField txfInvoice;
    @FXML
    private ComboBox<AccountAssociate> cbxAccount;
    private ObservableList<AccountAssociate> accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    private ObservableList<Associate> associat = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txfAmountToWithdraw.setTextFormatter(Formato.getInstance().integerFormat());
    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        boolean foundUser = false;
        if (!txfInvoice.getText().isEmpty()) {
            for (Associate associate : associat) {
                if (Objects.equals(associate.getInvoice(), txfInvoice.getText())) {
                    chargeCbxAccount();
                    lblNameAssociate.setText(associate.getName());
                    btnVerify.setDisable(true);
                    txfInvoice.setEditable(false);
                    cbxAccount.setDisable(false);
                    btnSearch.setDisable(false);
                    btnWithdrawal.setDisable(false);
                    foundUser=true;
                    break;
                }
            }
              if(!foundUser){
                 new Mensaje().showModal(Alert.AlertType.ERROR, "Revizar Cuentas", getStage(), "No se encontro ningun asociado con ese folio.");
            }
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void onActionBtnWithdrawal(ActionEvent event) {
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Deposito", getStage(), invalid);
            } else {
                safeWithdrawal();
                FlowController.getInstance().goViewInWindowModal("CheckWithdrawalView", getStage(), true);
                clear();
            }
        } catch (Exception e) {
            Logger.getLogger(DepositFunctionaryViewController.class.getName()).log(Level.SEVERE, "Error realizando el deposito.", e);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Deposito", getStage(), "Ocurrio un error realizando el deposito.");
        }

    }

    @Override
    public void initialize() {
        clear();
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText())
                    && Objects.equals(accountAssociate.getName(), account)) {
                btnWithdrawal.setDisable(false);
                lblBalanceAccount.setText(accountAssociate.getBalanceAccount());
            }
        }

    }

    private void chargeCbxAccount() {
        ObservableList<AccountAssociate> filterList = accountAssociat.filtered(accountAssociate
                -> Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText()));
        cbxAccount.setItems(filterList);
    }

    private void safeWithdrawal() {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText())
                    && Objects.equals(accountAssociate.getName(), account)) {
                String amountAccount = "0";
                amountAccount = accountAssociate.getBalanceAccount();
                int amountAccoun = Integer.parseInt(amountAccount);
                int amountWithdrawal = Integer.parseInt(txfAmountToWithdraw.getText());
                amountAccoun -= amountWithdrawal;
                accountAssociate.setBalanceAccount(String.valueOf(amountAccoun));
                safeMovement(accountAssociate, amountWithdrawal, amountAccoun);
            }
        }
    }

    private void safeMovement(AccountAssociate accountAssociate, int amount, int balanceAccount) {
        ObservableList<Movements> movement = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");
        long idAccount = accountAssociate.getId();
        String invoice = accountAssociate.getInvoice();
        String accountname = accountAssociate.getName();
        long amoun = amount;
        long balanceAccoun = balanceAccount;
        movement.add(new Movements(idAccount, accountname, invoice, "Retiro", amoun, balanceAccoun));
    }

    private String validateRequired() {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (txfAmountToWithdraw.getText().isEmpty()) {
                return "Digita el monto a retirar";
            }
            if (Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText())
                    && Objects.equals(accountAssociate.getName(), account)) {
                String amountAccount = "0";
                amountAccount = accountAssociate.getBalanceAccount();
                int amountAccoun = Integer.parseInt(amountAccount);
                int amountWithdrawal = Integer.parseInt(txfAmountToWithdraw.getText());
                if (amountWithdrawal > amountAccoun) {
                    return "Digita una cantidad igual o menor a la que contiene la cuenta";
                }
            }
        }
        return "";
    }

    private void clear() {
        txfInvoice.clear();
        btnWithdrawal.setDisable(true);
        txfAmountToWithdraw.clear();
        lblBalanceAccount.setText("");
        lblNameAssociate.setText("");
        btnVerify.setDisable(false);
        txfInvoice.setEditable(true);
        cbxAccount.setDisable(true);
        btnSearch.setDisable(true);
        cbxAccount.setItems(FXCollections.observableArrayList());
    }
}
