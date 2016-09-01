/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.model;

import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author OCTI01
 */
public class Inimigo extends Objetos {

    public Inimigo(int velocidade) {
        initObjeto();
        setVelocidade(velocidade);
    }
    
    
    @Override
    protected void initObjeto() {
        setAltura(30);
        setLargura(30);

        setY(new Random().nextInt(alturaTela - 30));
        if (getY() < 0) {
            setY(0);
        }
        setX(larguraTela);
        setVisivel(true);
        setImage(new Image(getClass().getResourceAsStream(dirImage + "allien" + (new Random().nextInt(13) + 1) + ".png"), getLargura(), getAltura(), false, true));
        setVelocidade(1);
    }

    @Override
    public void mexer() throws UltrapassouException {
        setX(getX() - getVelocidade());
        if (getX() < 0) {
            setX(larguraTela);
            throw new UltrapassouException(10);
        }
    }

    public class UltrapassouException extends Exception {

        private int pontuacao;

        public UltrapassouException(int pontuacao) {
            this.pontuacao = pontuacao;
        }

        public int getPontuacao() {
            return pontuacao;
        }

        public void setPontuacao(int pontuacao) {
            this.pontuacao = pontuacao;
        }

    }
}
