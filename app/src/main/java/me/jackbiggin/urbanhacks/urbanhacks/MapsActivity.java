package me.jackbiggin.urbanhacks.urbanhacks;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

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
        mMap.setOnInfoWindowClickListener(this);

        Boolean Library = getIntent().getBooleanExtra("Library", false);
        Boolean Museum = getIntent().getBooleanExtra("Museum", false);
        Boolean Arena = getIntent().getBooleanExtra("Arena", false);
        Boolean Beaches = getIntent().getBooleanExtra("Beaches", false);
        Boolean Campgrounds = getIntent().getBooleanExtra("Campgrounds", false);
        Boolean Soccer = getIntent().getBooleanExtra("Soccer", false);
        Boolean Baseball = getIntent().getBooleanExtra("Baseball", false);
        Boolean Tennis = getIntent().getBooleanExtra("Tennis", false);
        Boolean Basketball = getIntent().getBooleanExtra("Basketball", false);
        Boolean Rec = getIntent().getBooleanExtra("Rec", false);
        //Adding markers based on request
        String url = "http://pleasegiveusafreeraspberrypi.com/urbanhacks/api.php?category=";

        if(getIntent().getBooleanExtra("Arena", false)){
            request(url + "arena", 285F);
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
            request(url + "Baseball", 36F);
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
        if(getIntent().getBooleanExtra("Library", false)){
            request(url + "library", 224F);
        }
        if(getIntent().getBooleanExtra("Museum", false)){
            request(url + "museum", 60F);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Opening Location Info",
                Toast.LENGTH_SHORT).show();
        Log.d("code3", marker.getTitle());
        Intent intent = new Intent(this,Site.class);

        String search =  marker.getTitle();
        if (search.equalsIgnoreCase("Baseball") ||search.equalsIgnoreCase("Basketball") ||search.equalsIgnoreCase("Soccer") ||search.equalsIgnoreCase("Tennis"))
        {
            Toast.makeText(this, "No Additional Info Available",
                    Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra("url", "http://pleasegiveusafreeraspberrypi.com/urbanhacks/?query=" + marker.getTitle() + "&uid=4");
            startActivity(intent);
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
