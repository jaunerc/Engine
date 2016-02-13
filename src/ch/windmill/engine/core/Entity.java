/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine.core;

import ch.windmill.engine.GameState;
import java.awt.Graphics2D;

/**
 * A Entity is a general abstraction for an drawable object in the game.
 * @author jaunerc
 */
public interface Entity {
    
    /**
     * Draws the Entity with the given graphics context.
     * @param state The state of the game.
     * @param g2 The graphics context.
     */
    void draw(GameState state, Graphics2D g2);
    
    /**
     * Updates the Entity based on the given game state.
     * @param state The state of the game.
     */
    void update(GameState state);
    
    /**
     * Wheter this entity is expired or not.
     * @return Wheter this entity is expired or not.
     */
    boolean isExpired();
    
    /**
     * Expires the Entity.
     */
    void expire();
    
    /**
     * Method to call, when this entity was definitly removed.
     */
    void onExpire();
}
