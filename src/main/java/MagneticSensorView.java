import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MagneticSensorView extends Group {
    public MagneticSensorView () {
        switchView = new Rectangle(6,6);
        setCloseView();
        magnetView1 = new Rectangle(6,6);
        magnetView1.setFill(Color.BLACK);
        magnetView1.setVisible(false);
        magnetView = new Rectangle(6,6);
        magnetView.setFill(Color.BLACK);
        getChildren().addAll(magnetView,switchView, magnetView1);
    }
    public void aparecer(){
        magnetView.setVisible(false);
        magnetView1.setVisible(true);
    }
    public void desaparecer(){
        magnetView.setVisible(true);
        magnetView1.setVisible(false);
    }

    public void setCloseView(){
        switchView.setFill(Color.RED);
        switchView.setStroke(Color.RED);

    }

    public void setOpenView(){
        switchView.setFill(Color.GREEN);
        switchView.setStroke(Color.GREEN);
    }

    public Rectangle getSwitchView(){
        return switchView;
    }
    public Rectangle getMagnetView(){
        return magnetView;
    }
    public Rectangle getMagnetView1(){
        return magnetView1;
    }
    public DoorView getDoorView(){
        return door;
    }
    private DoorView door;
    private final Rectangle magnetView1;
    private final Rectangle switchView;
    private final Rectangle magnetView;

}
