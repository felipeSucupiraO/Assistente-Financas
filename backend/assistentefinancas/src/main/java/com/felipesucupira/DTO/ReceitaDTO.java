package com.felipesucupira.DTO;

import com.felipesucupira.Conta;
import com.felipesucupira.transacoes.Receita;

public class ReceitaDTO extends TransacaoDTO {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------
    
    public ReceitaDTO () {
        
    }

    public ReceitaDTO(int id, String nome, float valor, ContaDTO contaAssociada, String data) {
        super(id, nome, valor, contaAssociada, data);
    }

    public ReceitaDTO(String nome, float valor, ContaDTO contaAssociada, String data) {
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
