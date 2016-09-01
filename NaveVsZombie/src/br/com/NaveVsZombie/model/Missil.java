/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombie.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author OCTI01
 */
public class Missil extends Objetos {

    public Missil(int x, int y) {
        setX(x);
        setY(y);
        setVelocidade(2);
        setAltura(10);
        setLargura(10);
        setImage(new ImageIcon(getClass().getResource(imagens + "missil.png")).getImage().getScaledInstance(getAltura(), getLargura(), Image.SCALE_SMOOTH));
        setVisivel(true);
    }

    @Override
    public void mecher() {
        setX(getX() + getVelocidade());
        if (getX() > larguraTela) {
            setVisivel(false);
        }
    }
}
