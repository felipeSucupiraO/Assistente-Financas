package com.felipesucupira.DTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private int id;
    private String nome;
    private String senha;
    private List<ContaDTO> listaContas = new ArrayList<ContaDTO>();
    private List<TransacaoDTO> listaTransacoes = new ArrayList<TransacaoDTO>();
    private float balancoTotal = 0;

    // -------------------------------------------------------------------------
    
    public UsuarioDTO() {
        
    }

    public UsuarioDTO(int id, String nome, String senha, List<ContaDTO> listaContas, List<TransacaoDTO> listaTransacoes, float balancoTotal) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.listaContas = listaContas;
        this.listaTransacoes = listaTransacoes;
        this.balancoTotal = balancoTotal;
    }

    public UsuarioDTO(String nome, String senha, List<ContaDTO> listaContas, List<TransacaoDTO> listaTransacoes, float balancoTotal) {
        this.nome = nome;
        this.senha = senha;
        this.listaContas = listaContas;
        this.listaTransacoes = listaTransacoes;
        this.balancoTotal = balancoTotal;
    }
    
    // -------------------------------------------------------------------------
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ContaDTO> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<ContaDTO> listaContas) {
        this.listaContas = listaContas;
    }

    public List<TransacaoDTO> getListaTransacoes() {
        return listaTransacoes;
    }

    public void setListaTransacoes(List<TransacaoDTO> listaTransacoes) {
        this.listaTransacoes = listaTransacoes;
    }

    public float getBalancoTotal() {
        return balancoTotal;
    }

    public void setBalancoTotal(float balancoTotal) {
        this.balancoTotal = balancoTotal;
    }
}
