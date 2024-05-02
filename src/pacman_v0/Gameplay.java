package pacman_v0;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class Gameplay implements KeyListener
{
	
	public static boolean assureOneTimeRed ;
	public static boolean assureOneTimeBlue ;
	public static boolean assureOneTimeGreen ;
	public static boolean assureOneTimeOrange ;
	public static boolean assureOneTimeChance=false ;
	public static boolean assureSecondTimeChance=false;
	public static boolean assureOneKill=false;

	static String newRecord="";
	 //Count Chances available

    static int chances = 4;
    static boolean assureOneTime=false;
    static boolean assureSecondTime=false;
    static boolean test=false;
    static boolean test2=false;
    static boolean gameOver=false;

	public Gameplay() throws InterruptedException, IOException 
	{
		
		ImageIcon pacmanIconleft=new ImageIcon(this.getClass().getResource("/image/gileft.gif"));
		ImageIcon pacmanIconRight=new ImageIcon(this.getClass().getResource("/image/giright.gif"));
		ImageIcon pacmanIconTop=new ImageIcon(this.getClass().getResource("/image/gitop.gif"));
		ImageIcon pacmanIconButtom=new ImageIcon(this.getClass().getResource("/image/gibuttom.gif"));
		mapping map=new mapping();
		Board board = new Board() ;//calling board object
		PacManMoves pacman = new PacManMoves();//calling Pacman moves object

		RedGhost redGhost = new RedGhost();
		BlueGhost blueGhost = new BlueGhost();
		GreenGhost greenGhost = new GreenGhost();
		OrangeGhost orangeGhost = new OrangeGhost();
		GUI gui = new GUI(board , pacman);//calling GUI object
		int speedLvl=150;
		Timer pacmanTimer = new Timer//the function that is responsible for moving pacman every specific time
	            (
	            150, 
	            new ActionListener() 
	            {
	            	@Override
	            	public void actionPerformed(ActionEvent e)
	            	{	
	            	 if(PacManMoves.moveDirection == 1)
	            		{
	            			pacman.moveLeft();
	            			gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()].setIcon(pacmanIconleft);

	            			if(Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()+1]!=5||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()+1]!=6 ||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()+1]!=7||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()+1]!=8) {
	            				gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()+1].setIcon(null);
	    					}
	    					

	            		}
	            		else if (PacManMoves.moveDirection == 2)
	            		{
	            			pacman.moveUp();
	            			gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()].setIcon(pacmanIconTop);

	            			
	    					
	    					if(Board.gameBoard[PacManMoves.getPacmanI()+1][PacManMoves.getPacmanJ()]!=5||Board.gameBoard[PacManMoves.getPacmanI()+1][PacManMoves.getPacmanJ()]!=6 ||Board.gameBoard[PacManMoves.getPacmanI()+1][PacManMoves.getPacmanJ()]!=7||Board.gameBoard[PacManMoves.getPacmanI()+1][PacManMoves.getPacmanJ()]!=8) {
	    						gui.getBoardLabels()[PacManMoves.getPacmanI()+1][PacManMoves.getPacmanJ()].setIcon(null);
	    					}
	    					
	            		}
	            		else if (PacManMoves.moveDirection == 3)
	            		{
	            			pacman.moveDown();
	               			gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()].setIcon(pacmanIconButtom);
	            			if(PacManMoves.getPacmanI()-1!=-1) {

		    					if(Board.gameBoard[PacManMoves.getPacmanI()-1][PacManMoves.getPacmanJ()]!=5||Board.gameBoard[PacManMoves.getPacmanI()-1][PacManMoves.getPacmanJ()]!=6 ||Board.gameBoard[PacManMoves.getPacmanI()-1][PacManMoves.getPacmanJ()]!=7||Board.gameBoard[PacManMoves.getPacmanI()-1][PacManMoves.getPacmanJ()]!=8) {
		    						gui.getBoardLabels()[PacManMoves.getPacmanI()-1][PacManMoves.getPacmanJ()].setIcon(null);}
	            			}
	            			
	            		}
	            		else if (PacManMoves.moveDirection == 4)
	            		{
	            			pacman.moveRight();
	            			gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()].setIcon(pacmanIconRight);

	            			if(PacManMoves.getPacmanJ()-1!=-1) {
		    					if(Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()-1]!=5||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()-1]!=6 ||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()-1]!=7||Board.gameBoard[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()-1]!=8) {
		    						gui.getBoardLabels()[PacManMoves.getPacmanI()][PacManMoves.getPacmanJ()-1].setIcon(null);

		    					}
		    					
	            			}
	            			
	            		}
	          
	            	}
	            }		
	            );
		Timer redGhostTimer = new Timer//the function that is responsible for moving pacman every specific time
	            (
	            		speedLvl, 
	            new ActionListener() 
	            {
	            	@Override
	            	public void actionPerformed(ActionEvent e)
	            	{	assureOneTimeRed=true;
	            		gui.updateGameBoard(board);
	            		
	            		
	            		 if(RedGhost.moveDirection == 1)
	            		{
	            			redGhost.moveLeft();
	            		}
	            		else if (RedGhost.moveDirection == 2)
	            		{
	            			redGhost.moveUp();
	            		}
	            		else if (RedGhost.moveDirection == 3)
	            		{
	            			if(RedGhost.getRedAux()==0 && Board.gameBoard[RedGhost.getRedGhostI()+1][RedGhost.getRedGhostJ()]==2)
	            			{
	            				redGhost.moveRight();
	            			}
	            			else
	            			{
	            				redGhost.moveDown();	
	            			}
	            		}
	            		else if (RedGhost.moveDirection == 4)
	            		{
	            			redGhost.moveRight();
	            		}
	            	}
	            }		
	            );
		Timer orangeGhostTimer = new Timer//the function that is responsible for moving pacman every specific time
	            (
	            		speedLvl, 
	            new ActionListener() 
	            {
	            	@Override
	            	public void actionPerformed(ActionEvent e)
	            	{	assureOneTimeOrange=true;
	            		gui.updateGameBoard(board);
	            		
	            		
	            		 if(OrangeGhost.moveDirection == 1)
	            		{
	            			orangeGhost.moveLeft();
	            		}
	            		else if (OrangeGhost.moveDirection == 2)
	            		{
	            			orangeGhost.moveUp();
	            		}
	            		else if (OrangeGhost.moveDirection == 3)
	            		{
	            			if(OrangeGhost.getOrangeAux()==0 && Board.gameBoard[OrangeGhost.getOrangeGhostI()+1][OrangeGhost.getOrangeGhostJ()]==2)
	            			{
	            				orangeGhost.moveRight();
	            			}
	            			else
	            			{
	            				orangeGhost.moveDown();	
	            			}
	            		}
	            		else if (OrangeGhost.moveDirection == 4)
	            		{
	            			orangeGhost.moveRight();
	            		}
	            	}
	            }		
	            );
		Timer greenGhostTimer = new Timer//the function that is responsible for moving pacman every specific time
	            (
	            		speedLvl, 
	            new ActionListener() 
	            {
	            	@Override
	            	public void actionPerformed(ActionEvent e)
	            	{	assureOneTimeGreen=true;
	            		gui.updateGameBoard(board);
	            		
	            		
	            		 if(GreenGhost.moveDirection == 1)
	            		{
	            			greenGhost.moveLeft();
	            		}
	            		else if (GreenGhost.moveDirection == 2)
	            		{
	            			greenGhost.moveUp();
	            		}
	            		else if (GreenGhost.moveDirection == 3)
	            		{
	            			if(GreenGhost.getGreenAux()==0 && Board.gameBoard[GreenGhost.getGreenGhostI()+1][GreenGhost.getGreenGhostJ()]==2)
	            			{
	            				greenGhost.moveRight();
	            			}
	            			else
	            			{
	            				greenGhost.moveDown();	
	            			}
	            		}
	            		else if (GreenGhost.moveDirection == 4)
	            		{
	            			greenGhost.moveRight();
	            		}
	            	}
	            }		
	            );
		Timer blueGhostTimer = new Timer//the function that is responsible for moving pacman every specific time
	            (
	            		speedLvl, 
	            new ActionListener() 
	            {
	            	@Override
	            	public void actionPerformed(ActionEvent e)
	            	{	assureOneTimeBlue=true;
	            		gui.updateGameBoard(board);
	            		
	            		
	            		 if(BlueGhost.moveDirection == 1)
	            		{
	            			blueGhost.moveLeft();
	            		}
	            		else if (BlueGhost.moveDirection == 2)
	            		{
	            			blueGhost.moveUp();
	            		}
	            		else if (BlueGhost.moveDirection == 3)
	            		{
	            			if(BlueGhost.getBlueAux()==0 && Board.gameBoard[BlueGhost.getBlueGhostI()+1][BlueGhost.getBlueGhostJ()]==2)
	            			{
	            				blueGhost.moveRight();
	            			}
	            			else
	            			{
	            				blueGhost.moveDown();	
	            			}
	            		}
	            		else if (BlueGhost.moveDirection == 4)
	            		{
	            			blueGhost.moveRight();
	            		}
	            	}
	            }		
	            );
		
		gui.updateGameBoard(board);
		//Audio.startSound(gui.StartGameSound);
		//Thread.sleep(4200);
		//Audio.startClip(gui.gameplaySound);
		pacmanTimer.start();
		redGhostTimer.start();
		blueGhostTimer.start();
		greenGhostTimer.start();
		orangeGhostTimer.start();
		
		
		board.pause();
		
		
		
		
		
		
		
		while(!gameOver)
		{
			if(Board.getScore()==4 && !assureOneTime) {
				//Make ghosts Faster Lvl 1
				speedLvl=speedLvl-10;
				redGhostTimer.setDelay(speedLvl);
				blueGhostTimer.setDelay(speedLvl);
				greenGhostTimer.setDelay(speedLvl);
				orangeGhostTimer.setDelay(speedLvl);
				System.out.println("You Reach first Level Good Luck !");
				assureOneTime=true;
			}
			if(Board.getScore()==8 && !assureSecondTime) {
				//Make ghosts Faster Lvl 2
				speedLvl=speedLvl-20;
				redGhostTimer.setDelay(speedLvl);
				blueGhostTimer.setDelay(speedLvl);
				greenGhostTimer.setDelay(speedLvl);
				orangeGhostTimer.setDelay(speedLvl);
				System.out.println("You Reach Second Level Good Luck !");
				assureSecondTime=true;

			}
			
			
			board.spawnCoin();
			gui.updateGameBoard(board);
			redGhost.updateDirection();
			blueGhost.updateDirection();
			greenGhost.updateDirection();
			orangeGhost.updateDirection();
			if(redGhost.verifGameover()||blueGhost.verifGameover()||greenGhost.verifGameover()||orangeGhost.verifGameover()) {
				gui.decrementHearts();
				gui.restart(board);
			}
				/*if((redGhost.verifGameover()||blueGhost.verifGameover()||greenGhost.verifGameover()||orangeGhost.verifGameover())&& !assureOneTimeChance&&chances>0) {
					//gui.getBoardLabels()[pacman.getPacmanI()][pacman.getPacmanJ()].setIcon(null);
					//board.restartBoard();
					//gui.updateGameBoard(board);

					
	        		chances--;
	        		assureOneTimeChance=true;
	                	System.out.println(chances);				
					
				}*/

					//gui.decrementHearts();
					//Board.assureOneTime = 1;
					//gui.restart();
					
					if(redGhost.verifGameover()||blueGhost.verifGameover()||greenGhost.verifGameover()||orangeGhost.verifGameover()) {
						gui.redirect("gameover", "10", "10", "10");
						Audio.closeClip();
						Audio.startClip(gui.musicGameOver);
						Thread.sleep(1500);
						Audio.closeClip();
						if(board.getScore()>Integer.parseInt(SaveData.getMostScored())) {
							SaveData.SetScore(String.valueOf(board.getScore()));
							System.out.println("You Won . you break new record !");

						}

						break;
					}
					
						
						
						
					

					
				
				
			
			
			
			synchronized (board.lock) //verification if the program is paused or not
			{
	            while (Board.isPaused) 
	            {	
	            	pacmanTimer.stop();
	            	redGhostTimer.stop();
	            	blueGhostTimer.stop();
	            	greenGhostTimer.stop();
	            	orangeGhostTimer.stop();
	            	
	                try 
	                {
	                    board.lock.wait();
	                } catch (InterruptedException e) 
	                {
	                    Thread.currentThread().interrupt();
	                    break;
	                }
	                
	            }
	            if (Board.isPaused == false)
				{
	            	
	            	
	            	pacmanTimer.start();
	        		redGhostTimer.start();
	        		blueGhostTimer.start();
	        		greenGhostTimer.start();
	        		orangeGhostTimer.start();
				}
	        }
			
		}
		
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	

}

