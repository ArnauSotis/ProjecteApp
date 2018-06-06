package com.example.david.myapplication.Juego;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_objetos, parent, false);
        }
        //u.getImage()
        Picasso.with(super.getContext()).load("http://api.dsamola.tk/imagen.jpeg").into((ImageView) convertView.findViewById(R.id.avatar_image));
        TextView et = (TextView)convertView.findViewById(R.id.author_txt);
        TextView et2 = (TextView)convertView.findViewById(R.id.title_txt);

        et.setText(u.getNombre()); //cojo los nombre del Objeto
        //int x = u.getTitle();
        //et2.setText(Integer.toString(x));
        et2.setText(u.getCoste());
        return convertView;
    }
}
