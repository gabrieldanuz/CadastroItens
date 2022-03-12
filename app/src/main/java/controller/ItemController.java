package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import database.DadosOpenHelper;
import database.tabelas;
import model.Item;

public class ItemController {

    private SQLiteDatabase conexao;
    private Context context;

    public ItemController(Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }
    public boolean inserir(Item item) {
        try{

            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", item.nome);


            long result = conexao.insertOrThrow(tabelas.TB_ITENS, null, contentValues);
            if(result > 0){
                return true;
            }else{
                return false;
            }

        }catch (SQLException ex){
            return false;
        }catch (Exception ex){
            return false;
        }
    }



    public boolean alterar (Item item) {

        try {


            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", item.nome);

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(item.id);

            int ret = conexao.update("itens", contentValues, "id = ?", parametros);
            if(ret > 0){
                return true;
            }else{
                return false;
            }

        } catch (Exception ex) {
            Log.e("ERRO", "Loucura mano");
            return false;
        }
    }
    public Item buscar(int id){
        try{

            Item objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(tabelas.TB_ITENS);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Item();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
            }

            return objeto;


        }catch (Exception ex){
            return null;
        }
    }

    public boolean excluir(int id){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);

            conexao.delete(tabelas.TB_ITENS, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            return false;
        }
    }


    public ArrayList<Item> lista(){

        ArrayList<Item> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(tabelas.TB_ITENS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Item objeto;
                do{
                    objeto = new Item();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            return listagem;
        }
    }
}
