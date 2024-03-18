package pacman_v0;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;






public class GUI 
{
	mp panel =new mp();
	static GreenGhost greenGhost=new GreenGhost();
	static RedGhost redGhost=new RedGhost();
	static BlueGhost blueGhost=new BlueGhost();
	static OrangeGhost orangeGhost=new OrangeGhost();
	JFrame window ;//game window
	
	public JPanel mainPanel;
    private JPanel introPanel ;
    private JPanel mainMenuPanel;
    private JPanel howToPlayPanel;
    private JPanel highScorePanel;
    private JPanel gameOverPanel;
    private JPanel gamePanel;
    private JPanel startMenu;
    private JPanel pauseMenu;
    private JButton returnBtn=new JButton();
    
    
    private JButton mapButton1 = new JButton("Map 1"); ;
    private JButton mapButton2 = new JButton("Map 2"); ;

    //creating intro composants
	URL imageISMAIK = getClass().getResource("/image/ISMAIK.jpg");

    private JLabel introTitle = new JLabel("", SwingConstants.CENTER);;
    private JLabel introImage = new JLabel();
    
    
    private JLabel titleRec = new JLabel("", SwingConstants.CENTER);

  //creating highscore composants
	
  	private JLabel[][] highScoresLabels = new JLabel[10][2];//the highscore cells
  	private JLabel highscoreTitle = new JLabel("HighScores", SwingConstants.CENTER);//the highscore menu title
  	private JButton returnButton2 = new JButton();
  	
  	//creating how to play composants
  	
  	private JLabel howToPlay[] = new JLabel[6];//the how to play menu content
  	private JButton returnButton3 = new JButton();
  	private Boolean blockKey=false;
  	
  	private JButton returnButton4 = new JButton("Return to Main Menu");

  	private String bestScore;
  	private String score;
  	private String rec;
    //game over composants
    private JLabel scoreover=new JLabel(score);

	List<String> myList = new ArrayList<>();	

	
    
	URL GameOver = getClass().getResource("/image/ovm.png");


    
    //creating main menu composants
   

	private JButton gameplayButton = new JButton();
	private JButton optionsButton = new JButton();
	private JButton highScoreButton = new JButton();
	private JButton chooseMap = new JButton();

	private JButton exitButton = new JButton();
    
	
	
    public CardLayout cardLayout;
    private JSeparator separator = new JSeparator();
	@SuppressWarnings("unused")
	private ImageIcon pacmanIcon,
	redghostIcon,
	blueghostIcon,
	orangeghostIcon,
	fruitIcon,
	greenghostIcon,
	pacmanIconleft,
	wall,
	wood;
	PacManMoves pacman=new PacManMoves();
	private JPanel boardPanel = new JPanel(new GridLayout(Board.getWidth(), Board.getWidth()));//gameboard panel
    private static int remainingHearts = 3; // Number of hearts initially

	private static JLabel[][] boardLabels = new JLabel[Board.getWidth()][Board.getWidth()];//gameboard cells
	public URL gameplaySound = getClass().getResource("/Audio/gameplay.wav");
	public URL StartGameSound = getClass().getResource("/Audio/MainMenu.wav");

    private static JPanel heartsPanel = new JPanel(); //panel for displaying hearts
	private static JLabel scoreLabel = new JLabel("Score :"+Board.getScore(),SwingConstants.LEFT);

	private URL menuClickSound =getClass().getResource("/Audio/clicksound.wav");
	private URL menuMusic =getClass().getResource("/Audio/menumusic.wav");



    private JLabel pauseLabel;
    private ImageIcon pauseImage = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/pausedmenu.png"); // Change this to your pause image path
    // Recadrer l'image à la taille spécifiée
    Image image = pauseImage.getImage();
    Image imageRecadree = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    // Créer une nouvelle ImageIcon avec l'image recadrée
    ImageIcon iconRecadree = new ImageIcon(imageRecadree);
	@SuppressWarnings("serial")
	public static int chances=3;

