package pacman_v0;

import java.util.Random;

public class GreenGhost 
{
	private static int greenAux=2;
	private boolean putAux=true;
	private Random random = new Random() ;
	public static int moveDirection=2;//direction of pacman's movements
	private int RNG = 2;
	public static int notMoveDirection=3;//direction of pacman's movements
	
	public GreenGhost(){}
	
	public static int getGreenAux() 
	{
		return greenAux;
	}

	public static void setGreenAux(int greenAux) 
	{
		GreenGhost.greenAux = greenAux;
	}
	
	public static int getGreenGhostI()
	{
		int greenGhostI=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 8)
				{
					greenGhostI=i;
				}
			}
		}
		return greenGhostI;
	}
	
	public static int getGreenGhostJ()
	{
		int greenGhostJ=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 8)
				{
					greenGhostJ=j;
				}
			}
		}
		return greenGhostJ;
	}
	
	public boolean verifGameover()
	{
		if(GreenGhost.getGreenAux()==3)
		{
			Board.assureOneTime++;
			return true;
		}
		return false;
	}
	
	public boolean verifPathUp()
	{
		for(int i = PacManMoves.getPacmanI() ; i<GreenGhost.getGreenGhostI() ; i++)
		{
			if(Board.gameBoard[i][GreenGhost.getGreenGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathDown()
	{
		for(int i = GreenGhost.getGreenGhostI() ; i<PacManMoves.getPacmanI() ; i++)
		{
			if(Board.gameBoard[i][GreenGhost.getGreenGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathRight()
	{
		for(int i = GreenGhost.getGreenGhostJ() ; i<PacManMoves.getPacmanJ() ; i++)
		{
			if(Board.gameBoard[GreenGhost.getGreenGhostI()][i]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathLeft()
	{
		for(int i = PacManMoves.getPacmanJ() ; i<GreenGhost.getGreenGhostJ() ; i++)
		{
			if(Board.gameBoard[GreenGhost.getGreenGhostI()][i]==1)
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
				if(Board.gameBoard[i][j]== 8 && Gameplay.assureOneTimeGreen)
				{
					Gameplay.assureOneTimeGreen=false;
					/*left up down right*/
					if(greenAux==2)
					{
						moveDirection=2 ;
					}
					
					else if(i>PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && greenAux==0 && verifPathUp())
					{
						moveDirection=2 ;
					}
					else if(i<PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && greenAux==0 && verifPathDown())
					{
						moveDirection=3 ;
					}
					else if(i==PacManMoves.getPacmanI() && j<PacManMoves.getPacmanJ() && greenAux==0 && verifPathRight())
					{
						moveDirection=4 ;
					}
					else if((i==PacManMoves.getPacmanI() && j>PacManMoves.getPacmanJ() && greenAux==0 && verifPathLeft())||Board.gameBoard[GreenGhost.getGreenGhostI()+1][GreenGhost.getGreenGhostJ()]==2)
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
				if((Board.gameBoard[i][j]== 8) &&(j==0))
				{
					if((Board.gameBoard[i][Board.getWidth()-1]!= 1))
					{	
						if(Board.gameBoard[i][Board.getWidth()-1]== 0)
						{
							if(putAux)
							{	
								Board.gameBoard[i][Board.getWidth()-1]=8;
								Board.gameBoard[i][j]=getGreenAux();
								setGreenAux(0);
								putAux=false;
							}
							else
							{
								Board.gameBoard[i][Board.getWidth()-1]=8;
								Board.gameBoard[i][j]=0;
							}
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getGreenAux();
								setGreenAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=8;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setGreenAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=8;
								putAux=true;
							}
							
						}
					}
					j=Board.getWidth()-1;
				}
				else if((Board.gameBoard[i][j]== 8) &&(Board.gameBoard[i][j-1]!= 1))
				{	
					if(Board.gameBoard[i][j-1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j-1]=8;
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i][j-1]=8;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=8;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setGreenAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=8;
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
					if((Board.gameBoard[i][j]== 8) && (Board.gameBoard[i][0] != 1))
					{	
						if(Board.gameBoard[i][0]== 0)
						{	
							
							if(putAux)
							{	
								Board.gameBoard[i][0]=8;
								Board.gameBoard[i][j]=getGreenAux();
								setGreenAux(0);
								putAux=false;
								
								
							}
							else
							{
								Board.gameBoard[i][0]=8;
								Board.gameBoard[i][j]=0;
							}
							
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getGreenAux();
								setGreenAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=8;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setGreenAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=8;
								putAux=true;
							}
							
						}
						j=0;
						
					}
				}
				else if((Board.gameBoard[i][j]== 8) && (Board.gameBoard[i][j+1] != 1))
				{	
					
					if(Board.gameBoard[i][j+1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j+1]=8;
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(0);
							putAux=false;
							
						}
						else
						{
							Board.gameBoard[i][j+1]=8;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=8;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setGreenAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=8;
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
				if((Board.gameBoard[i][j]== 8) && (Board.gameBoard[i-1][j]!= 1))
				{
					if(Board.gameBoard[i-1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i-1][j]=8;
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i-1][j]=8;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=8;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setGreenAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=8;
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
				if((Board.gameBoard[i][j]== 8) && (Board.gameBoard[i+1][j] != 1))
				{

					if(Board.gameBoard[i+1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i+1][j]=8;
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i+1][j]=8;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getGreenAux();
							setGreenAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=8;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setGreenAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=8;
							putAux=true;
						}
						
					}
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
