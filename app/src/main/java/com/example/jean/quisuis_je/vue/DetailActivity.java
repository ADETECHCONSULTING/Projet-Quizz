package com.example.jean.quisuis_je.vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.modele.Quizz;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private Controle controle;
    public DetailActivity(){
        controle = Controle.getInstance(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //creerListe();
    }

    public void creerListe() {
        ArrayList<Quizz> lesBonnesReponses = new ArrayList<Quizz>();
        lesBonnesReponses = Controle.getInstance(this).getLesBonnesReponse();
        Log.d("** les bonnes ", ""+lesBonnesReponses.get(0).getQuestion());
        ListView listView = (ListView) findViewById(R.id.lstViewDetail);
        DetailListAdapter adapter = new DetailListAdapter(DetailActivity.this, lesBonnesReponses);
        listView.setAdapter(adapter);
    }
}
