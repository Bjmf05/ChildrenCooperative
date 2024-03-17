package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.controller.Controller;
import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CheckAccountViewController extends Controller implements Initializable {

    @FXML
    private Button btnVerify;
    @FXML
    private ListView<String> lstVAccountAvailabe;
    @FXML
    private ListView<String> lstVAssociateAccount;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        dragAndDrop();
       

    }    

    @FXML
    private void onActionBtnVerify(ActionEvent event) {
    
        
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        ((Stage)btnCancel.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }

    @Override
    public void initialize() {
   }
    private void dragAndDrop(){
         lstVAccountAvailabe.setOnDragDetected(e -> {
            if (!lstVAccountAvailabe.getSelectionModel().isEmpty()) {
                String selectedItem = lstVAccountAvailabe.getSelectionModel().getSelectedItem();
                Dragboard db = lstVAccountAvailabe.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedItem);
                db.setContent(content);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        lstVAssociateAccount.setOnDragDropped(e -> {
            if (e.getDragboard().hasString()) {
                String draggedItem = e.getDragboard().getString();
                lstVAssociateAccount.getItems().add(draggedItem);
                lstVAccountAvailabe.getItems().remove(draggedItem);
            }
            e.setDropCompleted(true);
            e.consume();
        });
    }
}
