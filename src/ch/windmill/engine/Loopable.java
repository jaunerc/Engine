/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 */
package ch.windmill.engine;

import ch.windmill.engine.input.AppInput;
import java.awt.Graphics2D;

/**
 * A loopable is a general abstraction for a game loop.
 * @author jaunerc
 */
public interface Loopable {
    
    /**
     * Initializes this loop. This method should be invoked first.
     * @param state The current application state.
     */
    void init(AppState state);
    
    /**
     * Invokes one iteration step. This method should contain routines of the game loop.
     * @param app The running application.
     * @param state The current application state.
     * @param g2 Graphics context.
     */
    boolean iterate(App app, AppState state, AppInput input, Graphics2D g2);
}
