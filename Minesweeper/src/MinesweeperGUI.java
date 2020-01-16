import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import java.util.Random;

public class MinesweeperGUI extends JFrame {
	public JButton buttonAlgorithem;
	private MinesButton[][] field;
	private JPanel canvas;
	private int canvasX;
	private int canvasY;
	private int numOfBombs;
	private Random rnd;
	private JLabel flagCount;

	MinesweeperGUI(int width, int height, int bombs) {
		super("Minesweeper");

		canvas = new JPanel();
		rnd = new Random();
		flagCount = new JLabel();

		setSize(width, height);
		canvasX = width / 20;
		canvasY = height / 20;
		if (bombs > 0 && bombs <= (canvasX * canvasY)) {
			numOfBombs = bombs;
		} else {
			numOfBombs = (canvasX * canvasY) / 8;
		}
		field = new MinesButton[canvasX][canvasY];

		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		canvas.add(flagCount);
		canvas.setLayout(new GridLayout(canvasX, canvasY));

		placeComponents2();
		placeNumbers();
		addActionListerToButtons();
		//uncoverAll();
		easyStart();
		printGameInfo();

		setVisible(true);
		
	}

	// easy 	0.15 % bombs
	// medium 	0.21111111 % bombs
	// hard 	0.37222222222 % bombs
	void printGameInfo() {
		System.out.println("You're playing with " + numOfBombs + " Bombs");
		double diff = (double) numOfBombs / ((double) canvasX * (double) canvasY);
		if (0 <= diff && diff <= 0.15) {
			System.out.println("Difficulty: Easy");
		} else if (0.15 < diff && diff <= 0.2111) {
			System.out.println("Difficulty: Medium");
		} else if (0.2111 < diff && diff <= 0.37222) {
			System.out.println("Difficulty: Hard");
		} else if (0.37222 < diff) {
			System.out.println("Difficulty: Very Hard");
		}
	}

