package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.util.Formato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MailBoxDepositViewController extends Controller implements Initializable {

    @FXML
    private TextField txfInvoice;
    @FXML
    private Button btnVerify;
    @FXML
    private Label lblNameAccount;
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
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txf10Amount;

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
  }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }
    
    private void disableData(){
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
    private void enableData(){
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
    private void clear(){
        disableData();
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
}
