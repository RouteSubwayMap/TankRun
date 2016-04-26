package MyTankGame1_12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

//�ҵ����
class MyPanel extends JPanel implements Runnable {
	// ����һ���ҵ�̹��
	TankUtil tankUtil = new TankUtil();
	Hero hero = null;

	Vector<Bomb> bombList = new Vector<Bomb>();
	Vector<Tank> tankList = new Vector<Tank>();
	Vector<Tank> EnemyList = new Vector<Tank>();
	// ������е��е���������
	Vector<ShotBoxClass> shotBoxVector = new Vector<ShotBoxClass>();
	int width;
	int height;
	Vector<Tank> beBoomTankList = new Vector<Tank>();

	// ���캯��
	public MyPanel(Hero hero) {
		this.hero = hero;
		shotBoxVector.add(hero.shotBoxC);
		tankList.add(this.hero);
		this.width = 500;
		this.height = 500;

		for (int i = 1; i <= 3; i++) {
			Enemy en = new Enemy(100 * i, 50);
			shotBoxVector.add(en.shotBoxC);
			this.EnemyList.add(en);
		}
		Thread t = new Thread(this);
		t.setName("���ˢ��");
		t.start();
	}

	// ��дpaint����
	public void paint(Graphics g) {
		super.paint(g);

		// ///////////////////
		// ���ɵз�̹��
		if (EnemyList.size() < 10) {
			Enemy ene = new Enemy((int) (Math.random() * 470),
					(int) (Math.random() * 470));
			EnemyList.add(ene);

		}

		// ������̹�˵�״̬
		Vector<Tank> deTank = new Vector<Tank>();
		for (Tank tank : EnemyList) {
			if (tank.live <= 0)
				deTank.add(tank);
		}
		EnemyList.removeAll(deTank);

		// ��̹����ӵ�̹�˼�����
		tankList.clear();
		if (EnemyList.size() > 0)
			tankList.addAll(EnemyList);
		if (hero.live > 0)
			tankList.add(hero);

		// �ж�̹�����
		tankUtil.tankCanRun(tankList);

		// ***********************************
		// ɾ���յ���
		Vector<ShotBoxClass> delShotBoxVector = new Vector<ShotBoxClass>();
		for (ShotBoxClass shotBoxC : shotBoxVector) {
			if (shotBoxC.useShotBox == true&&shotBoxC.shotBox.size()<=0)
				delShotBoxVector.add(shotBoxC);
		}
		shotBoxVector.removeAll(delShotBoxVector);

		// �ж�̹���Ƿ������ӵ��������䵯��״̬
		for (Tank tank : tankList) {

			if (tank.shotBoxC.shotBox.size() >= 1 && tank.shotBoxC.useShotBox == false) {

				shotBoxVector.add(tank.shotBoxC);
				tank.shotBoxC.useShotBox = true;
			} else if (tank.shotBoxC.shotBox.size() < 1) {

				tank.shotBoxC.useShotBox = false;
			}
		}
		// ****************************************************

		// �ж��ӵ��Ƿ���е�̹�ˣ�������̹�˺��ӵ���״̬,��ñ�ը
		hitTank(shotBoxVector, tankList);

		for (Tank beBoomTank : beBoomTankList) {
			if (beBoomTank != null) {
				bombList.add(new Bomb(beBoomTank.getX(), beBoomTank.getY()));
			}
		}
		// //////////////////

		// ���������
		g.fillRect(0, 0, width, height);

		// ����̹��
		for (Tank tank : tankList) {
			if (tank.live > 0)
				tankUtil.drawTank(tank, g);
		}

		// ���������ӵ�
		for (ShotBoxClass shotBoxC : shotBoxVector) {
			tankUtil.drawShot(shotBoxC.shotBox, g);
		}

		// ������ըЧ��
		Vector<Bomb> deBombList = new Vector<Bomb>();
		for (Bomb bomb : bombList) {
			if (bomb.life > 0) {
				g.drawImage(bomb.getImage(), bomb.x, bomb.y, 40, 40, this);
				bomb.life--;
			} else if (bomb.life <= 0)
				deBombList.add(bomb);
		}
		bombList.removeAll(deBombList);

		g.setColor(Color.white);
		g.drawString("������" + hero.live, 10, 10);
		g.drawString("�ӵ���" + hero.shotBoxC.shotBox.size(), 10, 25);
		g.drawString("���ˣ�" + EnemyList.size(), 10, 40);
		g.drawString("��ɱ��" + hero.hitNum, 10, 55);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// �����Զ��߳�ˢ��
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ÿ��50����replain
		while (true) {
			try {
				Thread.sleep(55);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// ���»�ͼ
			repaint();

		}

	}

	/**
	 * @author HZW_922
	 * @Description �ж��ҵ��ӵ��Ƿ���ез�̹�ˣ�������̹�˺��ӵ���״̬
	 */
	public void hitTank(Vector<ShotBoxClass> shotBoxVector,
			Vector<Tank> tankList) {
		beBoomTankList.clear();
		try {
			for (ShotBoxClass shotBoxC : shotBoxVector) {
				for (Shot shot : shotBoxC.shotBox) {
					for (Tank tank : tankList) {
						if (shot.getX() > tank.getX() - 1
								&& shot.getX() < tank.getX() + 31
								&& shot.getY() > tank.getY() - 1
								&& shot.getY() < tank.getY() + 31) {
							shot.isLive = false;
							shotBoxC.shotBox.remove(shot);

							if (shot.getType() != tank.getType()) {

								if (--tank.live <= 0) {
									if (tank.type == 0)
										hero.hitNum++;
									beBoomTankList.add(tank);
								}
							}
//							if (shotBoxC.shotBox.size() < 1) {
//								synchronized (this) {
//									shotBoxVector.remove(shotBoxC);
//								}
//							}

						}

					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
