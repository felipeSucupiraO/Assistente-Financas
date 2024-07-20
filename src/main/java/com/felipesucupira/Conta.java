package com.felipesucupira;

// Essa classe representa uma conta onde se tem dinheiro, como uma conta
// poupança, uma conta corrente ou até a carteira.
public class Conta {
    private String nome;
    private float saldo;


    public Conta(String nome, float saldoInicial) {
        this.nome = nome;
        saldo = saldoInicial;
    }


    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public float getSaldo() {
        return saldo;
    }
    

    public void aumentarSaldo(float valor) {
        saldo += valor;
    }

    public void diminuirSaldo(float valor) {
        saldo -= valor;
    }
}
