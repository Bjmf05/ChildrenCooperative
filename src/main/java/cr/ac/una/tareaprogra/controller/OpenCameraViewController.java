package cr.ac.una.tareaprogra.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import cr.ac.una.tareaprogra.util.FlowController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OpenCameraViewController extends Controller implements Initializable {

    private Webcam webcam = Webcam.getDefault();
    @FXML
    private ImageView imgPhoto;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnMakePhoto;
    private Image image;
    private boolean isPreviewing = false;
    private String parameter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (webcam != null) {
            webcam.setViewSize(WebcamResolution.VGA.getSize());

            if (!isPreviewing && webcam != null) {
                webcam.open();
                isPreviewing = true;
                startPreview();
            }
        }
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        webcam.close();
        parameter = "c";
        getStage().close();
        
    }

    @FXML
    private void onActionBtnMakePhoto(ActionEvent event) {
        if (webcam != null && webcam.isOpen()) {
            String folderPath = "C:\\ProgramData\\Cooperativa\\fotos_usuarios"; 
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                }
            }
            File file = new File(folder, "foto.jpg");
            try {
                ImageIO.write(webcam.getImage(), "JPG", file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                webcam.close();
                isPreviewing = false;
            }
        }
        parameter = "p";
        getStage().close();
    }

    @Override
    public void initialize() {
    }

    private void startPreview() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (webcam.isOpen()) {
                 image = SwingFXUtils.toFXImage(webcam.getImage(), null);
                Platform.runLater(() -> imgPhoto.setImage(image));
            }
        }, 0, 33, TimeUnit.MILLISECONDS);
    }

    public String getParameter() {
        return parameter;
    }

}
