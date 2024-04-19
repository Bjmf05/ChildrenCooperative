package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.App;
import cr.ac.una.tareaprogra.model.Data;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PrincipalViewController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
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
    private Button btnExit;
    @FXML
    private Button btnMaintenanceUser;
    @FXML
    private HBox prueba;
    @FXML
    private Button btnCheckAccountAssociate;
    @FXML
    private Button btnWithdrawal;
    @FXML
    private VBox vbxTeacher;
    @FXML
    private VBox vbxAssociate;
    @FXML
    private VBox vbxClerk;
    @FXML
    private Button btnDepositFuntionary;
    @FXML
    private Button btnMailBoxFunctionary;
    @FXML
    private VBox vbxButtons;
    @FXML
    private Button btnGame;
    @FXML
    private Button btnTutorial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String parameter = App.getParameter();
        delete(parameter);

    }

    @Override
    public void initialize() {

    }

    private void delete(String parameter) {
        if (parameter.equals("P")) {
            vbxButtons.getChildren().remove(vbxAssociate);
            vbxButtons.getChildren().remove(vbxClerk);
        }
        if (parameter.equals("A")) {
            vbxButtons.getChildren().remove(vbxClerk);
            vbxButtons.getChildren().remove(vbxTeacher);
        }
        if (parameter.equals("F")) {
            vbxButtons.getChildren().remove(vbxAssociate);
            vbxButtons.getChildren().remove(vbxTeacher);
        }
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

    private void onActionBtnMoveMoney(ActionEvent event) {
        FlowController.getInstance().goView("DepositFunctionaryView");
    }

    @FXML
    private void onActionBtnExit(ActionEvent event) {
        safeData();
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

    @FXML
    private void onActionBtnDepositFuntionary(ActionEvent event) {
        FlowController.getInstance().goView("DepositFunctionaryView");
    }

    @FXML
    private void onActionBtnMailBoxFunctionary(ActionEvent event) {
        FlowController.getInstance().goView("MailBoxDepositView");

    }

    private void safeData() {
        Data data = new Data();
        data.safeLists();
    }

    @FXML
    private void onActionBtnGame(ActionEvent event) {
    }

    @FXML
    private void onActionBtnTutorial(ActionEvent event) {
    }
}
