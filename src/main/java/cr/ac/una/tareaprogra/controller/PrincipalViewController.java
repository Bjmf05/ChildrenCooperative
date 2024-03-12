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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PrincipalViewController extends Controller implements Initializable {

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
    private Button btnRegisterUser;
    @FXML
    private Button btnMoveMoney;
    @FXML
    private Button btnExit;
    @FXML
    private ImageView imgLogoPrincipal;
    @FXML
    private Button btnMaintenanceUser;
    @FXML
    private HBox prueba;
    @FXML
    private Button btnCheckAccountAssociate;
    @FXML
    private Button btnWithdrawal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgLogoPrincipal.setImage(new Image("/cr/ac/una/tareaprogra/resources/logo.png"));

    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnCreateAccountType(ActionEvent event) {
        FlowController.getInstance().goView("CreateAccountTypeView");
    }

    @FXML
    private void onActionBtnModifyCooperative(ActionEvent event) {
        FlowController.getInstance().goView("ModifyCooperativeView");
    }

    @FXML
    private void onActionBtnCheckIn(ActionEvent event) {
        FlowController.getInstance().goView("RegisterUserView");
    }

    @FXML
    private void onActionBtnCheckAccount(ActionEvent event) {
        FlowController.getInstance().goView("CheckAccountView");
    }

    @FXML
    private void onActionBtnMailBox(ActionEvent event) {
        FlowController.getInstance().goView("MailBoxView");
    }

    @FXML
    private void onActionBtnRegisterUser(ActionEvent event) {
        FlowController.getInstance().goView("RegisterUserView");
    }

    @FXML
    private void onActionBtnMoveMoney(ActionEvent event) {
        FlowController.getInstance().goView("MoveMoneyView");
    }

    @FXML
    private void onActionBtnExit(ActionEvent event) {
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnMaintenanceUser(ActionEvent event) {
        FlowController.getInstance().goView("MaintenanceUserView");
    }

    @FXML
    private void onActionBtnCheckAccountAssociate(ActionEvent event) {
        FlowController.getInstance().goView("CheckAccountAssociateView");
    }

    @FXML
    private void onActionBtnWithdrawal(ActionEvent event) {
         FlowController.getInstance().goView("WithdrawalView");
    }
}
