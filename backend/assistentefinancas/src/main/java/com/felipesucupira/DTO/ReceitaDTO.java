package com.felipesucupira.DTO;

public class ReceitaDTO extends TransacaoDTO {
    private boolean eParteDeTransferencia = false;

    // -------------------------------------------------------------------------
    
    public ReceitaDTO () {
        
    }

    public ReceitaDTO(int id, String nome, float valor, int idContaAssociada, String data) {
        super(id, nome, valor, idContaAssociada, data, "receita");
    }

    public ReceitaDTO(String nome, float valor, int idContaAssociada, String data) {
        super(nome, valor, idContaAssociada, data, "receita");
    }

    // -------------------------------------------------------------------------

    public boolean eParteDeTransferencia() {
        return eParteDeTransferencia;
    }

    public void setEParteDeTransferencia(boolean eParteDeTransferencia) {
        this.eParteDeTransferencia = eParteDeTransferencia;
    }
}
