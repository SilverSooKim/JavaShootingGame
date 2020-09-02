package GameManager1;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameCanvas extends Canvas implements KeyListener {
	int f_width = 600; // ������ �������� ���� ���� �����մϴ�.
	int f_height = 800; // ������ �������� ���� ���� �����մϴ�.
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir; 
	// ���� ���۸�
	Image buffImage;
	Graphics buffg;
	// ���� ���۸�
	
	// https://m.blog.naver.com/dosem321/40170955804

	int x = 270, y = 700;
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	
	//
	Missile ms;
	ArrayList missileList = new ArrayList<>();
	
	GameCanvas() { // �������� ����ϴ�.
		init(); // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.
		start(); // ������ ���� �⺻���� ���� ���� ó�� �κ��Դϴ�.

		this.addKeyListener(this);
		actionKey();
	}

	public void init() { // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.
		myAir = tk.getImage("./images/myAir1.jpg");
	}

	public void start() { // ������ ���� �⺻���� ���� ���� ó�� �κ��Դϴ�.

	}

	public void actionKey() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// System.out.println("Ű������ ���۵Ǿ����ϴ�.");
					KeyProcess();
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	/*
	 * public void paint(Graphics g) { g.clearRect(0, 0, f_width, f_height);
	 * g.drawImage(myAir, x, y, this); }
	 */

	public void paint(Graphics g) {
		buffImage = createImage(f_width, f_height);   // Image��ü�� ���ۿ� ����
		// �������۸� ���� ũ�⸦ ȭ�� ũ��� ���� ����
		buffg = buffImage.getGraphics(); // ������ �׷��� ��ü�� ���
		buffg.clearRect(0, 0, f_width, f_height);  // ���۸� �����
		buffg.drawImage(myAir, x, y, this);        // ���ۿ� �׸��� �׸���
		g.drawImage(buffImage, 0, 0, this);        // ���ۿ� �ִ� ���� ȭ�鿡 �׸���.
		// ȭ�鿡 ���ۿ� �׸� �׸��� ������ �׸���
	}

	public void Draw_Char() { // ������ �׸����� �׸� �κ�
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		}
		KeyProcess();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// TODO Auto-generated method stub
	public void KeyProcess() {
		if (KeyUp == true)
			y -= 5;
		if (KeyDown == true)
			y += 5;
		if (KeyLeft == true)
			x -= 5;
		if (KeyRight == true)
			x += 5;
	}
}