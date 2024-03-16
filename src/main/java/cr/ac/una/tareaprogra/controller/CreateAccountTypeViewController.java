/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.model.Account;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CreateAccountTypeViewController extends Controller implements Initializable {

    @FXML
    private Button btnAddAccount;
    @FXML
    private TextField txtTypeAccount;
    @FXML
    private TableView<Account> tbvAccount;
    @FXML
    private TableColumn<Account, Boolean> tbcModify;
    @FXML
    private TableColumn<Account, String> tbcName;
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
    private void onActionBtnAddAccount(ActionEvent event) {
        if(!txtTypeAccount.getText().isEmpty()){
            Account account = new Account(txtTypeAccount.getText());
            System.out.println(account.getId());
            System.out.println(account.getName());
        }
        txtTypeAccount.setText("");
    }
    
}
