package pacman_v0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


class mp extends JPanel implements ActionListener {

	private Dimension d = new Dimension(600, 600);
	private final int BLOCK_SIZE = 21;
	private final int N_BLOCKS = 29;
	private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
	private Image right = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/giright.gif").getImage();
	private Image left = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/gileft.gif").getImage();
	private Image up = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/gitop.gif").getImage();
	private Image down = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/gibuttom.gif").getImage();
	private Image Ghost = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/blue.png").getImage();
	private Image heart = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/heart.png").getImage();
	private Image wall = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/wall.jpeg").getImage();

	private boolean ingame = false;
	private boolean dying = false;

	// private int pacmani,pacmanj;
	private int moveX;
	private int moveY;
	private int speed = 3;
	private int score;
	private int Lives = 3;
	private Timer timer;
	private int pacmanX = 294;
	private int pacmanY = 462;
	/**private int possibleMoves[] = { 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1,
			0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0 };
	private int possibleMoves1[] = { 0, 1, 1, 1, 2, 1, 0, 2, 1, 2, 1, 1, 0, 2, 0, 1, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 2, 1,
			1, 2, 1, 0, 1, 0, 0, 1, 0, 2, 1, 0, 0, 1, 1, 1, 2, 1, 0, 2, 1, 2, 1, 1, 0, 2, 0, 1, 1, 0, 1, 2, 1, 0, 1, 0,
			1, 0, 2, 1, 1, 2, 1, 0, 1, 0, 0, 1, 0, 2, 1, 0 };*/
	private int PacmanMatriceI = 22;
	private int PacmanMatriceJ = 14;
	private final Font smallFont = new Font("Arial", Font.BOLD, 14);
	public URL StartGameSound = getClass().getResource("/Audio/MainMenu.wav");
	private int ghostX = 294;
	private int ghostY = 294;
	private int ghostMoveX;
	private int ghostMoveY;
	private int ghostMatriceI = 14;
	private int ghostMatriceJ = 14;
	private int previous_coordinate_I;
	private int previous_coordinate_J;
	private Timer move_timer;

