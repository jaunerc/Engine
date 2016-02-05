/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 */
package ch.windmill.engine;

import java.awt.Graphics2D;

/**
 *
 * @author jaunerc
 */
public interface Loopable {
    
    void init(State state);
    
    void iterate(App app, State state, Graphics2D g2);
}
