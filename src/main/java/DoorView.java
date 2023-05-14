import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class DoorView extends Group {

    public DoorView(int x, int y, int angle){
        makeDoorViewWithoutSensor();
        setRotate(angle);
        getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
        relocate(x,y);
        //prepareOpen_CloseTransition();
        rectangleclose.setOnMouseClicked(event -> {
            startOpening();
        });
        rectangleopen.setOnMouseClicked(event -> {
            startClosing();
        });
    }
    private void makeDoorViewWithoutSensor(){
        msView = new MagneticSensorView();
        Polygon origenPillar = new Polygon();
        origenPillar.getPoints().addAll(0d,0d, 0d,20d, 10d, 20d, 10d, 10d, 20d, 10d, 20d, 0d);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);

        switchPillar = new Polygon(160d,0d, 160d, 10d,170d, 10d, 170d, 20d, 180d, 20d, 180d, 0d );
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);

        rectangleclose = new Rectangle(10,10,160,10);
        rectangleclose.setFill(Color.BURLYWOOD);
        rectangleclose.setTranslateX(-rectangleclose.getWidth()+160);
        rectangleopen = new Rectangle(10,10,10,160);
        rectangleopen.setFill(Color.BURLYWOOD);
        rectangleclose.setTranslateX(-rectangleclose.getWidth()+160);
        rectangleopen.setVisible(false);
        Group doorGroup = new Group(origenPillar, switchPillar, rectangleclose, rectangleopen);
        Rectangle border = new Rectangle(0,0 ,180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d,4d );

        getChildren().addAll(border);
        getChildren().add(doorGroup);
    }
    public void setDoorModel(Door model) {
        doorModel = model;
    }

    private void startOpening() {
        MagneticSensor sensor1 = new MagneticSensor(z);
        if (msView != null) {
            msView.setOpenView();
            if (sensor1 != null) {
                sensor1.setSensorOpen();
                System.out.println(sensor1.isClose());
            }
        }
        rectangleclose.setVisible(false);
        rectangleopen.setVisible(true);
        msView.aparecer();
        msView.getMagnetView().setVisible(false);
    }
    /*private void prepareOpen_CloseTransition(){
        transition = new RotateTransition(Duration.millis(8000), rectangleclose);
        transition.setCycleCount(1);
        transition.setOnFinished(e -> doorModel.finishMovement());
    }*/
    private void startClosing() {
        MagneticSensor sensor1 = new MagneticSensor(z);
        if (msView != null) {
            msView.setOpenView();
            if (sensor1 != null) {
                sensor1.setSensorClose();
                System.out.println(sensor1.isClose());
            }
        }
        rectangleopen.setVisible(false);
        rectangleclose.setVisible(true);
        msView.desaparecer();
        msView.getMagnetView().setVisible(true);
    }
    public Door getDoorModel(){

        return doorModel;
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        this.msView = msView;
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        mv.getMagnetView().setX(rectangleclose.getBoundsInLocal().getHeight()+154);
        mv.getMagnetView().setY(rectangleclose.getBoundsInLocal().getHeight()+10);
        mv.getSwitchView().setY(rectangleclose.getBoundsInLocal().getHeight()+10);
        mv.getSwitchView().setX(rectangleclose.getBoundsInLocal().getHeight()+162);
        mv.getMagnetView1().setY(rectangleopen.getBoundsInLocal().getHeight()+4);
        mv.getMagnetView1().setX(-rectangleopen.getBoundsInLocal().getHeight()+164);
    }
    public void setMagneticSensor(MagneticSensor sensor){
        this.sensor = sensor;
    }
    public MagneticSensor getMagneticSensor(){
        return sensor;
    }
    public void changeState(){
        if (state == State.CLOSE){
            state = State.OPEN;
        }
    }
    public void setState(State state){
        this.state = state;
    }
    public Sensor getSensor5(){
        return sensor5;
    }
    public void changeState2(){
        if (state == State.OPEN){
            state = State.CLOSE;
        }
    }
    private int z;
    private MagneticSensorView msView;
    private MagneticSensor sensor, magneticSensor;
    private RotateTransition transition;
    private Door doorModel;
    private Polygon switchPillar;
    private Rectangle rectangleclose,rectangleopen;
    private State state;
    private Sensor sensor5;

}
