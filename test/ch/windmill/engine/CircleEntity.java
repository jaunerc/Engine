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
import ch.windmill.engine.core.Vector2F;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author jaunerc
 */
public class CircleEntity implements Entity {
    
    public Rectangle2D.Float boundary;
    public Ellipse2D.Float ellipse = new Ellipse2D.Float();
    public Color color;
    public Vector2F pos, lastPos, vel;
    public float radius;

    public CircleEntity(float x, float y, Color color, Rectangle2D.Float boundary) {
        float angle = (float)(Math.random()*6.28);
        float speed = (float)(Math.random()*100) + 100;
        
        this.color = color;
        this.boundary = boundary;
        pos = new Vector2F(x, y);
        lastPos = new Vector2F();
        vel = new Vector2F((float)Math.cos( angle ) * speed, (float)Math.sin( angle ) * speed);
        radius = (float)(Math.random()*20) + 10;
    }
    
    
    
    @Override
    public void draw(GameState state, Graphics2D g2) {
        ellipse.x = (pos.x - lastPos.x) * state.interpolate + lastPos.x -radius;
        ellipse.y = (pos.y - lastPos.y) * state.interpolate + lastPos.y -radius;
        ellipse.width = radius * 2;
	ellipse.height = radius * 2;
        
        g2.setColor(color);
        g2.fill(ellipse);
    }

    @Override
    public void update(GameState state) {
        lastPos = Vector2F.copyOf(pos);
	pos.add(Vector2F.multiply(vel, state.seconds));
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void expire() {
    }

    @Override
    public void onExpire() {
    }
    
}
