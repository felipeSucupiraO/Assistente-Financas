package com.felipesucupira.transacoes;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.felipesucupira.Conta;

public class ReceitaTest {
    private Conta conta1 = new Conta("Poupança", 0);
    private Conta conta2 = new Conta("Carteira", 10);

    @Test
    public void receitaTest() {
        Receita receita = new Receita("receita", 10f, conta1);
        assertEquals(10f, conta1.getSaldo(), 0);
    }

    @Test
    public void setContaAssociadaTest() {
        Receita receita = new Receita("receita", 10f, conta1);
        assertEquals(conta1, receita.getContaAssociada());
        receita.setContaAssociada(conta2);
        assertEquals(conta2, receita.getContaAssociada());
    }

    @Test
    public void mudarContaAssociadaTest() {
        Receita receita = new Receita("receita", 10f, conta1);
        receita.mudarContaAssociada(conta2);
        assertEquals(0, conta1.getSaldo(), 0);
        assertEquals(20f, conta2.getSaldo(), 0);
    }
    
    @Test
    public void setValorTest() {
        Receita receita = new Receita("receita", 10f, conta1);
        receita.setValor(50);
        assertEquals(50, conta1.getSaldo(), 0);
        
        Receita receita2 = new Receita("receita", 50f, conta2);
        receita2.setValor(0);
        assertEquals(10, conta2.getSaldo(), 0);

        Receita receita3 = new Receita("receita", 100f, conta2);
        receita3.setValor(100f);
        assertEquals(110f, conta2.getSaldo(), 0);
    }
}
