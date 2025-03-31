package org.example.tabuleirosudoko;

import Sudoku.Modelo.Campo;
import Sudoku.Modelo.Tabuleiro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class PainelSudoku extends Application {
    private Tabuleiro tabuleiro = new Tabuleiro();
    private TextField[][] matrizCampos = new TextField[9][9];
    int tamanho = 9;
    boolean mostrarGabarito = false;

    @Override
    public void start(Stage stage) throws IOException {
        Map<String, String> parametros = Stream.of(getParameters().getRaw().toArray(new String[0]))
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        tabuleiro.iniciarJogo(parametros);

        BorderPane root = new BorderPane(); //BorderPane é um componente conteiner que armazena
        // outro conponetes(botoes, imagens, texto,etc
        root.setPadding(new Insets(10)); //define a margem

        GridPane tabuleiroSudokuGrid = criarPainelTabuleiro(tabuleiro);
        root.setCenter(tabuleiroSudokuGrid);

        HBox buttonPanel = createButtonPanel();
        root.setBottom(buttonPanel);


//        HBox buttonPanel = criarPainelBotoes();
//        root.setBottom(buttonPanel);

        updateView();

        Scene scene = new Scene(root, 500, 600);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();


    }

    private GridPane criarPainelTabuleiro(Tabuleiro tabuleiro) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(10));

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                TextField caixa = new TextField();
                caixa.setFont(Font.font(20));
                caixa.setPrefSize(40, 40);
                caixa.setAlignment(Pos.CENTER);

                if (linha / 3 % 2 == coluna / 3 % 2) {
                    caixa.setStyle("-fx-background-color: #f0f0f0;"); // Cinza claro
                } else {
                    caixa.setStyle("-fx-background-color: #ffffff;"); // Branco
                }

                int l = linha;
                int c = coluna;
                caixa.setOnAction(e -> {

                    int value = Integer.parseInt(caixa.getText());
                    if (value < 1 || value > 9) {

                        caixa.setText("");
                    } else {
                        tabuleiro.inserValor(l, c, value);
                        //updateCell(l, c);

                    }


                });
                matrizCampos[l][c] = caixa;
                grid.add(caixa, c, l);
            }
        }
        return grid;
    }

    private HBox createButtonPanel() {

        HBox buttonPanel = new HBox(10);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10, 0, 0, 0));


        Button gabarito = new Button("Gabarito");
        gabarito.setStyle("-fx-font-size: 14px; -fx-padding: 5 15;");
        gabarito.setOnAction(e -> {
            mostrarGabarito = !mostrarGabarito;
            updateView();
        });


        buttonPanel.getChildren().addAll(gabarito);
        return buttonPanel;

    }


//    private HBox criarPainelBotoes() {
//    }
//
    private void updateView() {

    for (int linha= 0; linha< tamanho; linha++){
        for (int coluna= 0; coluna< tamanho; coluna++)
            updateCampo(linha, coluna);

    }

    }

    private void updateCampo(int linha,int coluna){
        Campo campo = tabuleiro.getTabuleiro().get(linha).get(coluna);
        TextField caixa = matrizCampos[linha][coluna];

        if(mostrarGabarito == true){
            caixa.setText(String.valueOf(campo.getValorEsperado()));
            caixa.setEditable(false);

        }else {
            if (campo.isFixo()) {
                caixa.setText(String.valueOf(campo.getValorEsperado()));
                caixa.setEditable(false);

            } else {
                int valorInserido = campo.getValorInserido();
                caixa.setText(valorInserido == 0 ? "" : String.valueOf(valorInserido));
                caixa.setEditable(true);

            }

        }

        }
    }




