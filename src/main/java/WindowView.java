import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class WindowView extends Group {


    public WindowView(int x, int y, int angle){
        makeWindowViewWithoutSensor();
        setRotate(angle);
        getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
        relocate(x,y);
        prepareOpen_CloseTransition();
        setOnMouseClicked(event -> {
            // Check if the click event is inside the window area
            if (event.getX() > slidingGlas.getX() && event.getX() < slidingGlas.getX() + slidingGlas.getWidth()
                    && event.getY() > slidingGlas.getY() && event.getY() < slidingGlas.getY() + slidingGlas.getHeight()) {
                startOpening();
                if (msView != null) {
                    msView.setOpenView();
                }
                if (sensor != null) {
                        sensor.setSensorOpen();
                    }
                }
            else {
                startClosing();
                if (msView != null) {
                    msView.setCloseView();
                }
                if (sensor != null) {
                    sensor.setSensorClose();
                }
            }
        });

    }
    private void makeWindowViewWithoutSensor(){
        origenPillar = new Rectangle(0, 0, 20, 20);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Rectangle(180, 0, 20, 20);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        fixedGlas = new Rectangle(21, 4, 82, 6);
        fixedGlas.setFill(Color.LIGHTBLUE);
        slidingGlas = new Rectangle(97,11,82,6);
        slidingGlas.setFill(Color.LIGHTBLUE);
        Rectangle border = new Rectangle(0, 0, 200, 20);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().add(border);
        getChildren().addAll(origenPillar, switchPillar, fixedGlas,slidingGlas);
    }
    public void setWindowModel(Window model) {
        winModel = model;
    }
    public Window getWindowModel(){
        return winModel;
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        this.msView = msView;
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        mv.getMagnetView().setX(slidingGlas.getX()+slidingGlas.getWidth()-mv.getMagnetView().getWidth());
        mv.getMagnetView().setY(slidingGlas.getY()+slidingGlas.getHeight()/2-mv.getMagnetView().getHeight()/2);
        mv.getMagnetView().translateXProperty().bind(slidingGlas.translateXProperty()); // so it moves along with window
        mv.getSwitchView().setX(switchPillar.getX()-switchPillar.getWidth()+mv.getSwitchView().getWidth()*2);
    }
    private void prepareOpen_CloseTransition(){
        transition = new TranslateTransition(Duration.millis(8000), slidingGlas);
        transition.setCycleCount(1);
        transition.setOnFinished(e -> winModel.finishMovement());
    }
    public void startOpening(){
        transition.stop();
        transition.setFromX(slidingGlas.getTranslateX()); // establece el punto de inicio de la transición
        transition.setToX(origenPillar.getX() - slidingGlas.getX() + origenPillar.getWidth()); // establece el punto final de la transición
        transition.play(); // inicia la transición
    }
    public void startClosing(){
        transition.stop();
        transition.setFromX(slidingGlas.getTranslateX());
        transition.setToX(0);
        transition.play();
    }
    private TranslateTransition transition;
    private Window winModel;
    private State state;
    private Rectangle switchPillar;
    private Rectangle slidingGlas;
    private Rectangle fixedGlas;
    private Rectangle origenPillar;
    private MagneticSensor sensor;
    private MagneticSensorView msView;
}
