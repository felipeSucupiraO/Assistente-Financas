package com.felipesucupira.DTO;

public class TransferenciaDTO extends TransacaoDTO {
    private int idContaDestino;
    private ReceitaDTO transacaoReceita;
    private DespesaDTO transacaoDespesa;
    
    // -------------------------------------------------------------------------
    
    public TransferenciaDTO () {
        this.transacaoReceita = new ReceitaDTO();
        this.transacaoDespesa = new DespesaDTO();
    }

    public TransferenciaDTO (int id, String nome, float valor, int idContaAssociada, int idContaDestino, String data) {
        super(id, nome, valor, idContaAssociada, data, "transferencia");
        this.idContaDestino = idContaDestino;
        
        transacaoReceita = new ReceitaDTO(id + 1, nome + " (transferência)", valor, idContaAssociada, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new DespesaDTO(id + 2, nome + " (transferência)", valor, idContaDestino, data);
        transacaoDespesa.setEParteDeTransferencia(true);
    }

    public TransferenciaDTO (String nome, float valor, int idContaAssociada, int idContaDestino, String data) {
        super(nome, valor, idContaAssociada, data, "transferencia");
        this.idContaDestino = idContaDestino;
        
        transacaoReceita = new ReceitaDTO(nome + " (transferência)", valor, idContaAssociada, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new DespesaDTO(nome + " (transferência)", valor, idContaDestino, data);
        transacaoDespesa.setEParteDeTransferencia(true);
    }
    
    // -------------------------------------------------------------------------
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        transacaoDespesa.setNome(nome + "(transferência)");
        transacaoReceita.setNome(nome + "(transferência)");
    }

    @Override
    public void setValor(float valor) {
        super.setValor(valor);
        transacaoReceita.setValor(valor);
        transacaoDespesa.setValor(valor);
    }
    
    public int getIdContaDestino() {
        return idContaDestino;
    }
    
    public ReceitaDTO getTransacaoReceita() {
        return transacaoReceita;
    }
    
    public DespesaDTO getTransacaoDespesa() {
        return transacaoDespesa;
    }
}
