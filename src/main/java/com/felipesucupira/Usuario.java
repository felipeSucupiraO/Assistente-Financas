package com.felipesucupira;

import java.util.ArrayList;
import java.util.List;
import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;
import com.felipesucupira.transacoes.*;

// Representa um usuário do programa, com as próprias transações e contas
public class Usuario {
    private String nome;
    private String senha;
    private List<Conta> listaContas = new ArrayList<Conta>();
    private List<Transacao> listaTransacoes = new ArrayList<Transacao>();
    private RelacaoTransacoesContasHandler mediatorTransacoesContas;

    // -------------------------------------------------------------------------
    
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        mediatorTransacoesContas = new RelacaoTransacoesContasHandler(this);
    }
    
    // -------------------------------------------------------------------------
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public RelacaoTransacoesContasHandler getMediatorTransacoesContas() {
        return mediatorTransacoesContas;
    }
    
    public List<Conta> getListaContas() {
        return listaContas;
    }
    
    public List<Transacao> getListaTransacoes() {
        return listaTransacoes;
    }
    
    // -------------------------------------------------------------------------
    
    public void adicionarConta(Conta contaAdicionada) {
        listaContas.add(contaAdicionada);
    }
        
    public void adicionarTransacao(Transacao transacao) {
        transacao.setMediatorTransacoesContas(mediatorTransacoesContas);
        mediatorTransacoesContas.notifyTransacaoAdicionada(transacao);
        listaTransacoes.add(transacao);
    }

    public void deletarConta(int index) {
        Conta contaDeletada = listaContas.get(index);
        deletarConta(contaDeletada);
    }

    public void deletarConta(Conta conta) {
        for (Transacao transacao : listaTransacoes) {
            if (transacao.getContaAssociada() == conta) {
                deletarTransacao(transacao);
            }
        }

        listaContas.remove(conta);
    }

    public void deletarTransacao(int index) {
        Transacao transacaoDeletada = listaTransacoes.get(index);
        deletarTransacao(transacaoDeletada);
    }

    public void deletarTransacao(Transacao transacao) {
        mediatorTransacoesContas.notifyTransacaoDeletada(transacao);
        listaTransacoes.remove(transacao);
    }
}
