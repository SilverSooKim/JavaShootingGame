package MonsterPKG;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class RedMon implements Monster{
	public Point pos;
	
	/*Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir = tk.getImage("./images/redMon.png");*/
	
	Image myMon = new ImageIcon("./images/redMon.png").getImage();	
	
	public RedMon(int x, int y){
		pos=new Point(x,y);
	}
	
	public void move() {
			pos.y += 1;
	}
	
	public Image getImage() {
		return myMon;
	}
	
	public int getWidth() {
		return myMon.getWidth(null);
	}
	
	public int getHeight() {
		return myMon.getHeight(null);
	}
	
}
