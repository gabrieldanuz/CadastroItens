package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.gabriel.CadastroItensActivity;
import com.example.gabriel.R;

import java.util.ArrayList;

import model.Item;

public class ItemAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> elementos;

    public ItemAdapter(Context context, ArrayList<Item> elementos){
        super(context, R.layout.item_lista, elementos);
        this.context = context;
        this.elementos = elementos;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            final Item objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista, parent, false);

            TextView lblnome = rowView.findViewById(R.id.lblNome_Item);

            lblnome.setText(String.valueOf(objeto.getNome()));



            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent tela = new Intent(context, CadastroItensActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            return null;
        }

    }
}

