
package br.edu.ceub.navcrud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ceub.navcrud.R;
import br.edu.ceub.navcrud.adapter.AdapterContato;
import br.edu.ceub.navcrud.model.Contato;
import br.edu.ceub.navcrud.pesistencia.ContatoDAO;

public class lstContatoActivity extends AppCompatActivity {
private RecyclerView rv;
private AdapterContato contatoAdapter;
    private List<Contato> listaContato = new ArrayList<>();
    private Contato contatoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_contato);
        getSupportActionBar().setTitle("Contato");

        rv = findViewById(R.id.rvListaContatos);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Recuperar contatos para edição
                Contato contatoSelecionado = listaContato.get(position);
                //Envia contato para tela de addContato
                Intent intent = new Intent(lstContatoActivity.this, addContatoActivity.class);
                intent.putExtra("contatoSelecionado", (Serializable) contatoSelecionado);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recupear o contato para ser excluído
                contatoSelecionado = listaContato.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(lstContatoActivity.this);

                //Configurar o título da msg no alertDialog

                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir o contato: " + contatoSelecionado.getNomeContato() + "?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
                        if (contatoDAO.deletar(contatoSelecionado)) {
                            carregarListaTarefas();
                            Toast.makeText(getApplicationContext(), "Sucesso ao excluir o Contato", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao excluir o contato!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                //Exibir dialog
                dialog.create();
                dialog.show();

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        //clique botão

        Button btnNovo = (Button) findViewById(R.id.cmdNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lstContatoActivity.this, addContatoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void carregarListaTarefas() {
        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
        listaContato = contatoDAO.listar();
        //Exibe lista de contatos no Recyclerview

        //Configurar o adpater
        contatoAdapter = new AdapterContato(listaContato);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rv.setAdapter(contatoAdapter);
    }
}
