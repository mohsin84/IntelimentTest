package mohsin.reza.intelimenttest.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mohsin.reza.intelimenttest.R;

/**
 * Created by Mohsin on 11/3/2017.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback{

    //AutoClearedValue<FragmentMapBinding> binding;
    Double Lat=0.0, Lng=0.0;
    String Name="";
    private Context mContext;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v=layoutInflater.inflate(R.layout.fragment_map,container,false);
        mContext = getActivity();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        Lat=Double.parseDouble(args.getString("latitude"));
        Lng=Double.parseDouble(args.getString("longitude"));
        Name=args.getString("name");
        Log.v("MapFragment", args.getString("latitude")+","+args.getString("longitude"));
        mContext = getActivity();

        FragmentManager fm = getActivity().getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

    }


    public static MapFragment Create(String name,String lat, String ln)
    {
        MapFragment mapFragment=new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("latitude", lat.replaceAll("\\s",""));
        bundle.putString("longitude", ln.replaceAll("\\s",""));
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        //map.animateCamera(CameraUpdateFactory.zoomTo(15));
        Log.v("latlong",Lat+","+Lng);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.addMarker(new MarkerOptions().position(new LatLng(Lat,Lng)).title(Name));
    }
}
