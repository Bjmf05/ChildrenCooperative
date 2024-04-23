package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import cr.ac.una.tareaprogra.util.Mensaje;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CheckAccountViewController extends Controller implements Initializable {

    @FXML
    private Button btnVerify;
    @FXML
    private ListView<String> lstVAccountAvailabe;
    @FXML
    private ListView<String> lstVAssociateAccount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private ObservableList<String> obsAccountAvaible = FXCollections.observableArrayList();
    private ObservableList<String> obsAccountAssociat = FXCollections.observableArrayList();
    private ObservableList<AccountAssociate> accountAssociat;
    private ObservableList<Account> accountAvaible;
    private ObservableList<Associate> associat;

    @FXML
    private TextField txfIdUser;
    @FXML
    private Label lblNameAssociate;
    @FXML
    private Button btnDelete;

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dragAndDrop();
        accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
        accountAvaible = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
        associat = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        boolean foundUser = false;
        if (!txfIdUser.getText().isEmpty()) {
            for (Associate associate : associat) {
                if (Objects.equals(associate.getInvoice(), txfIdUser.getText())) {
                    compareList();
                    txfIdUser.setEditable(false);
                    btnSave.setDisable(false);
                    btnDelete.setDisable(false);
                    lblNameAssociate.setText(associate.getName() + " " + associate.getLastName1());
                    btnVerify.setDisable(true);
                    foundUser = true;
                }
            }
            if (!foundUser) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Revizar Cuentas", getStage(), "No se encontro ningun asociado con ese folio.");
            }
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
           ObservableList<AccountAssociate> filterAccountAssociate = filterAccountAssociates();
    Set<String> addedAccounts = getAddedAccounts(filterAccountAssociate);

    ObservableList<String> optionalList = lstVAssociateAccount.getItems();
    addNewAccounts(optionalList, addedAccounts, filterAccountAssociate);

    clear();
    }

    private void dragAndDrop() {
        lstVAccountAvailabe.setOnDragDetected(e -> {
            if (!lstVAccountAvailabe.getSelectionModel().isEmpty()) {
                String selectedItem = lstVAccountAvailabe.getSelectionModel().getSelectedItem();
                Dragboard db = lstVAccountAvailabe.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedItem);
                db.setContent(content);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragDropped(e -> {
            if (e.getDragboard().hasString()) {
                String draggedItem = e.getDragboard().getString();
                lstVAssociateAccount.getItems().add(draggedItem);
                lstVAccountAvailabe.getItems().remove(draggedItem);
            }
            e.setDropCompleted(true);
            e.consume();
        });
    }

    private String serializeAccount(Account account) {
        return account.getId() + "-" + account.getName();
    }

    private String serializeAccountAssociate(AccountAssociate accountAssociate) {
        return accountAssociate.getId() + "-" + accountAssociate.getName();
    }

    private void clear() {
        txfIdUser.clear();
        btnVerify.setDisable(false);
        lblNameAssociate.setText("");
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        txfIdUser.setEditable(true);
        lstVAccountAvailabe.setItems(FXCollections.observableArrayList());
        lstVAssociateAccount.setItems(FXCollections.observableArrayList());
        obsAccountAssociat.clear();
        obsAccountAvaible.clear();
    }

    private void fillLstVAccountAvailabe() {
        for (Account accountAvaib : accountAvaible) {
            String data = serializeAccount(accountAvaib);
            obsAccountAvaible.add(data);
        }

    }

    private void fillLstVAssociateAccount() {
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getInvoice(), txfIdUser.getText())) {
                String data = serializeAccountAssociate(accountAssociate);
                obsAccountAssociat.add(data);
            }

        }
    }

    private void compareList() {
        fillLstVAccountAvailabe();
        fillLstVAssociateAccount();
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (String item : obsAccountAvaible) {
            if (!obsAccountAssociat.contains(item)) {
                filteredList.add(item);
            }
        }
        lstVAccountAvailabe.setItems(filteredList);
        lstVAssociateAccount.setItems(obsAccountAssociat);
    }

    @FXML
    private void onActionBtnDelete(ActionEvent event) {
        DeleteAssociateAccountViewController deleteAssociate = (DeleteAssociateAccountViewController) FlowController.getInstance().getController("DeleteAssociateAccountView");
        deleteAssociate.chargeCbxAccount(txfIdUser.getText());
        FlowController.getInstance().goViewInWindowModal("DeleteAssociateAccountView", getStage(), true);
        FlowController.getInstance().delete("DeleteAssociateAccountView");
        clear();
    }
private ObservableList<AccountAssociate> filterAccountAssociates() {
    return accountAssociat.filtered(accountAssociate -> Objects.equals(accountAssociate.getInvoice(), txfIdUser.getText()));
}

private Set<String> getAddedAccounts(ObservableList<AccountAssociate> filterAccountAssociate) {
    Set<String> addedAccounts = new HashSet<>();
    for (AccountAssociate accountAssociate : filterAccountAssociate) {
        addedAccounts.add(Long.toString(accountAssociate.getId()));
    }
    return addedAccounts;
}

private void addNewAccounts(ObservableList<String> optionalList, Set<String> addedAccounts, ObservableList<AccountAssociate> filterAccountAssociate) {
    for (String item : optionalList) {
        String[] parts = item.split("-");
        String accountId = parts[0];
        String accountName = parts[1];
        boolean exists = checkIfAccountExists(accountId, accountName, addedAccounts, filterAccountAssociate);
        if (!exists) {
            addAccount(accountId, accountName, addedAccounts);
        }
    }
}

private boolean checkIfAccountExists(String accountId, String accountName, Set<String> addedAccounts, ObservableList<AccountAssociate> filterAccountAssociate) {
    if (addedAccounts.contains(accountId)) {
        return true;
    } else {
        for (AccountAssociate accountAssociate : filterAccountAssociate) {
            if (Objects.equals(accountAssociate.getId(), accountId) && Objects.equals(accountAssociate.getName(), accountName)) {
                return true;
            }
        }
    }
    return false;
}

private void addAccount(String accountId, String accountName, Set<String> addedAccounts) {
    if (!addedAccounts.contains(accountId)) {
        accountAssociat.add(new AccountAssociate(Long.parseLong(accountId), accountName, txfIdUser.getText()));
        addedAccounts.add(accountId);
    }
}

    @Override
    public void initialize() {
        clear();
}
}
