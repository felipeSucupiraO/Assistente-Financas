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
        if (encontrarConta(transacao.getContaAssociada()) != -1) {
            listaTransacoes.add(transacao);
        }
    }

    private int encontrarConta(Conta contaProcurada) {
        for (int i = 0; i < listaContas.size(); i++) {
            if (listaContas.get(i) == contaProcurada) {
                return i;
            }
        }

        return -1;
    }
}
