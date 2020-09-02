package GameManager;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import MonsterPKG.*;

public class GameFrame extends JFrame implements KeyListener {
	int f_width = 600; // ������ �������� ���� ���� �����մϴ�.
	int f_height = 800; // ������ �������� ���� ���� �����մϴ�.
	// Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir;
	Image missileImg;
	Image redMonImg;
	// ���� ���۸�
	Image buffImage;
	Graphics buffg;
	// ���� ���۸�

	//int score=0;
	int score=0;
	// 주석 갱신 

	int x = 270, y = 700;
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;

	MissileLine ms;
	ArrayList missileList = new ArrayList<>();

	RedMon redMon;
	ArrayList<RedMon> redMonList = new ArrayList<>();

	GameFrame() { // �������� ����ϴ�.
		init(); // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.
		start(); // ������ ���� �⺻���� ���� ��� ó�� �κ��Դϴ�.
		this.setTitle("GameStart"); // �������� �̸��� �����մϴ�.
		this.setSize(f_width, f_height); // �������� ũ�⸦ ���� ������ ������ ����
		this.setBackground(Color.BLACK);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// �������� �����쿡 ǥ�õɶ� ��ġ�� �����ϱ� ����
		// ���� ������� �ػ� ���� �޾ƿɴϴ�.

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);
		// �������� ����� ȭ�� ���߾ӿ� ��ġ��Ű�� ���� ��ǥ ���� ����մϴ�.

