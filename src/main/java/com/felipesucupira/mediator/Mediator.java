package com.felipesucupira.mediator;

import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.Usuario;

public class Mediator {
    Usuario usuario;

    public Mediator(Usuario usuario) {
        this.usuario = usuario;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    
    public void transacaoCriada(Transacao transacaoCriada) {
        if (!contaExiste(transacaoCriada.getContaAssociada())) {
            // error
        }

        if (transacaoCriada instanceof Despesa) {
            transacaoCriada.getContaAssociada().diminuirSaldo(transacaoCriada.getValor());
        } else if (transacaoCriada instanceof Receita) {
            transacaoCriada.getContaAssociada().aumentarSaldo(transacaoCriada.getValor());
        }
    }

    private boolean contaExiste(Conta conta) {
        if (usuario.getListaContas().contains(conta)) {
            return true;
        }
        return false;
    }

    public void contaDaTransacaoModificada(Transacao transacaoModificada, Conta novaConta) {
        if (!contaExiste(transacaoModificada.getContaAssociada())) {
            // error
        }

        Conta contaAntiga = transacaoModificada.getContaAssociada();
        if (transacaoModificada instanceof Despesa) {
            contaAntiga.aumentarSaldo(transacaoModificada.getValor());
            novaConta.diminuirSaldo(transacaoModificada.getValor());
        } else if (transacaoModificada instanceof Receita) {
            contaAntiga.diminuirSaldo(transacaoModificada.getValor());
            novaConta.aumentarSaldo(transacaoModificada.getValor());
        }
    }

    public void valorTransacaoModificado(Transacao transacaoModificada, float novoValor) {
        float diferenca = novoValor - transacaoModificada.getValor();
        if (transacaoModificada instanceof Despesa) {
            if (diferenca > 0) {
                transacaoModificada.getContaAssociada().diminuirSaldo(diferenca);
            } else if (diferenca < 0) {
                transacaoModificada.getContaAssociada().aumentarSaldo(diferenca * -1);
            }
        } else if (transacaoModificada instanceof Receita) {
            if (diferenca > 0) {
                transacaoModificada.getContaAssociada().aumentarSaldo(diferenca);
            } else if (diferenca < 0) {
                transacaoModificada.getContaAssociada().diminuirSaldo(diferenca * -1);
            }
        }
    }

    public void contaDeletada() {

    }

    public void transacaoDeletada(Transacao transacaoDeletada) {
        if (transacaoDeletada instanceof Despesa) {
            transacaoDeletada.getContaAssociada().aumentarSaldo(transacaoDeletada.getValor());
        } else if (transacaoDeletada instanceof Receita) {
            transacaoDeletada.getContaAssociada().diminuirSaldo(transacaoDeletada.getValor());
        }
    }
}