	public GUI (Board board , PacManMoves pacman) throws IOException
	{	

        // Create pause label
        pauseLabel = new JLabel();
        pauseLabel.setText("Press ESCAPE to resume");
        pauseLabel.setVisible(false); // Initially, the pause image is not visible
        pauseLabel.setLayout(null); // Set layout to null to remove the background

        // Set the alignment to center the image
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setVerticalAlignment(SwingConstants.CENTER);

		//Get Scores for game over Panel
		Path filePath = Paths.get("/Users/assylchouikh/Dev/Java/pacman_v0/scores/scores.txt");
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for(String line: lines) {
            		myList.add(line);
            	
            	
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        Collections.sort(myList, Collections.reverseOrder()); 
        
        
        
        
        
		wall=new ImageIcon(this.getClass().getResource("/image/wall.jpeg"));
		wood=new ImageIcon(this.getClass().getResource("/image/wood.jpg"));

		getBoardPanel().setSize(600,600);
		
		for (int i = 0; i <Board.getWidth(); i++)//loading the board cells from the table
        {
            for (int j = 0; j <Board.getWidth(); j++) 
            {
                getBoardLabels()[i][j] = new JLabel();
                getBoardLabels()[i][j].setOpaque(true);//make cell background color visible
                getBoardPanel().add(getBoardLabels()[i][j]);
                getBoardLabels()[i][j].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),BorderFactory.createEmptyBorder(0, 0, 0, 0)));//add black boarder to the GRID
                getBoardLabels()[i][j].setBorder(null);//remove the boarder after precising it
                if (Board.gameBoard[i][j] ==1) {
                	getBoardLabels()[i][j].setIcon(wall);
                }
                if (Board.gameBoard[i][j] ==10) {
                	getBoardLabels()[i][j].setIcon(wood);
                }
                
            }
        }
		ActionListener toIntroMenu = new ActionListener() //action required to move to main menu
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"intro");		
			}
		};
		ActionListener toGameplay = new ActionListener() //action required to move to gameplay

		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				blockKey=true;
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"gameplay");
				Audio.closeClip();
				
				try//restart the board everytime you enter gameplay 
				{		
					board.restartBoard();
					
				}
				catch (InterruptedException e1) 
				{
					e1.printStackTrace();
				} 	
				window.setFocusable(true);
				window.requestFocusInWindow();//focus to regain control of the game after the click
				
			}
		};
		ActionListener toStartMenu = new ActionListener() //action required to move to options
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				Audio.startSound(menuClickSound);

				cardLayout.show(mainPanel,"startMenu");
			}
		};
		ActionListener returnMainMenu = new ActionListener() //action required to move to main menu
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Audio.closeClip();
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"mainMenu");		
			}
		};

		ActionListener toMainMenu = new ActionListener() //action required to move to main menu
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"mainMenu");		
			}
		};
		ActionListener toHighscore = new ActionListener() //action required to move to highscore
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"highScore");
			}
		};
		ActionListener toHowToPlay = new ActionListener() //action required to move to options
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				Audio.startSound(menuClickSound);
				cardLayout.show(mainPanel,"howtoplay");
			}
		};
		ActionListener toExit = new ActionListener() //action required to move to exit the game
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Audio.startSound(menuClickSound);
				System.exit(0);
			}
		};

		Audio.startClip(menuMusic);

		// Create hearts panel
        heartsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        heartsPanel.setBackground(Color.white);
        heartsPanel.add(scoreLabel);
        for (int i = 0; i < remainingHearts; i++) {
            ImageIcon heartIcon = new ImageIcon(getClass().getResource("/image/heart.png")); // Assuming the heart image path
            JLabel heartLabel = new JLabel(heartIcon);
            heartsPanel.add(heartLabel);
        }
        
        
        // Create the CardLayout and main panel to move between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
		window = new JFrame("PACMAN by Assyl Chouikh");
		window.setVisible(true);//make the frame visible
		window.setSize(600,600);//size of the frame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when press X
		
		// Creating GamePlay panel to window

        gamePanel = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawOval(600, 600,50 , 50);           
                }
        };
        gamePanel.add(getBoardPanel(), BorderLayout.CENTER);
        gamePanel.add(heartsPanel, BorderLayout.NORTH);
        //Creating Choosing Map Panel
        gamePanel.setVisible(true); // Initially, the pause image is not visible

       
        
        //Creating Pause Menu
        
        pauseMenu=new JPanel(new FlowLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/stop.png");
                Image image = imageIcon.getImage();

                // Scale the image to fit the panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
        
 

        startMenu=new JPanel(new FlowLayout());
        startMenu.add(mapButton1);//add game button
        mapButton1.setPreferredSize(new Dimension(500, 60));
        mapButton1.addActionListener(toGameplay);
        
        startMenu.add(separator);
        
        startMenu.add(mapButton2);//add game button
        mapButton2.setPreferredSize(new Dimension(500, 60));
        mapButton2.addActionListener(toGameplay);
        startMenu.add(separator);
        
        startMenu.add(returnButton4);
        returnButton4.setPreferredSize(new Dimension(300,50));
        returnButton4.addActionListener(toMainMenu);
        startMenu.add(separator);
        
        

        
        // Creating the Intro Panel
        
        introPanel = new JPanel(new FlowLayout()){
            private Image backgroundImage = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/menuma.gif").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        introPanel.setLayout(null);
        
        
        

        JButton introButton = new JButton() ;
        introButton.setBounds(230, 444, 150, 45);

        introPanel.add(introButton);//add button
    	introButton.setBackground(null); // Revert background color
        introButton.setBorderPainted(false); 

     
        introButton.setFont(new Font("Calibri", Font.PLAIN, 16));
        
		introButton.addActionListener(toMainMenu);
		
		introPanel.add(separator);
        
        
        
        
		// Creating mainMenu panel to window

        mainMenuPanel = new JPanel(new FlowLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/finalmenu.png");
                Image image = imageIcon.getImage();

                // Scale the image to fit the panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainMenuPanel.setLayout(null);
        
        gameplayButton.setBorderPainted(false); 
        gameplayButton.setBackground(null); // Revert background color
        gameplayButton.setBounds(195, 155, 210, 58);
		mainMenuPanel.add(gameplayButton);//add game button
		gameplayButton.addActionListener(toGameplay);
        
		mainMenuPanel.add(separator);
		
		//mainMenuPanel.add(chooseMap);//add game button
		chooseMap.setBounds(200, 200, 200, 60);

		chooseMap.addActionListener(toStartMenu);
        
		mainMenuPanel.add(separator);
		
		highScoreButton.setBorderPainted(false); 
		highScoreButton.setBackground(null); // Revert background color
		highScoreButton.setBounds(195, 230, 210, 58);
		mainMenuPanel.add(highScoreButton);//add HighScore button
		highScoreButton.setPreferredSize(new Dimension(300, 60));
        highScoreButton.addActionListener(toHighscore);
        
		mainMenuPanel.add(separator);
		
		optionsButton.setBorderPainted(false); 
		optionsButton.setBackground(null); // Revert background color
		optionsButton.setBounds(195, 305, 210, 58);
		mainMenuPanel.add(optionsButton);//add options button
		optionsButton.addActionListener(toHowToPlay);
		
		mainMenuPanel.add(separator);
		
		exitButton.setBorderPainted(false); 
		exitButton.setBackground(null); // Revert background color
		exitButton.setBounds(195, 385, 210, 58);
		mainMenuPanel.add(exitButton);//add exit button
		exitButton.addActionListener(toExit);

		returnBtn.setBorderPainted(false); 
		returnBtn.setBackground(null); // Revert background color
		returnBtn.setBounds(230, 530, 130, 35);
		mainMenuPanel.add(returnBtn);//add exit button
		returnBtn.addActionListener(toIntroMenu);
		
		
		//Game over panel
        gameOverPanel = new JPanel(new FlowLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/gover.png");
                Image image = imageIcon.getImage();

                // Scale the image to fit the panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        gameOverPanel.setLayout(null);	
        
        gameOverPanel.setBackground(Color.black);
        introTitle.setPreferredSize(new Dimension(700,80));
        gameOverPanel.add(introTitle);
        titleRec.setPreferredSize(new Dimension(700,20));
        titleRec.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
        titleRec.setText(rec);
        titleRec.setForeground(Color.WHITE);
        
        
        introTitle.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 30));
        introTitle.setText("Best Score is : "+this.bestScore+" Your Score is : "+this.score);
        introTitle.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        introTitle.setForeground(Color.RED);
        BufferedImage image1 = ImageIO.read(GameOver);
		ImageIcon icon1 = new ImageIcon(image1);
		introImage.setIcon(icon1);
		
		introImage.setPreferredSize(new Dimension(480, 400));
		gameOverPanel.add(introImage,BorderLayout.CENTER);
        
		  

		
		//the how to play panel
		
        howToPlayPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/howp.png");
                Image image = imageIcon.getImage();

                // Scale the image to fit the panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        howToPlayPanel.setLayout(null);;
        

        
        howToPlay[0] = new JLabel();
        howToPlay[0].setForeground(Color.WHITE);
        howToPlay[0].setBounds(20, 10, 550, 350);
        howToPlay[0].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlay[0].setText("- Move Pacman by pressing on LEFT/Q , RIGHT/D , Up/Z and Down/S buttons.");
        howToPlayPanel.add(howToPlay[0]);
        
        howToPlay[1] = new JLabel();
        howToPlay[1].setForeground(Color.WHITE);
        howToPlay[1].setBounds(20, 70, 550, 350);
        howToPlay[1].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlay[1].setText("- There is 1 Pac-Man and 4 Ghosts in the maze at a time.");
        howToPlayPanel.add(howToPlay[1]);
        
        howToPlay[2] = new JLabel();
        howToPlay[2].setForeground(Color.WHITE);
        howToPlay[2].setBounds(20, 130, 550, 350);
        howToPlay[2].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlay[2].setText("- The Ghosts win if the Pac-Man loses all their  (you can choose how many lives they will have to vary the difficulty).");
        howToPlayPanel.add(howToPlay[2]);
        
        howToPlay[3] = new JLabel();
        howToPlay[3].setForeground(Color.WHITE);
        howToPlay[3].setBounds(20, 190, 550, 350);
        howToPlay[3].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlay[3].setText("- If a Ghost catches a Pac-Man, they lose a life and have to sacrifice all the pieces they are holding.");
        howToPlayPanel.add(howToPlay[3]);
        
        howToPlay[4] = new JLabel();
        howToPlay[4].setForeground(Color.WHITE);
        howToPlay[4].setBounds(20, 250, 550, 350);
        howToPlay[4].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlay[4].setText("- If a Pac-Man collects all the pieces before they lose their lives, they win.");
        howToPlayPanel.add(howToPlay[3]);
        
        howToPlay[5] = new JLabel("**GOOD LUCK**",SwingConstants.RIGHT);
        howToPlay[5].setForeground(Color.WHITE);
        howToPlay[5].setBounds(20, 310, 550, 350);
        howToPlay[5].setFont(new Font("Arial", Font.BOLD, 14));
        howToPlayPanel.add(howToPlay[4]);
        
        returnButton2.setBackground(null); // Revert background color

        returnButton2.setBorderPainted(false); 
        returnButton2.addActionListener(toMainMenu);
        howToPlayPanel.add(returnButton2);
        returnButton2.setBounds(230, 525, 130, 40);
        
        
        
        highScorePanel = new JPanel(){
            private Image backgroundImage = new ImageIcon("/Users/assylchouikh/Dev/Java/pacman_v0/resources/image/scores.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        highScorePanel.setLayout(null);;
        returnButton3.addActionListener(toMainMenu);
        highscoreTitle = new JLabel();
        highscoreTitle.setPreferredSize(new Dimension(400,70));
        //highScorePanel.add(highscoreTitle);

        highScorePanel.setForeground(Color.WHITE);
        highscoreTitle.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 10));
        for (int i = 0; i < myList.size()-1; i++) 
        {	
        	highScoresLabels[i][0] = new JLabel("",SwingConstants.LEFT);
        	highScoresLabels[i][0].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.YELLOW),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        	highScoresLabels[i][0].setBounds(50, 100, 250,40);
        	highScoresLabels[i][0].setText(" Player "+(i+1)+" :");
        	highScoresLabels[i][0].setFont(new Font("Arial", Font.BOLD, 20));
        	highScoresLabels[i][0].setForeground(Color.white);
        	highScorePanel.add(highScoresLabels[i][0]);
        	
        	
        	
        	highScoresLabels[i][1] = new JLabel("",SwingConstants.RIGHT);
        	highScoresLabels[i][1].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.YELLOW),BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        	highScoresLabels[i][1].setBounds(300, 100, 250,40);
        	highScoresLabels[i][1].setText(myList.get(i));
        	highScoresLabels[i][1].setFont(new Font("Arial", Font.BOLD, 24));
        	highScoresLabels[i][1].setForeground(Color.white);
        	highScorePanel.add(highScoresLabels[i][1]);	
        }
        highScorePanel.add(separator);
        
        returnButton3.setBackground(null); // Revert background color
        returnButton3.setBorderPainted(false); 
        
        highScorePanel.add(returnButton3);
        returnButton3.setBounds(230, 525, 130, 40);

     // Add JPanels to main panel
        mainPanel.add(introPanel, "intro");
        mainPanel.add(mainMenuPanel, "mainMenu");
        mainPanel.add(gamePanel, "gameplay");
        mainPanel.add(howToPlayPanel, "howtoplay");
        mainPanel.add(highScorePanel, "highScore");
        mainPanel.add(startMenu, "startMenu");

        mainPanel.add(pauseMenu, "pauseMenu");

        mainPanel.add(gameOverPanel, "gameover");

        window.add(mainPanel);
		window.setLocationRelativeTo(null);//the frame will appear in the center of the screen
        window.setVisible(true);
		window.addKeyListener//game keyboard listeners(controls)
        (
        new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e)
            {	
            	window.setFocusable(true);
                switch (e.getKeyCode()) 
                {
                
                case KeyEvent.VK_LEFT:case KeyEvent.VK_Q:
                	for (int i=0;i<Board.getWidth();i++)
            		{
            			for (int j=0;j<Board.getWidth();j++)
            			{
            				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i][j-1]== 0)||(Board.gameBoard[i][j-1]== 4)))//cell should be empty at left direction
            				{
            					PacManMoves.moveDirection=1;//left direction
            				}
            			}
            		}
                	
                	break;
                case KeyEvent.VK_R:
                	
    				if(!Board.isPaused && Board.assureOneTime==0) {
    					blockKey=false;
        				Audio.startSound(menuClickSound);
                		System.out.println("pause");
                		board.pause();
                		Audio.closeClip();
        				cardLayout.show(mainPanel,"mainMenu");
                		Audio.startClip(menuMusic);
        				//cardLayout.show(mainPanel,"pauseMenu");

                	}    				
    				break;
                case KeyEvent.VK_ESCAPE:


                	togglePauseMenu();
                	//updateGameBoard(board);
                	if(!Board.isPaused) {
                		
                		System.out.println("pause");
                		board.pause();
                		Audio.pauseClip();
        				//cardLayout.show(mainPanel,"pauseMenu");

                	}else if(Board.isPaused&&blockKey) {
                		System.out.println("resume");
                		board.resume();
                		Audio.resumeClip();
        				//cardLayout.show(mainPanel,"gameplay");

                	}
                		

                	
                	break;
                case KeyEvent.VK_UP:
                	case KeyEvent.VK_Z:	
                	for (int i=0;i<Board.getWidth();i++)
            		{
            			for (int j=0;j<Board.getWidth();j++)
            			{
            				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i-1][j]== 0)||(Board.gameBoard[i-1][j]== 4)))//cell should be empty at up direction
            				{
            					PacManMoves.moveDirection=2;//up direction
            				}
            			}
            		}
                	break;
                case KeyEvent.VK_DOWN:case KeyEvent.VK_S:
                	for (int i=Board.getWidth()-1;i>0;i--)
            		{
            			for (int j=0;j<Board.getWidth();j++)
            			{
            				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i+1][j]== 0)||(Board.gameBoard[i+1][j]== 4)))//cell should be empty at down direction
            				{
            					PacManMoves.moveDirection=3;//down direction
            				}
            			}
            		}
                	break;
                case KeyEvent.VK_RIGHT:case KeyEvent.VK_D:
                	for (int i=0;i<Board.getWidth();i++)
            		{
            			for (int j=Board.getWidth()-1;j>=0;j--)
            			{
            				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i][j+1]== 0)||(Board.gameBoard[i][j+1]== 4)))//cell should be empty at right direction
            				{

            					PacManMoves.moveDirection=4;//right direction
            				}
            			}
            		}
                	break;
                }
            }
        }
        );	
	}
	
	
	
	
	
	
	
	
	
	  
	public void closeGui() {
		window.dispose();
	}

	public void decrementHearts() {
        remainingHearts--;
        heartsPanel.removeAll();
        
     
    }
	public static void restart(Board board) {

		Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()] =0;
        Board.gameBoard[22][14]=3;
		getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()].setIcon(null);;

        Board.gameBoard[RedGhost.getRedGhostI()][RedGhost.getRedGhostJ()]=0;
		getBoardLabels()[RedGhost.getRedGhostI()][RedGhost.getRedGhostJ()].setIcon(null);

		Board.gameBoard[BlueGhost.getBlueGhostI()][BlueGhost.getBlueGhostJ()]=0;
		getBoardLabels()[BlueGhost.getBlueGhostI()][BlueGhost.getBlueGhostJ()].setIcon(null);

		Board.gameBoard[OrangeGhost.getOrangeGhostI()][OrangeGhost.getOrangeGhostJ()]=0;
		getBoardLabels()[OrangeGhost.getOrangeGhostI()][OrangeGhost.getOrangeGhostJ()].setIcon(null);

		Board.gameBoard[GreenGhost.getGreenGhostI()][GreenGhost.getGreenGhostJ()]=0;
		getBoardLabels()[GreenGhost.getGreenGhostI()][GreenGhost.getGreenGhostJ()].setIcon(null);

		Board.gameBoard[0][0]=1;

		Board.gameBoard[13][13]=5;
		Board.gameBoard[13][15]=6;
		Board.gameBoard[15][14]=2;

		Board.gameBoard[15][13]=7;
		Board.gameBoard[15][15]=8;

		affiche();

		
		
	}
	public static void restartPositions() {
		  for(int i=0;i<Board.width;i++) {
				for(int j=0;j<Board.width;j++) {
					if(Board.gameBoard[i][j]!=0 && Board.gameBoard[i][j]!=2&& Board.gameBoard[i][j]!=1) {
						Board.gameBoard[i][j]=0;
					}
					if(Board.gameBoard[i][j]==5||Board.gameBoard[i][j]==6||Board.gameBoard[i][j]==7||Board.gameBoard[i][j]==8) {
						getBoardLabels()[i][j].setIcon(null);
					}

				}
		  }
		  Board.gameBoard[13][13]=5;
		  Board.gameBoard[13][15]=6;
		  Board.gameBoard[15][13]=7;
		  Board.gameBoard[15][15]=8;


	  }
	
	public static void affiche() {
		System.out.println("<----------------------Board------------------->");

		for(int i=0;i<Board.width;i++) {
			for(int j=0;j<Board.width;j++) {
				System.out.print(Board.gameBoard[i][j]);
			}
			System.out.println();
		}
	}
	// Method to toggle the visibility of the pause menu
    private void togglePauseMenu() {
        pauseLabel.setVisible(!pauseLabel.isVisible()); // Toggle visibility
        exitButton.setVisible(pauseLabel.isVisible()); // Show exit button when pause menu is visible
    }
	public void updateGameBoard(Board board)//update the board state by verifying the current IDs in the table
	{	
		pacmanIcon=new ImageIcon(this.getClass().getResource("/image/giright.gif"));

		blueghostIcon=new ImageIcon(this.getClass().getResource("/image/blue.png"));
		orangeghostIcon=new ImageIcon(this.getClass().getResource("/image/orange.png"));
		fruitIcon=new ImageIcon(this.getClass().getResource("/image/fruit.png"));
		redghostIcon=new ImageIcon(this.getClass().getResource("/image/red.png"));
		greenghostIcon=new ImageIcon(this.getClass().getResource("/image/green.png"));

		for (int i=0 ; i<Board.getWidth() ; i++)
		{
			for (int j=0 ; j<Board.getWidth() ; j++)
			{	
				
				if(Board.gameBoard[i][j] ==0)//empty cell
				{
					getBoardLabels()[i][j].setBackground(Color.BLACK);
					
				}
				/*else if (Board.gameBoard[i][j] ==1)//obstacle
				{
					getBoardLabels()[i][j].setBackground(Color.BLACK);
					//boardLabels[i][j].setIcon(bgIcon);
					
				}*/
				else if (Board.gameBoard[i][j] ==2)//ghost's room door
				{
					getBoardLabels()[i][j].setBackground(Color.GRAY);
				}
				else if (Board.gameBoard[i][j] ==3 )//Pacman
				{
					//boardLabels[i][j].setBackground(Color.YELLOW);

					/*getBoardLabels()[i][j].setIcon(pacmanIcon);
					if(Board.gameBoard[i][j+1]!=5||Board.gameBoard[i][j+1]!=6 ||Board.gameBoard[i][j+1]!=7||Board.gameBoard[i][j+1]!=8) {
						getBoardLabels()[i][j+1].setIcon(null);
					}
					if(Board.gameBoard[i][j-1]!=5||Board.gameBoard[i][j-1]!=6 ||Board.gameBoard[i][j-1]!=7||Board.gameBoard[i][j-1]!=8) {
						getBoardLabels()[i][j-1].setIcon(null);

					}
					if(Board.gameBoard[i+1][j]!=5||Board.gameBoard[i+1][j]!=6 ||Board.gameBoard[i+1][j]!=7||Board.gameBoard[i+1][j]!=8) {
						getBoardLabels()[i+1][j].setIcon(null);
					}
					if(Board.gameBoard[i-1][j]!=5||Board.gameBoard[i-1][j]!=6 ||Board.gameBoard[i-1][j]!=7||Board.gameBoard[i-1][j]!=8) {
						getBoardLabels()[i-1][j].setIcon(null);

					}*/
					
				}
				else if (Board.gameBoard[i][j] ==4)//coin
				{
					//boardLabels[i][j].setBackground(Color.PINK);
					getBoardLabels()[i][j].setIcon(fruitIcon);
					
					
				}
				else if (Board.gameBoard[i][j] ==5)//red ghost
				{
					//boardLabels[i][j].setBackground(Color.RED);
					getBoardLabels()[i][j].setIcon(redghostIcon);
					if((Board.gameBoard[i][j+1]!=6 ||Board.gameBoard[i][j+1]!=7||Board.gameBoard[i][j+1]!=8||Board.gameBoard[i][j+1]!=3)&&Board.gameBoard[i][j+1]!=1) {
						getBoardLabels()[i][j+1].setIcon(null);
					}
					if((Board.gameBoard[i][j-1]!=6 ||Board.gameBoard[i][j-1]!=7||Board.gameBoard[i][j-1]!=8||Board.gameBoard[i][j-1]!=3)&&Board.gameBoard[i][j-1]!=1) {
						getBoardLabels()[i][j-1].setIcon(null);

					}
					if((Board.gameBoard[i+1][j]!=6 ||Board.gameBoard[i+1][j]!=7||Board.gameBoard[i+1][j]!=8||Board.gameBoard[i+1][j]!=3)&&Board.gameBoard[i+1][j]!=1) {
						getBoardLabels()[i+1][j].setIcon(null);
					}
					if((Board.gameBoard[i-1][j]!=6 ||Board.gameBoard[i-1][j]!=7||Board.gameBoard[i-1][j]!=8||Board.gameBoard[i-1][j]!=3)&&Board.gameBoard[i-1][j]!=1) {
						getBoardLabels()[i-1][j].setIcon(null);

					}
					

					
				}
				else if (Board.gameBoard[i][j] ==6)//Blue ghost
				{
					//boardLabels[i][j].setBackground(Color.CYAN);
					getBoardLabels()[i][j].setIcon(blueghostIcon);
					if((Board.gameBoard[i][j+1]!=5 ||Board.gameBoard[i][j+1]!=7||Board.gameBoard[i][j+1]!=8||Board.gameBoard[i][j+1]!=3)&&Board.gameBoard[i][j+1]!=1) {
						getBoardLabels()[i][j+1].setIcon(null);
					}
					if((Board.gameBoard[i][j-1]!=5 ||Board.gameBoard[i][j-1]!=7||Board.gameBoard[i][j-1]!=8||Board.gameBoard[i][j-1]!=3)&&Board.gameBoard[i][j-1]!=1) {
						getBoardLabels()[i][j-1].setIcon(null);

					}
					if((Board.gameBoard[i+1][j]!=5 ||Board.gameBoard[i+1][j]!=7||Board.gameBoard[i+1][j]!=8||Board.gameBoard[i+1][j]!=3)&&Board.gameBoard[i+1][j]!=1) {
						getBoardLabels()[i+1][j].setIcon(null);
					}
					if((Board.gameBoard[i-1][j]!=5 ||Board.gameBoard[i-1][j]!=7||Board.gameBoard[i-1][j]!=8||Board.gameBoard[i-1][j]!=3)&&Board.gameBoard[i-1][j]!=1) {
						getBoardLabels()[i-1][j].setIcon(null);

					}
				}
				else if (Board.gameBoard[i][j] ==7)//orange ghost
				{
					getBoardLabels()[i][j].setIcon(orangeghostIcon);
					if((Board.gameBoard[i][j+1]!=6 ||Board.gameBoard[i][j+1]!=5||Board.gameBoard[i][j+1]!=8||Board.gameBoard[i][j+1]!=3)&&Board.gameBoard[i][j+1]!=1) {
						getBoardLabels()[i][j+1].setIcon(null);
					}
					if((Board.gameBoard[i][j-1]!=6 ||Board.gameBoard[i][j-1]!=5||Board.gameBoard[i][j-1]!=8||Board.gameBoard[i][j-1]!=3)&&Board.gameBoard[i][j-1]!=1) {
						getBoardLabels()[i][j-1].setIcon(null);

					}
					if((Board.gameBoard[i+1][j]!=6 ||Board.gameBoard[i+1][j]!=5||Board.gameBoard[i+1][j]!=8||Board.gameBoard[i+1][j]!=3)&&Board.gameBoard[i+1][j]!=1) {
						getBoardLabels()[i+1][j].setIcon(null);
					}
					if((Board.gameBoard[i-1][j]!=6 ||Board.gameBoard[i-1][j]!=5||Board.gameBoard[i-1][j]!=8||Board.gameBoard[i-1][j]!=3)&&Board.gameBoard[i-1][j]!=1) {
						getBoardLabels()[i-1][j].setIcon(null);

					}
				}
				else if (Board.gameBoard[i][j] ==8)//GREEN ghost
				{
					getBoardLabels()[i][j].setIcon(greenghostIcon);
					if((Board.gameBoard[i][j+1]!=6 ||Board.gameBoard[i][j+1]!=7||Board.gameBoard[i][j+1]!=5||Board.gameBoard[i][j+1]!=3)&&Board.gameBoard[i][j+1]!=1) {
						getBoardLabels()[i][j+1].setIcon(null);
					}
					if((Board.gameBoard[i][j-1]!=6 ||Board.gameBoard[i][j-1]!=7||Board.gameBoard[i][j-1]!=5||Board.gameBoard[i][j-1]!=3)&&Board.gameBoard[i][j-1]!=1) {
						getBoardLabels()[i][j-1].setIcon(null);

					}
					if((Board.gameBoard[i+1][j]!=6 ||Board.gameBoard[i+1][j]!=7||Board.gameBoard[i+1][j]!=5||Board.gameBoard[i+1][j]!=3)&&Board.gameBoard[i+1][j]!=1) {
						getBoardLabels()[i+1][j].setIcon(null);
					}
					if((Board.gameBoard[i-1][j]!=6 ||Board.gameBoard[i-1][j]!=7||Board.gameBoard[i-1][j]!=5||Board.gameBoard[i-1][j]!=3)&&Board.gameBoard[i-1][j]!=1) {
						getBoardLabels()[i-1][j].setIcon(null);

					}
				}
			}
		}
	}




	public static JLabel[][] getBoardLabels() {
		return boardLabels;
	}




	public void setBoardLabels(JLabel[][] boardLabels) {
		GUI.boardLabels = boardLabels;
	}


	public JPanel getBoardPanel() {
		return boardPanel;
	}


	public void setBoardPanel(JPanel boardPanel) {
		this.boardPanel = boardPanel;
	}
	public void redirect(String a,String bestScore,String score ,String rec) {
		cardLayout.show(mainPanel,a);
		this.bestScore=bestScore;
		this.score=score;
		this.rec=rec;
		

	}
public static void main(String[] args) throws InterruptedException, IOException {
	ActionListener incrementScore = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	  heartsPanel.removeAll();
	    	  for (int i = 0; i < remainingHearts; i++) {
	              ImageIcon heartIcon = new ImageIcon(getClass().getResource("/image/heart.png")); // Assuming the heart image path
	              JLabel heartLabel = new JLabel(heartIcon);
	              heartsPanel.add(heartLabel);
	          }
	      	 JLabel scoreLabel = new JLabel("Score :"+Board.getScore(),SwingConstants.CENTER);

	          heartsPanel.add(scoreLabel);
	    	  
	    	  
	          
	      }
	  };
		Timer timer=new Timer(150,incrementScore);
		timer.start();
		

		new Gameplay();
		
        

	}

}


