package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DeleteAssociateAccountViewController extends Controller implements Initializable {

    @FXML
    private Label lblNameAssoiate;
    @FXML
    private ComboBox<AccountAssociate> cbxAccount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    private ObservableList<AccountAssociate> accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    private ObservableList<Associate> associat = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
    private ObservableList<Movements> movement = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");

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
        clear();
        getStage().close();
    }

    @FXML
    private void onActionBtnDelete(ActionEvent event) {

        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Modificar Asociado", getStage(), invalid);
            } else {
                boolean answer = new Mensaje().showConfirmation("Eliminar Cuenta", getStage(), "¿Estas seguro de Eliminar está Cuenta?");
                if (answer) {

                    for (int i = 0; i < accountAssociat.size(); i++) {
                        deleteAccount(i);
                    }

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Cuenta", getStage(), "Cuenta eliminada correctamente.");

                    clear();
                    getStage().close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterUserViewController.class.getName()).log(Level.SEVERE, "Error eliminar la cuenta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cuenta", getStage(), "Ocurrio un error eliminando la cuenta.");
        }
    }

    public void chargeCbxAccount(String invoice) {
        ObservableList<AccountAssociate> filterList = accountAssociat.filtered(accountAssociate
                -> Objects.equals(accountAssociate.getInvoice(), invoice));
        cbxAccount.setItems(filterList);
        chargeNameAssociate(invoice);
    }

    private void chargeNameAssociate(String invoice) {
        for (Associate associate : associat) {
            if (Objects.equals(associate.getInvoice(), invoice)) {
                lblNameAssoiate.setText(associate.getName() + " " + associate.getLastName1());
                break;
            }
        }
    }

    private void clear() {
        lblNameAssoiate = null;
        cbxAccount.getSelectionModel().clearSelection();
    }

    private void deleteAccount(int i) {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        if (Objects.equals(accountAssociat.get(i).getName(), account)) {
            accountAssociat.remove(accountAssociat.get(i));
        }
        deleteMovement(account);
    }

    private void deleteMovement(String account) {
        for (int i = 0; i < movement.size(); i++) {
            if (Objects.equals(movement.get(i).getAccountName(), account)) {
                accountAssociat.remove(movement.get(i));
            }
        }
    }

    private String validateRequired() {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getName(), account)) {
                if (Objects.equals(accountAssociate.getBalanceAccount(), "0")) {
                    return "";
                }else{
                return "La cuenta aun tiene fondos, si deseas eliminar retira los fondos";}
            }
            
        }
        return "";
    }
}
