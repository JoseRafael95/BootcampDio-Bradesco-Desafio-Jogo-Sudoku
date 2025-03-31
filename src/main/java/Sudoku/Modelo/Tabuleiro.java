package Sudoku.Modelo;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tabuleiro {

    private List<List<Campo>> tabuleiro;
    private int tamanho = 9;

    public Tabuleiro() {
        this.tabuleiro = new ArrayList<>();

    }

    public List<List<Campo>> getTabuleiro() {
        return tabuleiro;
    }

    public void inserValor(int linha, int coluna, int valor) {
        if (tabuleiro.get(linha).get(coluna).isFixo()) {

        } else {
            if (valor > 0 && valor <= 9) {
                tabuleiro.get(linha).get(coluna).setValorInserido(valor);
            }
        }
    }

    public void limparJogo() {
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                if (tabuleiro.get(linha).get(coluna).isFixo()) {

                } else {
                    tabuleiro.get(linha).get(coluna).setValorInserido(0);
                }

            }
        }

    }

    public void iniciarJogo(Map<String, String> parametro) {
        for (int linha = 0; linha < 9; linha++) {
            List<Campo> listaCampos = new ArrayList<>();
            for (int coluna = 0; coluna < tamanho; coluna++) {
                String chave = linha + "," + coluna;
                String posicao = parametro.get(chave);
                String[] valores = posicao.split(",");
                int esperado = Integer.parseInt(valores[0]);
                boolean fixo = Boolean.parseBoolean(valores[1]);
                Campo campo = new Campo(fixo, esperado);
                if (fixo) {
                    campo.setValorInserido(esperado);
                }
                listaCampos.add(campo);
            }
            tabuleiro.add(listaCampos);
        }


    }

}


