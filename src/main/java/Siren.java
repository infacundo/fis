import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import java.io.File;
public class Siren {
    public Siren (String mediaURL){
        Media media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        view = new SirenView();
    }
    //iniciar sonido
    public void start(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }
    public Polygon getView() {
        return view;
    }
    private SirenView view;
    private MediaPlayer mediaPlayer;
}
