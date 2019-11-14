
package br.edu.ceub.navcrud.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ceub.navcrud.R;
import br.edu.ceub.navcrud.adapter.AdapterContato;

public class lstContatoActivity extends AppCompatActivity {
private RecyclerView rv;
private AdapterContato contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_contato);
        getSupportActionBar().setTitle("Contato");

    }
}
