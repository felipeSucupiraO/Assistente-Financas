package com.felipesucupira.mediator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;

public class RelacaoTransacoesContasHandlerTest {

    @Test
    public void transacaoAdicionadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediator();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1);
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1);
        
        usuario.adicionarConta(conta1);
        mediator.transacaoAdicionada(transacao1);
        assertEquals(2100, conta1.getSaldo(), 0);
        
        mediator.transacaoAdicionada(transacao2);
        assertEquals(1000, conta1.getSaldo(), 0);
    }
    
    @Test
    public void contaDaTransacaoModificadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediator();
        Conta conta1 = new Conta("Poupança", 0);
        Conta conta2 = new Conta("Conta corrente", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1);
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta2);

        usuario.adicionarConta(conta1);
        usuario.adicionarConta(conta2);
        mediator.transacaoAdicionada(transacao1);
        mediator.transacaoAdicionada(transacao2);

        mediator.contaDaTransacaoModificada(transacao1, conta2);
        assertEquals(0, conta1.getSaldo(), 0);
        assertEquals(1000, conta2.getSaldo(), 0);
        
        mediator.contaDaTransacaoModificada(transacao2, conta1);
        assertEquals(-1100, conta1.getSaldo(), 0);
        assertEquals(2100, conta2.getSaldo(), 0);
    }
    
    @Test
    public void valorTransacaoModificadoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediator();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1);
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1);
        
        usuario.adicionarConta(conta1);
        mediator.transacaoAdicionada(transacao1);
        mediator.transacaoAdicionada(transacao2);
        mediator.valorTransacaoModificado(transacao1, 3000);
        assertEquals(1900, conta1.getSaldo(), 0);
        
        mediator.valorTransacaoModificado(transacao2, 2000);
        assertEquals(1000, conta1.getSaldo(), 0);
    }
    
    @Test
    public void transacaoDeletadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediator();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1);
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1);

        usuario.adicionarConta(conta1);
        mediator.transacaoAdicionada(transacao1);
        mediator.transacaoAdicionada(transacao2);
        
        mediator.transacaoDeletada(transacao1);
        assertEquals(-1100, conta1.getSaldo(), 0);

        mediator.transacaoDeletada(transacao2);
        assertEquals(0, conta1.getSaldo(), 0);
    }
}
