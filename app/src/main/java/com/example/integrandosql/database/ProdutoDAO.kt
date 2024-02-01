package com.example.integrandosql.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.integrandosql.model.Produto

class ProdutoDAO(context: Context): IProdutoDAO {


    private val escrever = DatabaseHelper(context).writableDatabase
    private val ler = DatabaseHelper(context).readableDatabase


    override fun salvar(produto: Produto): Boolean {

        val titulo = produto.titulo
       // val sql = "INSERT INTO ${DatabaseHelper.TB_PRODUTOS} VALUES(null, '$titulo', 'Descricao...');"

        try {
            val valores = ContentValues()
            valores.put("${DatabaseHelper.TITULO}", produto.titulo)
            valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)

            //escrever.execSQL(sql)
            escrever.insert(
                DatabaseHelper.TB_PRODUTOS,
               null,
                valores
            )
            Log.i("info_db,", "Sucesso ao inserir")
        } catch (e: Exception) {
            Log.i("info_db,", "Erro ao inserir")
            return false

        }
        return true
    }

    override fun atualiazar(produto: Produto): Boolean {

       /* val titulo = produto.titulo
        val idProduto = produto.idProduto
        val sql = "UPDATE ${DatabaseHelper.TB_PRODUTOS} " +
                "SET ${DatabaseHelper.TITULO} = '$titulo' " +
                "WHERE ${DatabaseHelper.ID_PRODUTO} = $idProduto; "
*/
        val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}", produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)
        val args = arrayOf(produto.idProduto.toString())

        try{
            //escrever.execSQL(sql)
            escrever.update(
                DatabaseHelper.TB_PRODUTOS,
                valores,
                "id_produto = ?",
                args
                )
            Log.i("info_db,", "Sucesso ao atualizar")
        }catch (e:Exception){
            Log.i("info_db,", "Erro ao atualizar")
            return false
        }
        return true
    }


    override fun remover(idProduto: Int): Boolean {
        /*val sql = "DELETE FROM ${DatabaseHelper.TB_PRODUTOS} " +
                "WHERE ${DatabaseHelper.ID_PRODUTO} = $idProduto"*/
        val args = arrayOf(idProduto.toString())
        try{
            //ler.execSQL(sql)
            escrever.delete(
                DatabaseHelper.TB_PRODUTOS,
                "${DatabaseHelper.ID_PRODUTO} = ?",
                args
            )
            Log.i("info_db,", "Sucesso ao remover")
        }catch (e:Exception){
            Log.i("info_db,", "Erro ao remover")
            return false
        }
        return true
    }

    override fun listar(): List<Produto> {

        val listaProdutos = mutableListOf<Produto>()

        val sql = "SELECT * FROM ${DatabaseHelper.TB_PRODUTOS};"
        val cursor = ler.rawQuery(sql,null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while(cursor.moveToNext()){

            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
           // Log.i("info_db,", "$idProduto - $titulo")

            val produto = Produto(idProduto,titulo,descricao)
            listaProdutos.add(produto)
        }
        return listaProdutos
    }


}