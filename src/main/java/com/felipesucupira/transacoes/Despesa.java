package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

public class Despesa extends Transacao {
    public Despesa (String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
    }
}
