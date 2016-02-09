/*
 * The MIT License
 *
 * Copyright 2016 Cyrill Jauner.
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

import ch.windmill.engine.input.AppInput;
import java.awt.Graphics2D;

/**
 *
 * @author Cyrill Jauner
 */
public class InterpolatedLoop implements Loopable {
    
    public long frameRate;
    public int maxUpdates;
    private long time;

    public InterpolatedLoop(double frameRate, int maxUpdates) {
        this.frameRate = (long)(frameRate * 1000000000l);
        this.maxUpdates = maxUpdates;
    }
    
    
    
    @Override
    public void init(AppState state) {
        state.reset();
        state.setElapsed(frameRate);
    }

    @Override
    public boolean iterate(App app, AppState state, AppInput input, Graphics2D g2) {
        long nanosElapsed = state.tick();
        time += nanosElapsed;
        int updateCount = 0;
        
        while (time >= frameRate && updateCount < maxUpdates) {
            app.input(input);
            input.clear();
            
            if(!app.isRunning()) {
                return false;
            }
            
            state.updateChronoCycle();
            app.update(state);
            
            if(!app.isRunning()) {
                return false;
            }
            
            updateCount++;
            time -= frameRate;
        }
        
        state.interpolate = getStateInterpolation();
        state.forward = state.interpolate * state.seconds;
	state.backward = state.forward - state.seconds;
        
        state.drawChronoCycle();
        app.draw(state, g2);
        
        return true;
    }
    
    public float getStateInterpolation() {
        return (float) ((double) time / (double) frameRate);
    }
}
