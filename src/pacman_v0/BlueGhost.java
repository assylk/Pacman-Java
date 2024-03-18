package pacman_v0;

import java.util.Random;

public class BlueGhost 
{

	
	private boolean putAux=true;
	private int RNG = 2;
	private static int blueAux=2; 
	public static int moveDirection=2;//direction of pacman's movements
	public static int notMoveDirection=3;//direction of pacman's movements
	
	private Random random = new Random() ;
	
	public static int getBlueAux() 
	{
		return blueAux;
	}
	
	public static void setBlueAux(int blueAux) 
	{
		BlueGhost.blueAux = blueAux;
	}
	
	public static int getBlueGhostI()
	{
		int blueGhostI=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 6)
				{
					blueGhostI=i;
				}
			}
		}
		return blueGhostI;
	}
	
	public static int getBlueGhostJ()
	{
		int blueGhostJ=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 6)
				{
					blueGhostJ=j;
				}
			}
		}
		return blueGhostJ;
	}

	public boolean verifGameover()
	{
		if(BlueGhost.getBlueAux()==3)
		{
			Board.assureOneTime++;
			return true;
		}
		return false;
	}

	public boolean verifPathUp()
	{
		for(int i = PacManMoves.getPacmanI() ; i<BlueGhost.getBlueGhostI() ; i++)
		{
			if(Board.gameBoard[i][BlueGhost.getBlueGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathDown()
	{
		for(int i = BlueGhost.getBlueGhostI() ; i<PacManMoves.getPacmanI() ; i++)
		{
			if(Board.gameBoard[i][BlueGhost.getBlueGhostJ()]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathRight()
	{
		for(int i = BlueGhost.getBlueGhostJ() ; i<PacManMoves.getPacmanJ() ; i++)
		{
			if(Board.gameBoard[BlueGhost.getBlueGhostI()][i]==1)
			{
				return false ;
			}
		}
		return true;
	}
	
	public boolean verifPathLeft()
	{
		for(int i = PacManMoves.getPacmanJ() ; i<BlueGhost.getBlueGhostJ() ; i++)
		{
			if(Board.gameBoard[BlueGhost.getBlueGhostI()][i]==1)
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
				if(Board.gameBoard[i][j]== 6 && Gameplay.assureOneTimeBlue)
				{
					Gameplay.assureOneTimeBlue=false;
					/*left up down right*/
					
					if(blueAux==2)
					{
						moveDirection=2 ;
					}
					
					else if(i>PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && blueAux==0 && verifPathUp())
					{
						moveDirection=2 ;
					}
					else if(i<PacManMoves.getPacmanI() && j==PacManMoves.getPacmanJ() && blueAux==0 && verifPathDown())
					{
						moveDirection=3 ;
					}
					else if(i==PacManMoves.getPacmanI() && j<PacManMoves.getPacmanJ() && blueAux==0 && verifPathRight())
					{
						moveDirection=4 ;
					}
					else if((i==PacManMoves.getPacmanI() && j>PacManMoves.getPacmanJ() && blueAux==0 && verifPathLeft())||Board.gameBoard[BlueGhost.getBlueGhostI()+1][BlueGhost.getBlueGhostJ()]==2)
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
				if((Board.gameBoard[i][j]== 6) &&(j==0))
				{
					if((Board.gameBoard[i][Board.getWidth()-1]!= 1))
					{	
						if(Board.gameBoard[i][Board.getWidth()-1]== 0)
						{
							if(putAux)
							{	
								Board.gameBoard[i][Board.getWidth()-1]=6;
								Board.gameBoard[i][j]=getBlueAux();
								setBlueAux(0);
								putAux=false;
							}
							else
							{
								Board.gameBoard[i][Board.getWidth()-1]=6;
								Board.gameBoard[i][j]=0;
							}
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getBlueAux();
								setBlueAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=6;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setBlueAux(Board.gameBoard[i][Board.getWidth()-1]);
								Board.gameBoard[i][Board.getWidth()-1]=6;
								putAux=true;
							}
							
						}
					}
					j=Board.getWidth()-1;
				}
				else if((Board.gameBoard[i][j]== 6) &&(Board.gameBoard[i][j-1]!= 1))
				{	
					if(Board.gameBoard[i][j-1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j-1]=6;
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i][j-1]=6;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=6;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i][j-1]);
							Board.gameBoard[i][j-1]=6;
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
					if((Board.gameBoard[i][j]== 6) && (Board.gameBoard[i][0] != 1))
					{	
						if(Board.gameBoard[i][0]== 0)
						{	
							
							if(putAux)
							{	
								Board.gameBoard[i][0]=6;
								Board.gameBoard[i][j]=getBlueAux();
								setBlueAux(0);
								putAux=false;
								
								
							}
							else
							{
								Board.gameBoard[i][0]=6;
								Board.gameBoard[i][j]=0;
							}
							
						}
						else
						{
							if(putAux)
							{
								
								Board.gameBoard[i][j]=getBlueAux();
								setBlueAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=6;
								putAux=true;
							}
							else
							{
								Board.gameBoard[i][j]=0;
								setBlueAux(Board.gameBoard[i][0]);
								Board.gameBoard[i][0]=6;
								putAux=true;
							}
							
						}
						j=0;
						
					}
				}
				else if((Board.gameBoard[i][j]== 6) && (Board.gameBoard[i][j+1] != 1))
				{	
					
					if(Board.gameBoard[i][j+1]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i][j+1]=6;
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(0);
							putAux=false;
							
						}
						else
						{
							Board.gameBoard[i][j+1]=6;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=6;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i][j+1]);
							Board.gameBoard[i][j+1]=6;
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
				if((Board.gameBoard[i][j]== 6) && (Board.gameBoard[i-1][j]!= 1))
				{
					if(Board.gameBoard[i-1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i-1][j]=6;
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i-1][j]=6;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=6;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i-1][j]);
							Board.gameBoard[i-1][j]=6;
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
				if((Board.gameBoard[i][j]== 6) && (Board.gameBoard[i+1][j] != 1))
				{

					if(Board.gameBoard[i+1][j]== 0)
					{
						if(putAux)
						{	
							Board.gameBoard[i+1][j]=6;
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(0);
							putAux=false;
						}
						else
						{
							Board.gameBoard[i+1][j]=6;
							Board.gameBoard[i][j]=0;
						}
					}
					else
					{
						if(putAux)
						{
							
							Board.gameBoard[i][j]=getBlueAux();
							setBlueAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=6;
							putAux=true;
						}
						else
						{
							Board.gameBoard[i][j]=0;
							setBlueAux(Board.gameBoard[i+1][j]);
							Board.gameBoard[i+1][j]=6;
							putAux=true;
						}
						
					}
				}
			}
		}
	}

	
	
	
	
	
	
	

}
