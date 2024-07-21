package com.felipesucupira.transacoes;

import java.time.LocalDate;

import com.felipesucupira.Conta;

// Representa uma transação de ganho
public class Receita extends Transacao {
    public Receita(String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
        getContaAssociada().aumentarSaldo(valor);
    }


    public void mudarContaAssociada(Conta novaConta) {
        getContaAssociada().diminuirSaldo(getValor());
        setContaAssociada(novaConta);
        getContaAssociada().aumentarSaldo(getValor());
    }
}
