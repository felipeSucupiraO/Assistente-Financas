package com.felipesucupira.transacoes;

import java.time.LocalDate;
import com.felipesucupira.*;
import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;

// Representa uma transação de qualquer tipo, que será definido por suas classes
// filhas: Receita, Despesa ou Transferencia
public abstract class Transacao {
    private String nome;
    private float valor;
    private Conta contaAssociada;
    private LocalDate data;
    private RelacaoTransacoesContasHandler mediatorTransacoesContas;

    // -------------------------------------------------------------------------
    
    public Transacao(String nome, float valor, Conta contaAssociada) {
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
    }
    
    // -------------------------------------------------------------------------
    
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
        mediatorTransacoesContas.notifyValorTransacaoModificado(this, valor);
        this.valor = valor;
    }
    
    public Conta getContaAssociada() {
        return contaAssociada;
    }

    protected void setContaAssociada(Conta contaAssociada) {
        this.contaAssociada = contaAssociada;
    }
    
    public RelacaoTransacoesContasHandler getMediatorTransacoesContas() {
        return mediatorTransacoesContas;
    }
    
    public void setMediatorTransacoesContas(RelacaoTransacoesContasHandler mediator) {
        this.mediatorTransacoesContas = mediator;
    }
    
    // -------------------------------------------------------------------------
    
    public void mudarContaAssociada(Conta novaConta) {
        mediatorTransacoesContas.notifyContaDaTransacaoModificada(this, novaConta);
        contaAssociada = novaConta;
    }
}
