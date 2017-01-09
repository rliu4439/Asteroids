import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship extends GameObject {

	public Ship() {
		super(300, 450, 50, 50);
		setImage("src/image/Spaceship.jpg");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	public void moveRight() {
		// System.out.println("Moving Right");
		setX(getX() + 6);
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		// System.out.println("Moving right");
		setX(getX() - 6);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		// System.out.println("Drawing");
		g.drawImage(img, x, y, w, h, null);
		// g.setColor(Color.BLACK);
		// g.draw3DRect(x + 13, y + 9, w - 30, h - 10, true);// adjusts for
		// picture
		// blank space
		myRect = new Rectangle(x + 10, y + 9, w - 30, h - 10);
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

}
