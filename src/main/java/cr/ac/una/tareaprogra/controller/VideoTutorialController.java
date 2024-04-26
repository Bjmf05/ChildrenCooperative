package cr.ac.una.tareaprogra.controller;

import cr.ac.una.tareaprogra.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class VideoTutorialController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Slider sliderOfVideo;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnStop;
    @FXML
    private HBox hBoxVideo;

    private MediaPlayer mediaPlayer;
    private String videoPath = "/cr/ac/una/tareaprogra/resources/guideTeachersVideo.mp4";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String parameter = App.getParameter();
        starVideo(parameter);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionPlay(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void onActionStop(ActionEvent event) {
        mediaPlayer.pause();
    }

    public void starVideo(String parameter) {
        if (parameter.equals("P")) {
            videoPath = "/cr/ac/una/tareaprogra/resources/guideTeachersVideo.mp4";
        }
        if (parameter.equals("F")) {
            videoPath = "/cr/ac/una/tareaprogra/resources/guideFuncionarioVideo.mp4";
        }
        if (parameter.equals("A")) {
            videoPath = "/cr/ac/una/tareaprogra/resources/guideAssociatesVideo.mp4";
        }
        
        URL videoUrl = getClass().getResource(videoPath);
        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        //Obtiene el valor maximo del video para que coincida con el video
        mediaPlayer.setOnReady(() -> {
            Duration duration = mediaPlayer.getMedia().getDuration();
            sliderOfVideo.setMax(duration.toSeconds());
        });

        //Actualiza el valor del marcador de progreso con el tiempo actual de la reproducción
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            sliderOfVideo.setValue(newValue.toSeconds());
        });

        //Permite cambiar la posición de la reproducion del video usando el marcador de progreso del slider
        sliderOfVideo.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (sliderOfVideo.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });

        //Permite cambiar la posición de la reproducion al hacer clic en cualquier parte del slider
        sliderOfVideo.setOnMouseClicked(event -> {
            double mouseXPosition = event.getX();
            double value = (mouseXPosition / sliderOfVideo.getWidth()) * sliderOfVideo.getMax();
            mediaPlayer.seek(Duration.seconds(value));
        });

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(700);
        mediaView.setFitHeight(415);
        hBoxVideo.getChildren().add(mediaView);
    }

}
