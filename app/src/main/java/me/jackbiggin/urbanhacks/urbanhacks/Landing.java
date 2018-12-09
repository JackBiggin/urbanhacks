package me.jackbiggin.urbanhacks.urbanhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Landing extends AppCompatActivity {

    private Button button;
    private CheckBox Arena;

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
    }

    public void openactivity(){
        Intent intent = new Intent(this,MapsActivity.class);


        Arena = (CheckBox) findViewById(R.id.Arenas);

        intent.putExtra("TEST", Arena.isChecked());
        startActivity(intent);
    }

}
