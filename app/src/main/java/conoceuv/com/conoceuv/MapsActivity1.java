package conoceuv.com.conoceuv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import conoceuv.com.conoceuv.Modelos.EdificioModel;

public class MapsActivity1 extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private double lat = 0.0, lon = 0.0;
    private LatLng FIUV = new LatLng(19.165458081797656, -96.11414909362793);
    ArrayList<HashMap<String,String>> location = new ArrayList<>();
    HashMap<String, String> map;
    private Marker markerDefault;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_maps1, container, false);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArray = obj.getJSONArray("Edificios");
            for(int i = 0;i<m_jArray.length();i++){
                JSONObject io = m_jArray.getJSONObject(i);
                EdificioModel edificioModel = new EdificioModel(io);
                map = new HashMap<>();
                map.put("LocationID",""+(i+1));
                map.put("Latitud",edificioModel.latitud);
                map.put("Longitud",edificioModel.longitud);
                map.put("LocationName",edificioModel.title);
                location.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        return rootView;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        markerDefault = googleMap.addMarker(new MarkerOptions().position(FIUV).title("Facultad Ingenieria UV"));

        for(int i = 0;i<location.size();i++){
            lat = Double.parseDouble(location.get(i).get("Latitud"));
            lon = Double.parseDouble(location.get(i).get("Longitud"));
            String name = location.get(i).get("LocationName");

            MarkerOptions marker = new MarkerOptions().position(new LatLng(lat,lon)).title(name);
            googleMap.addMarker(marker);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FIUV, 17.6f));
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(markerDefault)){
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                    FIUV, 17.8f);
            mMap.moveCamera(location);
        }
        return false;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("conoceuv.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
