package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import cr.ac.una.tareaprogra.util.Formato;
import cr.ac.una.tareaprogra.util.Mensaje;

import java.io.File;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class RegisterUserViewController extends Controller implements Initializable {

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
    private Long id;
    private String sex;
    private String addressPhoto;
    private String invoise;
    private LocalDate dateOfBirth;
    private String lastName1;
    private String lastName2;
    @FXML
    private TextField txtLastName1;
    @FXML
    private TextField txtLastName2;
    @FXML
    private Button btnOpenCamera;
    private Image image;
    private boolean photoRequired = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgMakePhoto.setImage(new Image("/cr/ac/una/tareaprogra/resources/IconChildPhoto.png"));
        cbxSex.getItems().addAll("Femenino", "Masculino");
        
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtName.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtLastName1.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtLastName2.setTextFormatter(Formato.getInstance().letrasFormat(30));
        indicateRequired();
        txtId.requestFocus();
    }

    @Override
    public void initialize() {
        clear();
    }

    @FXML
    private void onActionCbxSex(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), invalid);
            } else {
                safeToView();
                Associate associate = new Associate(id, name, lastName1, lastName2, invoise, dateOfBirth, sex, addressPhoto);
                ObservableList<Associate> associates = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
                associates.add(associate);
                FlowController.getInstance().goViewInWindowModal("CheckRegistrationView", getStage(), true);
                clear();
              
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterUserViewController.class.getName()).log(Level.SEVERE, "Error guardando el asociado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Ocurrio un error guardando el asociado.");
        }

    }

    @FXML
    private void onActionDpDateOfBirth(ActionEvent event) {
    }

    private void indicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(txtId, txtName, txtLastName1, txtLastName2, cbxSex, dpDateOfBirth));
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
        if (valid && photoRequired) {
            return "";
        } else {
            return "Aún hay espacios requeridos en blanco. Llénalos e intenta de nuevo.";
        }
    }

    private void safeToView() {
        name = txtName.getText();
        id = Long.parseLong(txtId.getText());
        sex = cbxSex.getValue();
        dateOfBirth = dpDateOfBirth.getValue();
        lastName1 = txtLastName1.getText();
        lastName2 = txtLastName2.getText();
        invoise = createInvoice();
        addressPhoto = changeFileName(invoise);
    }

    private String changeFileName(String newInvoise) {
        String FOLDER_PATH = "C:/ProgramData/fotos_usuarios/";
        if (newInvoise != null && !newInvoise.isEmpty()) {
            File file = new File("C:/ProgramData/fotos_usuarios/foto.jpg");
            File newFile = new File(FOLDER_PATH + newInvoise + ".jpg");
            if (file.exists()) {
                file.renameTo(newFile);
            }
        }
        String address = FOLDER_PATH + newInvoise + ".jpg";
        return address;
    }

    private String createInvoice() {
        char firstLetter = lastName1.charAt(0);
        String newInvoice = " ";
        int countEqualLetter = 0;
        ObservableList<Associate> associates = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");

        for (Associate associate : associates) {
            char firstChar = associate.getInvoice().charAt(0);
            if (firstChar == firstLetter) {
                countEqualLetter++;
            }
        }

        if (countEqualLetter == 0) {
            newInvoice = firstLetter + "0001";
        } else {
            int maxNumber = 0;
            for (Associate associate : associates) {
                char firstChar = associate.getInvoice().charAt(0);
                if (firstChar == firstLetter) {
                    String numberPart = associate.getInvoice().substring(1);
                    int number = Integer.parseInt(numberPart);
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                }
            }
            newInvoice = firstLetter + String.format("%04d", maxNumber + 1);
        }
        return newInvoice;
    }

    @FXML
    private void onActionBtnOpenCamera(ActionEvent event) {
        OpenCameraViewController openCamera = (OpenCameraViewController) FlowController.getInstance().getController("OpenCameraView");
        FlowController.getInstance().goViewInWindowModal("OpenCameraView", getStage(), true);
        if (openCamera.getParameter().equals("p")) {
            File file = new File("C:/ProgramData/fotos_usuarios/foto.jpg");
            String localUrl = file.toURI().toString();
            Image image = new Image(localUrl);
            imgMakePhoto.setImage(image);
            photoRequired = true;
            
        }
    }

    private void clear() {
        
        txtId.clear();
        txtName.clear();
        txtLastName1.clear();
        txtLastName2.clear();
        dpDateOfBirth.setValue(null);
        cbxSex.getSelectionModel().clearSelection();
        imgMakePhoto.setImage(new Image("/cr/ac/una/tareaprogra/resources/IconChildPhoto.png"));
        cbxSex.setValue("Masculino");
        photoRequired = false;
        txtId.requestFocus();
        FlowController.getInstance().delete("CheckRegistrationView");
        FlowController.getInstance().delete("OpenCameraView");
    }

    public String getInvoise() {
        return invoise;
    }
    
}
