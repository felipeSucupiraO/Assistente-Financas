package com.felipesucupira.transacoes;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.mediator.RelacaoTransacoesContasHandler;

public class TransacaoTest {
    @Test
    public void setValorTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        usuario.adicionarConta(conta1);
        
        Transacao receita = new Receita("receita", 10f, conta1);
        usuario.adicionarTransacao(receita);
        receita.setValor(50);
        assertEquals(50, receita.getValor(), 0);
        
        Transacao despesa = new Despesa("receita", 50f, conta1);
        usuario.adicionarTransacao(despesa);
        despesa.setValor(0);
        assertEquals(0, despesa.getValor(), 0);
    }
    
    @Test
    public void mudarContaAssociadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        Conta conta2 = new Conta("Carteira", 10);
        usuario.adicionarConta(conta1);
        usuario.adicionarConta(conta2);
        Transacao receita = new Receita("receita", 10f, conta1);
        usuario.adicionarTransacao(receita);

        receita.mudarContaAssociada(conta2);
        assertEquals(conta2, receita.getContaAssociada());
    }
}
