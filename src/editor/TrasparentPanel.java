package editor;

import gui.MenuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import core.World;

@SuppressWarnings("serial")
public class TrasparentPanel extends JPanel{
	
	private Toolkit tk;
	private Dimension dim;
	private int currentX;
	private int currentY;
	private int positionGridX=0;
	private int positionGridY=0;
	private int dimensionBlock=0;
	
	public TrasparentPanel(World world,RightPanel rightpanel, EditorPanel editorPanel, MenuFrame frame) {
		
		
		positionGridX=editorPanel.getBordX();
		positionGridY=editorPanel.getBordY();
		dimensionBlock=editorPanel.getDimensionBlock();
		tk=Toolkit.getDefaultToolkit();
		dim=tk.getScreenSize();
		this.setPreferredSize(dim);
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.setBounds(0, 0, dim.width, dim.height);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				currentX=e.getX();
				currentY=e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// do nothing
			}
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing
			}
			@Override
			public void mousePressed(MouseEvent e) {
				insertBlock(world,rightpanel,e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// do nothing
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing
			}
		} );
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(TrasparentPanel.this.getMousePosition()==null) {
			currentX=0;
			currentY=0;
		}
		for(int i=0;i<World.Y_MATRIX_STRING;i++){
			for(int j=0;j<World.X_MATRIX_STRING;j++){
				
				if(currentX >i*dimensionBlock+positionGridX && currentX <(i*dimensionBlock)+positionGridX+dimensionBlock &&
						currentY >j*dimensionBlock+positionGridY && currentY <(j*dimensionBlock)+positionGridY+dimensionBlock){
					g.setColor(Color.WHITE.brighter().brighter());	
				}
				else{
					g.setColor(Color.BLACK.darker().darker());
				}
				g.draw3DRect(i*dimensionBlock+positionGridX, j*dimensionBlock+positionGridY ,
						dimensionBlock-1, dimensionBlock-1, true);
			}
		}
	}
	
	private void insertBlock(World world, RightPanel rightPanel, MouseEvent e){
		
		int mouseX=e.getX();
		int mouseY=e.getY();

		for(int i=0;i<World.Y_MATRIX_STRING;i++){
			for(int j=0;j<World.X_MATRIX_STRING;j++){
				
				if(mouseX >i*dimensionBlock+positionGridX && mouseX <(i*dimensionBlock)+positionGridX+dimensionBlock &&
						mouseY >j*dimensionBlock+positionGridY && mouseY <(j*dimensionBlock)+positionGridY+dimensionBlock){
					
					world.getMatrixString()[j]  [i]=rightPanel.getSelected();
				}
			}
		}
	}
}