package com.example.gabriel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    CardView cardViewCadastrar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardViewCadastrar = findViewById(R.id.cardViewCadastrarItem);

        context = MenuActivity.this;

        cardViewCadastrar.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View view) {

                                                         Intent it = new Intent(context, ListaItensActivity.class);
                                                         startActivity(it);

                                                     }

                                                 }
        );

    }
}