import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import java.awt.*;

public class MinesButton extends JButton{
	ImageIcon tile, tileEmpty, bomb, flag, flagCross, bombRed, num1, num2, num3, num4, num5, num6, num7, num8;
	String currentIcon = "";
	String currentObject = "";
	boolean uncoverd = false;
	boolean flagged = false;
	int posX;
	int posY;

	int button;

	public MinesButton(String icon, String object, int x, int y) {
		posX = x;
		posY = y;
		tile = new ImageIcon("pics/tile.png");
		tileEmpty = new ImageIcon("pics/tile2.png");
		bomb = new ImageIcon("pics/bomb.png");
		flag = new ImageIcon("pics/flag.png");
		flagCross = new ImageIcon("pics/flagCross.png");
		bombRed = new ImageIcon("pics/bombRed.png");
		num1 = new ImageIcon("pics/1one.png");
		num2 = new ImageIcon("pics/2two.png");
		num3 = new ImageIcon("pics/3three.png");
		num4 = new ImageIcon("pics/4four.png");
		num5 = new ImageIcon("pics/5five.png");
		num6 = new ImageIcon("pics/6six.png");
		num7 = new ImageIcon("pics/7seven.png");
		num8 = new ImageIcon("pics/8eight.png");
		currentObject = object;
		changeIcon(icon);

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
}