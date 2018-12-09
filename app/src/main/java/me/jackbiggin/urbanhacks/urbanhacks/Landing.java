package me.jackbiggin.urbanhacks.urbanhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Timer;
import java.util.TimerTask;

public class Landing extends AppCompatActivity {

    private Button button;
    private CheckBox Arena, Beaches, Campgrounds, Soccer, Baseball, Tennis, Basketball, Rec, Library, Museum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity();
            }
        });
        updatePoints();
        final long period = 0;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                updatePoints();
            }
        }, 0, 5000);
    }

    public void openactivity(){
        Intent intent = new Intent(this,MapsActivity.class);

        Library = (CheckBox) findViewById(R.id.Library);
        Museum = (CheckBox) findViewById(R.id.Museum);
        Arena = (CheckBox) findViewById(R.id.Arenas);
        Beaches = (CheckBox) findViewById(R.id.Beaches);
        Campgrounds = (CheckBox) findViewById(R.id.Campgrounds);
        Soccer = (CheckBox) findViewById(R.id.Soccer);
        Baseball = (CheckBox) findViewById(R.id.Baseball);
        Tennis = (CheckBox) findViewById(R.id.Tennis);
        Basketball = (CheckBox) findViewById(R.id.Basketball);
        Rec = (CheckBox) findViewById(R.id.Rec);

        intent.putExtra("Library", Library.isChecked());
        intent.putExtra("Museum", Museum.isChecked());
        intent.putExtra("Arena", Arena.isChecked());
        intent.putExtra("Beaches", Beaches.isChecked());
        intent.putExtra("Campgrounds", Campgrounds.isChecked());
        intent.putExtra("Soccer", Soccer.isChecked());
        intent.putExtra("Baseball", Baseball.isChecked());
        intent.putExtra("Tennis", Tennis.isChecked());
        intent.putExtra("Basketball",  Basketball.isChecked());
        intent.putExtra("Rec", Rec.isChecked());
        startActivity(intent);
    }

    public void updatePoints()
    {
        //network code
        Log.d("Net code", "Starting network request");
        String url = "http://pleasegiveusafreeraspberrypi.com/urbanhacks/get_points_count.php?uid=1";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Net code3 response:", response);
                        TextView tv = findViewById(R.id.textView5);
                        tv.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Net code3", "Something is terribly wrong");
            }
        });
        queue.add(stringRequest);
    }

}
