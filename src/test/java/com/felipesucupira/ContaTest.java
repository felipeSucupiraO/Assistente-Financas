package com.felipesucupira;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContaTest {
    @Test
    public void getSaldoInicialTest() {
        float valorInicial = 15.5f;
        Conta contaTeste = new Conta("teste", valorInicial);
        assertEquals(valorInicial, contaTeste.getSaldo(), 0);
    }
    
    @Test
    public void getSaldoAdicionadoTest() {
        float valorInicial = -100.00f;
        Conta contaTeste = new Conta("teste", valorInicial);
        assertEquals(-100.00f, contaTeste.getSaldo(), 0);

        contaTeste.adicionarSaldo(15.0f);
        assertEquals(-85.0f, contaTeste.getSaldo(), 0);
    
        contaTeste.adicionarSaldo(0.55f);
        assertEquals(-84.45f, contaTeste.getSaldo(), 0);
    
        contaTeste.adicionarSaldo(0f);
        assertEquals(-84.45f, contaTeste.getSaldo(), 0);

        contaTeste.adicionarSaldo(84.45f);
        assertEquals(0, contaTeste.getSaldo(), 0);
        
        contaTeste.adicionarSaldo(15.55f);
        assertEquals(15.55f, contaTeste.getSaldo(), 0);
    }

    @Test
    public void getSaldoRemovidoTest() {
        float valorInicial = 100.00f;
        Conta contaTeste = new Conta("teste", valorInicial);
        assertEquals(100.00, contaTeste.getSaldo(), 0);
    
        contaTeste.removerSaldo(15.0f);
        assertEquals(85.0f, contaTeste.getSaldo(), 0);
    
        contaTeste.removerSaldo(0.55f);
        assertEquals(84.45f, contaTeste.getSaldo(), 0);
    
        contaTeste.removerSaldo(0f);
        assertEquals(84.45f, contaTeste.getSaldo(), 0);

        contaTeste.removerSaldo(84.45f);
        assertEquals(0, contaTeste.getSaldo(), 0);

        contaTeste.removerSaldo(15.55f);
        assertEquals(-15.55f, contaTeste.getSaldo(), 0);
    }
}
