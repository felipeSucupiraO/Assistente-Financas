package com.felipesucupira;

public class Conta {
    private String nomeDaConta;
    private float saldo;


    public Conta(String nome, float saldoInicial) {
        nomeDaConta = nome;
        saldo = saldoInicial;
    }


    public String getNomeDaConta() {
        return nomeDaConta;
    }

    public void setNomeDaConta(String nomeDaConta) {
        this.nomeDaConta = nomeDaConta;
    }

    public float getSaldo() {
        return saldo;
    }
    

    public void adicionarSaldo(float valor) {
        saldo += valor;
    }

    public void removerSaldo(float valor) {
        saldo -= valor;
    }
}
