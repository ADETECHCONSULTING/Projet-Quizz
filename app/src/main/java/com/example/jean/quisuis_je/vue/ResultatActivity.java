package com.example.jean.quisuis_je.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.jean.quisuis_je.R;

public class ResultatActivity extends AppCompatActivity {
    private TextView txtScore;
    private Button btnDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        txtScore = (TextView) findViewById(R.id.txtScoreRes);
        btnDetail = (Button) findViewById(R.id.btnDetail);
        txtScore.setText(""+score);

        /*btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(ResultatActivity.this, DetailActivity.class);
                startActivity(intentDetail);
            }
        });*/
    }
}
