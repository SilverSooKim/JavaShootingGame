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
	int f_width = 600; // ������ �������� ���� ���� �����մϴ�.
	int f_height = 800; // ������ �������� ���� ���� �����մϴ�.

	GameCanvas gc = new GameCanvas();

	GameFrame() { // �������� ����ϴ�.
		this.setTitle("GameStart"); // �������� �̸��� �����մϴ�.
		this.setSize(f_width, f_height); // �������� ũ�⸦ ���� ������ ������ ����
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// �������� �����쿡 ǥ�õɶ� ��ġ�� �����ϱ� ����
		// ���� ������� �ػ� ���� �޾ƿɴϴ�.

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);
		// �������� ����� ȭ�� ���߾ӿ� ��ġ��Ű�� ���� ��ǥ ���� ����մϴ�.
		this.add(gc, "Center");
		this.setLocation(f_xpos, f_ypos);// �������� ȭ�鿡 ��ġ
		this.setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true); // �������� ���� ���̰� ���ϴ�.
	}
}