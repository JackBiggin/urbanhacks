package me.jackbiggin.urbanhacks.urbanhacks;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng point = new LatLng(43.27366276,-79.86750593);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,8.51f));

        //Adding markers based on request
        String url = "http://pleasegiveusafreeraspberrypi.com/urbanhacks/api.php?category=";

        if(getIntent().getBooleanExtra("Arena", false)){
            request(url + "arena", 200F);
        }
        if(getIntent().getBooleanExtra("Beaches", false)){
            request(url + "beach", 250F);
        }
        if(getIntent().getBooleanExtra("Campgrounds", false)){
            request(url + "campground", 44F);
        }
        if(getIntent().getBooleanExtra("Soccer", false)){
            request(url + "Soccer", 112F);
        }
        if(getIntent().getBooleanExtra("Baseball", false)){
            request(url + "Baseball", 209F);
        }
        if(getIntent().getBooleanExtra("Tennis", false)){
            request(url + "Tennis", 12F);
        }
        if(getIntent().getBooleanExtra("Basketball", false)){
            request(url + "Basketball", 228F);
        }
        if(getIntent().getBooleanExtra("Rec", false)){
            request(url + "rec", 215F);
        }
    }


    public void request(String url, final Float colour) {
        //network code
        Log.d("Net code", "Starting network request");
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Net code3 response:", response);
                        parse(response, colour);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Net code3", "Something is terribly wrong");
            }
        });
        queue.add(stringRequest);
    }

    public void parse(String data, final Float colour) {
        try {
            Gson googleJson = new Gson();
            Type type = new TypeToken<List<Location>>(){}.getType();
            List<Location> objList = googleJson.fromJson(data, type);


            for (Location s : objList) {
                addMarker(s.name , s.x , s.y, colour);
            }

        } catch (Exception e) {
            Log.d("Net code3", e.toString());
        }
    }

    public void addMarker(String name, double x, double y, final Float colour){
        // Add a marker in Sydney and move the camera
        LatLng point = new LatLng(x, y);
        BitmapDescriptor bd = BitmapDescriptorFactory.defaultMarker(colour);

        mMap.addMarker(new MarkerOptions().position(point).title(name).icon(bd));
    }
}

class Location{
    String name;
    double x;
    double y;
}