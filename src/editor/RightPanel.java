package editor;

import gui.ImageProvider;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class RightPanel extends JPanel implements MouseListener{
	
	private String [] StartArray ;
	private ImageIcon [] imageStart;
	private String selected;
	

	private JLabel grass;
	private JLabel horizontal;
	private JLabel vertical;
	private JLabel curveLeftUp;
	private JLabel curveLeftDown;
	private JLabel curveRightUp;
	private JLabel curveRightDown;
	private JLabel startCar;
	
	private GridBagConstraints constraints;
		
	private final static String HORIZONTAL="horizontal";
	private final static String VERTICAL="vertical";
	private final static String CURVE_LEFT_UP="curveleftup";
	private final static String CURVE_LEFT_DOWN="curveleftdown";
	private final static String CURVE_RIGHT_UP="curverightup";
	private final static String CURVE_RIGHT_DOWN="curverightdown";
	
	private final static String START_HORIZONTAL_RIGHT="starthorizontalright";
	private final static String START_HORIZONTAL_LEFT="starthorizontalleft";
	private final static String START_VERTICAL_UP="startverticalup";
	private final static String START_VERTICAL_DOWN="startverticaldown";
	private final static String GRASS="grass";
	
	private int cont=0;
	
	
	public RightPanel(EditorPanel editorPanel) {
		
		this.setFocusable(true);
		
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		
		
		StartArray=new String [4];
		
		StartArray [0]=START_HORIZONTAL_RIGHT;
		StartArray [1]=START_HORIZONTAL_LEFT;
		StartArray [2]=START_VERTICAL_UP;
		StartArray [3]=START_VERTICAL_DOWN;
		
		selected=new String("grass");
		
		imageStart=new ImageIcon [4];
		imageStart[0]=new ImageIcon(ImageProvider.getStarthorizontalrightEditor());
		imageStart[1]=new ImageIcon(ImageProvider.getStarthorizontalleftEditor());
		imageStart[2]=new ImageIcon(ImageProvider.getStartverticalupEditor());
		imageStart[3]=new ImageIcon(ImageProvider.getStartverticaldownEditor());
		
		
		startCar=new JLabel(imageStart[0]);
		grass=new JLabel(new ImageIcon(ImageProvider.getGrassEditor()));
		horizontal=new JLabel(new ImageIcon(ImageProvider.getHorizontalEditor()));
		vertical=new JLabel(new ImageIcon(ImageProvider.getVerticalEditor()));
		curveLeftUp=new JLabel(new ImageIcon(ImageProvider.getUpLeftEditor()));
		curveLeftDown=new JLabel(new ImageIcon(ImageProvider.getDownLeftEditor()));
		curveRightUp=new JLabel(new ImageIcon(ImageProvider.getUpRigthEditor()));
		curveRightDown=new JLabel(new ImageIcon(ImageProvider.getDownRightEditor()));
		
		constraints=new GridBagConstraints();
		constraints.insets=new Insets(1,1,1,1);
		constraints.gridx=0;
	    constraints.gridy=0;
		
		startCar.setToolTipText("Left click to selected piece, Right click to rotate the direction");
		grass.setToolTipText("Left click to selected piece 'Grass'");
		horizontal.setToolTipText("Left click to selected piece 'Horizontal'");
		vertical.setToolTipText("Left click to selected piece 'Vertical'");
		curveLeftUp.setToolTipText("Left click to selected piece 'Curve left up'");
		curveLeftDown.setToolTipText("Left click to selected piece 'Curve left down'");
		curveRightUp.setToolTipText("Left click to selected piece 'Curve right up'");
		curveRightDown.setToolTipText("Left click to selected piece 'Curve right down'");
		
		grass.setBackground(Color.RED.darker());
		
		startCar.setBorder(new BevelBorder(BevelBorder.RAISED));
		grass.setBorder(new BevelBorder(BevelBorder.RAISED));
		horizontal.setBorder(new BevelBorder(BevelBorder.RAISED));
		vertical.setBorder(new BevelBorder(BevelBorder.RAISED));
		curveLeftUp.setBorder(new BevelBorder(BevelBorder.RAISED));
		curveLeftDown.setBorder(new BevelBorder(BevelBorder.RAISED));
		curveRightUp.setBorder(new BevelBorder(BevelBorder.RAISED));
		curveRightDown.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		
		this.add(startCar,constraints);
		constraints.gridx++;
		this.add(vertical,constraints);
		constraints.gridy--;
		constraints.gridx--;
		this.add(grass,constraints);
		constraints.gridx++;
		this.add(horizontal,constraints);
		constraints.gridy--;
		constraints.gridx--;
		this.add(curveLeftUp,constraints);
		constraints.gridx++;
		this.add(curveRightUp,constraints);
		constraints.gridy--;
		constraints.gridx--;
		this.add(curveLeftDown,constraints);
		constraints.gridx++;
		this.add(curveRightDown,constraints);
		
		startCar.addMouseListener(this);
		grass.addMouseListener(this);
		horizontal.addMouseListener(this);
		vertical.addMouseListener(this);
		curveLeftUp.addMouseListener(this);
		curveLeftDown.addMouseListener(this);
		curveRightUp.addMouseListener(this);
		curveRightDown.addMouseListener(this);
		
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// do nothing
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource()==startCar){
			startCar.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=StartArray[cont];
			startCar.setBackground(Color.RED.darker());
		}
		else{
			startCar.setBackground(Color.WHITE);
		}
		if(e.getSource()==grass){
			grass.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=GRASS;
			grass.setBackground(Color.RED.darker());
		}
		else{
			grass.setBackground(Color.WHITE);
		}
		if(e.getSource()==horizontal){
			horizontal.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=HORIZONTAL;
			horizontal.setBackground(Color.RED.darker());
		}
		else{
			horizontal.setBackground(Color.WHITE);
		}
		if(e.getSource()==vertical){
			vertical.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=VERTICAL;
			vertical.setBackground(Color.RED.darker());
		}
		else{
			vertical.setBackground(Color.WHITE);
		}
		if(e.getSource()==curveLeftUp){
			curveLeftUp.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=CURVE_LEFT_UP;
			curveLeftUp.setBackground(Color.RED.darker());
		}
		else{
			curveLeftUp.setBackground(Color.WHITE);
		}
		if(e.getSource()==curveLeftDown){
			curveLeftDown.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=CURVE_LEFT_DOWN;
			curveLeftDown.setBackground(Color.RED.darker());
		}
		else{
			curveLeftDown.setBackground(Color.WHITE);
		}
		if(e.getSource()==curveRightUp){
			curveRightUp.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=CURVE_RIGHT_UP;
			curveRightUp.setBackground(Color.RED.darker());
		}
		else{
			curveRightUp.setBackground(Color.WHITE);
		}
		if(e.getSource()==curveRightDown){
			curveRightDown.setBorder(new BevelBorder(BevelBorder.LOWERED));
			selected=CURVE_RIGHT_DOWN;
			curveRightDown.setBackground(Color.RED.darker());
		}
		else{
			curveRightDown.setBackground(Color.WHITE);
		}
	}
	
	public String getSelected() {
		return selected;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==startCar){
			startCar.setBorder(new BevelBorder(BevelBorder.RAISED));
			if(SwingUtilities.isRightMouseButton(e)){
				cont++;
				if(cont>=4){
					cont=0;
				}
				selected=StartArray[cont];
				startCar.setIcon(imageStart[cont]);
			}
		}
		if(e.getSource()==grass){
			grass.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==horizontal){
			horizontal.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==vertical){
			vertical.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==curveLeftUp){
			curveLeftUp.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==curveLeftDown){
			curveLeftDown.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==curveRightUp){
			curveRightUp.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		if(e.getSource()==curveRightDown){
			curveRightDown.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}

}