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
public class AppState {
    
    public float seconds;
    public long millis;
    public long nanos;
    public long micros;
    public long startTime;
    public long currentTime;
    public float forward = 0;
    public float backward = 0;
    public float interpolate = 0;
    public long lastTime;
    public Chronograph updateChrono;
    public Chronograph drawChrono;

    public AppState() {
        updateChrono = new Chronograph(500, "Updates per second", TimeUnit.MILLISECONDS);
        drawChrono = new Chronograph(500, "Draws per second", TimeUnit.MILLISECONDS);
    }
    
    public long tick() {
        lastTime = currentTime;
        currentTime = System.nanoTime();
        return (currentTime - lastTime);
    }
    
    public long getElapsedSinceTick() {
	return (System.nanoTime() - currentTime);
    }

    public void setElapsed(long nanosElapsed) {
        nanos = nanosElapsed;
        micros = nanosElapsed / 1000L;
        millis = nanosElapsed / 1000000L;
        seconds = (float) (nanosElapsed * 0.000000001);
    }

    public long reset() {
        long resetT = System.nanoTime();
        
        startTime = resetT;
        currentTime = resetT;
        lastTime = resetT;
        
        updateChrono.reset();
        drawChrono.reset();
        
        return resetT;
    }
    
    public void updateChronoCycle() {
        updateChrono.cycle();
    }
    
    public void drawChronoCycle() {
        drawChrono.cycle();
    }
}
