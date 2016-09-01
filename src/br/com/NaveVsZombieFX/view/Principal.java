/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.view;

import br.com.NaveVsZombieFX.control.Engine;
import br.com.NaveVsZombieFX.control.GameEngine;
import br.com.NaveVsZombieFX.model.Commun;
import br.com.NaveVsZombieFX.model.Inimigo;
import br.com.NaveVsZombieFX.model.Missil;
import br.com.NaveVsZombieFX.model.Nave;
import br.com.NaveVsZombieFX.model.Objetos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author OCTI01
 */
public class Principal extends Application {

    private Nave nave;

    private int tempoParaInimigo = 2000;
    private int time = tempoParaInimigo;
    private ProgressBar progressBar;
    private int posCenario = 0;
    private Image cenario;

    @Override
    public void start(Stage primaryStage) {
        nave = new Nave();
        cenario = new Image(getClass().getResourceAsStream(Objetos.dirImage + "cenario2.jpg"), 500, 500, false, true);
        Engine engine = new Engine(Commun.larguraTela, Commun.alturaTela) {
            @Override
            public void update() {
                time++;
                nave.mexer();
                for (Missil missei : nave.getMisseis()) {
                    missei.mexer();
                }
                for (Inimigo inimigo : nave.getInimigos()) {
                    try {
                        inimigo.mexer();
                    } catch (Inimigo.UltrapassouException ex) {
                        if (nave.isVisivel()) {
                            nave.setPontuacao(nave.getPontuacao() - ex.getPontuacao());
                        }
                    }
                }
                if (time >= tempoParaInimigo) {
                    nave.getInimigos().add(new Inimigo((nave.getPontuacao() / 200) + 1));
                    time = 0;
                }
                verificarColisoes();
                nave.getMisseis().removeIf((Missil missil) -> !missil.isVisivel());
                nave.getInimigos().removeIf((Inimigo inimigo) -> !inimigo.isVisivel());
                posCenario--;
                if (posCenario < -500) {
                    posCenario = 0;
                }
            }

            @Override
            public void display() {
                graphicsContext.drawImage(cenario, posCenario, 0);
                graphicsContext.drawImage(cenario, posCenario + 500,0);
                if (nave.isVisivel()) {
                    graphicsContext.drawImage(nave.getImage(), nave.getX(), nave.getY());
                    for (Missil missei : nave.getMisseis()) {
                        if (missei.isVisivel()) {
                            graphicsContext.drawImage(missei.getImage(), missei.getX(), missei.getY());
                        }
                    }
                    for (Inimigo inimigo : nave.getInimigos()) {
                        if (inimigo.isVisivel()) {
                            graphicsContext.drawImage(inimigo.getImage(), inimigo.getX(), inimigo.getY());
                        }
                    }
                    graphicsContext.setFill(Color.CYAN);
                    graphicsContext.setFont(new Font(20));
                    graphicsContext.fillText("Pontuação: " + nave.getPontuacao(), 0, 15, 500);

                } else {
                    graphicsContext.setFont(new Font(50));
                    graphicsContext.setFill(Color.RED);
                    graphicsContext.fillText("Game over", 120, Commun.alturaTela / 2, 500);
                    graphicsContext.setFill(Color.YELLOW);
                    graphicsContext.fillText("Pontuação final: " + nave.getPontuacao(), 50, Commun.alturaTela / 2 + 50, 500);
                    graphicsContext.setFill(Color.CYAN);
                    graphicsContext.fillText("Enter to restart", 80, Commun.alturaTela - 50, 500);
                }
            }

            private void verificarColisoes() {
                if (nave.isVisivel()) {
                    for (Inimigo inimigo : nave.getInimigos()) {
                        for (Missil missil : nave.getMisseis()) {
                            if (missil.getBounds().intersects(inimigo.getBounds().getLayoutBounds())) {
                                missil.setVisivel(false);
                                inimigo.setVisivel(false);
                                nave.setPontuacao(nave.getPontuacao() + (10 * inimigo.getVelocidade()));
                                time = tempoParaInimigo;
                            }
                        }
                        if (inimigo.getBounds().intersects(nave.getBounds().getLayoutBounds())) {
                            inimigo.setVisivel(false);
                            nave.setVisivel(false);
                        }
                    }
                }
            }
        };
        GameEngine gameEngine = new GameEngine(100, engine);
        StackPane stackPane = new StackPane(engine.getCanvas());
        Scene scene = new Scene(stackPane);
        scene.setOnKeyReleased(nave::keyReleased);
        scene.setOnKeyPressed(nave::keyPressed);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        gameEngine.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
