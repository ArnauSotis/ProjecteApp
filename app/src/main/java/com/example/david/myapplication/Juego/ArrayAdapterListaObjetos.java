package com.example.david.myapplication.Juego;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArrayAdapterListaObjetos extends ArrayAdapter<Objeto> {
    public ArrayAdapterListaObjetos(@NonNull Context context, List<Objeto> resource) {
        super(context, 0,resource);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Objeto u = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.objeto_datos_basico, parent, false);
        }
        //u.getImage()
        if(u.getId()==1) Picasso.with(super.getContext()).load(R.drawable.espada).into((ImageView) convertView.findViewById(R.id.avatar_image));
        if(u.getId()==2) Picasso.with(super.getContext()).load(R.drawable.escudo).into((ImageView) convertView.findViewById(R.id.avatar_image));
        if(u.getId()==3) Picasso.with(super.getContext()).load(R.drawable.armadura).into((ImageView) convertView.findViewById(R.id.avatar_image));
        if(u.getId()==4) Picasso.with(super.getContext()).load(R.drawable.pocion).into((ImageView) convertView.findViewById(R.id.avatar_image));
        //casco
        if(u.getId()==5)Picasso.with(super.getContext()).load(R.drawable.casco).into((ImageView) convertView.findViewById(R.id.avatar_image));
        //espada de fuego
        if(u.getId()==6)Picasso.with(super.getContext()).load(R.drawable.espada_fuego).into((ImageView) convertView.findViewById(R.id.avatar_image));
        //escudo del dragon
        if(u.getId()==7)Picasso.with(super.getContext()).load(R.drawable.escudo_dragon).into((ImageView) convertView.findViewById(R.id.avatar_image));
        //llaves
        if (u.getId() == 8) Picasso.with(super.getContext()).load(R.drawable.llave_exposicion).into((ImageView) convertView.findViewById(R.id.avatar_image));
        if (u.getId() == 9) Picasso.with(super.getContext()).load(R.drawable.llave_exposicion).into((ImageView) convertView.findViewById(R.id.avatar_image));

        TextView et = (TextView)convertView.findViewById(R.id.nombre_txt);
        TextView et2 = (TextView)convertView.findViewById(R.id.tipo_txt);

        et.setText(u.getNombre()); //cojo los nombre del Objeto
        //int x = u.getTitle();
        //et2.setText(Integer.toString(x));
        et2.setText(u.getTipo());
        return convertView;
    }
}
