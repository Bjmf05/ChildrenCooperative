package cr.ac.una.tareaprogra.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RegisterUserViewController extends Controller implements Initializable {

    @FXML
    private Button btnMakePhoto;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cbxSex;
    @FXML
    private ImageView imgMakePhoto;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private DatePicker dpDateOfBirth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgMakePhoto.setImage(new Image("/cr/ac/una/tareaprogra/resources/IconChildPhoto.png"));
        cbxSex.getItems().addAll("Femenino","Masculino");
    }    

    @Override
    public void initialize() {
  }

    @FXML
    private void onActionBtnMakePhoto(ActionEvent event) {
    }

    @FXML
    private void onActionCbxSex(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }

    @FXML
    private void onActionDpDateOfBirth(ActionEvent event) {
    }
    
}
