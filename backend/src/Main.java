import java.util.Random;
import java.util.Scanner;

class SudokuSolver
{
    private int[][] board;
    private int grid_size,square_count;
    SudokuSolver(int grid_size)
    {
        this.grid_size=grid_size;
        this.square_count=(int)Math.sqrt(grid_size);
        board=new int[grid_size][grid_size];
        /* to generate solvable sudoku board*/
        while(true)
        {
            board=generate();
            if(findSolution(board))break;
        }
        /* let's erase some of numbers for user to do brain strom*/
        Random rand=new Random();
        int erase=rand.nextInt(grid_size*grid_size);
        for(int i=0;i<=erase;i++)
        {
            int pos=rand.nextInt(grid_size*grid_size);
            board[pos/grid_size][pos%grid_size]=0;
        }
        print();
    }
    private boolean is_valid(int x,int y,int val,int[][] board)
    {
        for(int i=grid_size-1;i>=0;i--)
        {
            if(i!=x&&board[i][y]==val)return false;
        }
        for(int i=grid_size-1;i>=0;i--)
        {
            if(i!=y&&board[x][i]==val)return false;
        }
        int row=(x/square_count)*square_count,col=(y/square_count)*square_count;
        for(int i=0;i<square_count;i++)
        {
            for(int j=0;j<square_count;j++)
            {
                if((row+i)!=x&&(col+j)!=y&&board[row+i][col+j]==val)return false;
            }
        }
        return true;
    }
    int[][] generate()
    {
        Random rand=new Random();
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++) {
                board[i][j] = rand.nextInt(2);
                if (board[i][j] == 1) {
                    for (int k = 1; k <= grid_size; k++) {
                        if (is_valid(i, j, k, board)) {
                            board[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        return board;
    }
    void setValue(int pos,int value)
    {
        board[pos/grid_size][pos%grid_size]=value;
    }
    /* backtracking algorithm to solve sudoku */

    boolean findSolution(int[][] board)
{
    int row=-1,col=-1;
    for(int i=0;i<grid_size;i++)
    {
        for(int j=0;j<grid_size;j++)
        {
            if(board[i][j]==0)
            {
                row=i;
                col=j;
                break;
            }
        }
        if(row!=-1)break;
    }
    if(row==-1)
    {
        return true;
    }
    for(int i=1;i<=grid_size;i++)
    {
        if(is_valid(row,col,i,board))
        {
            board[row][col]=i;
            if(findSolution(board))return true;

        }
    }
    board[row][col]=0;
    return false;
}
    void print()
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("*************************");
    }
    /* To validate sudoku board */
    boolean validate()
    {
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                if(board[i][j]==0)return false;
                if(is_valid(i,j,board[i][j],board))continue;
                else return false;
            }
        }
        return true;
    }

};
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Which size of board would you like to play");
        int grid_size=sc.nextInt();
        double temp=Math.sqrt(grid_size);
        if(Math.floor(temp)!=Math.ceil(temp))
        {
            System.out.println("Board size must be perfect square");
        }
        else {
            SudokuSolver sudokuSolver = new SudokuSolver(grid_size);
            while(true)
            {
                System.out.println("Enter the position of grid and value for that position");
                int pos= sc.nextInt();
                int value=sc.nextInt();
                sudokuSolver.setValue(pos,value);
                sudokuSolver.print();
                if(sudokuSolver.validate())
                {
                    System.out.println("Wohoooo!!!! You nailed it");
                    break;
                }
            }
            sc.close();
        }
    }
}