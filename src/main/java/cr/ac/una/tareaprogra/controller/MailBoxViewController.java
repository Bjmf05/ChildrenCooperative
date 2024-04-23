package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.model.MailBoxDeposit;
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
 * @author PC
 */
public class MailBoxViewController extends Controller implements Initializable {

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
    private TextField txfInvoice;
    @FXML
    private Button btnVerify;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txf10Amount;
    @FXML
    private ComboBox<AccountAssociate> cbxAccount;

    private ObservableList<Associate> associat = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
    private ObservableList<AccountAssociate> accountAssociat = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
    private ObservableList<MailBoxDeposit> mailBoxDeposit = (ObservableList<MailBoxDeposit>) AppContext.getInstance().get("newMailBoxDeposit");
    @FXML
    private Label lblNameInvoice;
    private int ThousandAmount20 = 0;
    private int ThousandAmount10 = 0;
    private int ThousandAmount5 = 0;
    private int ThousandAmount2 = 0;
    private int ThousandAmount1 = 0;
    private int Amount500 = 0;
    private int Amount100 = 0;
    private int Amount50 = 0;
    private int Amount25 = 0;
    private int Amount10 = 0;
    private int Amount5 = 0;

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
            for (Associate associate : associat) {
                if (Objects.equals(associate.getInvoice(), txfInvoice.getText())) {
                    chargeCbxAccount();
                    showNameAssociate(txfInvoice.getText());
                    btnSave.setDisable(false);
                    btnVerify.setDisable(true);
                    txfInvoice.setEditable(false);
                    enableData();
                    foundUser = true;
                    break;
                }
            }
            if (!foundUser) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Cuenta", getStage(), "No se encontro ningun asociado con ese folio.");
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
                clear();
            }
        } catch (Exception e) {
            Logger.getLogger(DepositFunctionaryViewController.class.getName()).log(Level.SEVERE, "Error realizando el deposito.", e);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Realizar Deposito", getStage(), "Ocurrio un error realizando el deposito.");
        }

    }

    private void disableData() {
        btnSave.setDisable(true);

        cbxAccount.setDisable(true);
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
        cbxAccount.setDisable(false);
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
        btnVerify.setDisable(false);
        txfInvoice.setEditable(true);
        txfInvoice.clear();
        lblNameInvoice.setText("");
        cbxAccount.setItems(FXCollections.observableArrayList());
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

    private void chargeCbxAccount() {
        ObservableList<AccountAssociate> filterList = accountAssociat.filtered(accountAssociate
                -> Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText()));
        cbxAccount.setItems(filterList);

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

        if (cbxAccount.getValue() == null) {
            return "No has elegido la cuenta";
        }
        if (atLeastOneHas) {
            return "";
        } else {
            return "No has dijitado ninguna cantidad. Hazlo he intenta de nuevo.";
        }
    }

    private void safeDeposit() {
        AccountAssociate selectedValue = cbxAccount.getValue();
        String account = selectedValue.getName();
        for (AccountAssociate accountAssociate : accountAssociat) {
            if (Objects.equals(accountAssociate.getInvoice(), txfInvoice.getText())
                    && Objects.equals(accountAssociate.getName(), account)) {
                changeToString();
                mailBoxDeposit.add(new MailBoxDeposit(accountAssociate.getInvoice(), accountAssociate.getName(), ThousandAmount20,
                        ThousandAmount10, ThousandAmount5, ThousandAmount2, ThousandAmount1,
                        Amount500, Amount100, Amount50, Amount25,
                        Amount10, Amount5));
                break;
            }
        }
    }

    private int checkNull(TextField data) {
        if (data.getText().isEmpty() || data == null) {
            return 0;
        }
        int intData = Integer.parseInt(data.getText());
        return intData;
    }

    private void changeToString() {
        ThousandAmount20 = checkNull(txf20ThousandAmount);
        ThousandAmount10 = checkNull(txf10ThousandAmount);
        ThousandAmount5 = checkNull(txf5ThousandAmount);
        ThousandAmount2 = checkNull(txf2ThousandAmount);
        ThousandAmount1 = checkNull(txf1ThousandAmount);;
        Amount500 = checkNull(txf500Amount);
        Amount100 = checkNull(txf100Amount);
        Amount50 = checkNull(txf50Amount);
        Amount25 = checkNull(txf25Amount);
        Amount10 = checkNull(txf10Amount);
        Amount5 = checkNull(txf5Amount);
    }

    private void showNameAssociate(String invoice) {
        ObservableList<Associate> associateList = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
        ObservableList<Associate> filterAsscociateList = associateList.filtered(associate -> Objects.equals(invoice, associate.getInvoice()));
        Associate associate = filterAsscociateList.get(0);
        lblNameInvoice.setText(associate.getName() + " " + associate.getLastName1());
    }
}
