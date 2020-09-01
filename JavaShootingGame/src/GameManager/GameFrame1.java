package GameManager;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame1 extends JFrame {
	int f_width = 600; // ������ �������� ���� ���� �����մϴ�.
	int f_height = 800; // ������ �������� ���� ���� �����մϴ�.

	GameFrame1() { // �������� ����ϴ�.
		init(); // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.
		start(); // ������ ���� �⺻���� ���� ���� ó�� �κ��Դϴ�.
		setTitle("GameStart"); // �������� �̸��� �����մϴ�.
		setSize(f_width, f_height); // �������� ũ�⸦ ���� ������ ������ ����
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// �������� �����쿡 ǥ�õɶ� ��ġ�� �����ϱ� ����
		// ���� ������� �ػ� ���� �޾ƿɴϴ�.

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);
		// �������� ����� ȭ�� ���߾ӿ� ��ġ��Ű�� ���� ��ǥ ���� ����մϴ�.

		setLocation(f_xpos, f_ypos);// �������� ȭ�鿡 ��ġ
		setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setVisible(true); // �������� ���� ���̰� ���ϴ�.
	}

	public void init() { // ������ ���� �����ӿ� �� ������Ʈ ���� �޼ҵ��Դϴ�.

	}

	public void start() { // ������ ���� �⺻���� ���� ���� ó�� �κ��Դϴ�.

	}

}