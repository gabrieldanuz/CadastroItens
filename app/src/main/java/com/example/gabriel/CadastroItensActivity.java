package com.example.gabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controller.ItemController;
import Globais.Globais;
import model.Item;

public class CadastroItensActivity extends AppCompatActivity {

    Context context;
    EditText edtNome;
    private ItemController itemController;
    Button btnExcluir;
    private int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_itens);

        context = CadastroItensActivity.this;
        edtNome = findViewById(R.id.edtNomeItem);
        btnExcluir = findViewById(R.id.btnExcluir);

        codigo = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("id")) {

            codigo = bundle.getInt("id");
            itemController = new ItemController(context);
            Item objeto = itemController.buscar(codigo);
            preencheCampos(objeto);

        } else {
            btnExcluir.setVisibility(View.INVISIBLE);
        }

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemController = new ItemController(context);
                boolean retorno = itemController.excluir(codigo);
                if (retorno) {
                    Globais.exibirMensagem(context, "Excluir!", "Item excluido!");
                    finish();

                }


            }
        });


    }

    public boolean validaDados() {

        boolean retorno = true;
        String nome = edtNome.getText().toString();

        if (Globais.isCampoVazio(nome)) {
            edtNome.requestFocus();
            retorno = false;
        }


        if (!retorno) {
            Globais.exibirMensagem(context, "Aviso", "Há campos inválidos ou em branco");
            return retorno;
        }

        return retorno;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_caditens, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_salvar:

                if (validaDados()) {

                    Item objeto = new Item();
                    objeto.setNome(edtNome.getText().toString());


                    ItemController controller = new ItemController(context);

                    boolean ret;

                    if (codigo == 0) {
                        ret = controller.inserir(objeto);

                    } else {
                        objeto.setId(codigo);
                        ret = controller.alterar(objeto);

                    }

                } else {

                    break;
                }

            case R.id.action_cancelar:
                finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void preencheCampos(Item objeto) {
        try {

            edtNome.setText(String.valueOf(objeto.getNome()));

        } catch (Exception ex) {

            Globais.exibirMensagem(context, "ERRO", "FALHOU");
        }
    }
}