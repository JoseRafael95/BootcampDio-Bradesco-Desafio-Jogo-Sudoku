package Sudoku.Modelo;

public class Campo {

    private boolean fixo = false;
    private int valorEsperado;
    private int valorInserido;

    public Campo(boolean fixo, int valorEsperado) {
        this.fixo = fixo;
        this.valorEsperado = valorEsperado;
    }

    public void setValorInserido(int valorInserido) {
        this.valorInserido = valorInserido;
    }

    public boolean isFixo() {
        return fixo;
    }

    public int getValorEsperado() {
        return valorEsperado;
    }

    public int getValorInserido() {
        return valorInserido;
    }
}
