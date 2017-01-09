import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				System.out.println("done");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				
			}
			//e.printStackTrace();
		}
		
		JFrame jf = new JFrame("Asteriods");
		SwingUtilities.updateComponentTreeUI(jf);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.add(new GamePanel());
		jf.pack();
		jf.setVisible(true);
		
	}
	}


