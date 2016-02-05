/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 */
package ch.windmill.engine;

import java.awt.Graphics2D;

/**
 *
 * @author jaunerc
 */
public class VariableLoop implements Loopable {
    
    @Override
    public void init(State state) {
        state.reset();
    }

    @Override
    public void iterate(App app, State state, Graphics2D g2) {
        state.tick();
        state.updateChronoCycle();
        app.update(state);
        
        state.drawChronoCycle();
        app.draw(state, g2);
    }
    
}