		this.addKeyListener(this);
		actionKey();
		createRedMonTime();
		this.setLocation(f_xpos, f_ypos);// �������� ȭ�鿡 ��ġ
		this.setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true); // �������� ���� ���̰� ���ϴ�.
	}

	public void init() { // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.
		// myAir = tk.getImage("./images/myAir1.jpg");
		myAir = new ImageIcon("./images/myAir1.jpg").getImage();
		ms = new MissileLine(x, y);
		missileImg = ms.getImage();
		redMon = new RedMon(0, 0);
		redMonImg = redMon.getImage();
	}

	public void start() { // ������ ���� �⺻���� ���� ��� ó�� �κ��Դϴ�.

	}

	public void actionKey() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// System.out.println("Ű������ ���۵Ǿ����ϴ�.");
					KeyProcess();
					missileProcess();
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
	public void createRedMonTime() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					 while (true) {
					Thread.sleep(3000);
					createRedMon();
					 }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	public void createRedMon() {
		int monsterX = 50;
		int monsterY = 10;
		for (int i = 0; i <= 10; i++) {
			redMonList.add(new RedMon(monsterX + (i * 45), monsterY));
		}

	}

	public void paint(Graphics g) {
		buffImage = createImage(f_width, f_height); // Image��ü�� ���ۿ� ����
		// ������۸� ���� ũ�⸦ ȭ�� ũ��� ���� ����
		buffg = buffImage.getGraphics(); // ������ �׷��� ��ü�� ���
		buffg.clearRect(0, 0, f_width, f_height); // ���۸� �����
		buffg.drawImage(myAir, x, y, this); // ���ۿ� �׸��� �׸���
		drawMissile();
		drawMonster();
		// ȭ�鿡 ���ۿ� �׸� �׸��� ������ �׸���

		buffg.setColor(Color.white);
		buffg.setFont(new Font("Defualt", Font.BOLD, 20));

		// ��Ʈ ������ �մϴ�. �⺻��Ʈ, ����, ������ 20
		buffg.drawString("X : " + x, 400, 70);
		buffg.drawString("Y : " + y, 400, 90);
		buffg.drawString("Score : " + score, 400, 110);
		g.drawImage(buffImage, 0, 0, this); // ���ۿ� �ִ� ���� ȭ�鿡 �׸���.
	}

	public void drawMissile() { // �̻��� �׸��� �޼ҵ�
		for (int i = 0; i < missileList.size(); ++i) {
			// �̻��� ���� ������ Ȯ���Ѵ�.
			ms = (MissileLine) (missileList.get(i));
			// �̻��� ��ġ���� Ȯ��
			buffg.drawImage(missileImg, ms.pos.x, ms.pos.y, this);
			// ���� ��ǥ�� �̻��� �׸���.
			// �̹��� ũ�⸦ ������ �̻��� �߻� ��ǥ�� ������.
			for (int j = 0; j < redMonList.size(); j++) {
				redMon = redMonList.get(j);
				hitMonster(i, j, ms, redMon);
			}
			ms.move();
			// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
			if (ms.pos.y <= 0) { // �̻����� ȭ�� ������ ������
				missileList.remove(i); // �̻��� �����

			}
		}
	}

	public void drawMonster() {
		for (int i = 0; i < redMonList.size(); ++i) {
			// �̻��� ���� ������ Ȯ���Ѵ�.
			redMon = redMonList.get(i);
			// �̻��� ��ġ���� Ȯ��
			// System.out.println("Monster :"+redMon.pos.x+"/"+redMon.pos.y);
			buffg.drawImage(redMonImg, redMon.pos.x, redMon.pos.y, this);
			// ���� ��ǥ�� �̻��� �׸���.
			// �̹��� ũ�⸦ ������ �̻��� �߻� ��ǥ�� ������.

			for (int j = 0; j < missileList.size(); j++) {
				ms = (MissileLine) missileList.get(j);
				hitMonster(j, i, ms, redMon);
			}
			redMon.move();
			// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
			if (redMon.pos.y > f_height) { // �̻����� ȭ�� ������ ������
				redMonList.remove(i); // �̻��� �����

			}
		}
	}

	public void hitMonster(int i, int j, MissileLine m, RedMon r) {
		/*
		 * if (Math.abs( (r.pos.x - r.getWidth() / 2) - m.pos.x) < (r.getWidth() / 2 +
		 * m.getWidth() / 2) && Math.abs((r.pos.y - m.getHeight() / 2) - (m.pos.y)) <
		 * (r.getHeight() / 2 + m.getHeight() / 2)) { System.out.println("�浹");
		 * missileList.remove(i); redMonList.remove(j); }
		 */

		/*
		 * if () { System.out.println("�浹"); missileList.remove(i);
		 * redMonList.remove(j); }
		 */

		if ((m.pos.y >= r.pos.y && m.pos.y <= r.pos.y + r.getHeight())
				&& (m.pos.x >= r.pos.x && m.pos.x <= r.pos.x + r.getWidth())) {
			System.out.println("�浹");
			score += 10;
			missileList.remove(i);
			redMonList.remove(j);
		}

		/*
		 * System.out.println("redMon x: "+r.pos.x+"/ x+w: "+(r.pos.x+r.getWidth()));
		 * System.out.println("redMon y: "+r.pos.y+"/ y+h: "+(r.pos.y+r.getHeight()));
		 * System.out.println("Missil x: "+m.pos.x+"/ x+h: "+(m.pos.x+m.getWidth()));
		 * System.out.println("Missil y: "+m.pos.y+"/ y+h: "+(m.pos.y+m.getHeight()));
		 * System.out.println("-------------");
		 */

	}

	public void Draw_Char() { // ������ �׸����� �׸� �κ�
	}

	public void missileProcess() {
		if (KeySpace) { // �����̽��� Ű ���°� true ��
			ms = new MissileLine(x + 29, y - 5); // ��ǥ üũ�Ͽ� �ѱ��
			missileList.add(ms);
		}
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
		case KeyEvent.VK_SPACE: // �����̽�Ű �Է� ó�� �߰�
			KeySpace = true;
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
		case KeyEvent.VK_SPACE: // �����̽�Ű �Է� ó�� �߰�
			KeySpace = false;
			break;
		}
		// KeyProcess();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// TODO Auto-generated method stub
	public void KeyProcess() {
		if (KeyUp == true) 
			if(y <= 20) {
				y=20;
			}else {
				y -= 5;
			}
		if (KeyDown == true)
			if(y >= 730) {
				y=730;
			}else {
				y += 5;
			}
		if (KeyLeft == true)
			if(x <= 5) {
				x=5;
			}else {
				x -= 5;
			}
		if (KeyRight == true)
			if(x >= 530) {
				x=530;
			}else {
				x += 5;
			}
	}
}
