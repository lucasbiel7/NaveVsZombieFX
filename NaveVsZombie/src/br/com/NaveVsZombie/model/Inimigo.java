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
public class Inimigo extends Objetos {

    public Inimigo(int x, int y, String imagem) {
        setX(x);
        setY(y);
        setAltura(30);
        setLargura(30);
        setVisivel(true);
        setVelocidade(1);
        setImage(new ImageIcon(getClass().getResource(imagens + imagem)).getImage().getScaledInstance(getLargura(), getAltura(), Image.SCALE_SMOOTH));
    }

    @Override
    public void mecher() {
        if (getX() < 0) {
            setX(larguraTela);
        } else {
            setX(getX()-getVelocidade());
        }
    }

}
