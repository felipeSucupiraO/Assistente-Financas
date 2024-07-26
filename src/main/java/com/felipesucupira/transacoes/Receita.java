package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

// Representa uma transação de ganho
public class Receita extends Transacao {
    public Receita (String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
    }
}
