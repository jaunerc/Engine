/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 */
package ch.windmill.engine;

import java.awt.Graphics2D;

/**
 *
 * @author Cyrill Jauner
 */
public interface App {
    
    void start();
    
    void update(State state);
    
    void draw(State state, Graphics2D g2);
    
    void exit();
    
    boolean isRunning();
}
