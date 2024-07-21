package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

// Representa uma transação de ganho
public class Receita extends Transacao {
    public Receita (String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
        getContaAssociada().aumentarSaldo(valor);
    }


    @Override
    public void setValor(float valor) {
        float diferenca = valor - getValor();
        if (diferenca > 0) {
            getContaAssociada().aumentarSaldo(diferenca);
        } else if (diferenca < 0) {
            getContaAssociada().diminuirSaldo(diferenca * -1);
        }
        super.setValor(valor);
    }


    public void mudarContaAssociada(Conta novaConta) {
        getContaAssociada().diminuirSaldo(getValor());
        setContaAssociada(novaConta);
        getContaAssociada().aumentarSaldo(getValor());
    }
}
