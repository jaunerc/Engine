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
package ch.windmill.engine.move;

import ch.windmill.engine.Vector2F;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaunerc
 */
public class Scene {
    
    public List<Body> bodies;
    public float dt;
    
    public Scene(float dt) {
        this.dt = dt;
        bodies = new ArrayList<>();
        init();
        System.out.println("Work with "+dt+" updates");
    }
    
    private void init() {
        Circle c = new Circle(150, 150, 20);
        c.velocity = new Vector2F(100, 80);
        bodies.add(c);
    }
    
    public void step() {
        for(Body b : bodies) {
            integrateVelocity(b);
        }
    }
    
    private void integrateVelocity(Body b) {
        Vector2F v = Vector2F.multiply(b.velocity, dt);
        b.position.add(v);
    }
}
