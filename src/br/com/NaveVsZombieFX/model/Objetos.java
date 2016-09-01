/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author OCTI01
 */
public abstract class Objetos implements Commun {
//Posicao na tela

    private int x;
    private int y;
//Movimentar na tela
    private int mx;
    private int my;
//Dimensoes do objeto
    private int largura;
    private int altura;
    private boolean visivel;
    private Image image;
    private int velocidade;

    public Objetos() {
        initObjeto();
    }

    protected abstract void initObjeto();

    public abstract void mexer() throws Exception;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMx() {
        return mx;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public int getMy() {
        return my;
    }

    public void setMy(int my) {
        this.my = my;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }
}
