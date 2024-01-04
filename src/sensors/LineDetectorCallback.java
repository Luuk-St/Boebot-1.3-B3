package sensors;

public interface LineDetectorCallback {

    /**
     * onLine
     * @author Joshua Roovers
     * @param lineDetector the lineDetector that calls this callback method.
     * callback method for a lineDetector Sensor
     */
    public void onLine(LineDetector lineDetector);
}
