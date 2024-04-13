/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.util.AppContext;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class TitleOfCooperativeViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label txtTitle;
    @FXML
    private ImageView imgMainlogo;
    private ObservableList <Cooperative> cooperativeList = (ObservableList <Cooperative>) AppContext.getInstance().get("newCooperative");
    private Cooperative cooperative = new Cooperative();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDataOfCooperative();
        setNameOfTitle(cooperative.getNameOfCooperative());
        setImagePath(cooperative.getLogoPath());
    }    

    @Override
    public void initialize() {
    }
    
    public void setNameOfTitle(String nameOfTitle){
        this.txtTitle.setText(nameOfTitle);
    }
    
    public void setImagePath(String pathOfImage){
        Image image = new Image(pathOfImage);
        this.imgMainlogo.setImage(image);
    }
    
    private void getDataOfCooperative(){
        for (Cooperative cooperative1 : cooperativeList) {
            cooperative = cooperative1;
        }
    }
}
