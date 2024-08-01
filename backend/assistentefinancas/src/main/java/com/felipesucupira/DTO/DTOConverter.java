package com.felipesucupira.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.transform.TransformingClassLoader;

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

    public static ContaDTO converterConta(Conta conta) {
        return new ContaDTO(conta.getId(), conta.getNome(), conta.getSaldo());
    }

    public static Conta converterContaDTO(ContaDTO contaDTO) {
        return new Conta(contaDTO.getNome(), contaDTO.getSaldo());
    }

    public static TransacaoDTO converterTransacao(Transacao transacao) {
        if (transacao instanceof Despesa) {
            return new DespesaDTO(transacao.getId(), transacao.getNome(), transacao.getValor(), converterConta(transacao.getContaAssociada()), transacao.getData().toString());
        } else if (transacao instanceof Receita) {
            return new ReceitaDTO(transacao.getId(), transacao.getNome(), transacao.getValor(), converterConta(transacao.getContaAssociada()), transacao.getData().toString());
        } else {
            Transferencia transferencia = (Transferencia) transacao;
            return new TransferenciaDTO(transferencia.getId(), transferencia.getNome(), transferencia.getValor(), converterConta(transferencia.getContaAssociada()), converterConta(transferencia.getContaDestino()), transferencia.getData().toString());
        }
    }
    
    public static Transacao converterTransacaoDTO(TransacaoDTO transacaoDTO) {
        if (transacaoDTO instanceof DespesaDTO) {
            return new Despesa(transacaoDTO.getNome(), transacaoDTO.getValor(), converterContaDTO(transacaoDTO.getContaAssociada()), transacaoDTO.getData().toString());
        } else if (transacaoDTO instanceof ReceitaDTO) {
            return new Despesa(transacaoDTO.getNome(), transacaoDTO.getValor(), converterContaDTO(transacaoDTO.getContaAssociada()), transacaoDTO.getData().toString());
        } else {
            TransferenciaDTO transferenciaDTO = (TransferenciaDTO) transacaoDTO;
            return new Transferencia(transferenciaDTO.getNome(), transferenciaDTO.getValor(), converterContaDTO(transferenciaDTO.getContaAssociada()), converterContaDTO(transferenciaDTO.getContaDestino()), transferenciaDTO.getData().toString());
        } 
    }
}