	mp() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		initVariables();

	}

	private int gameBoard[][] = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 7, 0, 7, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 7, 9, 7, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 7, 7, 7, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public void initVariables() {

		timer = new Timer(50, this);
		timer.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, d.width, d.height);
		DrawMaze(g2d);
		drawScore(g2d);
		drawhearts(g2d);
		if (ingame) {
			playGame(g2d);
		} else {
			showintro(g2d);
		}

		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}

	private void showintro(Graphics2D g2d) {

		String start = "Press SPACE to start";
		g2d.setColor(Color.yellow);
		g2d.drawString(start, (SCREEN_SIZE) / 4 + 75, (SCREEN_SIZE) / 2 + 25);
	}

	public void DrawMaze(Graphics2D g2d) {
		int v = 0;
		int countarray = 0;
		int i;
		for (i = 0; i < SCREEN_SIZE; i += BLOCK_SIZE) {
			int k = 0;
			for (int j = 0; j < SCREEN_SIZE; j += BLOCK_SIZE) {
				g2d.setColor(new Color(0, 72, 251));
				if (gameBoard[v][k] == 1) {
					g2d.drawImage(wall, j + 8, i + 10, this);

				}
				if (gameBoard[v][k] == 0) {
					g2d.setColor(new Color(255, 255, 255));
					g2d.fillOval(j + 10, i + 10, 6, 6);
					// arr[countarray]=i+j;
					// countarray++;
				}
				// if(gameBoard[v][k]== 9) { //System.out.println("i : "+i+" k : "+j);

				// System.out.println("x : "+v+"y : "+k);}
				k++;
				// System.out.println(k);

			}
			v++;
			// System.out.println(v);

		}
		// System.out.println(score);
	}

	public void drawPac(Graphics2D g2d) {
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (moveX == -1) {
			g2d.drawImage(left, pacmanX, pacmanY, this);
			if (gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 0) {
				score++;
				// g2d.setColor(new Color(0, 72, 251));
				// g2d.fillRect(pacmanX+BLOCK_SIZE, pacmanY, BLOCK_SIZE, BLOCK_SIZE);
				gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] = 4;

			}
		} else if (moveX == 1) {
			g2d.drawImage(right, pacmanX, pacmanY, this);
			if (gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 0) {
				score++;
				gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] = 4;

			}
		} else if (moveY == -1) {
			g2d.drawImage(up, pacmanX, pacmanY, this);
			if (gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 0) {
				score++;
				gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] = 4;

			}
		} else {
			g2d.drawImage(down, pacmanX, pacmanY, this);
			if (gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 0) {
				score++;
				gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] = 4;

			}
		}

	}

	public void movePac() {
		if (moveX == -1 && moveY == 0
				&& (gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 0
						|| gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 3
						|| gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 4)) {// left
			PacmanMatriceJ = PacmanMatriceJ + moveX;
			pacmanX = pacmanX - BLOCK_SIZE;

		}
		if (moveX == 1 && moveY == 0
				&& (gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 0
						|| gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 3
						|| gameBoard[PacmanMatriceI][PacmanMatriceJ + moveX] == 4)) {// right
			PacmanMatriceJ = PacmanMatriceJ + moveX;
			pacmanX = pacmanX + BLOCK_SIZE;
		}
		if (moveX == 0 && moveY == -1
				&& (gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 0
						|| gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 4
						|| gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 3)) {// up
			PacmanMatriceI = PacmanMatriceI + moveY;
			pacmanY = pacmanY - BLOCK_SIZE;
		}
		if (moveX == 0 && moveY == 1
				&& (gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 0
						|| gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 3
						|| gameBoard[PacmanMatriceI + moveY][PacmanMatriceJ] == 4)) {// down
			PacmanMatriceI = PacmanMatriceI + moveY;
			pacmanY = pacmanY + BLOCK_SIZE;
		}

	}

	public void drawScore(Graphics2D g2d) {
		g2d.setFont(smallFont);
		g2d.setColor(new Color(5, 181, 79));
		String s = "Score: " + score;
		g2d.drawString(s, SCREEN_SIZE / 2 + 200, SCREEN_SIZE + 20);
	}

	public void drawhearts(Graphics2D g2d) {
		g2d.setFont(smallFont);
		g2d.setColor(new Color(5, 181, 79));
		String s = "lives: " + Lives;
		// g2d.drawString(s, SCREEN_SIZE / 2 -250, SCREEN_SIZE + 20);
		int y = 0;
		for (int i = 0; i <= Lives; i++) {
			g2d.drawImage(heart, SCREEN_SIZE / 2 - 350 + y, SCREEN_SIZE + 10, this);
			y = y + 50;
		}

	}

	public void draw_ghost(Graphics2D g2d) {
		if (ghostMoveX == 1) {
			g2d.drawImage(Ghost, ghostX, ghostY, this);
		}
		if (ghostMoveX == 2) {
			g2d.drawImage(Ghost, ghostX, ghostY, this);
		}
		if (ghostMoveY == 1) {
			g2d.drawImage(Ghost, ghostX, ghostY, this);
		}
		if (ghostMoveY == 2) {
			g2d.drawImage(Ghost, ghostX, ghostY, this);
		}

	}

	public void playGame(Graphics2D g2d) {
		check_collision();
		if (dying) {
			death();
		} else {
			drawPac(g2d);
			movePac();
			moveGhost();
			draw_ghost(g2d);
			

		}
	}

	public void death() {
		Lives--;
		initgame();
		if (Lives == 0) {
			ingame = false;
			score = 0;
		}
	}

	public void initgame() {
		pacmanX = 294;
		pacmanY = 462;
		PacmanMatriceI = 22;
		PacmanMatriceJ = 14;
		ghostX = 294;
		ghostY = 294;
		ghostMatriceI = 14;
		ghostMatriceJ = 14;
		dying = false;
	}

	public void check_collision() {
		if (ghostX == pacmanX && ghostY == pacmanY) {
			dying = true;
		}

	}

	public void moveGhost() {
		Random rand = new Random();
		int move_index;
		int rdm;
		/*try {
			Thread.sleep(120);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		move_index = rand.nextInt(4);
		System.out.println(move_index);
		switch (move_index) {
		case 0:
			if (gameBoard[ghostMatriceI][ghostMatriceJ + 1] == 0 && previous_coordinate_I != 2
					|| (gameBoard[ghostMatriceI][ghostMatriceJ + 1] == 4 && previous_coordinate_I != 2)) {
				ghostMatriceJ = ghostMatriceJ + 1;
				ghostX = ghostX + BLOCK_SIZE;
				previous_coordinate_I = 1;
				ghostMoveX = 1;

			}
			break;
		case 1:
			if (gameBoard[ghostMatriceI][ghostMatriceJ - 1] == 0 && previous_coordinate_I != 1
					|| (gameBoard[ghostMatriceI][ghostMatriceJ - 1] == 4 && previous_coordinate_I != 1)) {
				ghostMatriceJ = ghostMatriceJ - 1;
				ghostX = ghostX - BLOCK_SIZE;
				previous_coordinate_I = 2;
				ghostMoveX = 2;
			}
			break;
		case 2:
			if (gameBoard[ghostMatriceI + 1][ghostMatriceJ] == 0 && previous_coordinate_I != 4
					|| (gameBoard[ghostMatriceI + 1][ghostMatriceJ] == 4 && previous_coordinate_I != 4)) {
				ghostMatriceI = ghostMatriceI + 1;
				ghostY = ghostY + BLOCK_SIZE;
				previous_coordinate_I = 3;
				ghostMoveY = 1;
			}
			break;
		case 3:
			if (gameBoard[ghostMatriceI - 1][ghostMatriceJ] == 0
					&& (previous_coordinate_I != 3 || gameBoard[ghostMatriceI + 1][ghostMatriceJ] == 9)
					|| (gameBoard[ghostMatriceI - 1][ghostMatriceJ] == 4
							&& (previous_coordinate_I != 3 || gameBoard[ghostMatriceI + 1][ghostMatriceJ] == 9))) {
				ghostMatriceI = ghostMatriceI - 1;
				ghostY = ghostY - BLOCK_SIZE;
				previous_coordinate_I = 4;
				ghostMoveY = 2;
			}
			break;

		}
	}

	class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (ingame) {
				if (key == KeyEvent.VK_LEFT) {
					moveX = -1;
					moveY = 0;
				} else if (key == KeyEvent.VK_RIGHT) {
					moveX = 1;
					moveY = 0;
				} else if (key == KeyEvent.VK_UP) {
					moveX = 0;
					moveY = -1;
				} else if (key == KeyEvent.VK_DOWN) {
					moveX = 0;
					moveY = 1;
				} else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
					ingame = false;
				}
			} else {
				if (key == KeyEvent.VK_SPACE) {
					ingame = true;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
