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
    private float balancoTotal = 0;

    // -------------------------------------------------------------------------
    
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        mediatorTransacoesContas = new RelacaoTransacoesContasHandler(this);

        // criação de contas padrão em todo usuário
        adicionarConta(new Conta("Conta Poupança", 0));
        adicionarConta(new Conta("Conta Corrente", 0));
        adicionarConta(new Conta("Cartão de Crédito", 0));
        adicionarConta(new Conta("Carteira", 0));
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

    public float getBalancoTotal() {
        return balancoTotal;
    }
    
    // -------------------------------------------------------------------------
    
    public void aumentarBalancoTotal(float valor) {
        balancoTotal += valor;
    }

    public void diminuirBalancoTotal(float valor) {
        balancoTotal -= valor;
    }

    public void adicionarConta(Conta contaAdicionada) {
        if (contaExiste(contaAdicionada)) {
            throw new IllegalArgumentException("A conta sendo adicionada já está no usuário.");
        }
        
        contaAdicionada.setMediatorTransacoesContas(mediatorTransacoesContas);
        mediatorTransacoesContas.notifyContaCriada(contaAdicionada);
        listaContas.add(contaAdicionada);
    }
        
    public void adicionarTransacao(Transacao transacao) {
        if (!contaExiste(transacao.getContaAssociada())) {
            throw new IllegalArgumentException("A conta associada à transação não existe no usuário.");
        }
        
        transacao.setMediatorTransacoesContas(mediatorTransacoesContas);
        mediatorTransacoesContas.notifyTransacaoAdicionada(transacao);
        listaTransacoes.add(transacao);
        if (transacao instanceof Transferencia) {
            Transferencia transferencia = (Transferencia) transacao;
            adicionarTransacao(transferencia.getTransacaoDespesa());
            adicionarTransacao(transferencia.getTransacaoReceita());
        }
    }
    
    public void deletarConta(int index) {
        Conta contaDeletada = listaContas.get(index);
        deletarConta(contaDeletada);
    }
    
    public void deletarConta(Conta conta) {
        if (!contaExiste(conta)) {
            throw new IllegalArgumentException("A conta sendo deletada não existe no usuário.");
        }
        
        for (Transacao transacao : listaTransacoes) {
            if (transacao.getContaAssociada() == conta) {
                deletarTransacao(transacao);
            }
        }
        
        mediatorTransacoesContas.notifyContaDeletada(conta);
        listaContas.remove(conta);
    }

    private boolean contaExiste(Conta conta) {
        return listaContas.contains(conta);
    }
    
    public void deletarTransacao(int index) {
        Transacao transacaoDeletada = listaTransacoes.get(index);
        deletarTransacao(transacaoDeletada);
    }
    
    public void deletarTransacao(Transacao transacao) {
        if (!transacaoExiste(transacao)) {
            throw new IllegalArgumentException("A transação sendo deletada não existe no usuário.");
        }
        
        mediatorTransacoesContas.notifyTransacaoDeletada(transacao);
        listaTransacoes.remove(transacao);
        if (transacao instanceof Transferencia) {
            Transferencia transferencia = (Transferencia) transacao;
            deletarTransacao(transferencia.getTransacaoDespesa());
            deletarTransacao(transferencia.getTransacaoReceita());
        }
    }

    private boolean transacaoExiste(Transacao transacao) {
        return listaTransacoes.contains(transacao);
    }
}
