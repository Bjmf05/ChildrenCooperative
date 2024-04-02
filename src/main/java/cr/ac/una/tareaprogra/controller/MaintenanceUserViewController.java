package cr.ac.una.tareaprogra.controller;

//import cr.ac.una.tareaprogra.model.PrintPdf;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import cr.ac.una.tareaprogra.util.Formato;
import cr.ac.una.tareaprogra.util.Mensaje;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
public class MaintenanceUserViewController extends Controller implements Initializable {

    @FXML
    private Button btnVerify;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cbxSex;
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
    @FXML
    private TextField txfLastName1;
    @FXML
    private TextField txfLastName2;
    @FXML
    private TextField txtFolio;
    private String addressImage;
    private Image image;
    Associate associate;
    List<Node> required = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbxSex.getItems().addAll("Femenino", "Masculino");
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtName.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfLastName1.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfLastName2.setTextFormatter(Formato.getInstance().letrasFormat(30));
        indicateRequired();
        disableData();
    }

    @Override
    public void initialize() {
        disableData();
    }

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
        ObservableList<Associate> associates = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
        for (Associate associat : associates) {
            if (txtFolio.getText().equals(associat.getInvoice())) {                
                
                associate = associat;
                unbindAssociate();
                bindAssociate();
                addressImage = associate.getAdressPhoto();
                File file = new File(addressImage);
                String localUrl = file.toURI().toString();
                imgMakePhoto.setImage(new Image(localUrl));
                enableData();
                break;
            }
        }

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
        clear();
    }

    @FXML
    private void onActionBtnPrintPdf(ActionEvent event) throws FileNotFoundException, IOException {

        //PrintPdf print = new PrintPdf();
        //print.printAsociate();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Modificar Asociado", getStage(), invalid);
            } else {
                ObservableList<Associate> associates = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
                if (associate.getId() != null) {
                    for (Associate associat : associates) {
                        if (Objects.equals(associat.getInvoice(), associate.getInvoice())) {
                            associat.setAssociate(associate);

                        }
                    }
                }
                                            
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Asociado", getStage(), "Asociado actualizado correctamente.");
                            clear();
                            associate = new Associate();
                            unbindAssociate();

            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterUserViewController.class.getName()).log(Level.SEVERE, "Error guardando el asociado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Ocurrio un error guardando el asociado.");
        }

    }

    private void enableData() {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txfLastName1.setDisable(false);
        txfLastName2.setDisable(false);
        cbxSex.setDisable(false);
        dpDateOfBirth.setDisable(false);
        btnPrintPdf.setDisable(false);
        btnMakePhoto.setDisable(false);
        txtFolio.setDisable(true);
    }

    private void disableData() {
        addressImage = "/cr/ac/una/tareaprogra/resources/IconChildPhoto.png";
        imgMakePhoto.setImage(new Image(addressImage));
        txtId.setDisable(true);
        txtName.setDisable(true);
        txfLastName1.setDisable(true);
        txfLastName2.setDisable(true);
        cbxSex.setDisable(true);
        dpDateOfBirth.setDisable(true);
        btnPrintPdf.setDisable(true);
        btnMakePhoto.setDisable(true);
        txtFolio.setDisable(false);
    }

    private void bindAssociate() {
        txtFolio.textProperty().bind(associate.invoice);
        txtId.textProperty().bindBidirectional(associate.id);
        txtName.textProperty().bindBidirectional(associate.name);
        txfLastName1.textProperty().bindBidirectional(associate.lastName1);
        txfLastName2.textProperty().bindBidirectional(associate.lastName2);
        cbxSex.valueProperty().bindBidirectional(associate.sex);
        dpDateOfBirth.valueProperty().bindBidirectional(associate.dateOfBirth);

    }

    private void unbindAssociate() {
        txtFolio.textProperty().unbind();
        txtId.textProperty().unbindBidirectional(associate.id);
        txtName.textProperty().unbindBidirectional(associate.name);
        txfLastName1.textProperty().unbindBidirectional(associate.lastName1);
        txfLastName2.textProperty().unbindBidirectional(associate.lastName2);
        cbxSex.valueProperty().unbindBidirectional(associate.sex);
        dpDateOfBirth.valueProperty().unbindBidirectional(associate.dateOfBirth);
    }

    private void indicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(txtId, txtName, txfLastName1, txfLastName2, cbxSex, dpDateOfBirth));
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

    private void clear() {
        disableData();

        txtId.clear();
        txtName.clear();
        txfLastName1.clear();
        txfLastName2.clear();
        dpDateOfBirth.setValue(null);
        cbxSex.getSelectionModel().clearSelection();
        imgMakePhoto.setImage(new Image("/cr/ac/una/tareaprogra/resources/IconChildPhoto.png"));
        cbxSex.setValue("Masculino");
        txtFolio.requestFocus();
        FlowController.getInstance().delete("CheckRegistrationView");
        FlowController.getInstance().delete("OpenCameraView");
    }
}
