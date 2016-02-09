/*
 * Copyright (c) 2013 Philip Diffenderfer http://magnos.org
 *
 * Modified by Cyrill Jauner
 */
package ch.windmill.engine;

import ch.windmill.engine.input.AppInput;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jaunerc
 */
public class AppScreen extends JPanel {
    
    private App app;
    private Loopable loop;
    private AppState state;
    private AppInput input;
    private Graphics2D g2;
    private BufferedImage buffer;
    
    public AppScreen(App app, AppState state, Loopable loop, int width, int height) {
        this.app = app;
        this.loop = loop;
        this.state = state;
        input = new AppInput();
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        Dimension d = new Dimension(width, height);
        setSize(d);
        setPreferredSize(d);
    }

    @Override
    public void paint(Graphics g) {
        if(g2 != null && buffer != null) {
            render();
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    /**
     * Starts the application. This method creates a loop that runs as long as the app is not terminated.
     * The loop invokes the app.update and the loop.iterate method in each iteration.
     */
    public void startApp() {
        input.mouseInside = getParent().contains( MouseInfo.getPointerInfo().getLocation() );
	addMouseListener( input );
	addMouseMotionListener( input );
        
        resetGraphics();
        
        app.start();
        
        loop.init(state);
        
        while(app.isRunning()) {
            loop.iterate(app, state, input, g2);
            render();
            resetGraphics();
        }
    }
    
    public void render() {
        Graphics g = getGraphics();
        g.drawImage(buffer, 0, 0, this);
        g.dispose();
    }
    
    private void resetGraphics() {
        g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void showInFrame() {
        JFrame frame = new JFrame("Engine test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
}
