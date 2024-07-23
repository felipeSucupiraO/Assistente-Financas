package com.felipesucupira;

import java.util.List;
import com.felipesucupira.transacoes.*;;

public class Usuario {
    private String nome;
    private String senha;
    private List<Conta> listaContas;
    private List<Transacao> listaTransacoes;


    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }


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

    public List<Conta> getListaContas() {
        return listaContas;
    }

    public List<Transacao> getListaTransacoes() {
        return listaTransacoes;
    }

    public void adicionarConta(Conta contaAdicionada) {
        listaContas.add(contaAdicionada);
    }

    public void adicionarTransacao(Transacao transacao) {
        if (listaContas.contains(transacao.getContaAssociada())) {
            listaTransacoes.add(transacao);
        }
    }

    public void deletarConta(Conta conta) {
        if (listaContas.contains(conta)) {
            listaContas.remove(conta);
        }
    }

    public void deletarConta(int index) {
        listaContas.remove(index);
    }

    public void deletarTransacao(Transacao transacao) {
        if (listaTransacoes.contains(transacao)) {
            listaTransacoes.remove(transacao);
        }
    }

    public void deletarTransacao(int index) {
        listaTransacoes.remove(index);
    }
}
