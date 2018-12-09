package me.jackbiggin.urbanhacks.urbanhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Landing extends AppCompatActivity {

    private Button button;
    private CheckBox Arena, Beaches, Campgrounds, Soccer, Baseball, Tennis, Basketball, Rec;

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
        Beaches = (CheckBox) findViewById(R.id.Beaches);
        Campgrounds = (CheckBox) findViewById(R.id.Campgrounds);
        Soccer = (CheckBox) findViewById(R.id.Soccer);
        Baseball = (CheckBox) findViewById(R.id.Baseball);
        Tennis = (CheckBox) findViewById(R.id.Tennis);
        Basketball = (CheckBox) findViewById(R.id.Basketball);
        Rec = (CheckBox) findViewById(R.id.Rec);

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

}
