package me.jackbiggin.urbanhacks.urbanhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Landing extends AppCompatActivity {

    private Button button;
    private CheckBox Arena;
    private boolean isArena = false;

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
        Arena = (CheckBox) findViewById(R.id.Arenas);
        if (Arena.isChecked())
        {
            isArena = true;
        }
        Intent arena = new Intent(this, MapsActivity.class);
        arena.putExtra("isArena", true);
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
