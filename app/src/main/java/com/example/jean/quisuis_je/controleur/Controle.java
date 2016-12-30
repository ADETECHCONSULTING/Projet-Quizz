package com.example.jean.quisuis_je.controleur;

import android.content.Context;
import android.util.Log;

import com.example.jean.quisuis_je.modele.AccesDistant;
import com.example.jean.quisuis_je.modele.Quizz;
import com.example.jean.quisuis_je.modele.Theme;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by JEAN on 22/12/2016.
 */

public final class Controle {
    private static Controle instance = null;
    private ArrayList<Quizz> lesQuestions;
    private ArrayList<Theme> lesThemes;
    private ArrayList<Quizz> lesBonnesReponse;
    private ArrayList<Quizz> lesMauvaisesReponses;
    private static Context context;
    private static AccesDistant accesDistant;

    public Controle(){
        super();
        lesQuestions = new ArrayList<Quizz>();
    }


    public final static Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            accesDistant = new AccesDistant();
            Controle.context = context;
        }

        return Controle.instance;
    }

    /**
     * Getter de l'arrayList lesQuestions
     * @return ArrayList
     */
    public ArrayList<Quizz> getLesQuestions(){
        return this.lesQuestions;
    }

    /**
     * Getter de l'arrayList lesThemes
     * @return
     */
    public ArrayList<Theme> getLesThemes(){
        return this.lesThemes;
    }


    /**
     * Setter de l'arrayList lesQuestions
     * @param lesQuestions
     */
    public void setLesQuestions(ArrayList<Quizz> lesQuestions){
        this.lesQuestions = lesQuestions;
        Log.d("****", "TAG tous"+this.lesQuestions);
    }

    public void setLesThemes(ArrayList<Theme> lesThemes){
        this.lesThemes = lesThemes;
        Log.d("*****", "TAG theme"+this.lesThemes);
    }

    public void envoiAccesDistant(String name){
        accesDistant.envoi(name, new JSONArray());
    }

    public ArrayList<Quizz> getLesBonnesReponse() {
        return lesBonnesReponse;
    }

    public void setLesBonnesReponse(ArrayList<Quizz> lesBonnesReponse) {
        this.lesBonnesReponse = lesBonnesReponse;
    }

    public ArrayList<Quizz> getLesMauvaisesReponses() {
        return lesMauvaisesReponses;
    }

    public void setLesMauvaisesReponses(ArrayList<Quizz> lesMauvaisesReponses) {
        this.lesMauvaisesReponses = lesMauvaisesReponses;
    }
}
