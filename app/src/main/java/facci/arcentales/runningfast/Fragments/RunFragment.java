package facci.arcentales.runningfast.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.SharedPreferencesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import facci.arcentales.runningfast.CorrerActivity;
import facci.arcentales.runningfast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunFragment extends Fragment {

    Button botonInicioConteo;
    Chronometer tiempo;
    SharedPreferences preferences;

    public RunFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        tiempo = (Chronometer) view.findViewById(R.id.tiempo);
        botonInicioConteo = (Button) view.findViewById(R.id.boton_iniciar_conteo);

        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        tiempo.setText(preferences.getString("tiempo", "00:00"));

        botonInicioConteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CorrerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
