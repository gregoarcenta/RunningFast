package facci.arcentales.runningfast.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.SharedPreferencesCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import java.io.IOException;

import facci.arcentales.runningfast.CorrerActivity;
import facci.arcentales.runningfast.R;

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
                try {
                    int gpsSeñal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
                    if (gpsSeñal == 0){
                        showInfoAlert();
                    }else{
                        Intent intent = new Intent(getActivity(), CorrerActivity.class);
                        startActivity(intent);
                    }
                }catch (Settings.SettingNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
    public void showInfoAlert (){
        new AlertDialog.Builder(getContext())
                .setTitle(" Señal GPS ")
                .setMessage("Usted no tiene habilitada la señal GPS ¿Te gustaria activar la señal GPS ahora?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCELAR",null)
                .show();
    }
}
