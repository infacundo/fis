public class MagneticSensor extends Sensor {
    public MagneticSensor(int z){
        super(z);
        view= new MagneticSensorView();
    }
    //crear setSensorOpen y setSensorClose
    public void setSensorOpen() {
        System.out.println("Sensor Open");
        setState(SwitchState.OPEN);
    }
    public void setSensorClose() {
        System.out.println("Sensor Close");
        setState(SwitchState.CLOSE);
    }
    public MagneticSensorView getView(){ return view;}
    private final MagneticSensorView view;
}
