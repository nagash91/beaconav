package micc.beaconav.indoorEngine.building.spot;

import android.graphics.PointF;



/**
 * Rappresenta un MarkerSpot sensorizzato da un beacon.
 * È quindi strettamente associato ad un unico beacon bluetooth)
 */
public class BeaconSpot extends MarkerSpot {

    public BeaconSpot(float x, float y) {
        super(x, y);
    }
}
