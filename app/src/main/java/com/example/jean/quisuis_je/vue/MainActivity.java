package com.example.jean.quisuis_je.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;

public class MainActivity extends AppCompatActivity {
    private Button btnStartCGE;
    private Button btnStartTheme;
    private Controle controle;

    public MainActivity(){
        controle = Controle.getInstance(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartCGE = (Button) findViewById(R.id.bntStartCGE);
        btnStartTheme = (Button) findViewById(R.id.btnStartTheme);

        btnStartCGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controle.envoiAccesDistant("tous");
                Intent redirection = new Intent(MainActivity.this, QuizzActivity.class);
                startActivity(redirection);
            }
        });

        btnStartTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controle.envoiAccesDistant("theme");
                Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                startActivity(intent);
            }
        });
    }

    protected boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    protected boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mainMenu:
                Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
