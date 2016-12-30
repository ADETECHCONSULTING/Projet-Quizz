package com.example.jean.quisuis_je.vue;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.modele.Quizz;

import java.util.ArrayList;
import java.util.Collections;

public class QuizzActivity extends AppCompatActivity {
    private Controle controle;
    private String TAG = "test";
    private Integer nbIndice = 2;
    private TextView txtQuestion;
    private TextView txtIndice;
    private RadioGroup rdGroupe;
    private RadioButton option0;
    private RadioButton option1;
    private RadioButton option2;
    private Button btnIndice;
    private ImageView btnAccueil;
    private RadioButton option3;
    private Chronometer chrono;
    private ArrayList<Quizz> lesBonnesReponses;
    private TextView txtScore;
    private int score = 0;
    private Button btnSuivant;
    private Button btnPrecedent;
    private int questionActuel = 0;
    private int countQuizz;
    private ArrayList<Quizz> lesQuestions;

    /**
     * Constructeur de la classe QuizzActivity
     */
    public QuizzActivity() {
        lesBonnesReponses = new ArrayList<>();
        controle = Controle.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        lesQuestions = new ArrayList<Quizz>();
        lesQuestions = controle.getLesQuestions();
        Collections.shuffle(lesQuestions);
        countQuizz = lesQuestions.size();
        txtQuestion = (TextView) findViewById(R.id.txtQuestionDetail);
        rdGroupe = (RadioGroup) findViewById(R.id.radioGroup);
        option0 = (RadioButton) findViewById(R.id.radio0);
        chrono = (Chronometer) findViewById(R.id.chronoQuizz);
        option1 = (RadioButton) findViewById(R.id.radio1);
        option2 = (RadioButton) findViewById(R.id.radio2);
        option3 = (RadioButton) findViewById(R.id.radio3);
        btnAccueil = (ImageView) findViewById(R.id.btnAccueil);
        btnSuivant = (Button) findViewById(R.id.btnSuivant);
        btnPrecedent = (Button) findViewById(R.id.btnPrecedent);
        btnIndice = (Button) findViewById(R.id.btnIndice);
        Log.d(TAG, "*** "+countQuizz);
        txtQuestion.setText(lesQuestions.get(questionActuel).getQuestion());
        String[] choixReponses = lesQuestions.get(questionActuel).getChoix().split(",");
        option0.setText(choixReponses[0]);
        option1.setText(choixReponses[1]);
        option2.setText(choixReponses[2]);
        option3.setText(choixReponses[3]);


        /**
         * Evenement du bouton suivant
         */
        btnSuivant.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              int radioChoisie = rdGroupe.getCheckedRadioButtonId();
                                              int choixUtilisateur = getReponseChoisie(radioChoisie);
                                              int reponse = lesQuestions.get(questionActuel).getReponse();
                                              if (reponse == choixUtilisateur) {
                                                  btnIndice.setText("Un indice ?");
                                                  btnIndice.setClickable(true);
                                                  score += 20;
                                                  //lesBonnesReponses.add(lesQuestions.get(questionActuel));
                                                  if (questionActuel == countQuizz) {
                                                      Intent intentRes = new Intent(QuizzActivity.this, ResultatActivity.class);
                                                      intentRes.putExtra("score", score);
                                                      controle.setLesBonnesReponse(lesBonnesReponses);
                                                      startActivity(intentRes);
                                                  } else {
                                                      questionActuel++;
                                                      txtQuestion.setText(lesQuestions.get(questionActuel).getQuestion());
                                                      String[] choixReponses = lesQuestions.get(questionActuel).getChoix().split(",");
                                                      decocher();
                                                      option0.setText(choixReponses[0]);
                                                      option1.setText(choixReponses[1]);
                                                      option2.setText(choixReponses[2]);
                                                      option3.setText(choixReponses[3]);
                                                  }
                                              } else {
                                                  questionActuel++;
                                                  score += -10;
                                                  if (questionActuel == countQuizz) {
                                                      Intent intentRes = new Intent(QuizzActivity.this, ResultatActivity.class);
                                                      intentRes.putExtra("score", score);
                                                      startActivity(intentRes);
                                                  } else {
                                                      txtQuestion.setText(lesQuestions.get(questionActuel).getQuestion());
                                                      String[] choixReponses = lesQuestions.get(questionActuel).getChoix().split(",");
                                                      decocher();
                                                      option0.setText(choixReponses[0]);
                                                      option1.setText(choixReponses[1]);
                                                      option2.setText(choixReponses[2]);
                                                      option3.setText(choixReponses[3]);
                                                  }
                                              }
                                          }
        });

        btnPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionActuel == 0){
                    Toast.makeText(QuizzActivity.this, "Vous venez de commencer le quizz, il n'y a aucune question précedente", Toast.LENGTH_SHORT).show();
                }else{
                    //création d'un AlertDialog
                    final AlertDialog.Builder builder = new AlertDialog.Builder(QuizzActivity.this);
                    builder.setMessage("Attention cette action est irréversible, vous perdrez 10 points");
                    builder.setTitle("Question précédente ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            btnIndice.setText("Un indice ?");
                            btnIndice.setClickable(true);
                            questionActuel--;
                            score += -10;
                            txtScore.setText("Score : "+score);
                            txtQuestion.setText(lesQuestions.get(questionActuel).getQuestion());
                            String[] choixReponses = lesQuestions.get(questionActuel).getChoix().split(",");
                            decocher();
                            option0.setText(choixReponses[0]);
                            option1.setText(choixReponses[1]);
                            option2.setText(choixReponses[2]);
                            option3.setText(choixReponses[3]);
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        btnIndice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nbIndice == 0){
                    Toast.makeText(QuizzActivity.this, "Vous n'avez plus de jetons Indice",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    nbIndice--;
                    btnIndice.setText(lesQuestions.get(questionActuel).getIndice()+" ( "+nbIndice+" indice(s) restant(s) )");
                    btnIndice.setClickable(false);
                }

            }
        });

        btnAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizzActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Methode qui decoche tous les boutons radios
     */
    private void decocher() {
        option0.setChecked(false);
        option1.setChecked(false);
        option2.setChecked(false);
        option3.setChecked(false);
    }

    /**
     * Methode qui traduit le bouton selectionner en integer de 1 à 4
     * @param radioChoisie
     * @return
     */
    private int getReponseChoisie(int radioChoisie){

        int réponse = 0;

        if(radioChoisie == R.id.radio0){

            réponse = 1;

        }

        if(radioChoisie == R.id.radio1){

            réponse = 2;

        }

        if(radioChoisie == R.id.radio2){

            réponse = 3;

        }

        if(radioChoisie == R.id.radio3){

            réponse = 4;

        }

        return réponse;

    }

    public class MonTimer{

    }
}



