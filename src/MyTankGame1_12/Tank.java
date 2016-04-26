package MyTankGame1_12;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

//̹����
class Tank {
	int x = 0;// ��ʾ̹�˵ĺ�����
	int y = 0;// ��ʾ̹�˵�������
	int direct = 0;// ̹�˵ķ���0.up 1.down 2.left 3.right
	int type;// ̹�˵�����
	int speed;// ̹�˵��ٶ�
	int shotSpeed=8;
	int live=1;

	boolean canUp=true;
	boolean canDown=true;
	boolean canLeft=true;
	boolean canRight=true;
	ShotBoxClass shotBoxC = new ShotBoxClass();
	int shotBoxSize = 0;// ���д�С
	Shot s;

	public Tank(int x, int y, int type, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.speed = speed;

	}

	//�����еķ���
	public void cleanShotBox(){
		Vector<Shot> cleanShot = new Vector<Shot>();
		for(Shot shot:shotBoxC.shotBox)
		{
			if(shot.isLive == false)
				cleanShot.add(shot);
		}		
		shotBoxC.shotBox.removeAll(cleanShot);
	}
	
	// ���𷽷�
	public void shotOther() {
		if (shotBoxC.shotBox.size() < shotBoxSize) {
			s = new Shot(this.x, this.y, this.direct, this.type, shotSpeed);
			shotBoxC.shotBox.add(s);
			Thread sThread = new Thread(s);
			sThread.setName("Shot");
			sThread.start();
		}
	}

	// �ĸ�Ԥ�ƶ�����
	public int wantUp() {
		return y - speed;
	}

	public int wantDown() {
		return y + speed;
	}

	public int wantLeft() {
		return x - speed;
	}

	public int wantRight() {
		return x + speed;
	}

	// �ĸ��ƶ�����
	public void goUp() {
		y -= speed;
	}

	public void goDown() {
		y += speed;
	}

	public void goLeft() {
		x -= speed;
	}

	public void goRight() {
		x += speed;
	}

	// switchת�򷽷�
	public void runTo(Graphics g) {
		// �ж�̹�˵ķ���
		
		switch (direct) {
		// ��������
		case 0:
			// 1.��ߵľ���
			g.fill3DRect(x, y, 7, 30, false);
			// 2.�ұߵľ���
			g.fill3DRect(x + 24, y, 7, 30, false);
			// 3.�м�ľ���
			g.fill3DRect(x + 8, y + 5, 16, 20, false);
			// 4.Բ
			g.fillOval(x + 8, y + 8, 14, 14);
			// 5.ֱ��
			//g.drawLine(x + 15, y, x + 15, y + 10);
			
			g.fill3DRect(x + 14, y-3, 3, 11, false);
			break;
		// ��������
		case 1:
			// 1.��ߵľ���
			g.fill3DRect(x, y, 7, 30, false);
			// 2.�ұߵľ���
			g.fill3DRect(x + 24, y, 7, 30, false);
			// 3.�м�ľ���
			g.fill3DRect(x + 8, y + 5, 16, 20, false);
			// 4.Բ
			g.fillOval(x + 8, y + 8, 14, 14);
			// 5.ֱ��
			//g.drawLine(x + 15, y + 20, x + 15, y + 30);
			
			g.fill3DRect(x + 14, y + 22, 3, 11, false);
			break;
		// ��������
		case 2:
			// 1.��ߵľ���
			g.fill3DRect(x, y, 30, 7, false);
			// 2.�ұߵľ���
			g.fill3DRect(x, y + 24, 30, 7, false);
			// 3.�м�ľ���
			g.fill3DRect(x + 5, y + 8, 20, 16, false);
			// 4.Բ
			g.fillOval(x + 8, y + 8, 14, 14);
			// 5.ֱ��
			//g.drawLine(x, y + 15, x + 10, y + 15);
			
			g.fill3DRect(x-3, y + 14, 11, 3, false);
			break;
		// ��������
		case 3:
			// 1.��ߵľ���
			g.fill3DRect(x, y, 30, 7, false);
			// 2.�ұߵľ���
			g.fill3DRect(x, y + 24, 30, 7, false);
			// 3.�м�ľ���
			g.fill3DRect(x + 5, y + 8, 20, 16, false);
			// 4.Բ
			g.fillOval(x + 8, y + 8, 14, 14);
			// 5.ֱ��
			//g.drawLine(x + 20, y + 15, x + 30, y + 15);
			
			g.fill3DRect(x + 22, y + 14, 11, 3, false);
			break;
		default:
			break;
		}
		//����̹��Ѫ��
//		g.setColor(Color.red);
//		g.fill3DRect(x + 33, y+10, this.live*4, 4, false);
	}

	// get and set
	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
