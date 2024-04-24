/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
    private String videoPath;

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
        if(parameter.equals("A")){
            videoPath = "/cr/ac/una/tareaprogra/resources/guideAssociatesVideo.mp4";
        }
        else{
        videoPath = "/cr/ac/una/tareaprogra/resources/guideTeachersVideo.mp4";}
        URL videoUrl = getClass().getResource(videoPath);
        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnStopped(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });

        mediaPlayer.setOnReady(() -> {
            Duration duration = mediaPlayer.getMedia().getDuration();
            sliderOfVideo.setMax(duration.toSeconds());
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            sliderOfVideo.setValue(newValue.toSeconds());
        });

        // Permitir al usuario cambiar la posición del video usando el slider
        sliderOfVideo.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (sliderOfVideo.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(700);
        mediaView.setFitHeight(415);
        hBoxVideo.getChildren().add(mediaView);
    }

}
