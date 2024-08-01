package com.felipesucupira.databaseHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.transacoes.Transferencia;

public class DatabaseHandler {
    private static final Path CAMINHO_DATABASE = Paths.get("src/main/resources/database");

    // -------------------------------------------------------------------------
    
    public static Path getCAMINHO_DATABASE() {
        return CAMINHO_DATABASE;
    }
    
    // -------------------------------------------------------------------------
    
    public static Usuario lerPastaUsuario(int id) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + id);

        Usuario usuario = lerArquivoUsuario(caminhoPastaUsuario.resolve("usuario.csv"));

        List<Conta> listaContas = lerArquivoContas(caminhoPastaUsuario.resolve("contas.csv"));
        usuario.adicionarListaContas(listaContas);

        List<Transacao> listaTransacoes = lerArquivoDespesas(caminhoPastaUsuario.resolve("despesas.csv"), listaContas);
        usuario.adicionarListaTransacoes(listaTransacoes);
        
        listaTransacoes = lerArquivoReceitas(caminhoPastaUsuario.resolve("receitas.csv"), listaContas);
        usuario.adicionarListaTransacoes(listaTransacoes);

        listaTransacoes = lerArquivoTransferencias(caminhoPastaUsuario.resolve("transferencias.csv"), listaContas);
        usuario.adicionarListaTransacoes(listaTransacoes);

        return usuario;
    }

    public static Usuario lerArquivoUsuario(Path caminhoArquivo) {
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

        if (linhas.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return null;
        }
        
        String[] linha = linhas.get(0).split(",");
        return new Usuario(Integer.parseInt(linha[0]), linha[1], linha[2]);
    }
    
    public static List<Conta> lerArquivoContas(Path caminhoArquivo) {
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    
        if (linhas.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return null;
        }

        List<Conta> listaContas = new ArrayList<Conta>();
        Conta contaAtual;
        String[] arrayLinha;
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");
            contaAtual = new Conta(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]));
            listaContas.add(contaAtual);
        }

        return listaContas;
    }
    
    public static List<Transacao> lerArquivoDespesas(Path caminhoArquivo, List<Conta> listaContas) {
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    
        if (linhas.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return null;
        }
    
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        Conta contaAssociada = null;
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");

            for (Conta conta : listaContas) {
                if (conta.getId() == Integer.parseInt(arrayLinha[3])) {
                    contaAssociada = conta;
                }
            }

            transacaoAtual = new Despesa(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), contaAssociada, arrayLinha[4]);
            listaTransacoes.add(transacaoAtual);
        }
    
        return listaTransacoes;
    }
    
    public static List<Transacao> lerArquivoReceitas(Path caminhoArquivo, List<Conta> listaContas) {
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    
        if (linhas.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return null;
        }
    
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        Conta contaAssociada = null;
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");

            for (Conta conta : listaContas) {
                if (conta.getId() == Integer.parseInt(arrayLinha[3])) {
                    contaAssociada = conta;
                }
            }

            transacaoAtual = new Receita(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), contaAssociada, arrayLinha[4]);
            listaTransacoes.add(transacaoAtual);
        }
    
        return listaTransacoes;
    }
    
    public static List<Transacao> lerArquivoTransferencias(Path caminhoArquivo, List<Conta> listaContas) {
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    
        if (linhas.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return null;
        }
    
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        Conta contaAssociada = null;
        Conta contaDestino = null;
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");

            for (Conta conta : listaContas) {
                if (conta.getId() == Integer.parseInt(arrayLinha[3])) {
                    contaAssociada = conta;
                }
                if (conta.getId() == Integer.parseInt(arrayLinha[4])) {
                    contaDestino = conta;
                }
            }

            transacaoAtual = new Transferencia(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), contaAssociada, contaDestino, arrayLinha[5]);
            listaTransacoes.add(transacaoAtual);
        }
    
        return listaTransacoes;
    }

    public static void escreverNovaTransacao(Usuario usuario, Transacao transacao) {
        Path caminhoArquivo = null;
        String linha = null;

        if (transacao instanceof Despesa) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "despesas.csv");
            linha = Integer.toString(transacao.getId()) + "," + transacao.getNome() + "," + Float.toString(transacao.getValor()) + "," + Integer.toString(transacao.getContaAssociada().getId()) + "," + transacao.getData().toString();
        } else if (transacao instanceof Receita) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "receitas.csv");
            linha = Integer.toString(transacao.getId()) + "," + transacao.getNome() + "," + Float.toString(transacao.getValor()) + "," + Integer.toString(transacao.getContaAssociada().getId()) + "," + transacao.getData().toString();
        } else if (transacao instanceof Transferencia) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "transferencias.csv");
            Transferencia transferencia = (Transferencia) transacao;
            linha = Integer.toString(transferencia.getId()) + "," + transferencia.getNome() + "," + Float.toString(transferencia.getValor()) + "," + Integer.toString(transferencia.getContaAssociada().getId()) + "," + Integer.toString(transferencia.getContaDestino().getId()) + "," + transferencia.getData().toString();
        }

        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivo)) {
            buffer.write(linha);
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
