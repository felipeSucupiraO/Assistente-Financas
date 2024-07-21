package com.felipesucupira.transacoes;

import java.time.LocalDate;
import com.felipesucupira.*;

// Representa uma transação de qualquer tipo, que será
// definido por suas classes filhas: Receita,
// Despesa, Transferencia
public abstract class Transacao {
    private String nome;
    private float valor;
    private Conta contaAssociada;
    private LocalDate data;

    
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
        this.valor = valor;
    }

    protected void setContaAssociada(Conta contaAssociada) {
        this.contaAssociada = contaAssociada;
    }

    public Conta getContaAssociada() {
        return contaAssociada;
    }


    public abstract void mudarContaAssociada(Conta novaConta);
}
