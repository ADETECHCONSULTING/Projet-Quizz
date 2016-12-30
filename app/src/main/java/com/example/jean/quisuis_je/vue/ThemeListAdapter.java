package com.example.jean.quisuis_je.vue;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.jean.quisuis_je.R;
import com.example.jean.quisuis_je.controleur.Controle;
import com.example.jean.quisuis_je.modele.Theme;

import java.util.ArrayList;

/**
 * Created by JEAN on 28/12/2016.
 */

public class ThemeListAdapter extends BaseAdapter {
    private ArrayList<Theme> lesThemes;
    private LayoutInflater inflater;
    private Context monContexte;
    private Controle controle;
    public ThemeListAdapter(Context context, ArrayList<Theme> lesThemes){
        monContexte = context;
        controle = Controle.getInstance(context);
        this.lesThemes = lesThemes;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lesThemes.size();
    }

    @Override
    public Object getItem(int i) {
        return lesThemes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        public Button btnTheme;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder est un objet de la petite classe
        ViewHolder holder ;
        // si la ligne n'existe pas encore
        if (convertView == null) {
            holder = new ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_list_histo)
            convertView = inflater.inflate(R.layout.layout_liste_theme, null) ;
            // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.btnTheme = (Button) convertView.findViewById(R.id.btnTheme);
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
            convertView.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
            holder = (ViewHolder)convertView.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.btnTheme.setText(lesThemes.get(position).getLibelle());
        holder.btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("*** libelle sport ? ", " "+lesThemes.get(position).getLibelle());
                controle.envoiAccesDistant(lesThemes.get(position).getLibelle());
                monContexte.startActivity(new Intent(monContexte, QuizzActivity.class));
            }
        });
        return convertView ;
    }

}
