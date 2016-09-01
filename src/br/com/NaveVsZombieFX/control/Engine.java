/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.control;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author OCTI01
 */
public abstract class Engine {

    protected GraphicsContext graphicsContext;
    protected Canvas canvas;
    private int width;
    private int height;

    public Engine(int width, int height) {
        this.width = width;
        this.height = height;
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public abstract void update();

    public abstract void display();

    public Canvas getCanvas() {
        return canvas;
    }
}
