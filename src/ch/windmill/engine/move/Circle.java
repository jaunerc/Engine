/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine.move;

import ch.windmill.engine.Vector2F;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author jaunerc
 */
public class Circle extends Body {
    float radius;
    
    public Circle(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.draw(new Ellipse2D.Float(position.x, position.y, radius, radius));
    }

    @Override
    public Vector2F center() {
        return null;
    }
    
}
