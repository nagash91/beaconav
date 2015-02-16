package micc.beaconav.outdoorEngine;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import micc.beaconav.FragmentHelper;
import micc.beaconav.test.JSONTest;
import micc.beaconav.R;
import micc.beaconav.test.testAdaptedLocationActivity;
import micc.beaconav.test.testLastLocationActivity;

/**
 * Created by nagash on 26/01/15.
 */
public class MapFragment extends Fragment
{

    private static final LatLng startLatLng = new LatLng(42.2226192,12.55);
    private static final float  startZoom = 5.4f;

    private Map map; // Might be null if Google Play services APK is not available.
    private Context context;

    private View myFragmentView = null;


    public MapFragment(){}


    private MuseumMarkerManager manager;

    private Button buttonProximity;
    private Button buttonJson;
    private Button buttonLocation;
    private Button buttonSingleLocation;

// * * * * SET UP FRAGMENT * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


    public void setMuseumMarkerManager(MuseumMarkerManager manager)
    {
        this.manager = manager;
    }

    private void setUp()  {
//        buttonProximity      =  (Button) myFragmentView.findViewById(R.id.buttonProximity);
//        buttonJson           =  (Button) myFragmentView.findViewById(R.id.buttonJson);
//        buttonLocation       =  (Button) myFragmentView.findViewById(R.id.buttonLocation);
//        buttonSingleLocation =  (Button) myFragmentView.findViewById(R.id.buttonSingleLocation);
    }
    private void setUpMap() {
        map.setCamera(startLatLng, startZoom);
    }


    private GoogleMap getGMapFromXML() {
        // Try to obtain the map from the SupportMapFragment.
        return ((SupportMapFragment)((FragmentActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        //return ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }







// * * * * SET UP EVENT LISTENER * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    public void setUpEventListeners() {

//        buttonJson.setOnClickListener(new View.OnClickListener()
//        {
//            @Override  public void onClick(View v) {
//                Intent intent = new Intent(context, JSONTest.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonLocation.setOnClickListener(new View.OnClickListener()
//        {
//            @Override  public void onClick(View v) {
//                Intent intent = new Intent(context, testAdaptedLocationActivity.class);
//                startActivity(intent);
//            }
//        });
//        buttonSingleLocation.setOnClickListener(new View.OnClickListener()
//        {
//            @Override  public void onClick(View v) {
//                Intent intent = new Intent(context, testLastLocationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonProximity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { toggleFakeProximity(v); }
//        });
    }

    public void onClickNavigate(View view)
    {
        map.route();
    }


    public void toggleFakeProximity() {
        map.resetLastProxyMuseum();
        if(map.getFakeProximity() == false)
            map.setFakeProximity(true);
        else map.setFakeProximity(false);

    }









// * * * * OVERRIDE METHODS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        //super.onCreateView(inflater, container, savedInstanceState);
//        myFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
//        return myFragmentView;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (myFragmentView != null)
        {
            ViewGroup parent = (ViewGroup) myFragmentView.getParent();
            if (parent != null) parent.removeView(myFragmentView);
        }
        try
        {
            myFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        }
        catch (InflateException e) { /* map is already there, just return view as it is */ }

        return myFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getArguments();
        context = this.getActivity();
        setUp();
        setUpEventListeners();
        if(map == null) {
            map = Map.setupIstance(getGMapFromXML(), manager);
            setUpMap();
        }
    }


}
