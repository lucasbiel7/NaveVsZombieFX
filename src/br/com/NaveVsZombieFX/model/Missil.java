/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.model;

import javafx.scene.image.Image;

/**
 *
 * @author OCTI01
 */
public class Missil extends Objetos {

    public Missil(int x, int y) {
        initObjeto();
        setX(x);
        setY(y);
    }

    @Override
    protected void initObjeto() {
        setAltura(10);
        setLargura(10);
        setVisivel(true);
        setVelocidade(3);
        setImage(new Image(getClass().getResourceAsStream(dirImage + "missil.png"), getLargura(), getAltura(), false, true));
    }

    @Override
    public void mexer() {
        setX(getX() + getVelocidade());
        if (getX() > larguraTela) {
            setVisivel(false);
        }
    }
}
