package com.example.integrandosql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.integrandosql.database.DatabaseHelper
import com.example.integrandosql.database.ProdutoDAO
import com.example.integrandosql.databinding.ActivityMainBinding
import com.example.integrandosql.model.Produto

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /* private val bancoDados by lazy {
        DatabaseHelper(this)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            btnSalvar.setOnClickListener {
                salvar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }

            btnListar.setOnClickListener {
                listar()
            }
        }
    }

    private fun salvar() {
        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, "$titulo", "descricao..."
        )
        if (produtoDAO.salvar(produto)) {
            Toast.makeText(
                this,
                "Sucesso ao cadastrar produto",
                Toast.LENGTH_SHORT
            ).show()
            binding.editProduto.setText("")
        } else {
            Toast.makeText(
                this,
                "Erro ao cadastrar produto",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            3, titulo, "descricao..."
        )
        produtoDAO.atualiazar(produto)
    }


    private fun remover() {

        val produtoDAO = ProdutoDAO(this)
        produtoDAO.remover(3)
    }

    private fun listar() {

        val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()
        var texto = ""

        if (listaProdutos.isNotEmpty()) {
            listaProdutos.forEach { produto ->
                texto += "${produto.idProduto} - ${produto.titulo} \n"
                Log.i("info_db,", "${produto.idProduto} - ${produto.titulo}")

            }
            binding.textResultado.text = texto
        }else{binding.textResultado.text = "Nenhum item adicionado"
        }
    }
}