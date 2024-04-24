package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class CheckRegistrationViewController extends Controller implements Initializable {

    @FXML
    private Label lblInvoice;
    @FXML
    private Button btnAccept;
    @FXML
    private Label lblTitle;
    private ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) AppContext.getInstance().get("newCooperative");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RegisterUserViewController registerUser = (RegisterUserViewController) FlowController.getInstance().getController("RegisterUserView");
        String invoise = registerUser.getInvoise();
        lblInvoice.setText(invoise);
        lblTitle.setText(getName());
    }

    @FXML
    private void onActionBtnAccept(ActionEvent event) {
        getStage().close();
    }

    @Override
    public void initialize() {
    }

    public String getName() {
        for (Cooperative cooperative : cooperativeList) {
            return cooperative.getNameOfCooperative();
        }
        return "COOPETOY";
    }

}
