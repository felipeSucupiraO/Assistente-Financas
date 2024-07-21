package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

public class Despesa extends Transacao {
    public Despesa (String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
        getContaAssociada().diminuirSaldo(valor);
    }


    @Override
    public void setValor(float valor) {
        float diferenca = valor - getValor();
        if (diferenca > 0) {
            getContaAssociada().diminuirSaldo(diferenca);
        } else if (diferenca < 0) {
            getContaAssociada().aumentarSaldo(diferenca * -1);
        }
        super.setValor(valor);
    }


    public void mudarContaAssociada(Conta novaConta) {
        getContaAssociada().aumentarSaldo(getValor());
        setContaAssociada(novaConta);
        getContaAssociada().diminuirSaldo(getValor());
    }
}
