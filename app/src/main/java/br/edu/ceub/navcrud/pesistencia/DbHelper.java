package br.edu.ceub.navcrud.pesistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_SEUBANCO";
    public static String TABELA_CONTATO = "contato";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOME_DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS" + TABELA_CONTATO
                + "(id INTERGER PRIMARY KEY AUTOINCREMENT,"
                + "(nomeContato TEXT NOT NULL,telContato TEXT);";

        try{
            db.execSQL(sql);
        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar tabela"+ e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
