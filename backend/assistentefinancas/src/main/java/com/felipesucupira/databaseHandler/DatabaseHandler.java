package com.felipesucupira.databaseHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import com.felipesucupira.Conta;
import com.felipesucupira.Usuario;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;
import com.felipesucupira.transacoes.Transferencia;

public class DatabaseHandler {
    private static final Path CAMINHO_DATABASE = Paths.get("assistentefinancas/src/main/resources/database");

    // -------------------------------------------------------------------------
    
    public static Path getCAMINHO_DATABASE() {
        return CAMINHO_DATABASE;
    }
    
    // -------------------------------------------------------------------------

    public static Usuario lerPastaUsuario(int id) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + id);

        Usuario usuario = lerArquivoUsuario(caminhoPastaUsuario.resolve("usuario.csv"));

        List<Conta> listaContas = lerArquivoContas(id);
        usuario.adicionarListaContas(listaContas);

        List<Transacao> listaTransacoes = lerArquivoDespesas(id);
        for (Transacao transacao : listaTransacoes) {
            for (Conta conta : listaContas) {
                if (conta.getId() == transacao.getContaAssociada().getId()) {
                    transacao.setContaAssociada(conta);
                }
            }
        }
        usuario.adicionarListaTransacoes(listaTransacoes);
        
        listaTransacoes = lerArquivoReceitas(id);
        for (Transacao transacao : listaTransacoes) {
            for (Conta conta : listaContas) {
                if (conta.getId() == transacao.getContaAssociada().getId()) {
                    transacao.setContaAssociada(conta);
                }
            }
        }
        usuario.adicionarListaTransacoes(listaTransacoes);

        listaTransacoes = lerArquivoTransferencias(id);
        for (Transacao transacao : listaTransacoes) {
            Transferencia transferencia = (Transferencia) transacao;
            for (Conta conta : listaContas) {
                if (conta.getId() == transferencia.getContaAssociada().getId()) {
                    transferencia.setContaAssociada(conta);
                }
                if (conta.getId() == transferencia.getContaDestino().getId()) {
                    transferencia.setContaDestino(conta);
                }
            }
        }
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
    
    public static List<Conta> lerArquivoContas(int idUsuario) {
        Path caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario + "/contas.csv");
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
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
    
    public static List<Transacao> lerArquivoDespesas(int idUsuario) {
        Path caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario + "/despesas.csv");
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
        
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        if (linhas.isEmpty() || linhas.get(0).isEmpty()) {
            return listaTransacoes;
        }
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");
            transacaoAtual = new Despesa(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), new Conta(Integer.parseInt(arrayLinha[3]),"temp", 0), arrayLinha[4]);
            listaTransacoes.add(transacaoAtual);
        }
        
        return listaTransacoes;
    }
    
    public static List<Transacao> lerArquivoReceitas(int idUsuario) {
        Path caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario + "/receitas.csv");
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
        
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        if (linhas.isEmpty() || linhas.get(0).isEmpty()) {
            return listaTransacoes;
        }
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");
            transacaoAtual = new Receita(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), new Conta(Integer.parseInt(arrayLinha[3]),"temp", 0), arrayLinha[4]);
            listaTransacoes.add(transacaoAtual);
        }
    
        return listaTransacoes;
    }
    
    public static List<Transacao> lerArquivoTransferencias(int idUsuario) {
        Path caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario + "/transferencias.csv");
        List<String> linhas;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Transacao transacaoAtual;
        String[] arrayLinha;
        if (linhas.isEmpty() || linhas.get(0).isEmpty()) {
            return listaTransacoes;
        }
        for (String stringLinha : linhas) {
            arrayLinha = stringLinha.split(",");
            transacaoAtual = new Transferencia(Integer.parseInt(arrayLinha[0]), arrayLinha[1], Float.parseFloat(arrayLinha[2]), new Conta(Integer.parseInt(arrayLinha[3]),"temp", 0), new Conta(Integer.parseInt(arrayLinha[4]),"temp", 0), arrayLinha[5]);
            listaTransacoes.add(transacaoAtual);
        }
    
        return listaTransacoes;
    }

    public static List<Transacao> lerArquivosTransacao(int idUsuario) {
        List<Transacao> listaTransacoes = new ArrayList<Transacao>();
        for (Transacao transacao : lerArquivoDespesas(idUsuario)) {
            listaTransacoes.add(transacao);
        }
        for (Transacao transacao : lerArquivoReceitas(idUsuario)) {
            listaTransacoes.add(transacao);
        }
        for (Transacao transacao : lerArquivoTransferencias(idUsuario)) {
            listaTransacoes.add(transacao);
        }

        return listaTransacoes;
    }

    private static Queue<Integer> lerIdsDeletados(Path caminhoArquivo) {
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Queue<Integer> idsDeletados = new LinkedList<Integer>();
        if (linhas.isEmpty() || linhas.get(0).isEmpty()) {
            return idsDeletados;
        }
        String[] numeros = linhas.get(0).split(",");
        for (String numero : numeros) {
            idsDeletados.add(Integer.parseInt(numero));
        }
        return idsDeletados;
    }

    public static void escreverNovoUsuario(Usuario usuario) {
        usuario.setId(pegarIdParaUsuarioNovo());
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId());
        Path caminhoArquivoUsuario = caminhoPastaUsuario.resolve("usuario.csv");
        
        try {
            Files.createDirectory(caminhoPastaUsuario);
            Files.createFile(caminhoArquivoUsuario);
            Files.createFile(caminhoPastaUsuario.resolve("contas.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("despesas.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("receitas.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("transferencias.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("ids-conta-deletados.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("ultimo-id-conta-usado.csv"));
            Files.createFile(caminhoPastaUsuario.resolve("ultimo-id-transacao-usado.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivoUsuario)) {
            buffer.write(Integer.toString(usuario.getId()) + "," + usuario.getNome() + "," + usuario.getSenha());
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("ultimo-id-conta-usado.csv"))) {
            buffer.write(Integer.toString(0));
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("ultimo-id-transacao-usado.csv"))) {
            buffer.write(Integer.toString(0));
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreverNovaConta(Usuario usuario, Conta conta) {
        conta.setId(pegarIdParaContaNova(usuario.getId()));
        Path caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/contas.csv");
        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            buffer.write(Integer.toString(conta.getId()) + "," + conta.getNome() + "," + Float.toString(conta.getSaldo()));
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Queue<Integer> idsDeletados = lerIdsDeletados(CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/ids-conta-deletados.csv"));
        for (Integer numero : idsDeletados) {
            if (numero == conta.getId()) {
                removerIdDeletado(CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/ids-conta-deletados.csv"), numero);
            }
        }
    }

    public static void escreverNovaTransacao(Usuario usuario, Transacao transacao) {
        transacao.setId(pegarIdParaTransacaoNova(usuario.getId()));
        Path caminhoArquivo = null;
        String linha = null;

        if (transacao instanceof Despesa) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/despesas.csv");
            linha = Integer.toString(transacao.getId()) + "," + transacao.getNome() + "," + Float.toString(transacao.getValor()) + "," + Integer.toString(transacao.getContaAssociada().getId()) + "," + transacao.getData().toString();
        } else if (transacao instanceof Receita) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/receitas.csv");
            linha = Integer.toString(transacao.getId()) + "," + transacao.getNome() + "," + Float.toString(transacao.getValor()) + "," + Integer.toString(transacao.getContaAssociada().getId()) + "," + transacao.getData().toString();
        } else if (transacao instanceof Transferencia) {
            caminhoArquivo = CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/transferencias.csv");
            Transferencia transferencia = (Transferencia) transacao;
            linha = Integer.toString(transferencia.getId()) + "," + transferencia.getNome() + "," + Float.toString(transferencia.getValor()) + "," + Integer.toString(transferencia.getContaAssociada().getId()) + "," + Integer.toString(transferencia.getContaDestino().getId()) + "," + transferencia.getData().toString();
        }

        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            buffer.write(linha);
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Queue<Integer> idsDeletados = lerIdsDeletados(CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/ids-transacao-deletados.csv"));
        for (Integer numero : idsDeletados) {
            if (numero == transacao.getId()) {
                removerIdDeletado(CAMINHO_DATABASE.resolve("usuarios/" + usuario.getId() + "/ids-transacao-deletados.csv"), numero);
            }
        }
    }

    public static void deletarUsuario(int idUsuario) {
        Path caminhoPasta = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        try {
            Files.walk(caminhoPasta).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            adicionarIdDeletado(CAMINHO_DATABASE.resolve("ids-deletados.csv"), idUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletarConta(int idUsuario, int idConta) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        try {
            List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("contas.csv"));
            List<String> linhasAtualizado = linhas.stream().filter(linha -> !linha.startsWith(idConta + ",")).collect(Collectors.toList());
            
            try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("contas.csv"))) {
                for (String linha : linhasAtualizado) {
                    buffer.write(linha);
                    buffer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (Transacao transacao : lerArquivoReceitas(idUsuario)) {
            if (transacao.getContaAssociada().getId() == idConta) {
                deletarReceita(idUsuario, transacao.getId());
            }
        }
        for (Transacao transacao : lerArquivoDespesas(idUsuario)) {
            if (transacao.getContaAssociada().getId() == idConta) {
                deletarDespesa(idUsuario, transacao.getId());
            }
        }
        for (Transacao transacao : lerArquivoTransferencias(idUsuario)) {
            Transferencia transferencia = (Transferencia) transacao;
            if (transferencia.getContaAssociada().getId() == idConta || transferencia.getContaDestino().getId() == idConta) {
                deletarTransferencia(idUsuario, transferencia.getId());
            }
        }


        adicionarIdDeletado(caminhoPastaUsuario.resolve("ids-conta-deletados.csv"), idConta);
    }

    public static void deletarReceita(int idUsuario, int idReceita) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        try {
            List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("receitas.csv"));
            List<String> linhasAtualizado = linhas.stream().filter(linha -> !linha.startsWith(idReceita + ",")).collect(Collectors.toList());

            try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("receitas.csv"))) {
                for (String linha : linhasAtualizado) {
                    buffer.write(linha);
                    buffer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        adicionarIdDeletado(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"), idReceita);
    }

    public static void deletarDespesa(int idUsuario, int idDespesa) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        try {
            List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("receitas.csv"));
            List<String> linhasAtualizado = linhas.stream().filter(linha -> !linha.startsWith(idDespesa + ",")).collect(Collectors.toList());

            try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("receitas.csv"))) {
                for (String linha : linhasAtualizado) {
                    buffer.write(linha);
                    buffer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        adicionarIdDeletado(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"), idDespesa);
    }

    public static void deletarTransferencia(int idUsuario, int idTransferencia) {
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        try {
            List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("receitas.csv"));
            List<String> linhasAtualizado = linhas.stream().filter(linha -> !linha.startsWith(idTransferencia + ",")).collect(Collectors.toList());

            try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("receitas.csv"))) {
                for (String linha : linhasAtualizado) {
                    buffer.write(linha);
                    buffer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        adicionarIdDeletado(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"), idTransferencia);
    }

    private static void adicionarIdDeletado(Path caminhoArquivo, int idDeletadoAdicionado) {
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        String linha;
        if (linhas.isEmpty()) {
            linha = Integer.toString(idDeletadoAdicionado);
        } else {
            linha = linhas.get(0);
            if (!linha.isBlank()) {
                linha += ",";
            }
            linha += Integer.toString(idDeletadoAdicionado);
        }
    
        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivo)) {
            buffer.write(linha);
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void removerIdDeletado(Path caminhoArquivo, int idDeletadoRemovido) {
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String linha = linhas.get(0);
        if (linha.length() == 1) {
            linha = linha.replace(Integer.toString(idDeletadoRemovido), "");
        } else if (linha.charAt(linha.length() - 1) == idDeletadoRemovido) {
            linha = linha.replace("," + Integer.toString(idDeletadoRemovido), "");
        } 
        else {
            linha = linha.replace(Integer.toString(idDeletadoRemovido) + ",", "");
        }

        try (BufferedWriter buffer = Files.newBufferedWriter(caminhoArquivo)) {
            buffer.write(linha);
            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int pegarIdParaUsuarioNovo() {
        int idASerRetornado = 0;
        Queue<Integer> idsDeletados = lerIdsDeletados(CAMINHO_DATABASE.resolve("ids-deletados.csv"));
        if (!idsDeletados.isEmpty()) {
            removerIdDeletado(CAMINHO_DATABASE.resolve("ids-deletados.csv"), idsDeletados.peek());
            return idsDeletados.poll();
        } else {
            try {
                List<String> linhas = Files.readAllLines(CAMINHO_DATABASE.resolve("ultimo-id-usado.csv"));
                idASerRetornado = Integer.parseInt(linhas.get(0)) + 1;

                try (BufferedWriter buffer = Files.newBufferedWriter(CAMINHO_DATABASE.resolve("ultimo-id-usado.csv"))) {
                    buffer.write(Integer.toString(idASerRetornado));
                    buffer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return idASerRetornado;
        }

    }

    private static int pegarIdParaContaNova(int idUsuario) {
        int idASerRetornado = 0;
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        Queue<Integer> idsDeletados = lerIdsDeletados(caminhoPastaUsuario.resolve("ids-conta-deletados.csv"));
        if (!idsDeletados.isEmpty()) {
            removerIdDeletado(caminhoPastaUsuario.resolve("ids-conta-deletados.csv"), idsDeletados.peek());
            return idsDeletados.poll();
        } else {
            try {
                List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("ultimo-id-conta-usado.csv"));
                idASerRetornado = Integer.parseInt(linhas.get(0)) + 1;

                try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("ultimo-id-conta-usado.csv"))) {
                    buffer.write(Integer.toString(idASerRetornado));
                    buffer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return idASerRetornado;
        }
    }

    private static int pegarIdParaTransacaoNova(int idUsuario) {
        int idASerRetornado = 0;
        Path caminhoPastaUsuario = CAMINHO_DATABASE.resolve("usuarios/" + idUsuario);
        Queue<Integer> idsDeletados = lerIdsDeletados(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"));
        if (!idsDeletados.isEmpty()) {
            removerIdDeletado(caminhoPastaUsuario.resolve("ids-transacao-deletados.csv"), idsDeletados.peek());
            return idsDeletados.poll();
        } else {
            try {
                List<String> linhas = Files.readAllLines(caminhoPastaUsuario.resolve("ultimo-id-transacao-usado.csv"));
                idASerRetornado = Integer.parseInt(linhas.get(0)) + 1;

                try (BufferedWriter buffer = Files.newBufferedWriter(caminhoPastaUsuario.resolve("ultimo-id-transacao-usado.csv"))) {
                    buffer.write(Integer.toString(idASerRetornado));
                    buffer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return idASerRetornado;
        }
    }
}
