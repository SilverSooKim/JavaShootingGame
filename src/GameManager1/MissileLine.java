package GameManager1;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class MissileLine implements Missile{
	
	Point pos;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir = tk.getImage("./images/missile1.jpg");
	
	MissileLine(int x, int y){
		pos=new Point(x,y);
	}
	
	public void move() {
		pos.y -= 10;
	}

}
