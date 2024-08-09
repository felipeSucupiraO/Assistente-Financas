package com.felipesucupira.DTO;

public class DespesaDTO extends TransacaoDTO {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------

    public DespesaDTO() {
        
    }

    public DespesaDTO (int id, String nome, float valor, int idContaAssociada, String data) {
        super(id, nome, valor, idContaAssociada, data, "despesa");
    }

    public DespesaDTO (String nome, float valor, int idContaAssociada, String data) {
        super(nome, valor, idContaAssociada, data, "despesa");
    }

    // -------------------------------------------------------------------------

    public boolean eParteDeTransferencia() {
        return eParteDeTransferencia;
    }

    public void setEParteDeTransferencia(boolean eParteDeTransferencia) {
        this.eParteDeTransferencia = eParteDeTransferencia;
    }
}
