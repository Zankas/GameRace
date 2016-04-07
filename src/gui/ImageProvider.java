package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.AbstractBlockRoadObject;
import editor.EditorPanel;

public class ImageProvider {

	private static Image play1;

	private static Image play2;

	private static Image editor1;

	private static Image editor2;

	private static Image options1;

	private static Image options2;

	private static Image esc1;

	private static Image esc2;

	private static Image background;

	private static Image info;

	private static Image title;

	private static Image cursor;

	private static Image cursorPencilCopy;

	private static Image car_icon;

	private static Image basiclevel1;

	private static Image basiclevel2;

	private static Image trackscreated1;

	private static Image trackscreated2;

	private static Image goback;

	private static Image gobackPressed;

	public static Image getGobackPressed() {
		return gobackPressed;
	}

	public static Image getStartHorizontal() {
		return startHorizontal;
	}

	private static Image level1;

	private static Image level2;

	///////// USO IMMAGINI PER L'EDITOR

	private static Image starthorizontalrightEditor;

	private static Image starthorizontalleftEditor;

	private static Image startverticalupEditor;

	private static Image startverticaldownEditor;

	private static Image horizontalEditor;

	private static Image verticalEditor;

	private static Image upRigthEditor;

	private static Image upLeftEditor;

	private static Image downRightEditor;

	private static Image downLeftEditor;

	private static Image grassEditor;

	///////////////

	private static Image starthorizontalright;

	private static Image starthorizontalleft;

	private static Image startverticalup;

	private static Image startverticaldown;

	private static Image startHorizontal;

	private static Image startVertical;

	private static Image horizontal;

	private static Image vertical;

	private static Image upRigth;

	private static Image upLeft;

	private static Image downRight;

	private static Image downLeft;

	private static Image grass;

	private static Image car;

	private static Image car2;

	private static Image car3;

	private static Image car4;

	private static Image clear;

	private static Image save;

	private static Image load;

	private static Image bottomEsc;

	///// miniMap

	private static Image panelGame;

	private static Image ministarthorizontal;

	private static Image ministartvertical;

	private static Image minihorizontal;

	private static Image minivertical;

	private static Image miniRigthup;

	private static Image miniLeftup;

	private static Image miniRightdown;

	private static Image miniLeftdown;

	private static Image minigrass;

	///////////// img JdialogMenu

	private static Image resume;

	private static Image resumePressed;

	private static Image restart;

	private static Image restartPressed;

	private static Image tracks;

	private static Image tracksPressed;

	private static Image sound;

	private static Image soundPressed;

	private static Image esc;

	private static Image escPressed;

	private static Image xEsc;

	private static Image xEscPressed;

	private static Image number3;

	private static Image number2;

	private static Image number1;

	private static Image go;

	private static Image boardHigh;

	private static Image boardDown;

	private static Image boardRight;

	private static Image boardLeft;

	private static Image boardEdgeUpLeft;

	private static Image boardEdgeUpRight;

	private static Image boardEdgeDownLeft;

	private static Image boardEdgeDownRight;

	private static Image panelInfo;

	private static Image arrows;

	private static Image wasd;

	public static Image getNumber3() {
		return number3;
	}

	public static Image getNumber2() {
		return number2;
	}

	public static Image getNumber1() {
		return number1;
	}

	public static Image getGo() {
		return go;
	}

	public static Image getxEsc() {
		return xEsc;
	}

	public static Image getxEscPressed() {
		return xEscPressed;
	}

	private static Image menuDialog;

	private static Image menuFinishDialog;

