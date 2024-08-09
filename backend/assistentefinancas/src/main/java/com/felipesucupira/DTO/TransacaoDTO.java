package com.felipesucupira.DTO;

import java.time.LocalDate;

public abstract class TransacaoDTO {
    private int id;
    private String nome;
    private float valor;
    private int idContaAssociada;
    private LocalDate data;
    private String tipo;

    // -------------------------------------------------------------------------
    
    public TransacaoDTO() {
        
    }

    public TransacaoDTO(int id, String nome, float valor, int idContaAssociada, String data, String tipo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.idContaAssociada = idContaAssociada;
        this.data = LocalDate.parse(data);
        this.tipo = tipo;
    }
    
    public TransacaoDTO(String nome, float valor, int idContaAssociada, String data, String tipo) {
        this.nome = nome;
        this.valor = valor;
        this.idContaAssociada = idContaAssociada;
        this.data = LocalDate.parse(data);
        this.tipo = tipo;
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
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public int getIdContaAssociada() {
        return idContaAssociada;
    }
    public void setidContaAssociada(int idContaAssociada) {
        this.idContaAssociada = idContaAssociada;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
