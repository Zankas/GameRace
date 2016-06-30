package editor;

import gui.MenuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SupportPanel extends JPanel {
	private RightPanel rightpanel;
	private OptionsPanel optionsPanel;
	
	private JPanel panelLaps;
	private JPanel panelEnemies;
	private JLabel labelLaps;
	private JLabel labelEnemies;
	private JButton buttonLapsMore;
	private JButton buttonLapsLess;
	private JButton buttonEnemiesMore;
	private JButton buttonEnemiesLess;
	private JTextArea textAreaLaps;
	private JTextArea textAreaEnemies;

	public SupportPanel(EditorPanel editorPanel,MenuFrame frame) {
		
		this.setFocusable(true);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		rightpanel=new RightPanel(editorPanel);
		optionsPanel= new OptionsPanel(editorPanel,frame);
		this.add(rightpanel,BorderLayout.CENTER);
		this.add(optionsPanel,BorderLayout.EAST);
		
		panelLaps=new JPanel();
		panelEnemies=new JPanel();
		labelLaps=new JLabel("Laps ");
		labelEnemies=new JLabel("Enemies");
		buttonLapsMore=new JButton("+");
		buttonLapsLess=new JButton("-");
		buttonEnemiesMore=new JButton("+");
		buttonEnemiesLess=new JButton("-");
		
		buttonLapsMore.setToolTipText("Increase the number of laps");
		buttonLapsLess.setToolTipText("Decrease the number of laps");
		buttonEnemiesMore.setToolTipText("Increase the number of enemies");
		buttonEnemiesLess.setToolTipText("Decrease the number of enemies");
		
		textAreaLaps=new JTextArea(Integer.toString (editorPanel.getTotalLaps()));
		textAreaEnemies=new JTextArea("0");
		
		Font font=new Font("ARIAL", 10, 25);
		
		panelLaps.setBackground(Color.ORANGE.brighter());
		labelLaps.setFont(font);
		buttonLapsLess.setFont(font);
		buttonLapsLess.setFocusPainted(false);
		buttonLapsLess.setBackground(Color.GRAY.brighter());
		buttonLapsMore.setFont(font);
		buttonLapsMore.setFocusPainted(false);
		buttonLapsMore.setBackground(Color.GRAY.brighter());
		textAreaLaps.setFont(font);
		textAreaLaps.setOpaque(false);
		textAreaLaps.setFocusable(false);
		
		panelLaps.add(labelLaps);
		panelLaps.add(buttonLapsLess);
		panelLaps.add(textAreaLaps);
		panelLaps.add(buttonLapsMore);
		
		panelEnemies.setBackground(Color.ORANGE.brighter());
		labelEnemies.setFont(font);
		buttonEnemiesLess.setFont(font);
		buttonEnemiesLess.setFocusPainted(false);
		buttonEnemiesLess.setBackground(Color.GRAY.brighter());
		buttonEnemiesMore.setFont(font);
		buttonEnemiesMore.setFocusPainted(false);
		buttonEnemiesMore.setBackground(Color.GRAY.brighter());
		textAreaEnemies.setFont(font);
		textAreaEnemies.setOpaque(false);
		textAreaEnemies.setFocusable(false);
		
		panelEnemies.add(labelEnemies);
		panelEnemies.add(buttonEnemiesLess);
		panelEnemies.add(textAreaEnemies);
		panelEnemies.add(buttonEnemiesMore);
		
		JPanel panelLapsEnemies=new JPanel();
		panelLapsEnemies.setBackground(Color.ORANGE.brighter());
		panelLapsEnemies.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		
		panelLapsEnemies.add(panelLaps,constraints);
		constraints.gridy++;
		panelLapsEnemies.add(panelEnemies,constraints);
		
		this.add(panelLapsEnemies,BorderLayout.NORTH);
		
		
		buttonLapsLess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalLaps()>1){
					editorPanel.setTotalLaps(editorPanel.getTotalLaps()-1);
					textAreaLaps.setText(Integer.toString(editorPanel.getTotalLaps()));
				}
			}
		});
		buttonLapsMore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalLaps()<10){
					editorPanel.setTotalLaps(editorPanel.getTotalLaps()+1);
					textAreaLaps.setText(Integer.toString(editorPanel.getTotalLaps()));
				}
			}
		});
		
		buttonEnemiesLess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalEnemies()>0){
					editorPanel.setTotalEnemies(editorPanel.getTotalEnemies()-1);
					textAreaEnemies.setText(Integer.toString(editorPanel.getTotalEnemies()));
				}
			}
		});
		buttonEnemiesMore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalEnemies()<3){
					editorPanel.setTotalEnemies(editorPanel.getTotalEnemies()+1);
					textAreaEnemies.setText(Integer.toString(editorPanel.getTotalEnemies()));
				}
			}
		});
	}
	public RightPanel getLeftpanel() {
		return rightpanel;
	}
}
