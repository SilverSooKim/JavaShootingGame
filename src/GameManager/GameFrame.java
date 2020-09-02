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
	int f_width = 600; // 생성할 프레임의 넓이 값을 설정합니다.
	int f_height = 800; // 생성할 프레임의 높이 값을 설정합니다.
	// Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir;
	Image missileImg;
	Image redMonImg;
	// 더블 버퍼링
	Image buffImage;
	Graphics buffg;
	// 더블 버퍼링

	int score=0;

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

	GameFrame() { // 프레임을 만듭니다.
		init(); // 나중을 위한 프레임에 들어갈 컴포넌트 세팅 메소드입니다.
		start(); // 나중을 위한 기본적인 시작 명령 처리 부분입니다.
		this.setTitle("GameStart"); // 프레임의 이름을 설정합니다.
		this.setSize(f_width, f_height); // 프레임의 크기를 위에 값에서 가져와 설정
		this.setBackground(Color.BLACK);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// 프레임이 윈도우에 표시될때 위치를 세팅하기 위해
		// 현재 모니터의 해상도 값을 받아옵니다.

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);
		// 프레임을 모니터 화면 정중앙에 배치시키기 위해 좌표 값을 계산합니다.

		this.addKeyListener(this);
		actionKey();
		createRedMonTime();
		this.setLocation(f_xpos, f_ypos);// 프레임을 화면에 배치
		this.setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true); // 프레임을 눈에 보이게 띄웁니다.
	}

	public void init() { // 나중을 위한 프레임에 들어갈 컴포넌트 세팅 메소드입니다.
		// myAir = tk.getImage("./images/myAir1.jpg");
		myAir = new ImageIcon("./images/myAir1.jpg").getImage();
		ms = new MissileLine(x, y);
		missileImg = ms.getImage();
		redMon = new RedMon(0, 0);
		redMonImg = redMon.getImage();
	}

	public void start() { // 나중을 위한 기본적인 시작 명령 처리 부분입니다.

	}

	public void actionKey() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// System.out.println("키감지가 시작되었습니다.");
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
		buffImage = createImage(f_width, f_height); // Image객체를 버퍼에 생성
		// 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		buffg.clearRect(0, 0, f_width, f_height); // 버퍼를 지운다
		buffg.drawImage(myAir, x, y, this); // 버퍼에 그림을 그린다
		drawMissile();
		drawMonster();
		// 화면에 버퍼에 그린 그림을 가져와 그리기

		buffg.setColor(Color.white);
		buffg.setFont(new Font("Defualt", Font.BOLD, 20));

		// 폰트 설정을 합니다. 기본폰트, 굵게, 사이즈 20
		buffg.drawString("X : " + x, 400, 70);
		buffg.drawString("Y : " + y, 400, 90);
		buffg.drawString("Score : " + score, 400, 110);
		g.drawImage(buffImage, 0, 0, this); // 버퍼에 있는 것을 화면에 그린다.
	}

	public void drawMissile() { // 미사일 그리는 메소드
		for (int i = 0; i < missileList.size(); ++i) {
			// 미사일 존재 유무를 확인한다.
			ms = (MissileLine) (missileList.get(i));
			// 미사일 위치값을 확인
			buffg.drawImage(missileImg, ms.pos.x, ms.pos.y, this);
			// 현재 좌표에 미사일 그리기.
			// 이미지 크기를 감안한 미사일 발사 좌표는 수정됨.
			for (int j = 0; j < redMonList.size(); j++) {
				redMon = redMonList.get(j);
				hitMonster(i, j, ms, redMon);
			}
			ms.move();
			// 그려진 미사일을 정해진 숫자만큼 이동시키기
			if (ms.pos.y <= 0) { // 미사일이 화면 밖으로 나가면
				missileList.remove(i); // 미사일 지우기

			}
		}
	}

	public void drawMonster() {
		for (int i = 0; i < redMonList.size(); ++i) {
			// 미사일 존재 유무를 확인한다.
			redMon = redMonList.get(i);
			// 미사일 위치값을 확인
			// System.out.println("Monster :"+redMon.pos.x+"/"+redMon.pos.y);
			buffg.drawImage(redMonImg, redMon.pos.x, redMon.pos.y, this);
			// 현재 좌표에 미사일 그리기.
			// 이미지 크기를 감안한 미사일 발사 좌표는 수정됨.

			for (int j = 0; j < missileList.size(); j++) {
				ms = (MissileLine) missileList.get(j);
				hitMonster(j, i, ms, redMon);
			}
			redMon.move();
			// 그려진 미사일을 정해진 숫자만큼 이동시키기
			if (redMon.pos.y > f_height) { // 미사일이 화면 밖으로 나가면
				redMonList.remove(i); // 미사일 지우기

			}
		}
	}

	public void hitMonster(int i, int j, MissileLine m, RedMon r) {
		/*
		 * if (Math.abs( (r.pos.x - r.getWidth() / 2) - m.pos.x) < (r.getWidth() / 2 +
		 * m.getWidth() / 2) && Math.abs((r.pos.y - m.getHeight() / 2) - (m.pos.y)) <
		 * (r.getHeight() / 2 + m.getHeight() / 2)) { System.out.println("충돌");
		 * missileList.remove(i); redMonList.remove(j); }
		 */

		/*
		 * if () { System.out.println("충돌"); missileList.remove(i);
		 * redMonList.remove(j); }
		 */

		if ((m.pos.y >= r.pos.y && m.pos.y <= r.pos.y + r.getHeight())
				&& (m.pos.x >= r.pos.x && m.pos.x <= r.pos.x + r.getWidth())) {
			System.out.println("충돌");
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

	public void Draw_Char() { // 실제로 그림들을 그릴 부분
	}

	public void missileProcess() {
		if (KeySpace) { // 스페이스바 키 상태가 true 면
			ms = new MissileLine(x + 29, y - 5); // 좌표 체크하여 넘기기
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
		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
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
		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
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
