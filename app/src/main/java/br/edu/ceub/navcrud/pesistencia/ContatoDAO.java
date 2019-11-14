package br.edu.ceub.navcrud.pesistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.ceub.navcrud.model.Contato;

public class ContatoDAO implements IcontatoDAO {

    private final String TABLE_EMISSORES = "Contato";
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ContatoDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato", contato.getTelContato());
        try {
            escreve.insert(DbHelper.TABELA_CONTATO, null, cv);
            Log.i("INFOB", "Sucesso ao salvar registro!");

        } catch (Exception e) {
            Log.e("INFODB", "Erro ao salvar registro!" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato" , contato.getTelContato());
        try {
            String[] args = {contato.getId().toString()};
            escreve.update(DbHelper.TABELA_CONTATO, cv, "id=?", args);
            Log.i("INFODB", "Sucesso ao atualizar o registro!");

            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Contato contato) {
        try {
            String[] args = {contato.getId().toString()};
            escreve.delete(DbHelper.TABELA_CONTATO, "id=?", args);
            Log.i("INFOB", "Sucesso ao excluir registro!");
        } catch (Exception e) {
            Log.e("INFODB", "Erro ao excluir registro!" + e.getMessage());

            return false;
        }

        return true;
    }

    @Override
    public List<Contato> listar() {
        List<Contato> lstContatos = new ArrayList<>();
        String sql = " SELECT * FROM " + DbHelper.TABELA_CONTATO + ";";
        Cursor c = le.rawQuery(sql, null);

        while (((Cursor) c).moveToNext()) {
            Contato contato = new Contato() ();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeContato = c.getString(c.getColumnIndex("nomeContato"));
            String telefoneContato = c.getString(c.getColumnIndex("telContato"));

            contato.setId(id);
            contato.setNomeContato(nomeContato);
            contato.setTelContato(telefoneContato);

            lstContatos.add(contato);

            Log.i("contatoDAO", contato.getNomeContato());
        }
        return lstContatos;
    }
}
