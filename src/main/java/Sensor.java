public class Sensor {
    public Sensor(int z){
        this(z, true);
    }
    public Sensor(int z, boolean close){
        zone = z;
        isClose = close;
    }
    public boolean isClose(){
        return isClose;
    }
    public int getZone() {
        return zone;
    }
    protected void setClose(boolean close) {
        isClose = close;
    }
    public void setState(SwitchState state){
        switch (state){
            case OPEN -> {
                setClose(false);
            }
            case CLOSE -> {
                setClose(true);}
        }
    }
    public SwitchState getState(){
        return state;
    }
    protected void setZone(int zone) {
        this.zone = zone;
    }
    private SwitchState state;
    private boolean isClose;
    private int zone;
}
