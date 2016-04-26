package MyTankGame1_12;

//����̹��
public class Enemy extends Tank implements Runnable {

	public Enemy(int x, int y) {
		super(x, y, 0, 3);
		this.shotSpeed = 6;
		this.shotBoxSize = 2;
		Thread t = new Thread(this);
		t.setName("Enemys");
		t.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			//������
			cleanShotBox();
			
			// �˶�ģ��
			switch (this.direct) {
			case 0:
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (this.wantUp() > 0 && this.canUp)
						this.goUp();
					else break;
				}
				break;
			case 1:
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (this.wantDown() < 470 && this.canDown)
						this.goDown();
					else break;
				}
				break;
			case 2:
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (this.wantLeft() > 0 && this.canLeft)
						this.goLeft();
					else break;
				}
				break;
			case 3:
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (this.wantRight() < 470 && this.canRight)
						this.goRight();
					else break;
				}
				break;
			}

			this.direct = (int) (Math.random() * 4);

			if ((int) (Math.random() * 4) < 2)
				this.shotOther();
			// ���̹����������break���߳̽���
			if (this.live <= 0) {
				break;
			}

		}
	}

}
