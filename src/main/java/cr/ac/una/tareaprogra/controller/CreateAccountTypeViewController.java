package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
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
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcModify.setCellValueFactory((TableColumn.CellDataFeatures<Account, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcModify.setCellFactory((TableColumn<Account, Boolean> p) -> new ButtonCell());
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
     private class ButtonCell extends TableCell<Account, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(200);
            cellButton.getStyleClass().add("btn-maintenance");
            cellButton.setText("Modificar");
            cellButton.setOnAction((ActionEvent t) -> {
                Account account = (Account) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
ChangeNameAccountViewController  ChangeNameAccount = (ChangeNameAccountViewController) FlowController.getInstance().getController("ChangeNameAccountView");
ChangeNameAccount.nameAccount(account);
FlowController.getInstance().goViewInWindowModal("ChangeNameAccountView", getStage(), true);
tbvAccount.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
