package facci.arcentales.runningfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CorrerActivity extends AppCompatActivity {

    int i=10;
    Chronometer chronometer;
    ImageView botonPause,botonStop,botonStart;
    boolean correr=false;
    long detenerse;
    TextView contador, comenzar;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correr);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        chronometer = (Chronometer)findViewById(R.id.conteo_tiempo);
        botonPause = (ImageView)findViewById(R.id.pausa);
        botonStop = (ImageView)findViewById(R.id.stop);
        botonStart = (ImageView)findViewById(R.id.start);
        contador = (TextView)findViewById(R.id.contador);
        comenzar = (TextView)findViewById(R.id.comenzar);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_map);

       /* floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (CorrerActivity.this, MapActivity.class);
                startActivity(i);
            }
        });*/

        preferences=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        progressBar.setProgress(0);
        cuenta();

    }
    public void cuenta (){
        new CountDownTimer(11000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(i*100/(10000/1000));
                i--;
                contador.setText((millisUntilFinished/1000) + "");
                contador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancel();
                        onFinish();
                    }
                });
            }

            @Override
            public void onFinish() {
                comenzar.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                contador.setVisibility(View.INVISIBLE);
                botonStop.setVisibility(View.VISIBLE);
                conteo();
            }
        }.start();
    }
    public void conteo(){
        chronometer.setBase(SystemClock.elapsedRealtime()-detenerse);
        chronometer.start();
        correr = true;

       /* chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-chronometer.getBase()>=50000){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
            }
        });*/
        botonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!correr) {
                    chronometer.setBase(SystemClock.elapsedRealtime()-detenerse);
                    chronometer.start();
                    correr = true;
                    botonStart.setVisibility(View.INVISIBLE);
                    botonPause.setVisibility(View.VISIBLE);
                }
            }
        });
        botonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correr) {
                    chronometer.stop();
                    detenerse=SystemClock.elapsedRealtime()-chronometer.getBase();
                    correr = false;
                    botonStart.setVisibility(View.VISIBLE);
                    botonPause.setVisibility(View.INVISIBLE);
                }
            }
        });
        botonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!correr) {
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("tiempo", chronometer.getText().toString());
                    editor.commit();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    detenerse = 0;
                    correr = false;
                }
            }
        });
    }
}
