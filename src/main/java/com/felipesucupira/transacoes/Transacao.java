package com.felipesucupira.transacoes;

import java.time.LocalDate;
import com.felipesucupira.*;
import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;

// Representa uma transação de qualquer tipo, que será
// definido por suas classes filhas: Receita,
// Despesa, Transferencia
public abstract class Transacao {
    private String nome;
    private float valor;
    private Conta contaAssociada;
    private LocalDate data;
    private RelacaoTransacoesContasHandler mediator;

    
    public Transacao(String nome, float valor, Conta contaAssociada) {
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        mediator.valorTransacaoModificado(this, valor);
        this.valor = valor;
    }

    protected void setContaAssociada(Conta contaAssociada) {
        this.contaAssociada = contaAssociada;
    }

    public Conta getContaAssociada() {
        return contaAssociada;
    }

    public RelacaoTransacoesContasHandler getMediator() {
        return mediator;
    }

    public void setMediator(RelacaoTransacoesContasHandler mediator) {
        this.mediator = mediator;
    }


    public void mudarContaAssociada(Conta novaConta) {
        mediator.contaDaTransacaoModificada(this, novaConta);
        contaAssociada = novaConta;
    }
}
