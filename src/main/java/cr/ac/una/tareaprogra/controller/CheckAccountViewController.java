package cr.ac.una.tareaprogra.controller;
import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.util.AppContext;
import java.net.URL;
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
    private ListView<Account> lstVAccountAvailabe;
    @FXML
    private ListView<AccountAssociate> lstVAssociateAccount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private ObservableList<Account> accountAvaible = FXCollections.observableArrayList();
    @FXML
    private TextField txfIdUser;

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        accountAvaible = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
        lstVAccountAvailabe.setItems(accountAvaible);
        dragAndDrop();
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }

    @Override
    public void initialize() {
    }
    private void dragAndDrop() {
        lstVAccountAvailabe.setOnDragDetected(e -> {
            if (!lstVAccountAvailabe.getSelectionModel().isEmpty()) {
                Account selectedItem = lstVAccountAvailabe.getSelectionModel().getSelectedItem();
                String serializeItem = serializeAccount(selectedItem);
                Dragboard db = lstVAccountAvailabe.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.put(AccountTransfer.DATA_FORMAT, serializeItem);
                db.setContent(content);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragOver(e -> {
            if (e.getDragboard().hasContent(AccountTransfer.DATA_FORMAT)) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragDropped(e -> {
            if (e.getDragboard().hasContent(AccountTransfer.DATA_FORMAT)) {
                String draggedItemString = (String) e.getDragboard().getContent(AccountTransfer.DATA_FORMAT);
                String[] parts = draggedItemString.split(",");
                String accountId = parts[0];
                String accountName = parts[1];
                lstVAssociateAccount.getItems().add(new AccountAssociate(Long.parseLong(accountId),accountName, txfIdUser.getText())); // Assuming you have a constructor in AccountAssociate that accepts an Account object
                lstVAccountAvailabe.getItems().removeIf(item->item.getId().equals(accountId));
                lstVAccountAvailabe.getItems().removeIf(item->item.getName().equals(accountName));
            }
            e.setDropCompleted(true);
            e.consume();
        });
    }

    private String serializeAccount(Account account) {
        return account.getId() + "," + account.getName();
    }

    public class AccountTransfer {

        public static final DataFormat DATA_FORMAT = new DataFormat("com.example.account");
    }

}
