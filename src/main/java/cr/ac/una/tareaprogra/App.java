package cr.ac.una.tareaprogra;

import cr.ac.una.tareaprogra.model.Cooperative;
import cr.ac.una.tareaprogra.model.Data;
import cr.ac.una.tareaprogra.util.AppContext;
import cr.ac.una.tareaprogra.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String parameter = "";
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
        if(args.length>0){
            parameter = args[0];
        }
        launch();
    }

    public static String getParameter() {
        return parameter;
    }
}
