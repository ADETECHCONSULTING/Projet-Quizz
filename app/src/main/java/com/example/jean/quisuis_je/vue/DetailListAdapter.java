package com.example.jean.quisuis_je.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.modele.Quizz;

import java.util.ArrayList;

/**
 * Created by JEAN on 29/12/2016.
 */

public class DetailListAdapter extends BaseAdapter {
    private ArrayList<Quizz> lesBonnesReponses;
    private LayoutInflater inflater;
    private Controle controle;

    public DetailListAdapter(Context context, ArrayList<Quizz> lesBonnesReponses){
        controle = Controle.getInstance(context);
        this.lesBonnesReponses = lesBonnesReponses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lesBonnesReponses.size();
    }

    @Override
    public Object getItem(int i) {
        return lesBonnesReponses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        public TextView txtQuestion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new DetailListAdapter.ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_list_histo)
            convertView = inflater.inflate(R.layout.layout_liste_detail, null) ;
            // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.txtQuestion = (TextView) convertView.findViewById(R.id.txtQuestionDetail);
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
            convertView.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
            holder = (DetailListAdapter.ViewHolder)convertView.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.txtQuestion.setText(lesBonnesReponses.get(position).getQuestion());
        return convertView ;
    }
}
