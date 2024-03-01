package br.app.ubuntu.auxiliar

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log


class ServicoDePerfil(context: Context) : IServicoDePerfil {
    private val db: SQLiteDatabase

    init {
        db = AuxiliarPerfilDatabase(context).writableDatabase
    }

    override fun obterPerfil(): Perfil {
        var retorno: Perfil? = null
        // Consultar dados
        val cursor: Cursor = db.query(
            "perfil",
            arrayOf("id", "perfil", "token", "invalido", "tipo_conta", "id_entregador"),
            null, null, null, null, null
        )


        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val perfil = cursor.getString(cursor.getColumnIndexOrThrow("perfil"))
            val token = cursor.getString(cursor.getColumnIndexOrThrow("token"))
            val invalido = cursor.getString(cursor.getColumnIndexOrThrow("invalido")) == "1"
            val tipoConta = cursor.getString(cursor.getColumnIndexOrThrow("tipo_conta"))
            val id_entregador = cursor.getString(cursor.getColumnIndexOrThrow("id_entregador"))
            if (perfil == "prod"){
                retorno = Perfil(id, token, perfil, invalido, tipoConta, id_entregador)
                Log.d("DEBUG", retorno.idEntregador.toString())
            }

        }

        return retorno!!
    }


    override fun salvarTokenProd() {
        TODO("Not yet implemented")
    }

    override fun definirTokenProd(token: String, tipoConta: String, idEntregador: String) {
        val sql = "UPDATE perfil SET token = ?," +
                " invalido = 0," +
                " tipo_conta = ?," +
                " id_entregador = $idEntregador WHERE perfil = 'prod'"

        try {
            db.execSQL(sql, arrayOf(token, tipoConta))
            Log.d("DEBUG", "Perfil 'prod' atualizado com o token: $token e id = $idEntregador")
        } catch (e: SQLException) {
            Log.e("ERROR", "Erro ao atualizar o token do perfil 'prod': ${e.message}")
        }
    }

    override fun encerrarSessao() {
        val sql = "UPDATE perfil SET invalido = 1  WHERE perfil = 'prod'"

        try {
            db.execSQL(sql)
            Log.d(Log.DEBUG.toString(), "Perfil 'prod' invalidado")
        } catch (e: SQLException) {
            Log.e(Log.ERROR.toString(), "Erro ao invalidar o token do perfil 'prod': ${e.message}")
        }
    }
}