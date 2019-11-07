package br.edu.ceub.navcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ceub.navcrud.R;
import br.edu.ceub.navcrud.model.Contato;

public class AdapterContato extends RecyclerView.Adapter<AdapterContato.MyViewHolder> {
private List<Contato> ListaContato;

private Context mContext;

    public AdapterContato(List<Contato> listaContato) {
        this.ListaContato = listaContato;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_contato, parent, false );
              return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Contato contato = ListaContato.get(position);
        holder.nomeContato.setText(contato.getNomeContato());
        holder.telContato.setText(contato.getTelContato());
    }

    @Override
    public int getItemCount() {
        return ListaContato.size(); // retornar o tamanho da lista
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeContato;
        TextView telContato;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeContato = itemView.findViewById(R.id.textViewNome);
            telContato = itemView.findViewById(R.id.textViewTelefone);
        }
    }

}
