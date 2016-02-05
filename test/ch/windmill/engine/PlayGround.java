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

import ch.windmill.engine.move.Body;
import ch.windmill.engine.move.Scene;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author jaunerc
 */
public class PlayGround implements App {
    
    public static void main(String[] args) {
        PlayGround pg = new PlayGround();
        State state = new State();
        VariableLoop loop = new VariableLoop();
        AppScreen screen = new AppScreen(pg, state, loop, 500, 400);
        screen.showInFrame();
        screen.startApp();
    }
    
    private boolean running;
    private float accu;
    private Scene scene;
    
    public PlayGround() {
        running = false;
        accu = 0;
        scene = new Scene(1.0f / 60);
    }
    
    @Override
    public void start() {
        running = true;
    }

    @Override
    public void update(State state) {
        accu += state.currentDeltaSeconds;
        
        if(accu >= scene.dt) {
            scene.step();
            accu -= scene.dt;
        }
    }

    @Override
    public void draw(State state, Graphics2D g2) {
        g2.setColor(Color.red);
        for(Body b : scene.bodies) {
            b.draw(g2);
        }
    }

    @Override
    public void terminate() {
    }

    @Override
    public boolean isRunning() {
        return running;
    }
    
}
