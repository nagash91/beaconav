package micc.beaconav.gui.customList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.FragmentHelper;
import micc.beaconav.db.timeStatistics.TimeStatisticsManager;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Date;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class MuseumDescrFragment extends Fragment
{


    private TextView textViewMuseumDescr = null;
    private MuseumRow museumRow = null;
    private FloatingActionButton toIndoorBtn = null;
    private FloatingActionButton navToMuseumBtn = null;

    public MuseumDescrFragment() {
    }

    Date startNavigationDate;


    @Override
    public void onResume() {
        super.onResume();
        startNavigationDate = new Date();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(museumRow != null) {
            long lastTimeInApp = TimeStatisticsManager.readInAppTime(this.museumRow);
            long timeInSeconds = ((new Date()).getTime() - startNavigationDate.getTime()) / 1000;
            TimeStatisticsManager.writeInAppTime(this.museumRow, timeInSeconds + lastTimeInApp);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_museum_descr, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewMuseumDescr = (TextView)getView().findViewById(R.id.museumDescription);
        if(museumRow != null) {
            textViewMuseumDescr.setText(museumRow.getDescr());
        }


        toIndoorBtn = (FloatingActionButton)getView().findViewById(R.id.toIndoorBtn);
        toIndoorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentHelper.instance().showIndoorFragment(museumRow);
                FragmentHelper.instance().getMainActivity().getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                // TODO:    QUESTO CODIEC SAREBBE MEGLIO SE SI RIUSCISSE A PORTARLO NELL'HELPER E/O MAIN ACTIVITY RENDENDOLO FRUIBILE CON METODO PUBBLICO
                // BASTEREBBE NEL FRAGMENT HELPER FARE DEI METODI CHE ALZANO E ABBASSANO IL PANEL DEL MAIN RICHIAMANDO UN METODO PUBBLICO DEL  MAIN CHE FA QUESTO.

            }
        });


        navToMuseumBtn = FragmentHelper.instance().getMainActivity().getFloatingActionButton();
        navToMuseumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( museumRow != null && museumRow instanceof MuseumRow)
                FragmentHelper.instance().navigateToMuseumOnBtnClick((MuseumRow)museumRow, v);
                FragmentHelper.instance().getMainActivity().getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }


    //Questo setter è fondamentale, al Fragment di quale museo sto parlando
    public void setMuseumRow(MuseumRow row){
        this.museumRow = row;
        if(textViewMuseumDescr != null) {
            textViewMuseumDescr.setText(museumRow.getDescr());
        }
    }


//    public void updateMuseumDescr(String newDescr){
//        if(textViewMuseumDescr != null)
//            textViewMuseumDescr.setText(newDescr);
//    }



}
