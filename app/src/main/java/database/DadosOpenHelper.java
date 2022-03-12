package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Globais.Globais;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NM_BANCO = "banco";
    private Context context;


    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            StringBuilder sqlItens = new StringBuilder();
            sqlItens.append(" CREATE TABLE IF NOT EXISTS  ");
            sqlItens.append(tabelas.TB_ITENS);
            sqlItens.append("(");
            sqlItens.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlItens.append(" nome VARCHAR (30) NOT NULL ");
            sqlItens.append(")");
            db.execSQL(sqlItens.toString());

            /*StringBuilder sqlAddBebidas = new StringBuilder();
            sqlAddBebidas.append("INSERT INTO");
            sqlAddBebidas.append(Tabelas.TB_BEBIDAS);
            sqlAddBebidas.append(" VALUES (DEFAULT)");
            db.execSQL(sqlAddBebidas.toString());*/

        } catch (Exception ex) {
            Globais.exibirMensagem(context, "Error", "Erro");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

