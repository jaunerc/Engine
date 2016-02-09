/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine;

import ch.windmill.engine.input.AppInput;
import java.awt.Graphics2D;

/**
 * This class represents a variable loop.
 * @author jaunerc
 */
public class VariableLoop implements Loopable {
    
    @Override
    public void init(AppState state) {
        state.reset();
    }

    @Override
    public boolean iterate(App app, AppState state, AppInput input, Graphics2D g2) {
        state.tick();
        state.updateChronoCycle();
        app.update(state);
        
        if(!app.isRunning()) {
            return false;
        }
        
        state.drawChronoCycle();
        app.draw(state, g2);
        
        return true;
    }
    
}
