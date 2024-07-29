package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

// Representa uma transação de ganho
public class Receita extends Transacao {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------
    
    public Receita (String nome, float valor, Conta contaAssociada) {
        super(nome, valor, contaAssociada);
    }

    // -------------------------------------------------------------------------
    
    public boolean eParteDeTransferencia() {
        return eParteDeTransferencia;
    }

    public void setEParteDeTransferencia(boolean eParteDeTransferencia) {
        this.eParteDeTransferencia = eParteDeTransferencia;
    }
}
