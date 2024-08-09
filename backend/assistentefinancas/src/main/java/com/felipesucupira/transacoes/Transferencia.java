package com.felipesucupira.transacoes;

import com.felipesucupira.Conta;

// Representa uma transferência de uma conta para outra. Basicamente funciona
// como duas transferencias separadas que se juntam nesse objeto.
public class Transferencia extends Transacao{
    private Conta contaDestino;
    private Receita transacaoReceita;
    private Despesa transacaoDespesa;
    
    // -------------------------------------------------------------------------
    
    public Transferencia () {
        super();
    }

    public Transferencia (int id, String nome, float valor, Conta contaAssociada, Conta contaDestino, String data) {
        super(id, nome, valor, contaAssociada, data);
        this.contaDestino = contaDestino;
        
        transacaoReceita = new Receita(id + 1, nome + " (transferência)", valor, contaDestino, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new Despesa(id + 2, nome + " (transferência)", valor, contaAssociada, data);
        transacaoDespesa.setEParteDeTransferencia(true);
    }

    public Transferencia (String nome, float valor, Conta contaAssociada, Conta contaDestino, String data) {
        super(nome, valor, contaAssociada, data);
        this.contaDestino = contaDestino;
        
        transacaoReceita = new Receita(nome + " (transferência)", valor, contaDestino, data);
        transacaoReceita.setEParteDeTransferencia(true);
        transacaoDespesa = new Despesa(nome + " (transferência)", valor, contaAssociada, data);
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
    
    public Conta getContaDestino() {
        return contaDestino;
    }

    @Override
    public void setContaAssociada(Conta contaAssociada) {
        transacaoDespesa.setContaAssociada(contaAssociada);
        super.setContaAssociada(contaAssociada);
    }

    public void setContaDestino(Conta contaDestino) {
        transacaoReceita.setContaAssociada(contaDestino);
        this.contaDestino = contaDestino;
    }
    
    public Receita getTransacaoReceita() {
        return transacaoReceita;
    }
    
    public Despesa getTransacaoDespesa() {
        return transacaoDespesa;
    }
    
    // -------------------------------------------------------------------------
    
    public void mudarContaAssociada(Conta novaConta) {
        transacaoDespesa.mudarContaAssociada(novaConta);
        super.setContaAssociada(novaConta);
    }

    public void mudarContaDestino(Conta novaConta) {
        transacaoReceita.mudarContaAssociada(novaConta);
        contaDestino = novaConta;
    }

    @Override
    public void mudarDataPara(String data) {
        super.mudarDataPara(data);
        transacaoDespesa.mudarDataPara(data);
        transacaoReceita.mudarDataPara(data);
    }
}
