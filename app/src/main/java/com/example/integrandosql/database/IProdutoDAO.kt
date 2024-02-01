package com.example.integrandosql.database

import com.example.integrandosql.model.Produto

interface IProdutoDAO {

    fun salvar(produto: Produto): Boolean
    fun atualiazar(produto: Produto): Boolean
    fun remover(idProduto: Int): Boolean
    fun listar(): List<Produto>

}