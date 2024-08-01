package com.felipesucupira.DTO;

import java.time.LocalDate;
import com.felipesucupira.Conta;

public abstract class TransacaoDTO {
    private int id;
    private String nome;
    private float valor;
    private ContaDTO contaAssociada;
    private LocalDate data;

    // -------------------------------------------------------------------------
    
    public TransacaoDTO() {
        
    }

    public TransacaoDTO(int id, String nome, float valor, ContaDTO contaAssociada, String data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
        this.data = LocalDate.parse(data);
    }
    
    public TransacaoDTO(String nome, float valor, ContaDTO contaAssociada, String data) {
        this.nome = nome;
        this.valor = valor;
        this.contaAssociada = contaAssociada;
        this.data = LocalDate.parse(data);
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
    public ContaDTO getContaAssociada() {
        return contaAssociada;
    }
    public void setContaAssociada(ContaDTO contaAssociada) {
        this.contaAssociada = contaAssociada;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    
}
