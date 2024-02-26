package br.app.ubuntu.auxiliar

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AuxiliarPerfilDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "app.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crie suas tabelas e defina a estrutura do banco de dados aqui
        db.execSQL("CREATE TABLE perfil (id INTEGER PRIMARY KEY , perfil TEXT, token TEXT,home TEXT, invalido BOOLEAN,tipo_conta TEXT)")
        db.execSQL("Insert into perfil values(null,'prod',null,null,1,'null' )")
        System.out.println("Inserido prod")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        if (oldVersion < 2) {
//            // Se a versão anterior for menor que 2, adicione a nova coluna
//            db.execSQL("ALTER TABLE perfil ADD COLUMN tipo_conta TEXT")
//        }
    }
}