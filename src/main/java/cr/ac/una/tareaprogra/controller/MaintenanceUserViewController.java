package cr.ac.una.tareaprogra.controller;

//import cr.ac.una.tareaprogra.model.PrintPdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MaintenanceUserViewController extends Controller implements Initializable {

    @FXML
    private Button btnVerify;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<?> cbxSex;
    @FXML
    private DatePicker dpDateOfBirth;
    @FXML
    private ImageView imgMakePhoto;
    @FXML
    private Button btnMakePhoto;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnPrintPdf;
    @FXML
    private Button btnSave;

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
    private void onActionBtnVerify(ActionEvent event) {
    }

    @FXML
    private void onActionCbxSex(ActionEvent event) {
    }

    @FXML
    private void onActionDpDateOfBirth(ActionEvent event) {
    }

    @FXML
    private void onActionBtnMakePhoto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
    }

    @FXML
    private void onActionBtnPrintPdf(ActionEvent event)throws FileNotFoundException, IOException  {
        
        //PrintPdf print = new PrintPdf();
        //print.printAsociate();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }
    
}
