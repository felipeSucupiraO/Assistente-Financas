package com.felipesucupira;

import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;

// Representa uma conta onde se tem dinheiro, como uma conta poupança, uma conta
// corrente ou até a carteira.
public class Conta {
    private String nome;
    private float saldo;
    private RelacaoTransacoesContasHandler mediatorTransacoesContas;

    // -------------------------------------------------------------------------
    
    public Conta(String nome, float saldoInicial) {
        this.nome = nome;
        saldo = saldoInicial;
    }
    
    // -------------------------------------------------------------------------
    
    public String getnome() {
        return nome;
    }
    
    public void setnome(String nome) {
        this.nome = nome;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public RelacaoTransacoesContasHandler getMediatorTransacoesContas() {
        return mediatorTransacoesContas;
    }

    public void setMediatorTransacoesContas(RelacaoTransacoesContasHandler mediatorTransacoesContas) {
        this.mediatorTransacoesContas = mediatorTransacoesContas;
    }
    
    // -------------------------------------------------------------------------
    
    public void aumentarSaldo(float valor) {
        saldo += valor;
        mediatorTransacoesContas.notifySaldoContaAumentado(this, valor);
    }
    
    public void diminuirSaldo(float valor) {
        saldo -= valor;
        mediatorTransacoesContas.notifySaldoContaDiminuido(this, valor);
    }
}
