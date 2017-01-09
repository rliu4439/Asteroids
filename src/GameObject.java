import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameObject implements Drawable,Moveable {
	int x, y, w, h;
	Rectangle myRect;
	Image img;

	public int getW() {
		return w;
	}

	public Rectangle getMyRect() {
		return myRect;
	}

	public void setMyRect(Rectangle myRect) {
		this.myRect = myRect;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	public void setW(int w){
		this.w=w;
	}
	public GameObject() {
		this(100, 100, 50, 50);
	}

	public GameObject(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		myRect= new Rectangle(new Point(x, y), new Dimension(w,h));
		

	}

	public void setY(int newy) {
		this.y = newy;
	}

	public void setX(int newx) {
		this.x = newx;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	public void setImage(String filepath){
		try {
			Image i = ImageIO.read(new File(filepath));
			setImage(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void setImage(Image i){	img = i;}
	
	
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, w, h, null);
		myRect = new Rectangle(x,y,w,h);
	}
	
	public  boolean collides(GameObject check){
		if(myRect.intersects(check.myRect))
			return true;
		return false;
	}
	public String toString(){
		return "G.O.: "+getClass()+ " rect: "+myRect;
	}
	public abstract void move() ;	
	
	
	public abstract void moveUp();
}


