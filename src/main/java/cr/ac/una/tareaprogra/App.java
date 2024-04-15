package cr.ac.una.tareaprogra;

import cr.ac.una.tareaprogra.model.Account;
import cr.ac.una.tareaprogra.model.AccountAssociate;
import cr.ac.una.tareaprogra.model.Associate;
import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.model.Data;
import cr.ac.una.tareaprogra.model.MailBoxDeposit;
import cr.ac.una.tareaprogra.model.Movements;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import java.io.File;
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
  
        Data data = new Data();
        data.startData();
        ObservableList <Cooperative> cooperativeList = (ObservableList <Cooperative>) AppContext.getInstance().get("newCooperative");
        Cooperative instanceCooperative = new Cooperative();
        for (Cooperative cooperative : cooperativeList) {
            instanceCooperative = cooperative;
        }
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image(instanceCooperative.getLogoPath()));
        stage.setTitle(instanceCooperative.getNameOfCooperative());
        FlowController.getInstance().goMain();
    }

    public static void main(String[] args) {
        launch();
    }

}
