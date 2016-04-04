package editor;

import gui.ImageProvider;
import gui.JButtonRound;
import gui.MenuFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.World;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	
	private JButton save;
	private JButton deleteAll;
	private JButton esc;
	private JButton load;
	private GridBagConstraints constraints;
	
	public OptionsPanel(EditorPanel editorPanel,MenuFrame frame) {
		this.setFocusable(true);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.ORANGE);
		
		constraints=new GridBagConstraints();
		constraints.insets=new Insets(40,3,40,3);
		constraints.gridx=0;
		constraints.gridy=0;
		
		save = new JButtonRound(new ImageIcon(ImageProvider.getSave()));
		save.setFocusPainted(false);
		
		deleteAll=new JButtonRound(new ImageIcon(ImageProvider.getClear()));
		deleteAll.setFocusPainted(false);
		
		esc= new JButtonRound(new ImageIcon(ImageProvider.getBottomEsc()));
		esc.setContentAreaFilled(false);
    	esc.setOpaque(false);
    	esc.setBorder(null);
    	esc.setFocusPainted(false);
		
		load=new JButtonRound(new ImageIcon(ImageProvider.getLoad()));
		load.setFocusPainted(false);
		
		
		esc.setBackground(Color.RED);
		deleteAll.setBackground(Color.GRAY.brighter());
		load.setBackground(Color.GRAY.brighter());
		save.setBackground(Color.GRAY.brighter());
		
		esc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialog = JOptionPane.showConfirmDialog(frame,"Do you really want to return at menu'?", "Exit to Editor",
					    JOptionPane.YES_NO_OPTION);
				if(dialog==JOptionPane.YES_OPTION){

					frame.setContentPane(frame.getMenuPanel());
					frame.getMenuPanel().requestFocus();
					frame.getMenuPanel().updateUI();
					
					deleteTrack(editorPanel);
					
				}
					
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.save();
			}
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.load();
			}
		});
		deleteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialog = JOptionPane.showConfirmDialog(frame,"Do you really want to delete all?", "Delete",
					    JOptionPane.YES_NO_OPTION);
				if(dialog==JOptionPane.YES_OPTION){
					deleteTrack(editorPanel);
				}
			}

		});
		
		esc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		load.setCursor(new Cursor(Cursor.HAND_CURSOR));
		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		this.add(esc,constraints);
		constraints.gridy++;
		this.add(load,constraints);
		constraints.gridy++;
		this.add(save,constraints);
		constraints.gridy++;
		this.add(deleteAll,constraints);
		
	}

	private void deleteTrack(EditorPanel editorPanel) {
		for(int i=0;i<World.X_MATRIX_STRING;i++){
			for(int j=0;j<World.Y_MATRIX_STRING;j++){
				editorPanel.getCenterPanel().getWorld().getMatrixString() [i] [j]="grass";
			}
		}
	}
}
