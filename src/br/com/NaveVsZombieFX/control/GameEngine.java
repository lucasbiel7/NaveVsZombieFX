/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NaveVsZombieFX.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;

/**
 *
 * @author OCTI01
 */
public class GameEngine {

    private int frameCount = 0;
    private int frameRate;
    private final Engine engine;
    private final Timeline loopGame;

    public GameEngine(int frameRate, Engine engine) {
        this.frameRate = frameRate;
        this.engine = engine;
        loopGame = createLoop();
    }

    private Timeline createLoop() {
        final Duration d = Duration.millis(1000 / frameRate);
        final KeyFrame oneFrame = new KeyFrame(d, this::run);
        Timeline timeline = new Timeline(frameRate, oneFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

    public void run(Event e) {
        frameCount++;
        engine.update();
        engine.display();
    }

    public void stop() {
        loopGame.stop();
    }

    public void start() {
        loopGame.playFromStart();
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

}
