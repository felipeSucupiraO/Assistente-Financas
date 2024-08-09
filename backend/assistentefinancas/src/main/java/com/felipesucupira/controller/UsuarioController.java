package com.felipesucupira.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.felipesucupira.Conta;
import com.felipesucupira.Relatorio;
import com.felipesucupira.Usuario;
import com.felipesucupira.DTO.ContaDTO;
import com.felipesucupira.DTO.DTOConverter;
import com.felipesucupira.DTO.DespesaDTO;
import com.felipesucupira.DTO.ReceitaDTO;
import com.felipesucupira.DTO.TransferenciaDTO;
import com.felipesucupira.DTO.UsuarioDTO;
import com.felipesucupira.DTO.TransacaoDTO;
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
        return DTOConverter.converterUsuario(usuario);
    }

    @GetMapping("/getListaTransacoesDoUsuario/{idUsuario}")
    public List<TransacaoDTO> getListaTransacoes(@PathVariable int idUsuario) {
        List<Transacao> listaTransacoes = DatabaseHandler.lerArquivosTransacao(idUsuario);

        listaTransacoes = Relatorio.ordenarTransacaoPorData(listaTransacoes);

        List<TransacaoDTO> listaTransacoesDTO = new ArrayList<TransacaoDTO>();
        for (Transacao transacao : listaTransacoes) {
            listaTransacoesDTO.add(DTOConverter.converterTransacao(transacao));
        }

        return listaTransacoesDTO;
    }

    @GetMapping("/getRelatorioAnoDoUsuario/{ano}/{idUsuario}")
    public Relatorio getRelatorioDoAno(@PathVariable int ano, @PathVariable int idUsuario) {
        List<Transacao> listaTransacoes = DatabaseHandler.lerArquivosTransacao(idUsuario);
        return Relatorio.relatorioDoAno(ano, listaTransacoes);
    }

    @GetMapping("/getRelatorioMesDoUsuario/{ano}/{mes}/{idUsuario}")
    public Relatorio getRelatorioDoMes(@PathVariable int ano, @PathVariable int mes, @PathVariable int idUsuario) {
        List<Transacao> listaTransacoes = DatabaseHandler.lerArquivosTransacao(idUsuario);
        return Relatorio.relatorioDoMes(ano, mes, listaTransacoes);
    }

    @PostMapping("/adicionarUsuario")
    public UsuarioDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = DTOConverter.converterUsuarioDTO(usuarioDTO);
        DatabaseHandler.escreverNovoUsuario(usuario);

        usuario = DatabaseHandler.lerPastaUsuario(usuario.getId());
        return DTOConverter.converterUsuario(usuario);
    }

    @PostMapping("/adicionarContaAoUsuario/{idUsuario}")
    public UsuarioDTO adicionarConta(@PathVariable int idUsuario, @RequestBody ContaDTO contaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Conta conta = DTOConverter.converterContaDTO(contaDTO);

        usuario.adicionarConta(conta);
        DatabaseHandler.escreverNovaConta(usuario, conta);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return DTOConverter.converterUsuario(usuario);
    }

    @PostMapping("/adicionarReceitaAoUsuario/{idUsuario}")
    public UsuarioDTO adicionarReceita(@PathVariable int idUsuario, @RequestBody ReceitaDTO receitaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Receita receita = (Receita) DTOConverter.converterTransacaoDTO(receitaDTO);

        for (Conta conta : usuario.getListaContas()) {
            if (conta.getId() == receitaDTO.getIdContaAssociada()) {
                receita.setContaAssociada(conta);
            }
        }
        if (receita.getContaAssociada() == null) {
            throw new IllegalArgumentException("A conta com o id associado à transação não existe no usuário.");
        }
        
        usuario.adicionarTransacao(receita);
        DatabaseHandler.escreverNovaTransacao(usuario, receita);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return DTOConverter.converterUsuario(usuario);
    }

    @PostMapping("/adicionarDespesaAoUsuario/{idUsuario}")
    public UsuarioDTO adicionarDespesa(@PathVariable int idUsuario, @RequestBody DespesaDTO despesaDTO) {
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Transacao transacao = DTOConverter.converterTransacaoDTO(despesaDTO);
        Despesa despesa = (Despesa) transacao;

        for (Conta conta : usuario.getListaContas()) {
            if (conta.getId() == despesaDTO.getIdContaAssociada()) {
                despesa.setContaAssociada(conta);
            }
        }
        if (despesa.getContaAssociada() == null) {
            throw new IllegalArgumentException("A conta com o id associado à transação não existe no usuário.");
        }
        
        usuario.adicionarTransacao(despesa);
        DatabaseHandler.escreverNovaTransacao(usuario, despesa);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return DTOConverter.converterUsuario(usuario);
    }

    @PostMapping("/adicionarTransferenciaAoUsuario/{idUsuario}")
    public UsuarioDTO adicionarTransferencia(@PathVariable int idUsuario, @RequestBody TransferenciaDTO transferenciaDTO) {
        System.out.println("Received TransferenciaDTO: " + transferenciaDTO);
        
        Usuario usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        Transferencia transacao = (Transferencia)DTOConverter.converterTransacaoDTO(transferenciaDTO);

        for (Conta conta : usuario.getListaContas()) {
            if (conta.getId() == transferenciaDTO.getIdContaAssociada()) {
                transacao.setContaAssociada(conta);
            }
            if (conta.getId() == transferenciaDTO.getIdContaDestino()) {
                transacao.setContaDestino(conta);
            }
        }
        if (transacao.getContaAssociada() == null) {
            throw new IllegalArgumentException("A conta com o id associado à transação não existe no usuário.");
        } else if (transacao.getContaDestino() == null) {
            throw new IllegalArgumentException("A conta com o id destino da transação não existe no usuário.");
        }
        
        usuario.adicionarTransacao(transacao);
        DatabaseHandler.escreverNovaTransacao(usuario, transacao);
        usuario = DatabaseHandler.lerPastaUsuario(idUsuario);
        return DTOConverter.converterUsuario(usuario);
    }

    @PostMapping("/deletarUsuario/{idUsuario}")
    public void deletarUsuario(@PathVariable int idUsuario) {
        DatabaseHandler.deletarUsuario(idUsuario);
    }

    @PostMapping("/deletarContaDoUsuario/{idConta}/{idUsuario}")
    public UsuarioDTO deletarConta(@PathVariable int idConta, @PathVariable int idUsuario) {
        DatabaseHandler.deletarConta(idUsuario, idConta);
        return DTOConverter.converterUsuario(DatabaseHandler.lerPastaUsuario(idUsuario));
    }

    @PostMapping("/deletarReceitaDoUsuario/{idReceita}/{idUsuario}")
    public UsuarioDTO deletarReceita(@PathVariable int idReceita, @PathVariable int idUsuario) {
        DatabaseHandler.deletarReceita(idUsuario, idReceita);
        return DTOConverter.converterUsuario(DatabaseHandler.lerPastaUsuario(idUsuario));
    }
}
