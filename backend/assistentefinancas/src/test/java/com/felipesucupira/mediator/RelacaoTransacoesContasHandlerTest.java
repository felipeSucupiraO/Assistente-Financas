package com.felipesucupira.mediator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;

public class RelacaoTransacoesContasHandlerTest {
    @Test
    public void notifyContaCriadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        
        mediator.notifyContaCriada(new Conta("Banco X", 1000));
        assertEquals(1000, usuario.getBalancoTotal(), 0);
    }
    
    @Test
    public void notifySaldoContaAumentadoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        usuario.adicionarConta(new Conta("Banco X", 1000));
        
        mediator.notifySaldoContaAumentado(usuario.getListaContas().get(4), 500);
        assertEquals(1500, usuario.getBalancoTotal(), 0);
    }
    
    @Test
    public void notifySaldoContaDiminuidoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        usuario.adicionarConta(new Conta("Banco X", 1000));
        
        mediator.notifySaldoContaDiminuido(usuario.getListaContas().get(4), 1000);
        assertEquals(0, usuario.getBalancoTotal(), 0);
    }
    
    @Test
    public void notifyContaDeletadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        usuario.adicionarConta(new Conta("Banco X", 1000));
            
        mediator.notifyContaDeletada(usuario.getListaContas().get(4));
        assertEquals(0, usuario.getBalancoTotal(), 0);
    }

    @Test
    public void notifyTransacaoAdicionadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1, LocalDate.now().toString());
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1, LocalDate.now().toString());
        
        usuario.adicionarConta(conta1);
        mediator.notifyTransacaoAdicionada(transacao1);
        assertEquals(2100, conta1.getSaldo(), 0);
        
        mediator.notifyTransacaoAdicionada(transacao2);
        assertEquals(1000, conta1.getSaldo(), 0);
    }
    
    @Test
    public void notifyContaDaTransacaoModificadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        Conta conta2 = new Conta("Conta corrente", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1, LocalDate.now().toString());
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta2, LocalDate.now().toString());

        usuario.adicionarConta(conta1);
        usuario.adicionarConta(conta2);
        mediator.notifyTransacaoAdicionada(transacao1);
        mediator.notifyTransacaoAdicionada(transacao2);

        mediator.notifyContaDaTransacaoModificada(transacao1, conta2);
        assertEquals(0, conta1.getSaldo(), 0);
        assertEquals(1000, conta2.getSaldo(), 0);
        
        mediator.notifyContaDaTransacaoModificada(transacao2, conta1);
        assertEquals(-1100, conta1.getSaldo(), 0);
        assertEquals(2100, conta2.getSaldo(), 0);
    }
    
    @Test
    public void notifyValorTransacaoModificadoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1, LocalDate.now().toString());
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1, LocalDate.now().toString());
        
        usuario.adicionarConta(conta1);
        mediator.notifyTransacaoAdicionada(transacao1);
        mediator.notifyTransacaoAdicionada(transacao2);
        mediator.notifyValorTransacaoModificado(transacao1, 3000);
        assertEquals(1900, conta1.getSaldo(), 0);
        
        mediator.notifyValorTransacaoModificado(transacao2, 2000);
        assertEquals(1000, conta1.getSaldo(), 0);
    }
    
    @Test
    public void notifyTransacaoDeletadaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        RelacaoTransacoesContasHandler mediator = usuario.getMediatorTransacoesContas();
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao1 = new Receita("Salário", 2100, conta1, LocalDate.now().toString());
        Transacao transacao2 = new Despesa("Aluguel", 1100, conta1, LocalDate.now().toString());

        usuario.adicionarConta(conta1);
        mediator.notifyTransacaoAdicionada(transacao1);
        mediator.notifyTransacaoAdicionada(transacao2);
        
        mediator.notifyTransacaoDeletada(transacao1);
        assertEquals(-1100, conta1.getSaldo(), 0);

        mediator.notifyTransacaoDeletada(transacao2);
        assertEquals(0, conta1.getSaldo(), 0);
    }
}
