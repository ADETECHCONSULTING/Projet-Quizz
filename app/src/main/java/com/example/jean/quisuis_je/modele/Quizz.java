package com.example.jean.quisuis_je.modele;

/**
 * Created by JEAN on 20/12/2016.
 */

public class Quizz {

    private int id;
    private String question;
    private String choix;
    private int reponse;
    private String indice;

    /**
     * Constructeur de la classe Quizz qui va modeler les données pour la classe QuizzActivity
     * @param id
     * @param question
     * @param choix
     * @param reponse
     */
    public Quizz(int id, String question, String choix, int reponse, String indice){
        this.id = id;
        this.question = question;
        this.choix = choix;
        this.reponse = reponse;
        this.indice = indice;
    }

    /**
     * Getter de l'id
     * @return id
     */
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }
}
