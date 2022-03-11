package com.example.gabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtLogin, txtSenha;
    Button btnEntrar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin_usuario);
        txtSenha = findViewById(R.id.txtSenha_usuario);

        context = MainActivity.this;

        btnEntrar = findViewById(R.id.btnLogin_usuario);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String login = txtLogin.getText().toString();
                    String senha = txtSenha.getText().toString();


                    if (login.equals("gabriel") && senha.equals("123")) {
                        Intent it = new Intent(context, MenuActivity.class);
                        startActivity(it);
                        alert("Usuário Logado");
                    } else {
                        alert("Usuário inexistente!");
                    }
                } catch (Exception ex) {
                    alert("Error");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}