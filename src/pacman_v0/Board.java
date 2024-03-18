package pacman_v0;
/*this class will contain the board that we play the game on
 include the ID of every possible object
 IDs :
 0 : empty cell
 1 : wall
 2 : door (where ghost may walk but pacman can't)
 3 : pacman
 4 : coin
 5 : ghosts
 */

import java.awt.Color;
import java.net.URL;
import java.util.Random;

import javax.swing.BorderFactory;









public class Board
{
	mapping map=new mapping();

	PacManMoves pacman=new PacManMoves();



	private URL eatFruit = getClass().getResource("/Audio/eatFruit.wav");
	private static int score=-1 ;
	static int assureOneTime = 0;
	private URL gamePlaySound = getClass().getResource("/Audio/gameplay.wav");
	public static int t=0;
	public static int gameBoard[][];
	
	static int width = 29 ;

	
	
	public static int getWidth() {
		return width;
	}
	

	public void setWidth(int width) {
		Board.width = width;
	}
	
	public static int getScore() {
		return Board.score;
	}

	public void setScore(int score) {
		Board.score = score;
	}

	public Board(){
		gameBoard=mapping.mainMap;
	}

	public static void afficheBoard() {
		for(int i=0;i<Board.width;i++) {
			for(int j=0;j<Board.width;j++) {
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	}
	
	public int countEmptyCells()//counting how many empty cell are in the board
	{	
		int emptyCells=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 0)
				{
					emptyCells++;
				}
			}
		}
		return emptyCells;
	}
	
	Random random = new Random();// used to verify when we can spawn coin
	static boolean isPaused = false;
	  public Object lock = new Object();
	  
	  public void pause()//function that pause the system 
	  {
	      synchronized (lock) 
	      {
	          isPaused = true;
	          
	      }
	      
	  }
	  public void pauseBoard() 
	  {		
			pause();
			if (Board.assureOneTime==1)
			  {
				  Audio.closeClip();
				    
			  }
			  else
			  {
				  Audio.pauseClip(); 
			  }
	  }

	  public void resume() //function that resume the system

	  {
	      synchronized (lock) 
	      {
	          isPaused = false;
	          lock.notify();
	      }
	  }
	  public void restartBoard() throws InterruptedException //to use before developing the user interface to see the board state
	  {
		  resume();
			score=0;
			
			Audio.startClip(gamePlaySound);
			assureOneTime = 0;
			for(int i=0;i<Board.width;i++) {
				for(int j=0;j<Board.width;j++) {
					if(Board.gameBoard[i][j]!=4) {
						Board.gameBoard[i][j]=mapping.mainMap[i][j];

					}
				}
			}
			
		  
		  
		
		
	  }
	  
	  
	  public void restartPacman() {
			
		  for (int i=0;i<Board.getWidth();i++)
			{
				for (int j=0;j<Board.getWidth();j++)
				{
					if(Board.gameBoard[i][j]== 3)
					{
						Board.gameBoard[i][j]=0;
					}
				}
			}
		  Board.gameBoard[22][11]=3;
		}
	  
	public void gameOver() {
		for(int i=10;i<20;i++) {
			for(int j=0;j<Board.getWidth();j++) {
				Board.gameBoard[i][j]=1;
				
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<Board.getWidth();j++) {
				Board.gameBoard[i][j]=0;
			}
		}
		for(int i=20;i<Board.getWidth();i++) {
			for(int j=0;j<Board.getWidth();j++) {
				Board.gameBoard[i][j]=0;
			}
		}
	}
	public boolean verifSpawnCoin()
	{
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 4)
				{
					return false;
				}
			}
		}
		if((OrangeGhost.getOrangeAux() == 4)||(RedGhost.getRedAux() == 4)||(BlueGhost.getBlueAux() == 4)||(GreenGhost.getGreenAux() == 4))
		{
			return false;
		}
		return true;
	}
	
	
	
	
	public void spawnCoin()//function responsible of spawning coins
	{
		if (verifSpawnCoin())
		{	score++;
			int coinCell = random.nextInt(countEmptyCells()+1);
			int counter=0;//count random number of 0 and put the coin
			if (score >0)
			{
				Audio.startSound(eatFruit);
			}
			for (int i=0;i<Board.getWidth();i++)
			{
				for (int j=0;j<Board.getWidth();j++)
				{
					
					if(Board.gameBoard[i][j]== 0)
					{
						counter++;
					}
					
					if ((counter == coinCell)&&(Board.gameBoard[i][j]== 0))
					{
						
						Board.gameBoard[i][j] = 4;
					}
					
				}
			}
		}
		
	}
	
	
}
