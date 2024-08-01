package com.felipesucupira.DTO;

import com.felipesucupira.Conta;
import com.felipesucupira.transacoes.Despesa;

public class DespesaDTO extends TransacaoDTO {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------

    public DespesaDTO() {
        
    }

    public DespesaDTO (int id, String nome, float valor, ContaDTO contaAssociada, String data) {
        super(id, nome, valor, contaAssociada, data);
    }

    public DespesaDTO (String nome, float valor, ContaDTO contaAssociada, String data) {
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
