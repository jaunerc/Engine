/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 */
package ch.windmill.engine;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cyrill Jauner
 */
public class Chronograph {
    
    private long lastTime;
    private long time;
    private long cycles;
    private long refreshInterval;
    private double rate;
    private String name;
    private String rateString;

    public Chronograph(long interval, String name, TimeUnit unit) {
        this.name = name;
        setRefreshInterval(interval, unit);
    }

    public String getRateString() {
        return rateString;
    }

    public void setRefreshInterval(long interval, TimeUnit unit) {
        refreshInterval = unit.toNanos(interval);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void cycle() {
        long currTime = System.nanoTime();
        long currDelta = currTime - lastTime;
        
        time += currDelta;
        cycles++;
        if(time >= refreshInterval) {
            rate = cycles / (time * 0.000000001);
            updateRateString();
            time -= refreshInterval;
            cycles = 0;
        }
        
        lastTime = currTime;
    }
    
    private void updateRateString() {
        rateString = name+": "+rate;
    }
    
    public void reset() {
        lastTime = System.nanoTime();
        time = 0;
        cycles = 0;
        rate = 0;
        updateRateString();
    }

    
}
