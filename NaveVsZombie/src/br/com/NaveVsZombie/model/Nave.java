/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombie.model;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author OCTI01
 */
public class Nave extends Objetos {

    public Nave() {
        misseis = new ArrayList<>();
        setX(alturaTela / 2);
        setY(larguraTela / 2);
        setAltura(30);
        setLargura(30);
        setVisivel(true);
        setImage(new ImageIcon(getClass().getResource(imagens + "disco_voador.gif")).getImage().getScaledInstance(getLargura(), getAltura(), Image.SCALE_SMOOTH));
    }
    private List<Missil> misseis;

    public List<Missil> getMisseis() {
        return misseis;
    }

    public void setMisseis(List<Missil> misseis) {
        this.misseis = misseis;
    }

    @Override
    public void mecher() {
        setX(getX() + getMx());
        setY(getY() + getMy());
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

    public void atirar() {
        misseis.add(new Missil(getX() + getLargura(), getY() + (getAltura() / 2)));
    }

    public void keyReleased(KeyEvent e) {
        setMx(0);
        setMy(0);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                setMx(-1);
                break;
            case KeyEvent.VK_RIGHT:
                setMx(+1);
                break;
            case KeyEvent.VK_UP:
                setMy(-1);
                break;
            case KeyEvent.VK_DOWN:
                setMy(+1);
                break;
            case KeyEvent.VK_SPACE:
                atirar();
                break;

        }
    }

}
