package com.example.jean.quisuis_je.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.modele.Theme;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity {
    private Button btnAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        btnAccueil = (Button) findViewById(R.id.btnAccueil);
        btnAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onStart(){
        super.onStart();
        creerListe();
    }

    public void creerListe(){
        ArrayList<Theme> lesThemes = new ArrayList<Theme>();
        lesThemes = Controle.getInstance(this).getLesThemes();
        ListView listView = (ListView) findViewById(R.id.lstView);
        ThemeListAdapter adapter = new ThemeListAdapter(ThemeActivity.this, lesThemes);
        listView.setAdapter(adapter);
    }
}
