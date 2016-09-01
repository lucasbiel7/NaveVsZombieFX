/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombie.view;

import br.com.NaveVsZombie.model.Comum;
import br.com.NaveVsZombie.model.Inimigo;
import br.com.NaveVsZombie.model.Missil;
import br.com.NaveVsZombie.model.Nave;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author OCTI01
 */
public class Fase extends JPanel implements Comum {

    private static final int tempoRespawnInimigo = 2844;
    private Image image;
    private Nave nave;
    private Timer timer;

    private boolean inGame;

    private List<Inimigo> inimigos;
    int timerInimigo = 2844;

    public Fase() {
        initComponents();
        inimigos = new ArrayList<>();
    }

    private void initComponents() {
        setFocusable(true);
        setDoubleBuffered(true);
        image = new ImageIcon(getClass().getResource(imagens + "naveVsZombie.jpg")).getImage().getScaledInstance(larguraTela, alturaTela, Image.SCALE_SMOOTH);
        nave = new Nave();
        addKeyListener(new Teclado());
        timer = new Timer(5, new AtualizarTela());
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(image, 0, 0, null);
        if (nave.isVisivel()) {
            graficos.drawImage(nave.getImage(), nave.getX(), nave.getY(), this);
        }
        for (Missil missil : nave.getMisseis()) {
            graficos.drawImage(missil.getImage(), missil.getX(), missil.getY(), this);
        }
        for (Inimigo inimigo : inimigos) {
            graficos.drawImage(inimigo.getImage(), inimigo.getX(), inimigo.getY(), this);
        }
        g.dispose();
    }

    public class AtualizarTela implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gerarinimigos();
            nave.mecher();
            for (Missil missil : nave.getMisseis()) {
                if (missil.isVisivel()) {
                    missil.mecher();
                }
            }

            for (Inimigo inimigo : inimigos) {
                if (inimigo.isVisivel()) {
                    inimigo.mecher();
                }
            }
            checarColisoes();
            inimigos.removeIf((Inimigo inimigo) -> !inimigo.isVisivel());
            nave.getMisseis().removeIf((Missil missil) -> !missil.isVisivel());
            repaint();
        }
    }

    public void checarColisoes() {
        for (Inimigo inimigo : inimigos) {
            if (nave.getBounds().intersects(inimigo.getBounds())) {
                inGame = false;
                nave.setVisivel(false);
                inimigo.setVisivel(false);
            }
        }

        for (Inimigo inimigo : inimigos) {
            for (Missil missel : nave.getMisseis()) {
                if (missel.getBounds().intersects(inimigo.getBounds())) {
                    inimigo.setVisivel(false);
                    missel.setVisivel(false);
                    timerInimigo = 2800;
                }
            }
        }
    }

    private void gerarinimigos() {
        timerInimigo++;
        if (timerInimigo >= tempoRespawnInimigo) {
            inimigos.add(new Inimigo(520, new Random().nextInt(alturaTela), "allien"
                    + (new Random().nextInt(3) + 1)
                    //                    3
                    + ".png"));
            timerInimigo = 0;
        }
    }

    private class Teclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            nave.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyReleased(e);
        }
    }
}
