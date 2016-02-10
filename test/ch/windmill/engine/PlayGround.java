/*
 * The MIT License
 *
 * Copyright 2016 jaunerc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ch.windmill.engine;

import ch.windmill.engine.core.Entity;
import ch.windmill.engine.input.GameInput;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jaunerc
 */
public class PlayGround implements Game {
    
    private boolean running;
    private List<Entity> entities;
    
    public static void main(String[] args) {
        Game game = new PlayGround();
        Loopable loop = new InterpolatedLoop( 3, 20, TimeUnit.MILLISECONDS);
        GameState state = new GameState();
        GameScreen screen = new GameScreen(game, state, loop, 600, 500);
        screen.setBackground(Color.black);
        screen.showInFrame();
        screen.start(); 
    }
    
    @Override
    public void start() {
        entities = new ArrayList<>();
        entities.add(new CircleEntity(100, 100, Color.yellow, null));
        running = true;
    }

    @Override
    public void input(GameInput input) {
        // ToDo
    }

    @Override
    public void update(GameState state) {
        for(Entity e : entities) {
            e.update(state);
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
