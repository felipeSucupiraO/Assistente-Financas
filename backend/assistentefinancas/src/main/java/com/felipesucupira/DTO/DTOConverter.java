package com.felipesucupira.DTO;

import java.util.ArrayList;
import java.util.List;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.transacoes.Transferencia;

public class DTOConverter {
    public static UsuarioDTO converterUsuario(Usuario usuario) {
        List<ContaDTO> listaContasDTO = new ArrayList<ContaDTO>();
        for (Conta conta : usuario.getListaContas()) {
            listaContasDTO.add(converterConta(conta));
        }

        List<TransacaoDTO> listaTransacoesDTO = new ArrayList<TransacaoDTO>();
        for (Transacao transacao : usuario.getListaTransacoes()) {
            listaTransacoesDTO.add(converterTransacao(transacao));
        }

        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getSenha(), listaContasDTO, listaTransacoesDTO, usuario.getBalancoTotal());
    }

    public static Usuario converterUsuarioDTO(UsuarioDTO usuarioDTO) {
        // the id 0 is a temporary id that will be changed in the UsuarioController
        return new Usuario(0, usuarioDTO.getNome(), usuarioDTO.getSenha());
    }

    public static ContaDTO converterConta(Conta conta) {
        return new ContaDTO(conta.getId(), conta.getNome(), conta.getSaldo());
    }

    public static Conta converterContaDTO(ContaDTO contaDTO) {
        return new Conta(contaDTO.getNome(), contaDTO.getSaldo());
    }

    public static TransacaoDTO converterTransacao(Transacao transacao) {
        if (transacao instanceof Despesa) {
            return new DespesaDTO(transacao.getId(), transacao.getNome(), transacao.getValor(), transacao.getContaAssociada().getId(), transacao.getData().toString());
        } else if (transacao instanceof Receita) {
            return new ReceitaDTO(transacao.getId(), transacao.getNome(), transacao.getValor(), transacao.getContaAssociada().getId(), transacao.getData().toString());
        } else {
            Transferencia transferencia = (Transferencia) transacao;
            return new TransferenciaDTO(transferencia.getId(), transferencia.getNome(), transferencia.getValor(), transferencia.getContaAssociada().getId(), transferencia.getContaDestino().getId(), transferencia.getData().toString());
        }
    }
    
    public static Transacao converterTransacaoDTO(TransacaoDTO transacaoDTO) {
        if (transacaoDTO instanceof DespesaDTO) {
            return new Despesa(transacaoDTO.getNome(), transacaoDTO.getValor(), null, transacaoDTO.getData().toString());
        } else if (transacaoDTO instanceof ReceitaDTO) {
            return new Receita(transacaoDTO.getNome(), transacaoDTO.getValor(), null, transacaoDTO.getData().toString());
        } else {
            TransferenciaDTO transferenciaDTO = (TransferenciaDTO) transacaoDTO;
            return new Transferencia(transferenciaDTO.getNome(), transferenciaDTO.getValor(), null, null, transferenciaDTO.getData().toString());
        } 
    }
}
