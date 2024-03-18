package pacman_v0;

import java.util.Random;

public class RedGhost 
{	
	/*public int speed = 200;*/
	private static int redAux=2;
	private boolean putAux=true;
	
	private Random random = new Random() ;
	public static int moveDirection=2;//direction of pacman's movements
	private int RNG = 2;
	public static int notMoveDirection=3;//direction of pacman's movements
	
	public RedGhost(){}
	
	public static int getRedAux() 
	{
		return redAux;
	}

	public static void setRedAux(int redAux) 
	{
		RedGhost.redAux = redAux;
	}
	
	public static int getRedGhostI()
	{
		int redGhostI=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 5)
				{
					redGhostI=i;
				}
			}
		}
		return redGhostI;
	}
	
	public static int getRedGhostJ()
	{
		int redGhostJ=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 5)
				{
					redGhostJ=j;
				}
			}
		}
		return redGhostJ;
	}
	
	public boolean verifGameover()
	{
		if(RedGhost.getRedAux()==3)
		{
			Board.assureOneTime++;
			return true;
		}
		return false;
	}
	
	public boolean verifPathUp()
	{
		for(int i = PacManMoves.getPacmanI() ; i<RedGhost.getRedGhostI() ; i++)
		{
			if(Board.gameBoard[i][RedGhost.getRedGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathDown()
	{
		for(int i = RedGhost.getRedGhostI() ; i<PacManMoves.getPacmanI() ; i++)
		{
			if(Board.gameBoard[i][RedGhost.getRedGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathRight()
	{
		for(int i = RedGhost.getRedGhostJ() ; i<PacManMoves.getPacmanJ() ; i++)
		{
			if(Board.gameBoard[RedGhost.getRedGhostI()][i]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathLeft()
	{
		for(int i = PacManMoves.getPacmanJ() ; i<RedGhost.getRedGhostJ() ; i++)
		{
			if(Board.gameBoard[RedGhost.getRedGhostI()][i]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public void updateDirection()//this function will precise how the redghost behave
	{

		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{	
				if(Board.gameBoard[i][j]== 5 && Gameplay.assureOneTimeRed)
				{
					Gameplay.assureOneTimeRed=false;
					/*left up down right*/
					
					if(redAux==2)
					{
						moveDirection=2 ;
					}
					
					else if(i>PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && redAux==0 && verifPathUp())
					{
						moveDirection=2 ;
					}
					else if(i<PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && redAux==0 && verifPathDown())
					{
						moveDirection=3 ;
					}
					else if((i==PacManMoves.getPacmanI() && j<PacManMoves.getPacmanJ() && redAux==0 && verifPathRight())||Board.gameBoard[RedGhost.getRedGhostI()+1][RedGhost.getRedGhostJ()]==2)
					{
						moveDirection=4 ;
					}
					else if(i==PacManMoves.getPacmanI() && j>PacManMoves.getPacmanJ() && redAux==0 && verifPathLeft())
					{
						moveDirection=1 ;
					}
					else if(((moveDirection==1)&&((Board.gameBoard[i+1][j]!=1)||(Board.gameBoard[i-1][j]!=1)))||((moveDirection==2)&&((Board.gameBoard[i][j-1]!=1)||(Board.gameBoard[i][j+1]!=1)))||((moveDirection==3)&&((Board.gameBoard[i][j+1]!=1)||(Board.gameBoard[i][j-1]!=1)))||((moveDirection==4)&&((Board.gameBoard[i+1][j]!=1)||(Board.gameBoard[i-1][j]!=1))))
					{
						do
						{
							RNG=random.nextInt(4)+1;
						}
						while((RNG==notMoveDirection)&&(RNG==moveDirection));
						
						moveDirection=RNG;
						notMoveDirection=5-moveDirection;
					}
				}
			
			}
		}
		
	}
	
	public void moveLeft()
	{
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{	
				if((Board.gameBoard[i][j]== 5) &&(j==0))
				{
					if((Board.gameBoard[i][Board.getWidth()-1]!= 1))
					{	
						if(Board.gameBoard[i][Board.getWidth()-1]== 0)
						{
							if(putAux)
							{	
								Board.gameBoard[i][Board.getWidth()-1]=5;
								Board.gameBoard[i][j]=getRedAux();
								setRedAux(0);
								putAux=false;
							}
							else
							{
								Board.gameBoard[i][Board.getWidth()-1]=5;
								Board.gameBoard[i][j]=0;
							}
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getRedAux();
								setRedAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=5;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setRedAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=5;
								putAux=true;
							}
							
						}
					}
					j=Board.getWidth()-1;
				}
				else if((Board.gameBoard[i][j]== 5) &&(Board.gameBoard[i][j-1]!= 1))
				{	
					if(Board.gameBoard[i][j-1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j-1]=5;
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i][j-1]=5;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=5;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setRedAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=5;
							putAux=true;
						}
						
					}
				}
			}
		}
	}
	
	public void moveRight()
	{
		
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=Board.getWidth()-1;j>=0;j--)
			{	
				if(j==Board.getWidth()-1)
				{
					if((Board.gameBoard[i][j]== 5) && (Board.gameBoard[i][0] != 1))
					{	
						if(Board.gameBoard[i][0]== 0)
						{	
							
							if(putAux)
							{	
								Board.gameBoard[i][0]=5;
								Board.gameBoard[i][j]=getRedAux();
								setRedAux(0);
								putAux=false;
								
								
							}
							else
							{
								Board.gameBoard[i][0]=5;
								Board.gameBoard[i][j]=0;
							}
							
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getRedAux();
								setRedAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=5;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setRedAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=5;
								putAux=true;
							}
							
						}
						j=0;
						
					}
				}
				else if((Board.gameBoard[i][j]== 5) && (Board.gameBoard[i][j+1] != 1))
				{	
					
					if(Board.gameBoard[i][j+1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j+1]=5;
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(0);
							putAux=false;
							
						}
						else
						{
							Board.gameBoard[i][j+1]=5;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=5;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setRedAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=5;
							putAux=true;
						}
						
					}
					
					
				}
				
			}
		}
	}
	
	public void moveUp()
	{
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if((Board.gameBoard[i][j]== 5) && (Board.gameBoard[i-1][j]!= 1))
				{
					if(Board.gameBoard[i-1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i-1][j]=5;
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i-1][j]=5;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=5;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setRedAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=5;
							putAux=true;
						}
						
					}
				}
			}
		}
	}
	
	public void moveDown()
	{
		for (int i=Board.getWidth()-1;i>0;i--)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if((Board.gameBoard[i][j]== 5) && (Board.gameBoard[i+1][j] != 1))
				{

					if(Board.gameBoard[i+1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i+1][j]=5;
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i+1][j]=5;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getRedAux();
							setRedAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=5;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setRedAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=5;
							putAux=true;
						}
						
					}
				}
			}
		}
	}

	
	
}
