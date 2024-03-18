package pacman_v0;



public class PacManMoves 
{		



	public static int moveDirection=1;//direction of pacman's movements
	public PacManMoves() 
	{
	}
	public static int getPacmanI()
	{	
		int pacmanI=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 3)
				{
					pacmanI=i;
				}
			}
		}
		return pacmanI;
	}
	
	public static int getPacmanJ()
	{
		int pacmanJ=0;
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{
				if(Board.gameBoard[i][j]== 3)
				{
					
					pacmanJ=j;
				}
			}
		}
		return pacmanJ;
	}
	public void moveLeft()
	{
		
		for (int i=0;i<Board.getWidth();i++)
		{
			for (int j=0;j<Board.getWidth();j++)
			{	
				if ((j==0)&& ((Board.gameBoard[i][j]==3)) )
				{
					Board.gameBoard[i][Board.getWidth()-1] = 3 ;
					Board.gameBoard[i][j] = 0 ;
					break;
				}
				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i][j-1]== 4)||(Board.gameBoard[i][j-1]== 0)))
				{
					Board.gameBoard[i][j-1] = 3 ;
					Board.gameBoard[i][j] = 0 ;
				}else if((Board.gameBoard[i][j]== 3) && (Board.gameBoard[i][j+1]== 10)) {
					Board.gameBoard[0][j] = 0 ;
					Board.gameBoard[i][j] = 0 ;
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
				if ((j==Board.getWidth()-1)&& (Board.gameBoard[i][j]==3))
				{
					Board.gameBoard[i][0] = 3 ;
					Board.gameBoard[i][j] = 0 ;
					break;
				}
				else if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i][j+1]== 4)||(Board.gameBoard[i][j+1]== 0)))
				{
					Board.gameBoard[i][j+1] = 3 ;
					Board.gameBoard[i][j] = 0 ;
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
				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i-1][j]== 4)||(Board.gameBoard[i-1][j]== 0)))
				{
					
					Board.gameBoard[i-1][j] = 3 ;
					Board.gameBoard[i][j] = 0 ;
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
				if((Board.gameBoard[i][j]== 3) && ((Board.gameBoard[i+1][j]== 4)||(Board.gameBoard[i+1][j]== 0)))
				{
					Board.gameBoard[i+1][j] = 3 ;
					Board.gameBoard[i][j] = 0 ;
				}
			}
		}
	}
}
