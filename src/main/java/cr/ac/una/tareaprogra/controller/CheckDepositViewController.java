package cr.ac.una.tareaprogra.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CheckDepositViewController extends Controller implements Initializable {

    @FXML
    private Button btnAccept;

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
    private void onActionBtnAccept(ActionEvent event) {
        getStage().close();
    }
    
}