	void addActionListerToButtons() {
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				final int x = i;
				final int y = j;
				field[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1 && field[x][y].uncoverd == false) { // Button 1

							if (field[x][y].flagged) {
								return;
							}

							System.out.println("Button 1");
							if (field[x][y].currentObject.equals("tile")) { // tile
								System.out.println("tile");
								uncover(x, y);
								return;
							} else if (field[x][y].currentObject.equals("bomb")) { // bomb
								System.out.println("bombRed");
								field[x][y].changeIcon("bombRed");
								field[x][y].uncoverd = true;
							} else { // number
								System.out.println("number");
								field[x][y].changeIcon(field[x][y].currentObject);
								field[x][y].uncoverd = true;
							}

						} else if (e.getButton() == MouseEvent.BUTTON3 && field[x][y].uncoverd == false) { // Button 3
																											// done
							if (field[x][y].flagged) {
								field[x][y].flagged = false;
								field[x][y].changeIcon("tile");
							} else {
								field[x][y].flagged = true;
								field[x][y].changeIcon("flag");
							}
						}
					}
				});
			}
		}
	}

	void easyStart() {
		for (int i = 10; i < canvasX; i++) {
			for (int j = 10; j < canvasY; j++) {
				if (field[i][j].currentObject.equals("tile")) {
					uncover(i, j);
					return;
				}
			}
		}
	}

	void uncoverAll() {
		canvas.removeAll();
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j].currentObject.equals("tile")) {
					field[i][j] = new MinesButton("tileEmpty", "tileEmpty", i, j);
					field[i][j].uncoverd = true;
					canvas.add(field[i][j]);
				} else {
					field[i][j] = new MinesButton(field[i][j].currentObject, field[i][j].currentObject, i, j);
					field[i][j].uncoverd = true;
					canvas.add(field[i][j]);
				}
			}
		}
		this.add(canvas);
	}

	void placeNumbers() {
		JPanel canvas2 = new JPanel();
		canvas2.setLayout(new GridLayout(canvasX, canvasY));
		int a = 0;
		for (int j = 0; j < canvasY; j++) {
			for (int i = 0; i < canvasX; i++) {
				if (field[i][j].currentObject.equals("tile")) {
					if (i > 0 && j > 0 && field[i - 1][j - 1].currentObject.equals("bomb")) { // oben links
						a++;
					}
					if (j > 0 && field[i][j - 1].currentObject.equals("bomb")) { // oben mitte
						a++;
					}
					if (i < canvasX - 1 && j > 0 && field[i + 1][j - 1].currentObject.equals("bomb")) { // oben rechts
						a++;
					}
					if (i > 0 && j < canvasY - 1 && field[i - 1][j + 1].currentObject.equals("bomb")) { // unten links
						a++;
					}
					if (j < canvasY - 1 && field[i][j + 1].currentObject.equals("bomb")) { // unten mitte
						a++;
					}
					if (i < canvasX - 1 && j < canvasY - 1 && field[i + 1][j + 1].currentObject.equals("bomb")) { // unten
																													// rechts
						a++;
					}
					if (i > 0 && field[i - 1][j].currentObject.equals("bomb")) { // mitte links
						a++;
					}
					if (i < canvasX - 1 && field[i + 1][j].currentObject.equals("bomb")) { // mitte rechts
						a++;
					}
				}
				if (a > 0) {
					field[i][j] = new MinesButton("tile", "num" + a, i, j);
					canvas2.add(field[i][j]);
					canvas.remove(field[i][j]);
					a = 0;
				} else if (!field[i][j].currentObject.equals("bomb")) {
					field[i][j] = new MinesButton("tile", "tile", i, j);
					canvas.remove(field[i][j]);
					canvas2.add(field[i][j]);
				} else {
					canvas.remove(field[i][j]);
					canvas2.add(field[i][j]);
					canvas2.add(field[i][j]);
				}
			}
		}

		canvas = canvas2;
		add(canvas);
		canvas.setSize(this.WIDTH + 20, this.HEIGHT + 20);
	}

	void placeComponents2() {
		canvas.removeAll();
		for (int i = 0; i < numOfBombs;) {
			int rndX = rnd.nextInt(canvasX);
			int rndY = rnd.nextInt(canvasY);
			if (field[rndX][rndY] == null) {
				field[rndX][rndY] = new MinesButton("tile", "bomb", rndX, rndY);
				i++;
			}
		}
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j] == null) {
					field[i][j] = new MinesButton("tile", "tile", i, j);
				}
			}
		}
	}

	void placeComponents() {
		canvas.removeAll();
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j] == null) {
					int a = rnd.nextInt((canvasX * canvasY) / numOfBombs);
					if (a == 1 || a == 1) {
						field[i][j] = new MinesButton("tile", "bomb", i, j);
					} else {
						field[i][j] = new MinesButton("tile", "tile", i, j);
					}
				}
			}
		}
	}

	void uncover(int x, int y) {
		if (x >= 0 && y >= 0 && x < canvasX && y < canvasY && field[x][y].currentObject.equals("tile")
				&& field[x][y].uncoverd == false) {
			field[x][y].changeIcon("tileEmpty");
			field[x][y].uncoverd = true;
			if (x < canvasX) {
				uncover(x + 1, y);
			}
			if (x > 0) {
				uncover(x - 1, y);
			}
			if (y < canvasY) {
				uncover(x, y + 1);
			}
			if (y > 0) {
				uncover(x, y - 1);
			}
			if (x < canvasX && y > 0) {
				uncover(x + 1, y - 1);
			}
			if (x < canvasX && y < canvasY) {
				uncover(x + 1, y + 1);
			}
			if (x > 0 && y > 0) {
				uncover(x - 1, y - 1);
			}
			if (x > 0 && y < canvasY) {
				uncover(x - 1, y + 1);
			}

		}
		if (x >= 0 && y >= 0 && x < canvasX && y < canvasY && !field[x][y].currentObject.equals("tile") && field[x][y].uncoverd == false) {
			field[x][y].changeIcon(field[x][y].currentObject);
			field[x][y].uncoverd = true;
		}
	}
}