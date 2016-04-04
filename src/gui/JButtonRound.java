package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class JButtonRound extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Shape shape = null;
	private boolean fieldFocusable = false;
	
	
	public JButtonRound() {
	super();
	resize();
	}
	
	public JButtonRound(String text) {
	super(text);
	resize();
	}
	
	public JButtonRound(String text, Icon icon) {
	super(text, icon);
	resize();
	}
	
	public JButtonRound(Icon icon) {
	super(icon);
	resize();
	}
	
	public boolean contains(int x, int y) {
	if (shape == null || !shape.getBounds().equals(getBounds())){
	shape = new Ellipse2D.Float(0,0,getWidth(),getHeight());
	}
	return shape.contains(x,y);
	}
	
	public boolean getFocusable() {
	return fieldFocusable;
	}
	
	public boolean isFocusTraversable() {
	return getFocusable();
	}
	
	public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	Color c1 = new Color(100,100,100);
	Color c2 = Color.white;
	Color b1 = getBackground();
	
	Color newC = Color.RED;
	if (getModel().isPressed()){
	Color ap = c2;
	c2 = c1;
	c1 = ap;
	b1 = newC;
	}
	GradientPaint gr = new GradientPaint(0+10,0+10,c2,getSize().width-10,getSize().height-10,c1);
	g2.setPaint(gr);
	g2.fillOval(0,0,getSize().width-1,getSize().height-1);
	g2.setColor(b1);
	g2.fillOval(0+4,0+4,getSize().width-8,getSize().height-8);
	
	super.paintComponent(g);	
	
	}
	
	private void resize() {
	Dimension d = getPreferredSize();
	d.width = d.height = Math.max(d.width,d.height);
	setPreferredSize(d);
	setContentAreaFilled(false);
	setBorderPainted(false);
	
	}
	
	public void setFocusable(boolean focusable) {
	fieldFocusable = focusable;
	}
}