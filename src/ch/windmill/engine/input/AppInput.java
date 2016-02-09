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
package ch.windmill.engine.input;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Cyrill Jauner
 */
public class AppInput implements MouseInputListener{
    
    public int mouseX, mouseY, mouseDownCount, mouseUpCount;
    public boolean[] mouseDown = new boolean[ MouseInfo.getNumberOfButtons() ];
    public boolean[] mouseUp = new boolean[ MouseInfo.getNumberOfButtons() ];
    public boolean mouseInside = true;
    public boolean mouseDragging = false;
    public boolean mouseMoving = false;
    public Queue<AppMouseEvent> mouseEvents = new ConcurrentLinkedQueue<>();
    
    public void clear() {
        mouseDownCount = 0;
        mouseUpCount = 0;
        mouseInside = false;
        mouseDragging = false;
        mouseMoving = false;
        mouseEvents.clear();
        
        for(int i = 0; i < mouseUp.length; i++) {
            mouseUp[i] = false;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseEvents.offer(new AppMouseEvent(AppMouseEvent.EventType.Click, e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown[ e.getButton() ] = true;
	mouseDownCount++;
        mouseEvents.offer(new AppMouseEvent(AppMouseEvent.EventType.Press, e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown[ e.getButton() ] = false;
	mouseUp[ e.getButton() ] = true;
	mouseUpCount++;
        mouseEvents.offer(new AppMouseEvent(AppMouseEvent.EventType.Release, e));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInside = true;
        mouseEvents.offer(new AppMouseEvent(AppMouseEvent.EventType.Enter, e));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
        mouseEvents.offer(new AppMouseEvent(AppMouseEvent.EventType.Exit, e));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseDragging = true;
        mouseMoving = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseDragging = false;
	mouseMoving = true;
	mouseX = e.getX();
	mouseY = e.getY();
    }
    
}
