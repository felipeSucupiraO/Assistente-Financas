package com.felipesucupira.controller;

import javax.xml.crypto.Data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.DTO.DTOConverter;
import com.felipesucupira.DTO.DespesaDTO;
import com.felipesucupira.DTO.ReceitaDTO;
import com.felipesucupira.DTO.TransacaoDTO;
import com.felipesucupira.DTO.TransferenciaDTO;
import com.felipesucupira.DTO.UsuarioDTO;
import com.felipesucupira.databaseHandler.DatabaseHandler;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.transacoes.Transferencia;

@RestController
public class UsuarioController {
    @GetMapping("/getUsuarioById/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable int id) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(id);

        UsuarioDTO usuarioDTO = DTOConverter.converterUsuario(usuario);

        return usuarioDTO;
    }

    @PostMapping("/adicionarReceitaAoUsuario/{idUsuario}")
    public Usuario adicionarReceita(@PathVariable int idUsuario, @RequestBody ReceitaDTO receitaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Transacao transacao = DTOConverter.converterTransacaoDTO(receitaDTO);

        usuario.adicionarTransacao(transacao);
        DatabaseHandler.escreverNovaTransacao(usuario, transacao);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return usuario;
    }

    @PostMapping("/adicionarDespesaAoUsuario/{idUsuario}")
    public Usuario adicionarDespesa(@PathVariable int idUsuario, @RequestBody DespesaDTO despesaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Transacao transacao = DTOConverter.converterTransacaoDTO(despesaDTO);
        for (Conta conta : usuario.getListaContas()) {
            if (conta.getId() == transacao.getContaAssociada().getId()) {
                transacao.mudarContaAssociada(conta);
            }
        }

        System.out.println("Adicionando transacao pelo controller");
        usuario.adicionarTransacao(transacao);
        DatabaseHandler.escreverNovaTransacao(usuario, transacao);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return usuario;
    }

    @PostMapping("/adicionarTransferenciaAoUsuario/{idUsuario}")
    public Usuario adicionarTransferencia(@PathVariable int idUsuario, @RequestBody TransferenciaDTO transferenciaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Transacao transacao = DTOConverter.converterTransacaoDTO(transferenciaDTO);
        
        usuario.adicionarTransacao(transacao);
        DatabaseHandler.escreverNovaTransacao(usuario, transacao);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return usuario;
    }
}
