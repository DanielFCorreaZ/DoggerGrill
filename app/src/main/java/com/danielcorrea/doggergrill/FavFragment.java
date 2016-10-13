package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class FavFragment extends Fragment {

    Productos datos[]=
            new Productos[]{
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos()
            };
    ListView list;

    public FavFragment() {
        // Required empty public constructor
    }

    public FavFragment(Productos[] datos1) {
        datos = datos1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Adapter adaptador1 = new Adapter(this.getContext(),datos);
        list= (ListView) getView().findViewById(R.id.list1);
        list.setAdapter(adaptador1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case(0):

                        break;
                    case(1):

                        break;
                    case(2):

                        break;
                    case(3):

                        break;
                    case(4):

                        break;

                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_fav, container, false);





        return myInflatedView;
    }


    class Adapter extends ArrayAdapter<Productos> {

        public Adapter(Context context,Productos[] datos) {
            super(context,R.layout.layout_produc,datos);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= LayoutInflater.from(getContext());
            View item= inflater.inflate(R.layout.layout_produc,null);



            TextView nombre=(TextView) item.findViewById(R.id.nompro);
            nombre.setText(datos[position].getNombre());

            TextView descrip=(TextView) item.findViewById(R.id.descpro);
            descrip.setText(datos[position].getDescripcion());

            TextView prec=(TextView) item.findViewById(R.id.prepro);
            prec.setText(datos[position].getPrecio());


            ImageView imagen=(ImageView) item.findViewById(R.id.imapro);
            imagen.setImageResource(datos[position].getIdima());


            return (item);
        }
    }




}
