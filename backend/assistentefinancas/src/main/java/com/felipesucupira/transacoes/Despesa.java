package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

public class Despesa extends Transacao {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------

    public Despesa () {
        super();
    }

    public Despesa (int id, String nome, float valor, Conta contaAssociada, String data) {
        super(id, nome, valor, contaAssociada, data);
    }

    public Despesa (String nome, float valor, Conta contaAssociada, String data) {
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
