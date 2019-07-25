package facci.arcentales.runningfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    TextView tiempo_result, distancia_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        showToolbar(getResources().getString(R.string.Resultado),true);

        tiempo_result = (TextView)findViewById(R.id.tiempo_result);
        distancia_result = (TextView)findViewById(R.id.distancia_result);

        Bundle b = getIntent().getExtras();
        String tiempo = b.getString("tiempo");
        tiempo_result.setText(tiempo);

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
