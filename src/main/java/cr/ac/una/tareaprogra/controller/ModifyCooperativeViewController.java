/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.util.AppContext;
import io.github.palexdev.materialfx.utils.FXCollectors;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) AppContext.getInstance().get("newAspect");    
    @FXML
    private TextField txtNewNameCooperative;
    @FXML
    private TextField txtAddressLogo;
    @FXML
    private ImageView imgNewLogo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnSearchLogo(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        String newName = txtNewNameCooperative.getText();
        Cooperative cooperative = new Cooperative (newName);        
        cooperativeList.add(cooperative);
        //actucalizar();
    }

    @Override
    public void initialize() {
         }
    
}
