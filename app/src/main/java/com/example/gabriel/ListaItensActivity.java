package com.example.gabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.ItemAdapter;
import controller.ItemController;
import model.Item;

public class ListaItensActivity extends AppCompatActivity {

    ListView rcvLista;
    FloatingActionButton btnAdd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        context = ListaItensActivity.this;
        rcvLista = findViewById(R.id.ltvItens_listaItens);
        btnAdd = findViewById(R.id.btnAddItem_listaItens);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(context, CadastroItensActivity.class);
                startActivity(it);

            }

        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){

        try{

            //buscar todos os usuarios e preencher em um List
            ItemController controller = new ItemController(context);
            ArrayList<Item> lista = controller.lista();
            if(lista != null){


                ArrayAdapter adapter = new ItemAdapter(context, lista);

                rcvLista.setAdapter(adapter);

            }

        }catch (Exception ex){


        }
    }
}