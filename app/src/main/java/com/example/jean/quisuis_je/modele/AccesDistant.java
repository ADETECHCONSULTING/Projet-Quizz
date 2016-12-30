package com.example.jean.quisuis_je.modele;

import android.util.Log;

import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.outils.AccesHTTP;
import com.example.jean.quisuis_je.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JEAN on 21/12/2016.
 */

public class AccesDistant implements AsyncResponse {
    //String SERVERADDR = "http://192.168.1.39/Quizz/index.php";
    String SERVERADDR = "http://172.20.10.4/Quizz/index.php";
    private String[] tabMessage = {"tous", "theme", "sport", "geographie","jeux"}; //0 tous - 1 theme - 2 sport - 3 geo - 4 jeux
    private Controle controle;

    public AccesDistant(){
        super();
        controle = Controle.getInstance(null);
    }

    @Override
    public void processFinish(String output) {
        Log.d("***", "**** AccesDistant " + output);
        //Le message prendra la valeur du output splité par % (dans le php j'envoie un prefixe(tous%,theme%...) suivie de % avec le json)
        //Je split le message en 2 partie (message[0] = tous ou theme ou sport... et message[1] = contenu du json
        String[] message = output.split("%");
        Log.d("****", "Message[0] = "+message[0].length()+" "+message[0]);
        Log.d("****", "tabMessage[0] = "+tabMessage[0].length()+" "+tabMessage[0]);
        Log.d("****", "Message[0] == TabMessage[0] ? "+message[0].substring(2).equals(tabMessage[0].trim()));
        if(message.length > 1){
            //Si message[0](tous) == tabMessage[0](tous)
            if(message[0].substring(2).equals(tabMessage[0])){
                try {
                    ArrayList<Quizz> lesQuestions = new ArrayList<Quizz>();
                    Log.d("******", "Message : " + message[0]);
                    JSONArray info = new JSONArray(message[1]);
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject quizzJson = info.getJSONObject(i);
                        lesQuestions.add(new Quizz(quizzJson.getInt("id"), quizzJson.getString("question"), quizzJson.getString("choix"), quizzJson.getInt("reponse"),quizzJson.getString("indice")));
                    }
                    Log.d("Creation des questions", "****" + lesQuestions);
                    controle.setLesQuestions(lesQuestions);
                } catch (Exception e) {
                    Log.d("Erreur", "***" + e);
                }

            }
            //Si message[0](theme) == tabMessage[1](theme)
            else if (message[0].substring(2).equals(tabMessage[1])){
                try {
                    ArrayList<Theme> lesThemes = new ArrayList<Theme>();
                    Log.d("***Theme", "Message theme "+message[1]);
                    JSONArray info = new JSONArray(message[1]);
                    for(int i =0; i < info.length(); i++){
                        JSONObject themeJson = info.getJSONObject(i);
                        lesThemes.add(new Theme(themeJson.getInt("idTheme"),themeJson.getString("libelle")));
                    }
                    controle.setLesThemes(lesThemes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //Si message[0](sport) == tabMessage[2](sport)
            else if(message[0].substring(2).equalsIgnoreCase(tabMessage[2])){
                try {
                    ArrayList<Quizz> lesQuestions = new ArrayList<Quizz>();
                    Log.d("******", "Message Sport : " + message[0]);
                    JSONArray info = new JSONArray(message[1]);
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject quizzJson = info.getJSONObject(i);
                        lesQuestions.add(new Quizz(quizzJson.getInt("id"), quizzJson.getString("question"), quizzJson.getString("choix"), quizzJson.getInt("reponse"),quizzJson.getString("indice")));
                    }
                    Log.d("Creation des questions", "****" + lesQuestions);
                    controle.setLesQuestions(lesQuestions);
                } catch (Exception e) {
                    Log.d("Erreur", "***" + e);
                }
            }
            //Si message[0](geo) == tabMessage[3](geographie)
            else if(message[0].substring(2).equalsIgnoreCase(tabMessage[3])){
                try {
                    ArrayList<Quizz> lesQuestions = new ArrayList<Quizz>();
                    Log.d("******", "Message Sport : " + message[0]);
                    JSONArray info = new JSONArray(message[1]);
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject quizzJson = info.getJSONObject(i);
                        lesQuestions.add(new Quizz(quizzJson.getInt("id"), quizzJson.getString("question"), quizzJson.getString("choix"), quizzJson.getInt("reponse"),quizzJson.getString("indice")));
                    }
                    Log.d("Creation des questions", "****" + lesQuestions);
                    controle.setLesQuestions(lesQuestions);
                } catch (Exception e) {
                    Log.d("Erreur", "***" + e);
                }
            }
            //Si message[0](jeux) == tabMessage[4](jeux)
            else if(message[0].substring(2).equalsIgnoreCase(tabMessage[4])){
                try {
                    ArrayList<Quizz> lesQuestions = new ArrayList<Quizz>();
                    Log.d("******", "Message Sport : " + message[0]);
                    JSONArray info = new JSONArray(message[1]);
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject quizzJson = info.getJSONObject(i);
                        lesQuestions.add(new Quizz(quizzJson.getInt("id"), quizzJson.getString("question"), quizzJson.getString("choix"), quizzJson.getInt("reponse"),quizzJson.getString("indice")));
                    }
                    Log.d("Creation des questions", "****" + lesQuestions);
                    controle.setLesQuestions(lesQuestions);
                } catch (Exception e) {
                    Log.d("Erreur", "***" + e);
                }
            }
        }



    }

    /**
     * Methode qui demande les requetes au fichier php
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesDonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
