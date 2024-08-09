package com.felipesucupira;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.felipesucupira.transacoes.Despesa;
import com.felipesucupira.transacoes.Receita;
import com.felipesucupira.transacoes.Transacao;

public class Relatorio {
    private float ganhos;
    private float gastos;
    private float balanco;
    
    // -------------------------------------------------------------------------
    
    public Relatorio(float ganhos, float gastos, float balanco) {
        this.ganhos = ganhos;
        this.gastos = gastos;
        this.balanco = balanco;
    }

    // -------------------------------------------------------------------------

    public float getBalanco() {
        return balanco;
    }

    public void setBalanco(float balanco) {
        this.balanco = balanco;
    }

    public float getGanhos() {
        return ganhos;
    }

    public void setGanhos(float ganhos) {
        this.ganhos = ganhos;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    // -------------------------------------------------------------------------

    public static List<Transacao> ordenarTransacaoPorData(List<Transacao> listaTransacoes) {
        Collections.sort(listaTransacoes, new Comparator<Transacao>() {
            @Override
            public int compare(Transacao transacao1, Transacao transacao2) {
                return transacao1.getData().compareTo(transacao2.getData());
            }
        });

        return listaTransacoes;
    }


    public static Relatorio relatorioDoMes(int ano, int mes, List<Transacao> listaTransacoes) {
        float gastos = 0;
        float ganhos = 0;
        float balancoTotal = 0;

        for (Transacao transacao : listaTransacoes) {
            LocalDate dataTransacao = transacao.getData();
            if (dataTransacao.getYear() == ano && dataTransacao.getMonthValue() == mes) {
                if (transacao instanceof Receita) {
                    ganhos += transacao.getValor();
                } else if (transacao instanceof Despesa) {
                    gastos += transacao.getValor();
                }
            }
        }

        balancoTotal = ganhos - gastos;
        return new Relatorio(ganhos, gastos, balancoTotal);
    }

    public static Relatorio relatorioDoAno(int ano, List<Transacao> listaTransacoes) {
        float gastos = 0;
        float ganhos = 0;
        float balancoTotal = 0;

        for (Transacao transacao : listaTransacoes) {
            LocalDate dataTransacao = transacao.getData();
            if (dataTransacao.getYear() == ano) {
                if (transacao instanceof Receita) {
                    ganhos += transacao.getValor();
                } else if (transacao instanceof Despesa) {
                    gastos += transacao.getValor();
                }
            }
        }

        balancoTotal = ganhos - gastos;
        return new Relatorio(ganhos, gastos, balancoTotal);
    }
}
