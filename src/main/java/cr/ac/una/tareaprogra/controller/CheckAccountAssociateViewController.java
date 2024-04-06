package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.model.Movements;
import cr.ac.una.tareaprogra.util.AppContext;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CheckAccountAssociateViewController extends Controller implements Initializable {

    @FXML
    private TextField txfInvoice;
    @FXML
    private Button btnVerify;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<Movements> tbvMovementAccount;
    @FXML
    private TableColumn<Movements, LocalDate> tbvClDate;
    @FXML
    private TableColumn<Movements, LocalTime> tbvClHour;
    @FXML
    private TableColumn<Movements, String> tbvClMovement;
    @FXML
    private TableColumn<Movements, Long> tbvClAmount;
    @FXML
    private TableColumn<Movements, Long> tbvClAccountBalance;
    @FXML
    private Button btnClear;
    private ObservableList<Movements> movement = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");
    private ObservableList<AccountAssociate> accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    @FXML
    private ComboBox<AccountAssociate> cbxAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbvClDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tbvClHour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        tbvClMovement.setCellValueFactory(new PropertyValueFactory<>("movement"));
        tbvClAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbvClAccountBalance.setCellValueFactory(new PropertyValueFactory<>("balanceAccount"));
    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        if (!txfInvoice.getText().isEmpty()) {
            for (Movements movements : movement) {
                if (Objects.equals(movements.getInvoice(), txfInvoice.getText())) {
                    chargeCbxAccount();
                    break;
                }
            }
            btnVerify.setDisable(true);
            txfInvoice.setEditable(false);
        }
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
                AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        ObservableList<Movements> filterList = movement.filtered(movements
                -> Objects.equals(movements.getAccountName(), account)
                && Objects.equals(movements.getInvoice(), txfInvoice.getText()));
        tbvMovementAccount.setItems(filterList);
    }

    @FXML
    private void onActionBtnClear(ActionEvent event) {
                    btnVerify.setDisable(false);
                    txfInvoice.clear();
            txfInvoice.setEditable(true);     
cbxAccount.setItems(FXCollections.observableArrayList());
tbvMovementAccount.setItems(FXCollections.observableArrayList());
    }

    private void chargeCbxAccount() {
        ObservableList<AccountAssociate> filterList = accountAssociat.filtered(accountAssociate
                -> Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText()));
        cbxAccount.setItems(filterList);
    }
}
