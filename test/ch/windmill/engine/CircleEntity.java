/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine;

import ch.windmill.engine.core.Entity;
import ch.windmill.engine.core.Vector2F;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This class represents a Circle.
 */
public class CircleEntity implements Entity {
    
    public Rectangle2D.Float boundary;
    public Ellipse2D.Float ellipse = new Ellipse2D.Float();
    public Color color;
    public Vector2F pos, lastPos, vel;
    public float radius;
    
    private boolean expired;
    
    public CircleEntity(float x, float y, Color color, Rectangle2D.Float boundary) {
        float angle = (float)(Math.random()*6.28);
        float speed = (float)(Math.random()*100) + 100;
        
        this.color = color;
        this.boundary = boundary;
        expired = false;
        pos = new Vector2F(x, y);
        lastPos = new Vector2F();
        vel = new Vector2F((float)Math.cos( angle ) * speed, (float)Math.sin( angle ) * speed);
        radius = (float)(Math.random()*20) + 10;
    }
    
    
    
    @Override
    public void draw(GameState state, Graphics2D g2) {
        ellipse.x = (pos.x - lastPos.x) * state.interpolate + lastPos.x -radius;
        ellipse.y = (pos.y - lastPos.y) * state.interpolate + lastPos.y -radius;
        //ellipse.x = pos.x + state.backward * vel.x -radius;
        //ellipse.y = pos.y + state.backward * vel.y -radius;
        //ellipse.x = pos.x + state.forward * vel.x -radius;
        //ellipse.y = pos.y + state.forward * vel.y -radius;
        ellipse.width = radius * 2;
	ellipse.height = radius * 2;
        
        g2.setColor(color);
        g2.fill(ellipse);
    }

    @Override
    public void update(GameState state) {
        lastPos = Vector2F.copyOf(pos);
	pos.add(Vector2F.multiply(vel, state.seconds));
        
        handleBoundaryCollision();
    }
    
    private void handleBoundaryCollision() {
        float bl = boundary.x + radius;
	float br = boundary.x + boundary.width - radius;
	float bt = boundary.y + radius;
	float bb = boundary.y + boundary.height - radius;
        
        if(pos.x < bl) {
            vel.x *= -1;
            pos.x = bl;
            colorUpdate();
        }
        if(pos.x > br) {
            vel.x *= -1;
            pos.x = br;
            colorUpdate();
        }
        if(pos.y < bt) {
            vel.y *= -1;
            pos.y = bt;
            colorUpdate();
        }
        if(pos.y > bb) {
            vel.y *= -1;
            pos.y = bb;
            colorUpdate();
        }
    }
    
    private void colorUpdate() {
        int alpha = color.getAlpha();
        if(alpha > 0) {
            alpha -= 51;
        }
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void expire() {
        expired = false;
    }

    @Override
    public void onExpire() {
    }
    
}
