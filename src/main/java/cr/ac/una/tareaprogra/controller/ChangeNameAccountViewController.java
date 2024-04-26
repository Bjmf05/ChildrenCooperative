package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.MailBoxDeposit;
import cr.ac.una.tareaprogra.model.Movements;
import cr.ac.una.tareaprogra.util.AppContext;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ChangeNameAccountViewController extends Controller implements Initializable {

    @FXML
    private TextField txfNewNameAccount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private ObservableList<Account> accountList = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
    private ObservableList<AccountAssociate> accountAssociateList = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    private ObservableList<Movements> movementsList = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");
    private ObservableList<MailBoxDeposit> mailBoxDepositList = (ObservableList<MailBoxDeposit>) AppContext.getInstance().get("newMailBoxDeposit");
    String oldName = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        txfNewNameAccount.clear();
        getStage().close();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
         try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cambiar Nombre Cuenta", getStage(), invalid);
            } else {
                newAccountName(oldName);
                txfNewNameAccount.clear();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Nombre Cuenta", getStage(), "El nombre de la cuenta se actualizo con exito.");
            getStage().close();
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterUserViewController.class.getName()).log(Level.SEVERE, "Error guardando la cuenta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Nombre Cuenta", getStage(), "Ocurrio un error guardando la cuenta.");
        }
    }

    //Funciones para cambiar el nombre de las cuentas en todos lados
    public void nameAccount(Account account) {
        txfNewNameAccount.setText(account.getName());
        oldName = account.getName();
    }

    private void newAccountName(String oldName) {
        String newName = txfNewNameAccount.getText();
        changeNameAccount(oldName, newName);
        changeNameAccountAssociate(oldName, newName);
        changeNameAccountMovements(oldName, newName);
        changeNameAccountMailBoxDeposit(oldName, newName);
    }

    private void changeNameAccount(String oldName, String newName) {

        for (Account account : accountList) {
            if (Objects.equals(account.getName(), oldName)) {
                account.setName(newName);
            }
        }
    }

    private void changeNameAccountAssociate(String oldName, String newName) {
        for (AccountAssociate accountAssociate : accountAssociateList) {
            if (Objects.equals(accountAssociate.getName(), oldName)) {
                accountAssociate.setName(newName);
            }
        }
    }

    private void changeNameAccountMovements(String oldName, String newName) {
        for(Movements movements : movementsList){
            if(Objects.equals(movements.getAccountName(), oldName)){
            movements.setAccountName(newName);
            }
        }
    }

    private void changeNameAccountMailBoxDeposit(String oldName, String newName) {
        for(MailBoxDeposit mailBoxDeposit : mailBoxDepositList){
            if(Objects.equals(mailBoxDeposit.getNameAccount(), oldName)){
            mailBoxDeposit.setNameAccount(newName);}
        }
    }
    private String validateRequired(){
        if(txfNewNameAccount.getText().isEmpty()){
        return "El nombre de la cuenta se encuentra en blanco";
        }
        return "";
    }
}
