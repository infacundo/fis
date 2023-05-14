public class Door {
    public Door(MagneticSensor sensor, DoorView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        dView = view;
        dView.addMagneticSensorView(magneticSensor.getView());
        dView.setDoorModel(this);
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
    public void startMovement() {
        // is called when this door starts closing or opening
        switch (state) {
            case OPENING -> magneticSensor.setSensorOpen();
            case CLOSING -> magneticSensor.setSensorClose();
        }
    }
    public void getZone(){
        magneticSensor.getZone();
    }
    public void setOpen() {
        state = State.OPEN;
    }
    public void setClose() {
        state = State.CLOSE;
    }
    public boolean getState(){
        return state==State.OPEN;
    }

    public DoorView getView(){
        return dView;
    }

    private final DoorView dView;
    private final MagneticSensor magneticSensor;
    private State state;
 }
