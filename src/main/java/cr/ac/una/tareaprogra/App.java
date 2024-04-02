package cr.ac.una.tareaprogra;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.model.Movements;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
         ObservableList<Account> accountList = FXCollections.observableArrayList();
         ObservableList<AccountAssociate> accountAssociateList = FXCollections.observableArrayList();
         ObservableList<Associate> associateList = FXCollections.observableArrayList();
         ObservableList<Movements> movementsList = FXCollections.observableArrayList();
         
         
         AppContext.getInstance().set("newAccount", accountList);
         AppContext.getInstance().set("newAccountAssociate", accountAssociateList);
         AppContext.getInstance().set("newAssociate", associateList);
         AppContext.getInstance().set("newMovement", movementsList);
         
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("cr/ac/una/tareaprogra/resources/logo.png"));
        stage.setTitle("COOPETOY");
        FlowController.getInstance().goMain();
    }


    public static void main(String[] args) {
        launch();
    }

}