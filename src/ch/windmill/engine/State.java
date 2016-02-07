/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cyrill Jauner
 */
public class State {
    
    public long startTime;
    public long currentTime;
    public long currentDeltaNanos;
    public float currentDeltaSeconds;
    public long lastTime;
    public Chronograph updateChrono;
    public Chronograph drawChrono;

    public State() {
        updateChrono = new Chronograph(500, "Updates per second", TimeUnit.MILLISECONDS);
        drawChrono = new Chronograph(500, "Draws per second", TimeUnit.MILLISECONDS);
    }
    
    public long tick() {
        lastTime = currentTime;
        currentTime = System.nanoTime();
        return (setCurrDelta(currentTime - lastTime));
    }
    
    private long setCurrDelta(long currDeltaNanos) {
        currentDeltaNanos = currDeltaNanos;
        currentDeltaSeconds = currDeltaNanos * 0.000000001f;
        return currentDeltaNanos;
    }
    
    public void reset() {
        long resetT = System.nanoTime();
        
        startTime = resetT;
        currentTime = resetT;
        lastTime = resetT;
        currentDeltaNanos = 0;
        
        updateChrono.reset();
        drawChrono.reset();
    }
    
    public void updateChronoCycle() {
        updateChrono.cycle();
    }
    
    public void drawChronoCycle() {
        drawChrono.cycle();
    }
}
