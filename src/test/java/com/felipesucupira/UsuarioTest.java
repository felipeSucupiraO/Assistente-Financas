package com.felipesucupira;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Transacao;

public class UsuarioTest {    
    @Test
    public void aumentarBalancoTotalTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.aumentarBalancoTotal(1000);
        assertEquals(1000, usuario.getBalancoTotal(), 0);
    }

    @Test
    public void diminuirBalancoTotalTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.aumentarBalancoTotal(0);
    }
    
    @Test
    public void getSenhaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        assertEquals("@123456", usuario.getSenha());
    }
    
    @Test
    public void setSenhaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.setSenha("#123456");
        assertEquals("#123456", usuario.getSenha());
    }
    
    @Test
    public void adicionarContaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        Conta conta1 = new Conta("Poupança", 0);
        usuario.adicionarConta(conta1);
        assertEquals(true, usuario.getListaContas().contains(conta1));
    }
    
    @Test
    public void adicionarTransacaoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao = new Despesa("Aluguel", 1100, conta1);
        usuario.adicionarConta(conta1);
        
        usuario.adicionarTransacao(transacao);
        assertEquals(transacao, usuario.getListaTransacoes().get(0));
    }
    
    @Test
    public void deletarContaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        Conta conta1 = new Conta("Poupança", 0);
        usuario.adicionarConta(conta1);
        usuario.deletarConta(conta1);
        assertEquals(false, usuario.getListaContas().contains(conta1));
        
        conta1 = usuario.getListaContas().get(0);
        usuario.deletarConta(0);
        assertEquals(false, usuario.getListaContas().contains(conta1));
    }
    
    @Test
    public void deletarTransacaoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        Conta conta1 = new Conta("Poupança", 0);
        Transacao transacao = new Despesa("Aluguel", 1100, conta1);
        usuario.adicionarConta(conta1);
        
        usuario.adicionarTransacao(transacao);
        usuario.deletarTransacao(transacao);
        assertEquals(false, usuario.getListaTransacoes().contains(transacao));
    
        usuario.adicionarTransacao(transacao);
        usuario.deletarTransacao(0);
        assertEquals(false, usuario.getListaTransacoes().contains(transacao));
    }
}
