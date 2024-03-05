/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PrincipalViewController extends Controller  implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Label lblTitulo;
    @FXML
    private VBox vpxButtons;
    @FXML
    private Button btnCreateAccountType;
    @FXML
    private Button btnModifyCooperative;
    @FXML
    private Button btnCheckIn;
    @FXML
    private Button btnCheckAccount;
    @FXML
    private Button btnMailBox;
    @FXML
    private Button btnMaintenance;
    @FXML
    private Button btnRegisterUser;
    @FXML
    private Button btnMoveMoney;
    @FXML
    private Button btnPrintId;
    @FXML
    private Button btnExit;

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
    private void onActionBtnCreateAccountType(ActionEvent event) {
        FlowController.getInstance().goView("createAccountType");
    }

    @FXML
    private void onActionBtnModifyCooperative(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCheckIn(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCheckAccount(ActionEvent event) {
    }

    @FXML
    private void onActionBtnMailBox(ActionEvent event) {
    }

    @FXML
    private void onActionBtnMaintenance(ActionEvent event) {
    }

    @FXML
    private void onActionBtnRegisterUser(ActionEvent event) {
    }

    @FXML
    private void onActionBtnMoveMoney(ActionEvent event) {
    }

    @FXML
    private void onActionBtnPrintID(ActionEvent event) {
    }

    @FXML
    private void onActionBtnExit(ActionEvent event) {
        FlowController.getInstance().salir();
    }
}