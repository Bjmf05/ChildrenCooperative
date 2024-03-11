/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareaprogra.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class WithdrawalViewController implements Initializable {

    @FXML
    private TextField txfVerify;
    @FXML
    private Button btnVerify;
    @FXML
    private ComboBox<?> txfAccount;
    @FXML
    private TextField txfAmountToWithdraw;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnWithdrawal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
    }

    @FXML
    private void onActionBtnWithdrawal(ActionEvent event) {
    }
    
}
