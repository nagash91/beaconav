package micc.beaconav.localization.beaconHelper.deprecated;

import com.estimote.sdk.Beacon;

import java.util.List;

/**
 * Created by nagash on 11/02/15.
 */
public interface BeaconProximityListener {

    public void OnBeaconProximity(List<Beacon> proximityBeacons);


}