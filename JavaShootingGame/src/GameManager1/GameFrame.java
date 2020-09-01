package GameManager1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	int f_width = 600; // 생성할 프레임의 넓이 값을 설정합니다.
	int f_height = 800; // 생성할 프레임의 높이 값을 설정합니다.

	GameCanvas gc = new GameCanvas();

	GameFrame() { // 프레임을 만듭니다.
		this.setTitle("GameStart"); // 프레임의 이름을 설정합니다.
		this.setSize(f_width, f_height); // 프레임의 크기를 위에 값에서 가져와 설정
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// 프레임이 윈도우에 표시될때 위치를 세팅하기 위해
		// 현재 모니터의 해상도 값을 받아옵니다.

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);
		// 프레임을 모니터 화면 정중앙에 배치시키기 위해 좌표 값을 계산합니다.
		this.add(gc, "Center");
		this.setLocation(f_xpos, f_ypos);// 프레임을 화면에 배치
		this.setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true); // 프레임을 눈에 보이게 띄웁니다.
	}
}
