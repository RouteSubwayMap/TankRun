package MyTankGame1_12;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//我的坦克 很溜
class Hero extends Tank implements KeyListener, Runnable {
	// Shot s = null;
	int runUp;
	int runDown;
	int runLeft;
	int runRight;// 坦克的移动状态状态
	int shots;// 坦克的射击状态
	int key;// 用于储存监听得到的键值
	int up;
	int down;
	int left;
	int right;
	int shot;// 射击键
	int hitNum;//射杀数
	public Hero(int x, int y) {
		super(x, y, 1, 5);
		this.live = 15;
		this.shotSpeed = 10;
		this.shotBoxSize = 2;
		this.runUp = 0;
		this.runRight = 0;
		this.runLeft = 0;
		this.runDown = 0;// 坦克的运动状态默认为静止的
		this.shots = 0;// 坦克默认为不是射击状态
		this.hitNum = 0;
		this.up = KeyEvent.VK_W;
		this.down = KeyEvent.VK_S;
		this.left = KeyEvent.VK_A;
		this.right = KeyEvent.VK_D;
		this.shot = KeyEvent.VK_J;
		Thread t = new Thread(this);
		t.setName("hero");
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				Thread.sleep(55);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//清理弹夹
			cleanShotBox();
			
			
			// 我的坦克（hero）射击
			if (shots != 0) {
				this.shotOther();
			}
			// 我的坦克（hero）的运动
			if (this.runUp != 0) {
				if (wantUp() > 0 && this.canUp)
					goUp();
				this.setDirect(0);
			} else if (this.runDown != 0) {
				if (wantDown() < 470 && this.canDown)
					goDown();
				this.setDirect(1);
			} else if (this.runLeft != 0) {
				if (wantLeft() > 0 && this.canLeft)
					goLeft();
				this.setDirect(2);
			} else if (this.runRight != 0) {
				if (wantRight() < 470 && this.canRight)
					goRight();
				this.setDirect(3);
			}
			// 如果坦克死亡，就break让线程结束
			if (live <= 0) {
				break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == this.up) {
			this.key = e.getKeyCode();
			this.runUp = 1;
		} else if (e.getKeyCode() == this.down) {
			this.key = e.getKeyCode();
			this.runDown = 1;
		} else if (e.getKeyCode() == this.right) {
			this.key = e.getKeyCode();
			this.runRight = 1;
		} else if (e.getKeyCode() == this.left) {
			this.key = e.getKeyCode();
			this.runLeft = 1;
		} else if (e.getKeyCode() == this.shot) {
			this.shots = 1;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == this.up) {
			this.runUp = 0;
		} else if (e.getKeyCode() == this.down) {
			this.runDown = 0;
		} else if (e.getKeyCode() == this.right) {
			this.runRight = 0;
		} else if (e.getKeyCode() == this.left) {
			this.runLeft = 0;
		} else if (e.getKeyCode() == this.shot) {
			this.shots = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
