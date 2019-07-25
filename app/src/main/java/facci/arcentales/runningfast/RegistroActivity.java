package facci.arcentales.runningfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity {

    EditText nombre,apellido,usuario,contraseña,conf_contraseña,email;
    RadioButton masc, fem;
    Button guardar;
    TextInputLayout nombrein,apellidoin,usuarioin,emailin,contraseñain,conf_contraseñain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        showToolbar(getResources().getString(R.string.toolbar_registro),true);

        nombre = (EditText) findViewById(R.id.editText_nombre_r);
        nombrein = (TextInputLayout) findViewById(R.id.input_nombre);
        apellido = (EditText) findViewById(R.id.editText_apellidos_r);
        apellidoin = (TextInputLayout) findViewById(R.id.input_apellido);
        usuario = (EditText) findViewById(R.id.editText_usuario_r);
        usuarioin = (TextInputLayout) findViewById(R.id.input_usuario);
        email = (EditText) findViewById(R.id.editText_email);
        emailin = (TextInputLayout) findViewById(R.id.input_email);
        contraseña = (EditText) findViewById(R.id.editText_contraseña_r);
        contraseñain = (TextInputLayout) findViewById(R.id.input_contraseña);
        conf_contraseña = (EditText) findViewById(R.id.editText_contraseña_conf);
        conf_contraseñain = (TextInputLayout) findViewById(R.id.input_comtraseña_conf);
        masc = (RadioButton) findViewById(R.id.radio_masc);
        fem = (RadioButton) findViewById(R.id.radio_fem);
    }
    public void showToolbar(String tittle, boolean button){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(button);
    }
    public boolean validar() {
        boolean retorno;
        String nombre_reg = nombre.getText().toString();
        String apellido_reg = apellido.getText().toString();
        String usuario_reg = usuario.getText().toString();
        String email_reg = email.getText().toString();
        String contraseña_reg = contraseña.getText().toString();
        String contraseña_conf = conf_contraseña.getText().toString();
        if (nombre_reg.isEmpty()) {
            nombrein.setError("Ingrese un nombre");
            return false;
        }
        if (apellido_reg.isEmpty()) {
            apellidoin.setError("Ingrese sus apellidos");
            return false;
        }
        if (usuario_reg.isEmpty()) {
            usuarioin.setError("Ingrese un nombre de usuario");
            return false;
        }
        if (email_reg.isEmpty()) {
            emailin.setError("Ingrese un email");
            return false;
        }else if (Patterns.EMAIL_ADDRESS.matcher(email_reg).matches()==false){
            emailin.setError("Email invalido");
            return false;
        }
        if (contraseña_reg.isEmpty()) {
            contraseñain.setError("Ingrese una contraseña");
            return false;
        }
        if (contraseña_conf.isEmpty()) {
            conf_contraseñain.setError("Confirme su contraseña");
            return false;
        }
        if (masc.isChecked()==false){
            masc.setError("");
            if (fem.isChecked()==false){
                fem.setError("");
                return false;
            }
        }
        return true;
    }
    public void iniciarSesionRegistro (View view){
        if (validar()==true) {
            Intent i = new Intent(this, ContainerActivity.class);
            startActivity(i);
        }
    }
}
