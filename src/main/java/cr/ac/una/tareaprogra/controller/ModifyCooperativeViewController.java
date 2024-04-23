package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import cr.ac.una.tareaprogra.util.Mensaje;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifyCooperativeViewController extends Controller implements Initializable {

    @FXML
    private Button btnSearchLogo;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) AppContext.getInstance().get("newCooperative");    
    @FXML
    private TextField txtNewNameCooperative;
    @FXML
    private TextField txtAddressLogo;
    @FXML
    private ImageView imgNewLogo;
    
    private String newNameOfCooperative;
    private String ruteOfArchive;
    private String newUrlOfLogo;
    private TitleOfCooperativeViewController titleOfCooperative = (TitleOfCooperativeViewController) FlowController.getInstance().getController("TitleOfCooperativeView");
    private Cooperative instanceCooperative;
    private File selectedFile;
    private  String imagePath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtAddressLogo.setDisable(true);
    }    

    @FXML
    private void onActionBtnSearchLogo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione Imagen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg", "PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            ruteOfArchive = selectedFile.getAbsolutePath();
            txtAddressLogo.setText(ruteOfArchive);
        }
        if (!txtAddressLogo.getText().isEmpty()) {
            newUrlOfLogo = "file:///" + ruteOfArchive.replace("\\", "/");
            Image image = new Image(newUrlOfLogo);
            imgNewLogo.setImage(image);
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        if(!txtNewNameCooperative.getText().isEmpty()){
            txtNewNameCooperative.clear();
        }
        if(!txtAddressLogo.getText().isEmpty()){
            txtAddressLogo.clear();
            imgNewLogo.setImage(null);
        }
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) throws IOException {
        newNameOfCooperative = txtNewNameCooperative.getText();
        Boolean checkMesaje = false;
        if (!txtNewNameCooperative.getText().isEmpty()) {
            titleOfCooperative.setNameOfTitle(newNameOfCooperative);
            checkMesaje = true;
        }
        if (!txtAddressLogo.getText().isEmpty()) {
            String relativePath = "./Logos/";
            String pathOfFolder = "./Logos";
            File folder = new File(pathOfFolder);
            if (!folder.exists()) { 
                folder.mkdir();// Crea la carpeta de logos si no existe.
            }
            Path source = Paths.get(selectedFile.getAbsolutePath());
            Path destiny = Paths.get(relativePath,selectedFile.getName());
            imagePath = "file:" + relativePath + selectedFile.getName();
            Files.copy(source, destiny, StandardCopyOption.REPLACE_EXISTING);

            titleOfCooperative.setImagePath(newUrlOfLogo);
            imgNewLogo.setImage(null);
            checkMesaje = true;
        }
        safeChangeCoopertive();
        if(checkMesaje){
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Confirmacion", getStage(), "Los cambios se guardaron con exito, \nAlgunos cambios se reflejaran despues de reiniciar la aplicacion");
        }else{
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(), "Tiene que llenar al menos un dato");
        }
        txtNewNameCooperative.clear();
        txtAddressLogo.clear();
    }

    @Override
    public void initialize() {
    }
    
    private void safeChangeCoopertive() {
        for (Cooperative cooperative : cooperativeList) {
            if(!txtNewNameCooperative.getText().isEmpty()){
                cooperative.setNameOfCooperative(newNameOfCooperative);
            }
            if(!txtAddressLogo.getText().isEmpty()){
                cooperative.setLogoPath(imagePath);
            }
        }
    }

    private void loadCooperative() {
        instanceCooperative = new Cooperative();
        for (Cooperative cooperative : cooperativeList) {
            instanceCooperative = cooperative;
        }
    }
}
