package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuPanel menuPanel;
	private Toolkit tk;
	private Dimension dim;
	private boolean fullScreen;
	private Font font;
	
    public MenuFrame(){
    	fullScreen=true;
    	tk = Toolkit.getDefaultToolkit();
    	tk.getScreenResolution();
		dim = tk.getScreenSize();
		try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("ARB 85 Poster Script JAN-39 FRE.ttf"));
             font =Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 40);
        } catch (Exception e) {
            e.printStackTrace();
            font=new Font("ARIAL",10,40);
            System.err.println("Custom font not loaded.\nLoad default font.");
        }
		this.setPreferredSize(dim);
		this.setBounds(0, 0, dim.width, dim.height);
		this.setUndecorated(true);
    	menuPanel=new MenuPanel(this);
    	this.setContentPane(menuPanel);
    	this.setIconImage(ImageProvider.getCar_icon());
    	this.setTitle("GameRace");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
	    
	    this.setFocusable(true);
	    this.setVisible(true);
    }
	
	public Toolkit getTk() {
		return tk;
	}
	public boolean isFullScreen() {
		return fullScreen;
	}
	public Dimension getDim() {
		return dim;
	}
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}
	
	public Font getFont() {
		return font;
	}

	public static int resizeX(int x){
		Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
		return ((int)dim.width*x)/1366;
	}
	public void resizeFrame(){
		
		if(fullScreen){
			
			this.dispose();
			tk = Toolkit.getDefaultToolkit();
	    	dim = new Dimension(resizeX((int)(tk.getScreenSize().getWidth())-40), resizeX((int)(tk.getScreenSize().getHeight())-40));
			this.setPreferredSize(dim);
			this.setBounds(0, 0, dim.width, dim.height);
			this.setUndecorated(false);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			fullScreen=false;
		
		}
		else
		{
			
			this.dispose();
			tk = Toolkit.getDefaultToolkit();
			dim = tk.getScreenSize(); 
			this.setPreferredSize(dim);
			this.setBounds(0, 0, dim.width, dim.height);
			this.setUndecorated(true);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			fullScreen=true;
		}
	}

	public static void main(String[] args) {
		LoadFrame loadFrame=new LoadFrame();
		new MenuFrame();
		loadFrame.dispose();
	}
}
