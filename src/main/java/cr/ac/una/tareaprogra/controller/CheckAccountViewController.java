package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.util.AppContext;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
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

import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.stage.Stage;

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

        if (!txfIdUser.getText().isEmpty()) {
            for (Associate associate : associat) {
                if (Objects.equals(associate.getInvoice(), txfIdUser.getText())) {
                    compareList();
                }
            }
            txfIdUser.setEditable(false);
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        ObservableList<String> optionalList = lstVAssociateAccount.getItems();
        for (String item : optionalList) {

            String[] parts = item.split("-");
            String accountId = parts[0];
            String acccountName = parts[1];

            boolean exists = false;
            for (AccountAssociate accountAssociate : accountAssociat) {
                if (Objects.equals(accountAssociate.getInvoice(), txfIdUser.getText())) {
                    if (Objects.equals(accountAssociate.getId(), accountId)) {
                        exists = true;
                        break;
                    }
                }
            }
            
            if (!exists) {
                accountAssociat.add(new AccountAssociate(Long.parseLong(accountId),acccountName,txfIdUser.getText()));
            }
        }
        clear();
    }

    @Override
    public void initialize() {
        clear();
        accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
        accountAvaible = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
        associat = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
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

}
