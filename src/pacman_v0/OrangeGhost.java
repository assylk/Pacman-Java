package pacman_v0;

import java.util.Random;

public class OrangeGhost 
{
	


	
	private boolean putAux=true;
	private int RNG = 2;
	private static int orangeAux=2; 
	public static int moveDirection=2;//direction of pacman's movements
	public static int notMoveDirection=3;//direction of pacman's movements
	
	private Random random = new Random() ;
	
	public static int getOrangeAux() 
	{
		return orangeAux;
	}
	
	public static void setBlueAux(int orangeAux) 
	{
		OrangeGhost.orangeAux = orangeAux;
	}
	
	public static int getOrangeGhostI()
	{
		int orangeGhostI=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 7)
				{
					orangeGhostI=i;
				}
			}
		}
		return orangeGhostI;
	}
	
	public static int getOrangeGhostJ()
	{
		int orangeGhostJ=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 7)
				{
					orangeGhostJ=j;
				}
			}
		}
		return orangeGhostJ;
	}

	public boolean verifGameover()
	{
		if(OrangeGhost.getOrangeAux()==3)
		{
			Board.assureOneTime++;
			return true;
		}
		return false;
	}

	public boolean verifPathUp()
	{
		for(int i = PacManMoves.getPacmanI() ; i<OrangeGhost.getOrangeGhostI() ; i++)
		{
			if(Board.gameBoard[i][OrangeGhost.getOrangeGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathDown()
	{
		for(int i = OrangeGhost.getOrangeGhostI() ; i<PacManMoves.getPacmanI() ; i++)
		{
			if(Board.gameBoard[i][OrangeGhost.getOrangeGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathRight()
	{
		for(int i = OrangeGhost.getOrangeGhostJ() ; i<PacManMoves.getPacmanJ() ; i++)
		{
			if(Board.gameBoard[OrangeGhost.getOrangeGhostI()][i]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathLeft()
	{
		for(int i = PacManMoves.getPacmanJ() ; i<OrangeGhost.getOrangeGhostJ() ; i++)
		{
			if(Board.gameBoard[OrangeGhost.getOrangeGhostI()][i]==1)
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
				if(Board.gameBoard[i][j]== 7 && Gameplay.assureOneTimeOrange)
				{
					Gameplay.assureOneTimeBlue=false;
					/*left up down right*/
					
					if(orangeAux==2)
					{
						moveDirection=2 ;
					}
					
					else if(i>PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && orangeAux==0 && verifPathUp())
					{
						moveDirection=2 ;
					}
					else if(i<PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && orangeAux==0 && verifPathDown())
					{
						moveDirection=3 ;
					}
					else if((i==PacManMoves.getPacmanI() && j<PacManMoves.getPacmanJ() && orangeAux==0 && verifPathRight())||Board.gameBoard[OrangeGhost.getOrangeGhostI()+1][OrangeGhost.getOrangeGhostJ()]==2)
					{
						moveDirection=4 ;
					}
					else if(i==PacManMoves.getPacmanI() && j>PacManMoves.getPacmanJ() && orangeAux==0 && verifPathLeft())
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
				if((Board.gameBoard[i][j]== 7) &&(j==0))
				{
					if((Board.gameBoard[i][Board.getWidth()-1]!= 1))
					{	
						if(Board.gameBoard[i][Board.getWidth()-1]== 0)
						{
							if(putAux)
							{	
								Board.gameBoard[i][Board.getWidth()-1]=7;
								Board.gameBoard[i][j]=getOrangeAux();
								setBlueAux(0);
								putAux=false;
							}
							else
							{
								Board.gameBoard[i][Board.getWidth()-1]=7;
								Board.gameBoard[i][j]=0;
							}
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getOrangeAux();
								setBlueAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=7;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setBlueAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=7;
								putAux=true;
							}
							
						}
					}
					j=Board.getWidth()-1;
				}
				else if((Board.gameBoard[i][j]== 7) &&(Board.gameBoard[i][j-1]!= 1))
				{	
					if(Board.gameBoard[i][j-1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j-1]=7;
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i][j-1]=7;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=7;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=7;
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
					if((Board.gameBoard[i][j]== 7) && (Board.gameBoard[i][0] != 1))
					{	
						if(Board.gameBoard[i][0]== 0)
						{	
							
							if(putAux)
							{	
								Board.gameBoard[i][0]=7;
								Board.gameBoard[i][j]=getOrangeAux();
								setBlueAux(0);
								putAux=false;
								
								
							}
							else
							{
								Board.gameBoard[i][0]=7;
								Board.gameBoard[i][j]=0;
							}
							
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getOrangeAux();
								setBlueAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=7;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setBlueAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=7;
								putAux=true;
							}
							
						}
						j=0;
						
					}
				}
				else if((Board.gameBoard[i][j]== 7) && (Board.gameBoard[i][j+1] != 1))
				{	
					
					if(Board.gameBoard[i][j+1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j+1]=7;
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(0);
							putAux=false;
							
						}
						else
						{
							Board.gameBoard[i][j+1]=7;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=7;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=7;
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
				if((Board.gameBoard[i][j]== 7) && (Board.gameBoard[i-1][j]!= 1))
				{
					if(Board.gameBoard[i-1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i-1][j]=7;
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i-1][j]=7;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=7;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=7;
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
				if((Board.gameBoard[i][j]== 7) && (Board.gameBoard[i+1][j] != 1))
				{

					if(Board.gameBoard[i+1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i+1][j]=7;
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i+1][j]=7;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getOrangeAux();
							setBlueAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=7;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=7;
							putAux=true;
						}
						
					}
				}
			}
		}
	}
}
