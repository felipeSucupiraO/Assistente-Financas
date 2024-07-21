package com.felipesucupira.transacoes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.felipesucupira.Conta;

public class DespesaTest {
    private Conta conta1 = new Conta("Poupança", 50);
    private Conta conta2 = new Conta("Carteira", 60);

    @Test
    public void despesaTest() {
        Despesa despesa = new Despesa("despesa", 10f, conta1);
        assertEquals(40f, conta1.getSaldo(), 0);
    }

    @Test
    public void setContaAssociadaTest() {
        Despesa despesa = new Despesa("despesa", 10f, conta1);
        assertEquals(conta1, despesa.getContaAssociada());
        despesa.setContaAssociada(conta2);
        assertEquals(conta2, despesa.getContaAssociada());
    }

    @Test
    public void mudarContaAssociadaTest() {
        Despesa despesa = new Despesa("despesa", 10f, conta1);
        despesa.mudarContaAssociada(conta2);
        assertEquals(50f, conta1.getSaldo(), 0);
        assertEquals(50f, conta2.getSaldo(), 0);
    }
    
    @Test
    public void setValorTest() {
        Despesa despesa = new Despesa("despesa", 10f, conta1);
        despesa.setValor(50);
        assertEquals(0, conta1.getSaldo(), 0);
        
        Despesa despesa2 = new Despesa("despesa", 50f, conta2);
        despesa2.setValor(0);
        assertEquals(60f, conta2.getSaldo(), 0);

        Despesa despesa3 = new Despesa("despesa", 100f, conta2);
        despesa3.setValor(100f);
        assertEquals(-40f, conta2.getSaldo(), 0);
    }
}
