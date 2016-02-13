/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine;

import ch.windmill.engine.core.Entity;
import ch.windmill.engine.input.GameInput;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class is a playground to test the engine.
 */
public class PlayGround implements Game {
    
    private boolean running;
    private Rectangle2D.Float boundary;
    private List<Entity> entities;
    
    public static void main(String[] args) {
        Game game = new PlayGround();
        Loopable loop = new InterpolatedLoop(3, 20, TimeUnit.MILLISECONDS);
        GameState state = new GameState();
        GameScreen screen = new GameScreen(game, state, loop, 600, 500);
        screen.setBackground(Color.black);
        screen.showInFrame();
        screen.start(); 
    }
    
    @Override
    public void start() {
        boundary = new Rectangle2D.Float( 0, 0, 600, 500 );
        entities = new ArrayList<>();
        running = true;
    }

    @Override
    public void input(GameInput input) {
        if(input.mouseUpCount > 0) {
            for(int i = 0; i < 10; i++) {
                int r = (int) (Math.random() * 255);
                int g = (int) (Math.random() * 255);
                int b = (int) (Math.random() * 255);
                entities.add(new CircleEntity(input.mouseX, input.mouseY, new Color(r, g, b), boundary));
            }
        }
        if(input.mouseDragging) {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            entities.add(new CircleEntity(input.mouseX, input.mouseY, new Color(r, g, b), boundary));
        }
    }

    @Override
    public void update(GameState state) {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()) {
            CircleEntity e = (CircleEntity)it.next();
            e.update(state);
            if(e.color.getAlpha() == 0) {
                it.remove();
            }
        }
    }

    @Override
    public void draw(GameState state, Graphics2D g2) {
        for(Entity e : entities) {
            e.draw(state, g2);
        }
    }

    @Override
    public void terminate() {
        for(Entity e : entities) {
            e.onExpire();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }
    
}
