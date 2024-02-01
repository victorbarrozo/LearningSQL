package com.example.integrandosql.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import android.util.Log


class DatabaseHelper(context:Context): SQLiteOpenHelper(
context, "loja.db" , null , 2
) {

    companion object{
        const val TB_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("info_db,","Execultou onCreate")


        val sql = "CREATE table if NOT EXISTS $TB_PRODUTOS(" +
                "$ID_PRODUTO integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$TITULO varchar(100)," +
                "$DESCRICAO text" +
                ");"
        try{
            db?.execSQL(sql)

            Log.i("info_db,","Sucesso ao criar a tabela")

        }catch (e:Exception){
            e.printStackTrace()
            Log.i("info_db,","Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("info_db,","Execultou onUpgrade")
    }
}