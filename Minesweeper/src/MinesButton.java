import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import java.awt.*;

public class MinesButton extends JButton{// implements ActionListener, MouseListener {
	ImageIcon tile, tileEmpty, bomb, flag, flagCross, bombRed, num1, num2, num3, num4, num5, num6, num7, num8;
	String currentIcon = "";
	String currentObject = "";
	boolean uncoverd = false;
	boolean flagged = false;
	//boolean recursion = false;
	int posX;
	int posY;

	int button;

	public MinesButton(String icon, String object, int x, int y) {
		posX = x;
		posY = y;
		tile = new ImageIcon("tile.png");
		tileEmpty = new ImageIcon("tile2.png");
		bomb = new ImageIcon("bomb.png");
		flag = new ImageIcon("flag.png");
		flagCross = new ImageIcon("flagCross.png");
		bombRed = new ImageIcon("bombRed.png");
		num1 = new ImageIcon("1one.png");
		num2 = new ImageIcon("2two.png");
		num3 = new ImageIcon("3three.png");
		num4 = new ImageIcon("4four.png");
		num5 = new ImageIcon("5five.png");
		num6 = new ImageIcon("6six.png");
		num7 = new ImageIcon("7seven.png");
		num8 = new ImageIcon("8eight.png");
		currentObject = object;
		changeIcon(icon);
		//this.addActionListener(this);
		//this.addMouseListener(this);

	}

	void changeIcon(String icon) {
		if (icon.equals("tile")) {
			this.setIcon(tile);
			currentIcon = icon;
		} else if (icon.equals("tileEmpty")) {
			this.setIcon(tileEmpty);
			currentIcon = icon;
		} else if (icon.equals("bomb")) {
			this.setIcon(bomb);
			currentIcon = icon;
		} else if (icon.equals("flag")) {
			this.setIcon(flag);
			currentIcon = icon;
		} else if (icon.equals("flagCross")) {
			this.setIcon(flagCross);
			currentIcon = icon;
		} else if (icon.equals("bombRed")) {
			this.setIcon(bombRed);
			currentIcon = icon;
		} else if (icon.equals("num1")) {
			this.setIcon(num1);
			currentIcon = icon;
		} else if (icon.equals("num2")) {
			this.setIcon(num2);
			currentIcon = icon;
		} else if (icon.equals("num3")) {
			this.setIcon(num3);
			currentIcon = icon;
		} else if (icon.equals("num4")) {
			this.setIcon(num4);
			currentIcon = icon;
		} else if (icon.equals("num5")) {
			this.setIcon(num5);
			currentIcon = icon;
		} else if (icon.equals("num6")) {
			this.setIcon(num6);
			currentIcon = icon;
		} else if (icon.equals("num7")) {
			this.setIcon(num7);
			currentIcon = icon;
		} else if (icon.equals("num8")) {
			this.setIcon(num8);
			currentIcon = icon;
		}
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("action");
	}

	@Override
	public void mouseClicked(MthisouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			button = 1;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			button = 3;
		}

		if (!uncoverd) {
			if (button == 1) {
				if (flagged) {
					return;
				}
				if (currentObject.equals("bomb")) { // bombe gefunden
					this.changeIcon("bombRed");
					uncoverd = true;
				} else if (currentObject.equals("tile")) { // leer gefunden
					uncoverd = true;
					this.changeIcon("tileEmpty");
					myGUI.uncover(posX,posY);
					System.out.println(posX+" "+posY);
					recursion = true;
				} else {

					for (int i = 0; i < 9; i++) {
						if (currentObject.equals("num" + i)) {
							this.changeIcon("num" + i);
							uncoverd = true;
							break;
						}
					}
				}

			} else if (button == 3) {
				if (flagged) {
					flagged = false;
					this.changeIcon("tile");
				} else if (!flagged && !uncoverd) {
					flagged = true;
					this.changeIcon("flag");
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}*/
}