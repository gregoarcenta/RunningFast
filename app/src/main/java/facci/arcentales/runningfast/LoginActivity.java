package facci.arcentales.runningfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contraseña;
    private TextInputLayout usuarioin, contraseñain;
    private Button botonIniciar;

    boolean usu=false;
    boolean pass=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.editText_usuario);
        usuarioin = (TextInputLayout) findViewById(R.id.input_login_usuario);
        contraseña = (EditText) findViewById(R.id.editText_contraseña);
        contraseñain = (TextInputLayout) findViewById(R.id.input_login_contraseña);
        botonIniciar = (Button) findViewById(R.id.boton_iniciar);
    }
    public void iniciarSesion (View view){
        String usuario_login = usuario.getText().toString();
        if (usuario_login.isEmpty()) {
            usuarioin.setError("Ingrese un nombre de usuario");
            usu = false;
        } else {
            usu = true;
            usuarioin.setError(null);
        }
        String contrasena_login = contraseña.getText().toString();
        if (contrasena_login.isEmpty()) {
            contraseñain.setError("Ingrese un nombre de usuario");
            pass = false;
        } else {
            pass = true;
            contraseñain.setError(null);
        }
        if (pass==true&&usu==true){
            if (usuario_login.equals("movil") && contrasena_login.equals("facci")) {
                Intent i = new Intent(this, ContainerActivity.class);
                startActivity(i);
            }else {
                usuarioin.setError("");
                contraseñain.setError("");
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void registrarse(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

}
