package com.felipesucupira.DTO;

public class ContaDTO {
    private int id;
    private String nome;
    private float saldo;

    // -------------------------------------------------------------------------
    
    public ContaDTO(int id, String nome, float saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
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
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
