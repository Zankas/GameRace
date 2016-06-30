package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.AbstractBlockRoadObject;
import core.BlockRoadObject;
import core.CarManager;
import core.GameManager;
import core.World;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BordVisual = 360;
	private final Dimension dim;
	private GameManager game;
	private RenderingHints renderingHints;

	private JDialogOptions dialogOptions;

	private boolean minimap = true;
	private boolean pause = false;

	private boolean dialogFinish = true;

	private long recordTrack;

	private int I;
	private int J;

	private int x;
	private int y;

	private String nameTrack;
	private String path;
	private GregorianCalendar startCalendar;
	private GregorianCalendar actualCalendar;
	private GregorianCalendar tmp;

	private long diff;

	private boolean start = true;

	private boolean number3 = false;
	private boolean number2 = false;
	private boolean number1 = false;
	private boolean go = false;

	private boolean focus = true;

	private int pressUP;
	private int pressDOWN;
	private int pressLEFT;
	private int pressRIGHT;

	public GamePanel(String path, String nameTrackGame, LevelsPanel levelsPanel, MenuFrame frame) {

		dim = frame.getDim();
		game = new GameManager();

		if (frame.getMenuPanel().isWASD()) {
			pressUP = KeyEvent.VK_W;
			pressDOWN = KeyEvent.VK_S;
			pressLEFT = KeyEvent.VK_A;
			pressRIGHT = KeyEvent.VK_D;
		} else {
			pressUP = KeyEvent.VK_UP;
			pressDOWN = KeyEvent.VK_DOWN;
			pressLEFT = KeyEvent.VK_LEFT;
			pressRIGHT = KeyEvent.VK_RIGHT;
		}

		nameTrack = nameTrackGame;
		this.path = path;
		renderingHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		threadGamepanel(frame, this);

		time();

		I = 0;
		J = 0;
		x = (int) (game.getWorld().getCar().getX1() + game.getWorld().getCar().getX3()) / 2 + I;
		y = (int) (game.getWorld().getCar().getY1() + game.getWorld().getCar().getY2()) / 2 + J;

		recordTrack=LevelLoadSave.load(path,nameTrack,game);

		game.getWorld().makeWorld();
		game.init();

		this.setPreferredSize(dim);
		this.setFocusable(true);
		this.setBackground(new Color(0, 128, 255));
		this.setDoubleBuffered(true);
		this.setBounds(0, 0, dim.width, dim.height);
		this.requestFocus();

		this.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (focus) {
					if (e.getKeyCode() == KeyEvent.VK_5) {
						System.out.println("CAR1");
						System.out.println("X1 :" + game.getCarManagerList().get(0).getCar().getX1());
						System.out.println("X2 :" + game.getCarManagerList().get(0).getCar().getX2());
						System.out.println("X3 :" + game.getCarManagerList().get(0).getCar().getX3());
						System.out.println("X4 :" + game.getCarManagerList().get(0).getCar().getX4());
						System.out.println("Y1 :" + game.getCarManagerList().get(0).getCar().getY1());
						System.out.println("Y2 :" + game.getCarManagerList().get(0).getCar().getY2());
						System.out.println("Y3 :" + game.getCarManagerList().get(0).getCar().getY3());
						System.out.println("Y4 :" + game.getCarManagerList().get(0).getCar().getY4());
						System.out.println("CAR2");
						System.out.println("X1 :" + game.getCarManagerList().get(1).getCar().getX1());
						System.out.println("X2 :" + game.getCarManagerList().get(1).getCar().getX2());
						System.out.println("X3 :" + game.getCarManagerList().get(1).getCar().getX3());
						System.out.println("X4 :" + game.getCarManagerList().get(1).getCar().getX4());
						System.out.println("Y1 :" + game.getCarManagerList().get(1).getCar().getY1());
						System.out.println("Y2 :" + game.getCarManagerList().get(1).getCar().getY2());
						System.out.println("Y3 :" + game.getCarManagerList().get(1).getCar().getY3());
						System.out.println("Y4 :" + game.getCarManagerList().get(1).getCar().getY4());
						// for (int i = 0; i < game.getCarManagerList().size();
						// i++) {
						// System.out.println("CAR N: " + i + " ID N: " +
						// game.getCarManagerHuman().getCar().getID());
						// }
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {


						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {

								game.setUpdate(false);
								pause = true;
								game.getWorld().getCar().setUpDownLeftRightFalse();
								dialogOptions = new JDialogOptions(frame, GamePanel.this);
								dialogOptions.setVisible(true);
							}
						});


					}
					if (!game.endGame()) {
						if (e.getKeyCode() == pressUP) {
							game.getWorld().getCar().setUP(true);

						}

						if (e.getKeyCode() == pressDOWN) {
							game.getWorld().getCar().setDOWN(true);

						}
						if (e.getKeyCode() == pressLEFT) {
							game.getWorld().getCar().setLEFT(true);

						}

						if (e.getKeyCode() == pressRIGHT) {
							game.getWorld().getCar().setRIGHT(true);

						}

					}
					if (e.getKeyCode() == KeyEvent.VK_O) {
						if (minimap) {
							minimap = false;
						} else {
							minimap = true;
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_P) {
						if (game.isUpdate()) {
							game.setUpdate(false);
							pause = true;
						} else {
							game.setUpdate(true);
							pause = false;
						}
					}
				}
			}

			public void keyReleased(KeyEvent e) {

				if (!game.endGame() && focus) {
					if (e.getKeyCode() == pressUP) {
						game.getWorld().getCar().setUP(false);
					}

					if (e.getKeyCode() == pressDOWN) {
						game.getWorld().getCar().setDOWN(false);
					}
					if (e.getKeyCode() == pressLEFT) {
						game.getWorld().getCar().setLEFT(false);
					}

					if (e.getKeyCode() == pressRIGHT) {
						game.getWorld().getCar().setRIGHT(false);
					}
				}
			}

		});

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNameTrack() {
		return nameTrack;
	}

	public void setNameTrack(String nameTrack) {
		this.nameTrack = nameTrack;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	private void paintWorldImage(final Graphics g) {

		for (int i = 0; i < World.X_MATRIX_STRING; i++) {
			for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
				if ("horizontal".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getHorizontal(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("vertical".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getVertical(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("curveleftup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getUpLeft(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("curveleftdown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getDownLeft(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("curverightup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getUpRigth(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("curverightdown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getDownRight(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("grass".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getGrass(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("starthorizontalright".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getStart(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("starthorizontalleft".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getStart(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("startverticalup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getStartVertical(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
				if ("startverticaldown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getStartVertical(), j * AbstractBlockRoadObject.getSize() + I,
							i * AbstractBlockRoadObject.getSize() + J, null);
				}
			}
		}
	}

	private void moveCamera() {
		x = (((int) game.getWorld().getCar().getX1() + (int) game.getWorld().getCar().getX3()) / 2) + I;

		y = (((int) game.getWorld().getCar().getY1() + (int) game.getWorld().getCar().getY2()) / 2) + J;

		if (x > (int) (dim.width - BordVisual))
			I -= x - (int) (dim.width - BordVisual);

		if (x < BordVisual) {
			I += BordVisual - x;
		}
		if (y > (int) (dim.height - BordVisual)) {
			J -= y - (dim.height - BordVisual);
		}
		if (y < BordVisual) {
			J += BordVisual - y;
		}
	}

	public void paintWorld(final Graphics g) { // per il debugging
		// //////*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/

		int width = World.X_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		int height = World.Y_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		double dimention = 1.0;
		for (double i = 0; i < width; i++) {
			for (double j = 0; j < height; j++) {

				if (game.getWorld().getMatrixWorld().getValuePosition((int) (i / dimention),
						(int) (j / dimention)) == 1) {
					g.setColor(Color.GREEN);
					g.fillRect((int) j + I, (int) i + J, 1, 1);
				}
				if (game.getWorld().getMatrixWorld().getValuePosition((int) (i / dimention),
						(int) (j / dimention)) == 2) {
					g.setColor(Color.RED);
					g.fillRect((int) j + I, (int) i + J, 1, 1);
				}

			}
		}
	}

	private void paintCornice(final Graphics g) {

		int x = I - AbstractBlockRoadObject.getSize();
		int y = J - AbstractBlockRoadObject.getSize();

		g.drawImage(ImageProvider.getBoardEdgeUpLeft(), x, y, null);

		g.drawImage(ImageProvider.getBoardEdgeDownRight(),
				World.Y_MATRIX_STRING * AbstractBlockRoadObject.getSize() + x + AbstractBlockRoadObject.getSize(),
				World.X_MATRIX_STRING * AbstractBlockRoadObject.getSize() + y + AbstractBlockRoadObject.getSize(),
				null);

		g.drawImage(ImageProvider.getBoardEdgeUpRight(),
				World.Y_MATRIX_STRING * AbstractBlockRoadObject.getSize() + x + AbstractBlockRoadObject.getSize(), y,
				null);

		g.drawImage(ImageProvider.getBoardEdgeDownLeft(), x,
				World.X_MATRIX_STRING * AbstractBlockRoadObject.getSize() + y + AbstractBlockRoadObject.getSize(),
				null);

		for (int i = 0; i < World.Y_MATRIX_STRING; i++) {
			g.drawImage(ImageProvider.getBoardHigh(),
					i * AbstractBlockRoadObject.getSize() + x + AbstractBlockRoadObject.getSize(), 0 + y, null);

			g.drawImage(ImageProvider.getBoardDown(),
					i * AbstractBlockRoadObject.getSize() + x + AbstractBlockRoadObject.getSize(),
					(World.X_MATRIX_STRING + 1) * AbstractBlockRoadObject.getSize() + y, null);

		}

		for (int i = 0; i < World.X_MATRIX_STRING; i++) {
			g.drawImage(ImageProvider.getBoardLeft(), 0 + x,
					i * AbstractBlockRoadObject.getSize() + y + AbstractBlockRoadObject.getSize(), null);

			g.drawImage(ImageProvider.getBoardRight(),
					(World.Y_MATRIX_STRING + 1) * AbstractBlockRoadObject.getSize() + x,
					i * AbstractBlockRoadObject.getSize() + y + AbstractBlockRoadObject.getSize(), null);

		}

	}

	private void paintMiniWorld(final Graphics g) {

		int size = 40;
		int I = dim.width - size * World.Y_MATRIX_STRING;
		int J = 0;

		for (int i = 0; i < World.X_MATRIX_STRING; i++) {
			for (int j = 0; j < World.Y_MATRIX_STRING; j++) {

				if ("horizontal".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinihorizontal(), j * size + I, i * size + J, null);
				}
				if ("vertical".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinivertical(), j * size + I, i * size + J, null);
				}
				if ("curveleftup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMiniLeftup(), j * size + I, i * size + J, null);
				}
				if ("curveleftdown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMiniLeftdown(), j * size + I, i * size + J, null);
				}
				if ("curverightup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMiniRigthup(), j * size + I, i * size + J, null);
				}
				if ("curverightdown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMiniRightdown(), j * size + I, i * size + J, null);
				}
				if ("grass".equals(game.getWorld().getMatrixString()[i][j])) {
					// g.drawImage(ImageProvider.getMinigrass(),
					// j*size+I,i*size+J, null);
				}
				if ("starthorizontalright".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinistarthorizontal(), j * size + I, i * size + J, null);
				}
				if ("starthorizontalleft".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinistarthorizontal(), j * size + I, i * size + J, null);
				}
				if ("startverticalup".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinistartvertical(), j * size + I, i * size + J, null);
				}
				if ("startverticaldown".equals(game.getWorld().getMatrixString()[i][j])) {
					g.drawImage(ImageProvider.getMinistartvertical(), j * size + I, i * size + J, null);
				}
			}
		}

		if(game.getWorld().getCar2()!=null){

			g.setColor(Color.RED);

			g.fillRect((int) (game.getWorld().getCar2().getX1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ I),
					(int) (game.getWorld().getCar2().getY1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ J),6, 6);
		}


		if(game.getWorld().getCar3()!=null){
			g.setColor(Color.GRAY.brighter());

			g.fillRect((int) (game.getWorld().getCar3().getX1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ I),
					(int) (game.getWorld().getCar3().getY1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ J),6, 6);
		}

		if(game.getWorld().getCar4()!=null){
			g.setColor(Color.BLACK);

			g.fillRect((int) (game.getWorld().getCar4().getX1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ I),
					(int) (game.getWorld().getCar4().getY1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)+ J),6, 6);
		}

		g.setColor(Color.YELLOW);

		g.fillRect(
				(int) (game.getWorld().getCar().getX1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)
						+ I),
				(int) (game.getWorld().getCar().getY1() / ((double) AbstractBlockRoadObject.getSize() / (double) size)
						+ J),
				6, 6);

	}

	public void paintDebugCar(final Graphics g) {

		// coordinate macchina ruotate
		g.setColor(Color.WHITE);
		g.drawLine((int) game.getWorld().getCar().getX1rot() + I, (int) game.getWorld().getCar().getY1rot() + J,
				(int) game.getWorld().getCar().getX2rot() + I, (int) game.getWorld().getCar().getY2rot() + J);
		g.drawLine((int) game.getWorld().getCar().getX1rot() + I, (int) game.getWorld().getCar().getY1rot() + J,
				(int) game.getWorld().getCar().getX3rot() + I, (int) game.getWorld().getCar().getY3rot() + J);
		g.drawLine((int) game.getWorld().getCar().getX2rot() + I, (int) game.getWorld().getCar().getY2rot() + J,
				(int) game.getWorld().getCar().getX4rot() + I, (int) game.getWorld().getCar().getY4rot() + J);
		g.setColor(Color.RED);
		g.drawLine((int) game.getWorld().getCar().getX3rot() + I, (int) game.getWorld().getCar().getY3rot() + J,
				(int) game.getWorld().getCar().getX4rot() + I, (int) game.getWorld().getCar().getY4rot() + J);

		// coordinate macchina fisse
		g.setColor(Color.WHITE);
		g.drawLine((int) game.getWorld().getCar().getX1() + I, (int) game.getWorld().getCar().getY1() + J,
				(int) game.getWorld().getCar().getX2() + I, (int) game.getWorld().getCar().getY2() + J);
		g.drawLine((int) game.getWorld().getCar().getX1() + I, (int) game.getWorld().getCar().getY1() + J,
				(int) game.getWorld().getCar().getX3() + I, (int) game.getWorld().getCar().getY3() + J);
		g.drawLine((int) game.getWorld().getCar().getX2() + I, (int) game.getWorld().getCar().getY2() + J,
				(int) game.getWorld().getCar().getX4() + I, (int) game.getWorld().getCar().getY4() + J);
		g.setColor(Color.RED);
		g.drawLine((int) game.getWorld().getCar().getX3() + I, (int) game.getWorld().getCar().getY3() + J,
				(int) game.getWorld().getCar().getX4() + I, (int) game.getWorld().getCar().getY4() + J);

		g.setColor(Color.WHITE);
		g.drawLine((int) game.getWorld().getCar2().getX1rot() + I, (int) game.getWorld().getCar2().getY1rot() + J,
				(int) game.getWorld().getCar2().getX2rot() + I, (int) game.getWorld().getCar2().getY2rot() + J);
		g.drawLine((int) game.getWorld().getCar2().getX1rot() + I, (int) game.getWorld().getCar2().getY1rot() + J,
				(int) game.getWorld().getCar2().getX3rot() + I, (int) game.getWorld().getCar2().getY3rot() + J);
		g.drawLine((int) game.getWorld().getCar2().getX2rot() + I, (int) game.getWorld().getCar2().getY2rot() + J,
				(int) game.getWorld().getCar2().getX4rot() + I, (int) game.getWorld().getCar2().getY4rot() + J);
		g.setColor(Color.RED);
		g.drawLine((int) game.getWorld().getCar2().getX3rot() + I, (int) game.getWorld().getCar2().getY3rot() + J,
				(int) game.getWorld().getCar2().getX4rot() + I, (int) game.getWorld().getCar2().getY4rot() + J);

		// coordinate macchina fisse
		g.setColor(Color.WHITE);
		g.drawLine((int) game.getWorld().getCar2().getX1() + I, (int) game.getWorld().getCar2().getY1() + J,
				(int) game.getWorld().getCar2().getX2() + I, (int) game.getWorld().getCar2().getY2() + J);
		g.drawLine((int) game.getWorld().getCar2().getX1() + I, (int) game.getWorld().getCar2().getY1() + J,
				(int) game.getWorld().getCar2().getX3() + I, (int) game.getWorld().getCar2().getY3() + J);
		g.drawLine((int) game.getWorld().getCar2().getX2() + I, (int) game.getWorld().getCar2().getY2() + J,
				(int) game.getWorld().getCar2().getX4() + I, (int) game.getWorld().getCar2().getY4() + J);
		g.setColor(Color.RED);
		g.drawLine((int) game.getWorld().getCar2().getX3() + I, (int) game.getWorld().getCar2().getY3() + J,
				(int) game.getWorld().getCar2().getX4() + I, (int) game.getWorld().getCar2().getY4() + J);

	}

	private void paintPanelGame(Graphics2D g) {
		g.drawImage(ImageProvider.getPanelGame(), 0, 0, null);

		Font font = new Font("ARIAL", 50, 40);
		g.setFont(font);
		g.setColor(Color.BLACK);

		g.drawString("Track", 40, 40);

		g.drawString(nameTrack, 40, 90);

		g.drawString("Km/h " + (int) (game.getWorld().getCar().getSpeed() * 50), 480, 90);

		g.drawString((int) (game.getCarManagerHuman().getCheckpoints().getActualLaps()) + "/"
				+ game.getCarManagerHuman().getCheckpoints().getTotalLaps() + " Laps", 480, 40);

		g.drawString("Time " + actualCalendar.get(Calendar.MINUTE) + ":" + actualCalendar.get(Calendar.SECOND) + ":"
				+ actualCalendar.get(Calendar.MILLISECOND), 700, 90);

		GregorianCalendar tmpCalendar = new GregorianCalendar();

		if (recordTrack > 0) {
			tmpCalendar.setTimeInMillis(recordTrack);
			g.drawString("Best  " + tmpCalendar.get(Calendar.MINUTE) + ":" + tmpCalendar.get(Calendar.SECOND) + ":"
					+ tmpCalendar.get(Calendar.MILLISECOND), 700, 40);
		} else {
			g.drawString("Best  --:--:--", 700, 40);
		}
	}

	private void paintCar(Graphics2D g) {

		// stampo le macchine artificiali se non sono nulle
		if(game.getWorld().getCar2()!=null){
			Graphics2D graphics2d2 = (Graphics2D) g.create();

			graphics2d2.rotate(game.getWorld().getCar2().getAngle(),
					(((game.getWorld().getCar2().getX3() - game.getWorld().getCar2().getX1()) / 3)
							+ game.getWorld().getCar2().getX1() + I),
					(((game.getWorld().getCar2().getY2() - game.getWorld().getCar2().getY1()) / 2)
							+ game.getWorld().getCar2().getY1() + J));

			graphics2d2.drawImage(ImageProvider.getCar2(), (int) game.getWorld().getCar2().getX1() + I,
					(int) game.getWorld().getCar2().getY1() + J, null);
		}

		if(game.getWorld().getCar3()!=null){
			Graphics2D graphics2d3 = (Graphics2D) g.create();

			graphics2d3.rotate(game.getWorld().getCar3().getAngle(),
					(((game.getWorld().getCar3().getX3() - game.getWorld().getCar3().getX1()) / 3)
							+ game.getWorld().getCar3().getX1() + I),
					(((game.getWorld().getCar3().getY2() - game.getWorld().getCar3().getY1()) / 2)
							+ game.getWorld().getCar3().getY1() + J));

			graphics2d3.drawImage(ImageProvider.getCar3(), (int) game.getWorld().getCar3().getX1() + I,
					(int) game.getWorld().getCar3().getY1() + J, null);
		}

		if(game.getWorld().getCar4()!=null){
			Graphics2D graphics2d4 = (Graphics2D) g.create();

			graphics2d4.rotate(game.getWorld().getCar4().getAngle(),
					(((game.getWorld().getCar4().getX3() - game.getWorld().getCar4().getX1()) / 3)
							+ game.getWorld().getCar4().getX1() + I),
					(((game.getWorld().getCar4().getY2() - game.getWorld().getCar4().getY1()) / 2)
							+ game.getWorld().getCar4().getY1() + J));

			graphics2d4.drawImage(ImageProvider.getCar4(), (int) game.getWorld().getCar4().getX1() + I,
					(int) game.getWorld().getCar4().getY1() + J, null);

		}
		
		//stampo la macchina del giocatore
		Graphics2D graphics2d = (Graphics2D) g.create();

		graphics2d.rotate(game.getWorld().getCar().getAngle(),
				(((game.getWorld().getCar().getX3() - game.getWorld().getCar().getX1()) / 3)
						+ game.getWorld().getCar().getX1() + I),
				(((game.getWorld().getCar().getY2() - game.getWorld().getCar().getY1()) / 2)
						+ game.getWorld().getCar().getY1() + J));

		graphics2d.drawImage(ImageProvider.getCar(), (int) game.getWorld().getCar().getX1() + I,
				(int) game.getWorld().getCar().getY1() + J, null);
	}

	private void paintCountDown(Graphics2D g) {

		if (number3) {

			g.drawImage(ImageProvider.getNumber3(), (dim.width / 2) - 100, (dim.height / 2) - 100, null);
		}
		if (number2) {

			g.drawImage(ImageProvider.getNumber2(), (dim.width / 2) - 100, (dim.height / 2) - 100, null);
		}
		if (number1) {

			g.drawImage(ImageProvider.getNumber1(), (dim.width / 2) - 100, (dim.height / 2) - 100, null);
		}
		if (go) {

			g.drawImage(ImageProvider.getGo(), (dim.width / 2) - 100, (dim.height / 2) - 100, null);
		}
	}

	public void paintCheckPoint(Graphics2D g) { /* FOR DEBUG */
		int width = World.X_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		int height = World.Y_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		double dimention = 1.0;
		for (double i = 0; i < width; i++) {
			for (double j = 0; j < height; j++) {
				if (game.getWorld().getMatrixWorld().getValuePosition((int) (i / dimention),
						(int) (j / dimention)) >= BlockRoadObject.CHECKPOINT) {
					g.setColor(Color.BLACK);
					g.fillRect((int) j + I, (int) i + J, 1, 1);
				}
			}
		}
	}

	public void paintStart(Graphics2D g) { /* FOR DEBUG */
		int width = World.X_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		int height = World.Y_MATRIX_STRING * AbstractBlockRoadObject.getSize();
		double dimention = 1.0;
		for (double i = 0; i < width; i++) {
			for (double j = 0; j < height; j++) {
				if (game.getWorld().getMatrixWorld().getValuePosition((int) (i / dimention),
						(int) (j / dimention)) == BlockRoadObject.START) {
					g.setColor(Color.WHITE);
					g.fillRect((int) j + I, (int) i + J, 1, 1);
				}
			}
		}
	}

	public void restartGamePanel() {

		game = new GameManager();
		time();
		recordTrack=LevelLoadSave.load(path,nameTrack,game);
		game.getWorld().makeWorld();
		game.init();
		// game.getWorld().getCheckpoints().setFalseAllCheckPoint();
		setStart(true);
		dialogFinish = true;

	}

	public GameManager getGame() {
		return game;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void restartTime() {
		startCalendar = new GregorianCalendar();
		actualCalendar = new GregorianCalendar();
		actualCalendar.setTimeInMillis(0);
		tmp = new GregorianCalendar();
		diff = 0;

	}

	public boolean isStart() {
		return start;
	}

	public void threadGamepanel(MenuFrame frame, GamePanel gamePanel) {

		new Thread() {
			@Override
			public void run() {

				while (true) {

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gamePanel.repaint();
					if (game.endGame() && dialogFinish) {
						dialogFinish = false;
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								JDialogFinishTrack dialogOptions = new JDialogFinishTrack(frame, gamePanel);
								dialogOptions.setVisible(true);
								// game.setUpdate(false);
								// pause=true;

							}
						});
					}

				}
			}
		}.start();

	}

	public void time() {

		new Thread() {
			@Override
			public void run() {
				game.setUpdate(false);
				pause = true;
				focus = false;
				boolean gameFinish = false;// variabile necessaria per fermare
				// la chiamata ripetuta nel thread
				// di saveRecord
				startCalendar = new GregorianCalendar();
				actualCalendar = new GregorianCalendar();
				actualCalendar.setTimeInMillis(0);
				tmp = new GregorianCalendar();

				start = true;

				while (!game.endGame()) {

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (start) {
						try {
							number3 = true;
							sound("countDown.wav");
							Thread.sleep(1000);

							number3 = false;
							number2 = true;
							sound("countDown.wav");
							Thread.sleep(1000);

							number2 = false;
							number1 = true;
							sound("countDown.wav");
							Thread.sleep(1000);

							number1 = false;
							go = true;
							game.setUpdate(true);
							pause = false;
							focus = true;
							sound("RaceGo.wav");
							Thread.sleep(400);

							go = false;

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						start = false;
						restartTime();

					}

					if (!pause) {
						actualCalendar = new GregorianCalendar();
						actualCalendar.setTimeInMillis(
								(actualCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) - diff);
						tmp = actualCalendar;

					}
					if (pause) {
						tmp = new GregorianCalendar();
						tmp.setTimeInMillis(tmp.getTimeInMillis() - startCalendar.getTimeInMillis());
						diff = tmp.getTimeInMillis() - actualCalendar.getTimeInMillis();
					}
					if (game.endGame() && !gameFinish) {
						recordTrack=LevelLoadSave.saveRecord(actualCalendar.getTimeInMillis(),nameTrack);

						game.getWorld().getCar().setUP(false);
						game.getWorld().getCar().setDOWN(false);
						game.getWorld().getCar().setRIGHT(false);
						game.getWorld().getCar().setLEFT(false);

						gameFinish = true;

						game.setUpdate(false);

					}
				}
			}
		}.start();
	}

	private void sound(String nameSound) {
		Clip clip;
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("resource/fileSound/" + nameSound)));
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	public GregorianCalendar getActualCalendar() {
		return actualCalendar;
	}

	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;

		g.setRenderingHints(renderingHints);

		moveCamera();

		paintWorldImage(g);

		paintCornice(g);

		if (minimap) {
			paintMiniWorld(g);
		}
		// paintCheckPoint(g); //debug
		// paintStart(g); //debug
		// paintWorld(g); //debug
		// paintDebugCar(g); // debug



		paintCar(g);
		paintPanelGame(g);
		paintCountDown(g);
	}
}
