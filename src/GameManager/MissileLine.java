package GameManager;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class MissileLine implements Missile{
	
	Point pos;
	
	//Toolkit tk = Toolkit.getDefaultToolkit();
	//Image myAir = tk.getImage("./images/missile1.jpg");
	Image myAir = new ImageIcon("./images/missile1.jpg").getImage();	
	
	MissileLine(int x, int y){
		pos=new Point(x,y);
	}
	
	public void move() {
		pos.y -= 10;
	}
	
	public Image getImage() {
		return myAir;
	}
	
	public int getWidth() {
		return myAir.getWidth(null);
	}
	
	public int getHeight() {
		return myAir.getHeight(null);
	}

}
