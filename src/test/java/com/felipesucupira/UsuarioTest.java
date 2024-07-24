package com.felipesucupira;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Transacao;

public class UsuarioTest {
    Conta conta1 = new Conta("Poupança", 0);
    Conta conta2 = new Conta("Corrente", 0);
    Transacao transacao;

    
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
        usuario.adicionarConta(conta1);
        assertEquals(conta1, usuario.getListaContas().get(0));
    }
    
    @Test
    public void adicionarTransacaoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.adicionarConta(conta1);
        transacao = new Despesa("Aluguel", 1000, conta1);
        usuario.adicionarTransacao(transacao);
        assertEquals(transacao, usuario.getListaTransacoes().get(0));
    }
    
    @Test
    public void deletarContaTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.adicionarConta(conta1);
        usuario.deletarConta(conta1);
        assertEquals(false, usuario.getListaContas().contains(conta1));
        
        usuario.adicionarConta(conta1);
        usuario.deletarConta(0);
        assertEquals(false, usuario.getListaContas().contains(conta1));
    }
    
    @Test
    public void deletarTransacaoTest() {
        Usuario usuario = new Usuario("Savio", "@123456");
        usuario.adicionarConta(conta1);
        
        transacao = new Despesa("Aluguel", 1000, conta1);
        usuario.adicionarTransacao(transacao);
        usuario.deletarTransacao(transacao);
        assertEquals(false, usuario.getListaTransacoes().contains(transacao));
    
        usuario.adicionarTransacao(transacao);
        usuario.deletarTransacao(0);
        assertEquals(false, usuario.getListaTransacoes().contains(transacao));
    }
}