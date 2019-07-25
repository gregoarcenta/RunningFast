package facci.arcentales.runningfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import facci.arcentales.runningfast.SQL.sqliteopenhelper;

public class ResultadosActivity extends AppCompatActivity {

    TextView tiempo_result, distancia_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        showToolbar(getResources().getString(R.string.Resultado),true);
        sqliteopenhelper helper = new sqliteopenhelper(getApplicationContext());

        tiempo_result = (TextView)findViewById(R.id.tiempo_result);
        distancia_result = (TextView)findViewById(R.id.distancia_result);

        Bundle b = getIntent().getExtras();
        String tiempo = b.getString("tiempo");
        String distancia = b.getString("dis");
        tiempo_result.setText(tiempo);
        distancia_result.setText(distancia);

        helper.agregarTD(tiempo,distancia);
    }

    public void showToolbar(String tittle, boolean button){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(button);
    }
    public void onBackPressed() {
        Intent i = new Intent(ResultadosActivity.this, ContainerActivity.class);
        startActivity(i);
    }
}
