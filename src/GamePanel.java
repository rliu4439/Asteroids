import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	int delay = 75;
	Image Background;
	Timer ticker = new Timer(delay, new ObjectMover());
	List<Moveable> movers = new ArrayList<Moveable>();
	List<Drawable> drawme = new ArrayList<Drawable>();
	List<Asteriods> asteriods = new ArrayList<>();
	List<Bullets> bullets = new ArrayList<>();
	Random rand = new Random();
	GameObject ship;
	int ax = 0;
	boolean gameOver = false;
	int totalDestoyed = 0;
	int time=0;
	ArrayList<Integer> scores = new ArrayList<>();

	class ObjectMover implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (Moveable m : movers) {
				m.move();

			}
			
//			System.out.println("Time is "+time);
			if(time>35 && delay>40){
//				System.out.println("got here");
				delay-=5;
				ticker.setDelay(delay);
//				System.out.println("delay is "+delay);
				time=0;

			}
			time++;
			checkCreateAsteriod();

			checkCollisions();
			checkBulletCollision();
			checkRemoveBullet();
			repaint();
			if (gameOver == true) {
				System.out.println("Game over is true");
				GameOver();
			}

		}

		// ticker.stop();

		private void checkBulletCollision() {
			// TODO Auto-generated method stub
			for (int i = 0; i < bullets.size(); i++) {
				Bullets b = bullets.get(i);
				for (int a = 0; a < asteriods.size(); a++) {// need to check if
															// each bullet hits
															// asteruiod
					if (b.collides(asteriods.get(a))) {
						removeAsteriod(asteriods.get(a));
						removeBullet(b);
						addEndAsteriod();
					}
				}
			}
		}
	}

	public GamePanel() {
		this.setPreferredSize(new Dimension(900, 600));// width by height--x by
		// y

		setVisible(true);
		StartGame();
		try {
			Background = ImageIO.read(new File("src/image/background.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void StartGame() {
		if(gameOver==true){
			clearAll();
		}
	
		ship = new Ship();

		drawme.add(ship);
		movers.add(ship);
		System.out.println("ship is" + ship);
		setUpKeyMappings();
		createAsteriods();

		// addAsteriod(300, 0);//used for colison check
		ticker.start();
		System.out.println("begin delay is "+delay);

	}

	private void clearAll() {
		drawme.clear();
		asteriods.clear();
		movers.clear();
		time=0;
		delay=75;
		ticker.setDelay(delay);
		scores.add(new Integer(totalDestoyed));
		displayscores();
		totalDestoyed = 0;
		bullets.clear();
		ship = null;
		gameOver = false;

	}

	private void displayscores() {
		// TODO Auto-generated method stub
		Collections.sort(scores);
		Collections.reverse(scores);
		if (scores.size()>=3){
			JOptionPane.showMessageDialog(this, "High scores \n Scores \n 1. "
					+ scores.get(0)+" Asteriods \n "+" 2. "+scores.get(1)+" Asteriods \n"+"3. "+scores.get(2)+" Asteriods \n");
		}
		else if (scores.size()==2){
			JOptionPane.showMessageDialog(this, "High scores \n Scores \n 1. "
					+ scores.get(0)+" Asteriods \n"+" 2. "+scores.get(1)+" Asteriods \n" +"3. "+"----- Asteriods");
		}
		else if(scores.size()==1){
			
				JOptionPane.showMessageDialog(this, "High scores \n Scores \n 1. "
						+ scores.get(0)+" Asteriods \n"+" 2. "+"-----"+ "Asteriods \n"+"3. "+"----- Asteriods");
			}
		else{
			JOptionPane.showMessageDialog(this, "High scores \n Scores \n 1. "
					+ "------" +" Asteriods \n"+" 2. "+"----- "+ "Asteriods \n"+"3. "+"---- Asteriods");
		}
		
	}

	private void createAsteriods() {
		// TODO Auto-generated method stub
		int ax = 0;
		for (int i = 0; i < 15; i++) {
			int ay = rand.nextInt(100);
			ax = rand.nextInt(100) + ax + 40;
			addAsteriod(ax, ay);

		}
	}

	private void setUpKeyMappings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		this.getActionMap().put("moveRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moveRight();
				// System.out.println("clicked right");
			}

		});
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		this.getActionMap().put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moveLeft();
				// System.out.println("clicked right");
			}
		});
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");
		this.getActionMap().put("shoot", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shoot();
			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "restart");

		// //////// This associates the command jump with some action. In this
		// case, the action triggers a moveUp command. In general, whatever
		// goes in the actionPerformed method will be executed when a jump
		// command
		// is sent...
		this.getActionMap().put("restart", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameOver)
					StartGame();
				else{
                    ticker.stop();
                }
			}
		});

	}

	private void moveLeft() {
		((Ship) ship).moveLeft();
	}

	private void shoot() {
		if (bullets.size() < 10) {
			Bullets b = new Bullets(ship.getX()+15 , ship.getY());
			registerBullet(b);
		}

	}

	private void registerBullet(Bullets b) {
		drawme.add(b);
		bullets.add(b);
		movers.add(b);

	}

	private void checkRemoveBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getY() < 0) {
				removeBullet(bullets.get(i));
				// System.out.println("got here");
			}
		}

	}

	private void removeBullet(Bullets bullet) {
		// TODO Auto-generated method stub
		bullets.remove(bullet);
		drawme.remove(bullet);
		movers.remove(bullet);

	}

	public void addAsteriod(int x, int y) {
		Asteriods a = new Asteriods(x, y);
		registerAsteriod(a);
		asteriods.add(a);

	}

	private void moveRight() {
		// TODO Auto-generated method stub
		((Ship) ship).moveRight();
	}

	public void addEndAsteriod() {
		if (ax >= 900) {
			ax = 0;
		}
		ax = ax + 50 + rand.nextInt(100);
		int ay = 0;
		addAsteriod(ax, ay);

	}

	public void registerAsteriod(Asteriods a) {
		drawme.add(a);
		movers.add(a);
	}

	private void checkCreateAsteriod() {

		Asteriods removeme = asteriods.get(0);
		if (removeme.getY() + removeme.getW() - 30 > ship.getY()) {
			removeAsteriod(removeme);
			addEndAsteriod();
			// gameOver=true;

		}

	}

	private void checkCollisions() {
		// // TODO Auto-generated method stub
		for (int i = 0; i < asteriods.size(); i++) {
			if (ship.collides(asteriods.get(i))) {
				gameOver = true;
				System.out.println("Asteriod " + i
						+ asteriods.get(i).getMyRect());
				System.out.println("Ship " + ship.myRect);
				ship.setImage("src/image/explosion.jpg");
				//asteriods.get(i).setImage("src/image/AsteriodExplosion.jpg");
				removeAsteriod(asteriods.get(i));
			}

		}

	}

	private void GameOver() {
		// TODO Auto-generated method stub
		ticker.stop();
		System.out.println();
		JOptionPane.showMessageDialog(this,
				"Game Over! You score is " + totalDestoyed
						+ "\n " + " Press Enter to Play Again");
	}

	public void removeAsteriod(Asteriods remove) {
		asteriods.remove(remove);
		drawme.remove(remove);
		movers.remove(remove);
		totalDestoyed++;
	}

	private void setUpBackground(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Background, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setUpBackground(g);
		drawDrawables(g);

	}

	private void drawDrawables(Graphics g) {
		// TODO Auto-generated method stub
		for (Drawable d : drawme) {
			d.draw(g);
		}
	}
}
