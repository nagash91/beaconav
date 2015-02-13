package micc.beaconav.indoorEngine.building.spot;

/**
 * Simile ad un BeaconSpot, ma invece di essere associato ad un beacon è associato ad un codice QR.
 */
public class QrSpot extends PathSpot{

    public QrSpot(float x, float y) {
        super(x, y);
    }
}
