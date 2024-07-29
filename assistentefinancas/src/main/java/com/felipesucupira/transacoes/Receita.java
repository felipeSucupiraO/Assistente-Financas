package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

// Representa uma transação de ganho
public class Receita extends Transacao {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------
    
    public Receita (String nome, float valor, Conta contaAssociada, String data) {
        super(nome, valor, contaAssociada, data);
    }

    // -------------------------------------------------------------------------
    
    public boolean eParteDeTransferencia() {
        return eParteDeTransferencia;
    }

    public void setEParteDeTransferencia(boolean eParteDeTransferencia) {
        this.eParteDeTransferencia = eParteDeTransferencia;
    }
}
