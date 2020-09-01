package GameManager1;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameCanvas extends Canvas implements KeyListener {
	int f_width = 600; // 생성할 프레임의 넓이 값을 설정합니다.
	int f_height = 800; // 생성할 프레임의 높이 값을 설정합니다.
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir; 
	// 더블 버퍼링
	Image buffImage;
	Graphics buffg;
	// 더블 버퍼링
	
	// https://m.blog.naver.com/dosem321/40170955804

	int x = 270, y = 700;
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	
	//
	Missile ms;
	ArrayList missileList = new ArrayList<>();
	
	GameCanvas() { // 프레임을 만듭니다.
		init(); // 나중을 위한 프레임에 들어갈 컴포넌트 세팅 메소드입니다.
		start(); // 나중을 위한 기본적인 시작 명령 처리 부분입니다.

		this.addKeyListener(this);
		actionKey();
	}

	public void init() { // 나중을 위한 프레임에 들어갈 컴포넌트 세팅 메소드입니다.
		myAir = tk.getImage("./images/myAir1.jpg");
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
		buffImage = createImage(f_width, f_height);   // Image객체를 버퍼에 생성
		// 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		buffg.clearRect(0, 0, f_width, f_height);  // 버퍼를 지운다
		buffg.drawImage(myAir, x, y, this);        // 버퍼에 그림을 그린다
		g.drawImage(buffImage, 0, 0, this);        // 버퍼에 있는 것을 화면에 그린다.
		// 화면에 버퍼에 그린 그림을 가져와 그리기
	}

	public void Draw_Char() { // 실제로 그림들을 그릴 부분
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
