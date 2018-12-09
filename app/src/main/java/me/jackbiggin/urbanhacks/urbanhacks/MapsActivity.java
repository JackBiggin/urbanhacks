package me.jackbiggin.urbanhacks.urbanhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LatLng point = new LatLng(43.27366276,-79.86750593);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,8.51f));

        Boolean Arena = getIntent().getBooleanExtra("Arena", false);
        Boolean Beaches = getIntent().getBooleanExtra("Beaches", false);
        Boolean Campgrounds = getIntent().getBooleanExtra("Campgrounds", false);
        Boolean Soccer = getIntent().getBooleanExtra("Soccer", false);
        Boolean Baseball = getIntent().getBooleanExtra("Baseball", false);
        Boolean Tennis = getIntent().getBooleanExtra("Tennis", false);
        Boolean Basketball = getIntent().getBooleanExtra("Basketball", false);
        Boolean Rec = getIntent().getBooleanExtra("Rec", false);

        String url = "http://pleasegiveusafreeraspberrypi.com/urbanhacks/api.php?category=beach";
        request(url);
    }


    public void request(String url) {
        //network code
        Log.d("Net code", "Starting network request");
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Net code3 response:", response);
                        parse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Net code3", "Something is terribly wrong");
            }
        });
        queue.add(stringRequest);
    }

    public void parse(String data) {
        try {
            Gson googleJson = new Gson();
            Type type = new TypeToken<List<Location>>(){}.getType();
            List<Location> objList = googleJson.fromJson(data, type);


            for (Location s : objList) {
                addMarker(s.name , s.x , s.y);
            }

        } catch (Exception e) {
            Log.d("Net code3", e.toString());
        }
    }

    public void addMarker(String name, double x, double y){
        // Add a marker in Sydney and move the camera
        LatLng point = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(point).title(name));
    }
}

class Location{
    String name;
    double x;
    double y;
}