package com.danielcorrea.doggergrill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PerfilFragment extends Fragment {


    String a,b;


    public PerfilFragment() {
        // Required empty public constructor
    }

    public PerfilFragment(String a, String b) {
        this.a = a;
        this.b = b;
    }

    TextView tName,tmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Set the Text to try this out
        tName=(TextView) myInflatedView.findViewById(R.id.nombre);
        tmail=(TextView) myInflatedView.findViewById(R.id.email);
        tName.setText(a);
        tmail.setText(b);

        return myInflatedView;
        // Inflate the layout for this fragment



    }
}
