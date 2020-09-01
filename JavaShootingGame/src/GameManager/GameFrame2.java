package GameManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame2 extends JFrame implements KeyListener {
	int f_width = 600; // 생성할 프레임의 넓이 값을 설정합니다.
	int f_height = 800; // 생성할 프레임의 높이 값을 설정합니다.
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image myAir = tk.getImage("./images/myAir1.jpg");

	int x = 270, y = 700;
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;

	GameFrame2() { // 프레임을 만듭니다.
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
		this.setLocation(f_xpos, f_ypos);// 프레임을 화면에 배치
		this.setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true); // 프레임을 눈에 보이게 띄웁니다.
	}

	public void init() { // 나중을 위한 프레임에 들어갈 컴포넌트 세팅 메소드입니다.

	}

	public void start() { // 나중을 위한 기본적인 시작 명령 처리 부분입니다.

	}

	public void actionKey() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
				//	System.out.println("키감지가 시작되었습니다.");
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

	public void paint(Graphics g) {
		g.clearRect(0, 0, f_width, f_height);
		g.drawImage(myAir, x, y, this);
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