	static {
		try {

			play1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/play99.png"));

			play2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/play88.png"));

			editor1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/editor2.png"));

			editor2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/editor1.png"));

			options1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/options1.png"));

			options2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/options2.png"));

			esc1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/exit1.png"));

			esc2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/exit2.png"));

			background = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/sfondo.jpg"));

			info = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Info-icon.png"));

			title = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Logo.png"));

			cursor = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Cursor.png"));

			cursorPencilCopy = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/cursorDraw.png"));

			car_icon = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Car-icon.png"));

			basiclevel1 = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/basiclevel.png"));

			basiclevel2 = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/basiclevel2.png"));

			trackscreated1 = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/trackscreated.png"));

			trackscreated2 = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/trackscreated2.png"));

			goback = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Back.png"));

			gobackPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Back_Pressed.png"));

			level1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/iconTrack.png"))
					.getScaledInstance(resizeX(200), resizeY(200), java.awt.Image.SCALE_SMOOTH);

			level2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/iconTrack2.png"))
					.getScaledInstance(resizeX(200), resizeY(200), java.awt.Image.SCALE_SMOOTH);

			///////////////////// immagini editor

			starthorizontalrightEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startHorizontalRight.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			starthorizontalleftEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startHorizontalLeft.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			startverticalupEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startVerticalUp.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			startverticaldownEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startVerticalDown.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			horizontalEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/horizontalR.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			verticalEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/verticalR.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			upRigthEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/upRight.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			upLeftEditor = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/upLeft.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			downRightEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/downRight.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			downLeftEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/downLeft.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			grassEditor = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/grassRealistic.jpg"))
					.getScaledInstance(resizeX(EditorPanel.DIMENSION_BLOCK), resizeX(EditorPanel.DIMENSION_BLOCK),
							java.awt.Image.SCALE_SMOOTH);
			/////////////////////////////////// fine immagini editor

			starthorizontalright = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startHorizontalRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			starthorizontalleft = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startHorizontalLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			startverticalup = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startVerticalUp.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			startverticaldown = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startVerticalDown.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			startHorizontal = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startHorizontal.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			startVertical = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/startVertical.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			horizontal = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/horizontalR.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			vertical = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/verticalR.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			upRigth = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/upRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			upLeft = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/upLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			downRight = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/downRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			downLeft = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/downLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);
			grass = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/grassRealistic.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			car = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/car.gif"))
					.getScaledInstance(60, 30, java.awt.Image.SCALE_SMOOTH);
			
			car2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/car2.gif"))
					.getScaledInstance(60, 30, java.awt.Image.SCALE_SMOOTH);
			
			car3 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/car3.gif"))
					.getScaledInstance(60, 30, java.awt.Image.SCALE_SMOOTH);
			
			car4 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/car4.gif"))
					.getScaledInstance(60, 30, java.awt.Image.SCALE_SMOOTH);
			
			clear = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/clear.png"))
					.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

			save = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/save.png"))
					.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

			load = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/load.png"))
					.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
			bottomEsc = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/esc.png"))
					.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

			// miniMap

			panelGame = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/HUD.png"));

			ministarthorizontal = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ministarthorizontal.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			ministartvertical = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ministartvertical.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			minihorizontal = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minihorizontal.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			minivertical = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minivertical.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			miniRigthup = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minirightup.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			miniLeftup = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minileftup.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			miniRightdown = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minirightdown.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			miniLeftdown = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/minileftdown.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			minigrass = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/minigrass.png"))
					.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

			////////////////// img Jdialomenu

			resume = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Resume.png"));

			resumePressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Resume-Pressed.png"));

			resume = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Resume.png"));

			restart = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Restart.png"));

			restartPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Restart-Pressed.png"));

			tracks = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Tracks.png"));

			tracksPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Tracks-Pressed.png"));

			sound = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Sound.png"));

			soundPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Sound-Pressed.png"));

			esc = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Esc.png"));

			escPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/Esc_Pressed.png"));

			xEsc = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/X.png"));

			xEscPressed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/X-Pressed.png"));

			menuDialog = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Menu.png"));

			menuFinishDialog = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/MenuFinish.png"));

			number3 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/3.png"));

			number2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/2.png"));

			number1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/1.png"));

			go = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/go.png"));

			boardHigh = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/boardHigh.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardDown = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/boardDown.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardRight = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/boardRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardLeft = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/boardLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardEdgeUpLeft = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/boardEdgeUpLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardEdgeUpRight = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/boardEdgeUpRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardEdgeDownLeft = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/boardEdgeDownLeft.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			boardEdgeDownRight = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/boardEdgeDownRight.jpg"))
					.getScaledInstance(AbstractBlockRoadObject.getSize(), AbstractBlockRoadObject.getSize(),
							java.awt.Image.SCALE_SMOOTH);

			panelInfo = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/panelInfo.png"));

			arrows = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecce.png"));

			wasd = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/WASD.png"));

		} catch (final IOException e) {

			e.printStackTrace();
		}
	}

	public static Image getClear() {
		return clear;
	}

	public static Image getMinistarthorizontal() {
		return ministarthorizontal;
	}

	public static Image getMinistartvertical() {
		return ministartvertical;
	}

	public static Image getMinihorizontal() {
		return minihorizontal;
	}

	public static Image getMinivertical() {
		return minivertical;
	}

	public static Image getMiniRigthup() {
		return miniRigthup;
	}

	public static Image getMiniLeftup() {
		return miniLeftup;
	}

	public static Image getMiniRightdown() {
		return miniRightdown;
	}

	public static Image getMiniLeftdown() {
		return miniLeftdown;
	}

	public static Image getMinigrass() {
		return minigrass;
	}

	public static Image getCursorPencilCopy() {
		return cursorPencilCopy;
	}

	public static Image getStarthorizontalrightEditor() {
		return starthorizontalrightEditor;
	}

	public static Image getStarthorizontalleftEditor() {
		return starthorizontalleftEditor;
	}

	public static Image getStartverticalupEditor() {
		return startverticalupEditor;
	}

	public static Image getStartverticaldownEditor() {
		return startverticaldownEditor;
	}

	public static Image getHorizontalEditor() {
		return horizontalEditor;
	}

	public static Image getVerticalEditor() {
		return verticalEditor;
	}

	public static Image getUpRigthEditor() {
		return upRigthEditor;
	}

	public static Image getUpLeftEditor() {
		return upLeftEditor;
	}

	public static Image getDownRightEditor() {
		return downRightEditor;
	}

	public static Image getDownLeftEditor() {
		return downLeftEditor;
	}

	public static Image getGrassEditor() {
		return grassEditor;
	}

	public static Image getBottomEsc() {
		return bottomEsc;
	}

	public static Image getLoad() {
		return load;
	}

	public static Image getSave() {
		return save;
	}

	public static Image getLevel1() {
		return level1;
	}

	public static Image getLevel2() {
		return level2;
	}

	public static Image getBasiclevel2() {
		return basiclevel2;
	}

	public static Image getTrackscreated2() {
		return trackscreated2;
	}

	public static Image getTitle() {
		return title;
	}

	public static Image getCursor() {
		return cursor;
	}

	public static Image getCar_icon() {
		return car_icon;
	}

	public static Image getBasiclevel1() {
		return basiclevel1;
	}

	public static Image getTrackscreated1() {
		return trackscreated1;
	}

	public static Image getGoback() {
		return goback;
	}

	public static Image getPlay1() {
		return play1;
	}

	public static Image getPlay2() {
		return play2;
	}

	public static Image getEditor1() {
		return editor1;
	}

	public static Image getEditor2() {
		return editor2;
	}

	public static Image getOptions1() {
		return options1;
	}

	public static Image getOptions2() {
		return options2;
	}

	public static Image getEsc1() {
		return esc1;
	}

	public static Image getEsc2() {
		return esc2;
	}

	public static Image getBackground() {
		return background;
	}

	public static Image getInfo() {
		return info;
	}

	public static Image getStartVertical() {
		return startVertical;
	}

	public static Image getStarthorizontalright() {
		return starthorizontalright;
	}

	public static Image getStarthorizontalleft() {
		return starthorizontalleft;
	}

	public static Image getStartverticalup() {
		return startverticalup;
	}

	public static Image getStartverticaldown() {
		return startverticaldown;
	}

	public static Image getStart() {
		return startHorizontal;
	}

	public static Image getGrass() {
		return grass;
	}

	public static Image getCar() {
		return car;
	}

	public static Image getCar2() {
		return car2;
	}

	public static Image getCar3() {
		return car3;
	}

	public static Image getCar4() {
		return car4;
	}

	public static Image getHorizontal() {
		return horizontal;
	}

	public static Image getVertical() {
		return vertical;
	}

	public static Image getUpRigth() {
		return upRigth;
	}

	public static Image getUpLeft() {
		return upLeft;
	}

	public static Image getDownRight() {
		return downRight;
	}

	public static Image getDownLeft() {
		return downLeft;
	}

	public static Image getResume() {
		return resume;
	}

	public static Image getResumePressed() {
		return resumePressed;
	}

	public static Image getRestart() {
		return restart;
	}

	public static Image getRestartPressed() {
		return restartPressed;
	}

	public static Image getTracks() {
		return tracks;
	}

	public static Image getTracksPressed() {
		return tracksPressed;
	}

	public static Image getSound() {
		return sound;
	}

	public static Image getSoundPressed() {
		return soundPressed;
	}

	public static Image getEsc() {
		return esc;
	}

	public static Image getEscPressed() {
		return escPressed;
	}

	public static Image getMenuDialog() {
		return menuDialog;
	}

	public static Image getPanelGame() {
		return panelGame;
	}

	public static Image getBoardHigh() {
		return boardHigh;
	}

	public static Image getBoardDown() {
		return boardDown;
	}

	public static Image getBoardRight() {
		return boardRight;
	}

	public static Image getBoardLeft() {
		return boardLeft;
	}

	public static Image getBoardEdgeUpLeft() {
		return boardEdgeUpLeft;
	}

	public static Image getBoardEdgeUpRight() {
		return boardEdgeUpRight;
	}

	public static Image getBoardEdgeDownLeft() {
		return boardEdgeDownLeft;
	}

	public static Image getBoardEdgeDownRight() {
		return boardEdgeDownRight;
	}

	public static Image getMenuFinishDialog() {

		return menuFinishDialog;
	}

	public static Image getPanelInfo() {
		return panelInfo;
	}

	public static Image getArrows() {
		return arrows;
	}

	public static Image getWasd() {
		return wasd;
	}

	public static int resizeX(int x) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		return ((int) dim.width * x) / 1366;
	}

	public static int resizeY(int y) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		return ((int) dim.height * y) / 768;
	}

}
