package com.felipesucupira.transacoes;

import java.time.LocalDate;

import com.felipesucupira.*;
import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;

// Representa uma transação de qualquer tipo, que será definido por suas classes
// filhas: Receita, Despesa ou Transferencia
public abstract class Transacao {
    private int id = 0;
    private String nome;
    private float valor;
    private Conta contaAssociada;
    private LocalDate data;
    private RelacaoTransacoesContasHandler mediatorTransacoesContas;

    // -------------------------------------------------------------------------
    
    public Transacao() {
        
    }

    public Transacao(String nome, float valor, Conta contaAssociada, String data) {
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
        this.data = LocalDate.parse(data);
    }
    
    public Transacao(int id, String nome, float valor, Conta contaAssociada, String data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
        this.data = LocalDate.parse(data);
    }

    // -------------------------------------------------------------------------
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da transação não pode ser negativo.");
        }
        
        mediatorTransacoesContas.notifyValorTransacaoModificado(this, valor);
        this.valor = valor;
    }
    
    public Conta getContaAssociada() {
        return contaAssociada;
    }

    public void setContaAssociada(Conta contaAssociada) {
        this.contaAssociada = contaAssociada;
    }

    public LocalDate getData() {
        return data;
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

    public void mudarDataPara(String data) {
        this.data = LocalDate.parse(data);
    }
}
