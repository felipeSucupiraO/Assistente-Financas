package com.felipesucupira.mediator;

import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.Usuario;
import com.felipesucupira.Conta;

public class RelacaoTransacoesContasHandler {
    Usuario usuario;

    // -------------------------------------------------------------------------
    
    public RelacaoTransacoesContasHandler(Usuario usuario) {
        this.usuario = usuario;
    }
    
    // -------------------------------------------------------------------------
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    // -------------------------------------------------------------------------
    
    public void notifyContaCriada(Conta contaCriada) {
        usuario.aumentarBalancoTotal(contaCriada.getSaldo());
    }

    public void notifySaldoContaAumentado(Conta conta, float valor) {
        usuario.aumentarBalancoTotal(valor);
    }

    public void notifySaldoContaDiminuido(Conta conta, float valor) {
        usuario.diminuirBalancoTotal(valor);
    }

    public void notifyContaDeletada(Conta conta) {
        usuario.diminuirBalancoTotal(conta.getSaldo());
    }
    
    public void notifyTransacaoAdicionada(Transacao transacaoCriada) {
        if (transacaoCriada instanceof Despesa) {
            transacaoCriada.getContaAssociada().diminuirSaldo(transacaoCriada.getValor());

        } else if (transacaoCriada instanceof Receita) {
            transacaoCriada.getContaAssociada().aumentarSaldo(transacaoCriada.getValor());
        }
    }
    
    public void notifyContaDaTransacaoModificada(Transacao transacaoModificada, Conta novaConta) {
        Conta contaAntiga = transacaoModificada.getContaAssociada();
        if (transacaoModificada instanceof Despesa) {
            contaAntiga.aumentarSaldo(transacaoModificada.getValor());
            novaConta.diminuirSaldo(transacaoModificada.getValor());
        } else if (transacaoModificada instanceof Receita) {
            contaAntiga.diminuirSaldo(transacaoModificada.getValor());
            novaConta.aumentarSaldo(transacaoModificada.getValor());
        }
    }

    public void notifyValorTransacaoModificado(Transacao transacaoModificada, float novoValor) {
        float diferenca = novoValor - transacaoModificada.getValor();
        if (transacaoModificada instanceof Despesa) {
            valorDespesaModificado(diferenca, transacaoModificada);
        } else if (transacaoModificada instanceof Receita) {
            valorReceitaModificado(diferenca, transacaoModificada);
        }
    }
    
    private void valorDespesaModificado(float diferenca, Transacao transacaoModificada) {
        if (diferenca > 0) {
            transacaoModificada.getContaAssociada().diminuirSaldo(diferenca);
        } else if (diferenca < 0) {
            transacaoModificada.getContaAssociada().aumentarSaldo(diferenca * -1);
        }
    }
    
    private void valorReceitaModificado(float diferenca, Transacao transacaoModificada) {
        if (diferenca > 0) {
            transacaoModificada.getContaAssociada().aumentarSaldo(diferenca);
        } else if (diferenca < 0) {
            transacaoModificada.getContaAssociada().diminuirSaldo(diferenca * -1);
        }
    }


    public void notifyTransacaoDeletada(Transacao transacaoDeletada) {
        if (transacaoDeletada instanceof Despesa) {
            transacaoDeletada.getContaAssociada().aumentarSaldo(transacaoDeletada.getValor());
        } else if (transacaoDeletada instanceof Receita) {
            transacaoDeletada.getContaAssociada().diminuirSaldo(transacaoDeletada.getValor());
        }
    }
}
