/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author OCTI01
 */
public class Nave extends Objetos {

    private List<Missil> misseis;
    private List<Inimigo> inimigos;
    private int pontuacao;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        setVelocidade(pontuacao/300+1);
    }

    @Override
    protected void initObjeto() {
        misseis = new ArrayList<>();
        inimigos = new ArrayList<>();
        setAltura(30);
        setLargura(30);
        setVisivel(true);
        setPontuacao(0);
        setVelocidade(1);
        setX(0);
        setY(0);
        setImage(new Image(getClass().getResourceAsStream(dirImage + "disco_voador.gif"), getLargura(), getAltura(), false, true));
    }

    @Override
    public void mexer() {
        setX(getX() + getMx() * getVelocidade());
        setY(getY() + getMy() * getVelocidade());
        if (getX() < 0) {
            setX(0);
        }
        if (getX() + getLargura() > larguraTela) {
            setX(larguraTela - getLargura());
        }
        if (getY() < 0) {
            setY(0);
        }
        if (getY() + getAltura() > alturaTela) {
            setY(alturaTela - getAltura());
        }
    }

    public void keyReleased(KeyEvent e) {
        setMx(0);
        setMy(0);
        if (!isVisivel()) {
            if (e.getCode().equals(KeyCode.ENTER)) {
                setX(0);
                setY(0);
                setPontuacao(0);
                inimigos.clear();
                misseis.clear();
                setVisivel(true);
            }
        }
        System.out.println("Release");
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed");
        switch (e.getCode()) {
            case LEFT:
                setMx(-1);
                break;
            case RIGHT:
                setMx(+1);
                break;
            case UP:
                setMy(-1);
                break;
            case DOWN:
                setMy(+1);
                break;
            case SPACE:
                atirar();
                break;
        }
    }

    private void atirar() {
        System.out.println("atiro");
        misseis.add(new Missil(getX() + getLargura(), getY() + (getAltura() / 2)));
    }

    public List<Missil> getMisseis() {
        return misseis;
    }

    public void setMisseis(List<Missil> misseis) {
        this.misseis = misseis;
    }
}
