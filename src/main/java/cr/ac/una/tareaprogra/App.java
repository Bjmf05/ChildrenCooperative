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
import java.time.LocalDate;
import java.time.Month;
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
         
         AccountAssociate accountAssociate = new AccountAssociate(2L,"Puta","M0001");
         accountAssociateList.add(accountAssociate);
         LocalDate date = LocalDate.of(20001, 11, 05);
         Associate associate = new Associate(604700092L,"Breiner","Munoz","Fallas","M0001", date,"Masculino","C:/ProgramData/fotos_usuarios/M0001.jpg");
         associateList.add(associate);
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