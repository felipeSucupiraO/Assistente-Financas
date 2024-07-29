package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

public class Despesa extends Transacao {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------

    public Despesa (String nome, float valor, Conta contaAssociada) {
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
