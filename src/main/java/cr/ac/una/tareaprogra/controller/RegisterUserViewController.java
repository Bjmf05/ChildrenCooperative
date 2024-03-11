package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.util.Mensaje;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

    List<Node> required = new ArrayList<>();
    private String name;
    private int id;
    private String sex;
    private String addressPhoto;
    private String invoise;
    private LocalDate dateOfBirth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgMakePhoto.setImage(new Image("/cr/ac/una/tareaprogra/resources/IconChildPhoto.png"));
        cbxSex.getItems().addAll("Femenino", "Masculino");
        indicateRequired();
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
        try{
          String invalid= validateRequired();
          if(!invalid.isEmpty()){
          new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), invalid);}
          else{
                      safeToView();
        Associate associate = new Associate(id, name, invoise, dateOfBirth, sex, addressPhoto);
          }
        } catch(Exception ex){
            Logger.getLogger(RegisterUserViewController.class.getName()).log(Level.SEVERE, "Error guardando el asociado." , ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Ocurrio un error guardando el asociado.");
        }

    }

    @FXML
    private void onActionDpDateOfBirth(ActionEvent event) {
    }

    private void indicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(txtId, txtName, cbxSex, dpDateOfBirth));
    }

    private String validateRequired() {
        Boolean valid = true;
        for (Node node : required) {
            if (node instanceof TextField) {
                if (((TextField) node).getText().isEmpty()) {
                    valid = false;
                }
            } else if (node instanceof ComboBox) {
                if (((ComboBox<?>) node).getSelectionModel().isEmpty()) {
                    valid = false;
                }
            } else if (node instanceof DatePicker) {
                if (((DatePicker) node).getValue() == null) {
                    valid = false;
                }
            }
        }
        if (valid) {
            return "";
        } else {
            return "Aún hay espacios requeridos en blanco. Llénalos e intenta de nuevo.";
        }
    }

    private void safeToView() {
        name = txtName.getText();
        id = Integer.parseInt(txtId.getText());
        sex = cbxSex.getValue();
        dateOfBirth = dpDateOfBirth.getValue();
    }
}
