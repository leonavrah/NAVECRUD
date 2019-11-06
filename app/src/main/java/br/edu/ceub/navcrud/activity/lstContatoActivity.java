
package br.edu.ceub.navcrud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.edu.ceub.navcrud.R;

public class lstContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_contato);
        getSupportActionBar().setTitle("Contato");
    }
}
