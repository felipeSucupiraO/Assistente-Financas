package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

public class Transferencia extends Transacao{
    private Conta contaDestino;
    private Receita transacaoReceita;
    private Despesa transacaoDespesa;
    
    public Transferencia (String nome, float valor, Conta contaAssociada, Conta contaDestino) {
        super(nome, valor, contaAssociada);
        this.contaDestino = contaDestino;

        transacaoReceita = new Receita(nome + " receita", valor, contaDestino);
        transacaoDespesa = new Despesa(nome + " despesa", valor, contaAssociada);
    }


    @Override
    public void setValor(float valor) {
        transacaoReceita.setValor(valor);
        transacaoDespesa.setValor(valor);
    }


    public Conta getContaDestino() {
        return contaDestino;
    }

    public Receita getTransacaoReceita() {
        return transacaoReceita;
    }

    public Despesa getTransacaoDespesa() {
        return transacaoDespesa;
    }


    public void mudarContaAssociada(Conta novaConta) {
        transacaoDespesa.mudarContaAssociada(novaConta);
    }

    public void mudarContaDestino(Conta novaConta) {
        transacaoReceita.mudarContaAssociada(novaConta);
    }
}
