package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.util.AppContext;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CreateAccountTypeViewController extends Controller implements Initializable {

    @FXML
    private Button btnAddAccount;
    @FXML
    private TextField txtTypeAccount;
    @FXML
    private TableView<Account> tbvAccount;
    @FXML
    private TableColumn<Account, Boolean> tbcModify;
    @FXML
    private TableColumn<Account, String> tbcName;
    private ObservableList<Account> accountList = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
     @FXML
    private TableColumn<Account, String> tbcIdAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        tbcIdAccount.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
         tbvAccount.setItems(accountList);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAddAccount(ActionEvent event) {
        if (!txtTypeAccount.getText().isEmpty()) {
            Account account = new Account(txtTypeAccount.getText());
            accountList.add(account);
            
        }
        txtTypeAccount.setText("");
        tbvAccount.setItems(accountList);
    }

}
