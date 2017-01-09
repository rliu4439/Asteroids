import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullets extends GameObject {
	public Bullets(int x, int y) {
		super(x, y, 20, 10);

		setImage("src/image/bullet.jpg");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		setY(getY() - 5);
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		g.drawImage(getImg(), x, y, w, h, null);
		g.setColor(Color.BLACK);
		g.draw3DRect(x, y, w, h, true);
		myRect = new Rectangle(x, y, w, h);
	
	}

}
