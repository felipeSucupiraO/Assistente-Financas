package com.felipesucupira.DTO;

import com.felipesucupira.Conta;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;

public class TransferenciaDTO extends TransacaoDTO {
    private ContaDTO contaDestino;
    private ReceitaDTO transacaoReceita;
    private DespesaDTO transacaoDespesa;
    
    // -------------------------------------------------------------------------
    
    public TransferenciaDTO () {

    }

    public TransferenciaDTO (int id, String nome, float valor, ContaDTO contaAssociada, ContaDTO contaDestino, String data) {
        super(id, nome, valor, contaAssociada, data);
        this.contaDestino = contaDestino;
        
        transacaoReceita = new ReceitaDTO(id + 1, nome + " (transferência)", valor, contaDestino, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new DespesaDTO(id + 2, nome + " (transferência)", valor, contaAssociada, data);
        transacaoDespesa.setEParteDeTransferencia(true);
    }

    public TransferenciaDTO (String nome, float valor, ContaDTO contaAssociada, ContaDTO contaDestino, String data) {
        super(nome, valor, contaAssociada, data);
        this.contaDestino = contaDestino;
        
        transacaoReceita = new ReceitaDTO(nome + " (transferência)", valor, contaDestino, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new DespesaDTO(nome + " (transferência)", valor, contaAssociada, data);
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
        transacaoReceita.setValor(valor);
        transacaoDespesa.setValor(valor);
    }
    
    public ContaDTO getContaDestino() {
        return contaDestino;
    }
    
    public ReceitaDTO getTransacaoReceita() {
        return transacaoReceita;
    }
    
    public DespesaDTO getTransacaoDespesa() {
        return transacaoDespesa;
    }
}
