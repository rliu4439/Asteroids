import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Asteriods extends GameObject {
	int dx = 10;

	public Asteriods(int x, int y) {
		super(x, y, 30,30);

		setImage("src/image/asteriod.jpg");

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		setY(getY() + dx);

	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getImg(), x, y, w, h, null);
		g.setColor(Color.BLACK);
		g.draw3DRect(x, y, w , h , true);
		myRect = new Rectangle(x, y, w , h );
	}

//	 @Override
//	 public boolean collides(GameObject check) {
//	 // TODO Auto-generated method stub
//	 if(myRect.intersects(check.myRect))
//	 return true;
//	 return false;
//	 }
//	

}
