/**
 * A window with its magnetic sensor.
 */

public class Window {
    public Window(int zone, WindowView view) {
        magneticSensor = new MagneticSensor(zone);
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {
        switch (state) {
            case CLOSE:
                state = State.OPEN;
                break;
            case OPEN:
                state = State.CLOSE;
                break;
            case OPENING:
                state = State.CLOSING;
                break;
            case CLOSING:
                state = State.OPENING;
                break;
        }
    }
    public void finishMovement() {
        // is called when this window ends closing or opening
        if (state == State.CLOSE) {
            changeState();
            magneticSensor.setSensorOpen();
        } else if (state == State.OPEN) {
            changeState();
            magneticSensor.setSensorClose();
        }
    }
    public State getState() {
        return state;
    }

    public MagneticSensor getMagneticSensor() {
        return magneticSensor;
    }

    public WindowView getView(){
        return wView;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
