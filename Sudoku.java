
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Sudoku {
        
        static boolean f = true; //global flag to check the character in the board
	static int SIZE=0; //global integer variable for size of the board
	static char[][] board; //2-d array for representing game board
	static char[] sSymbols; //character array for storing list of characters to be used in the sudoku
	
        public static void main(String args[]) throws IOException
                
        {
            try{
		
                    readInput(); //calling input function
		
                    if(sudokuSolver()&& f == true) //checking the flag and function values are true
                    {
                            printBoard(); //calling printBoard function to print the solution
                    }
		
                    else
                    {
                        if(!sudokuSolver()) //if the function returns false
                        {
                            System.out.println("No Solution found");
                        }
                        
                        else if(f == false) //if the flag is false
                        {
                    
                            System.out.println("Incorrect Input. Please run the program again");
                        }
                        
                    }
                }
            
            catch(NullPointerException e){ //catching null pointer exception
            
                    System.out.println("Please enter the size of list of symbol equal to the size of the board");
            }
            
	}
	
        public static boolean sudokuSolver()  //function to solve sudoku
        {
		for(int row=0;row<SIZE;row++) //looping for all the rows of the 2-d array
                {
			for(int col=0;col<SIZE;col++) //looping for all the columns of the 2-d array
                        {
				if(board[row][col]=='.')  //checking if the input character is '.', dot exist hence there is blank value in board that needs to be filled
                                {
					for (char cs : sSymbols) //looping through the character in the character list, backtrack by putting symbols from the list
                                        {
						if(isValid(row, col, cs)) //checking whether the value returned from function is true of not
                                                {
							board[row][col]=cs; //if true set character at particular row and column index 
							
                                                        if(sudokuSolver())  //recursively call the function to fill the elements in the 2-d array
                                                        {    
								
                                                            return true;
                                                                
                                                        }        
							
                                                        else
                                                        {    
							        
                                                            board[row][col]='.';  //if fails replace it with '.' and backtrack 
                                                        
                                                        }	
						}	
					}

					return false;
				}
			}
		}
		
                return true;
	}
        
             
        public static String cleanInput(String temps , String lsym ,int N ) //function for checking input rows in sudoku and 
                                                                            //whether the input rows have characters from the list of symbols only    
        { 
             
            int m=0 , n=0;
            Scanner sc = new Scanner(System.in); //creating scanner object
           
                
            while (temps.length() != N)  //if input length is differnt it will allow you to re input
            {
		
                System.out.println("There is error in input length!! RETRY!! ");
                temps = sc.next();
                
            }
       
            
          
            for(m=0 ; m < N ; m++) //looping through rows of board
            {
                for(n=0; n< N ; n++) //looping through columns of board
                {
                    if(temps.charAt(m)== lsym.charAt(n) || temps.charAt(m) == '.') //check if character belongs to the list of symbols or is '.'
                    {
                        
                        f=true; //if yes than set flag to true
                        break;
                    }
                    
                    else
                    {    
                        f=false; //if no than set flag to false
                        
                    }
                    
                }
                
                if(f==false) //check if the character does not belong to the list of symbols 
                {    
                    System.out.println("Please enter the symbol from the symbol list only");
                    break;
                }
                
            }
        
            
            return temps;
   
        }
	
	public static void readInput() throws IOException  //function to read input from the user
        {
		System.out.println("Please enter size of Board");
		BufferedReader  in = new BufferedReader(new InputStreamReader(System.in)); //creating buffer reader object
		
                try
                {
			SIZE =Integer.parseInt(in.readLine()); //taking size of board from the user
			sSymbols = new char[SIZE]; //intializing size of list of characters array
			System.out.println("Please enter the list of characters ");
			String inputSym = in.readLine(); //reading list of characters from user
                        String inputRows ; 
			
                        if(inputSym.length()==SIZE) //checking if the size of the list of characters is equal to the size of the board 
                        {
				sSymbols =inputSym.toCharArray(); //converting string to character array
                                board = new char[SIZE][SIZE]; //initalizing the size of 2-d array for board of the game
                                System.out.println("Please Enter input rows ");
                                
                                for(int i=0;i<SIZE;i++) //looping till the size of board
                                {
                                    if(f==false) //if the input character is not from the list of symbol program will break and not take input
                                    {
                                        break;
                                    }
                                
                                    else
                                    { 
                                        inputRows=in.readLine(); //reading input row by row
                                        inputRows= cleanInput(inputRows,inputSym,SIZE); //checking for input row whether the charcaters are from the list of symbols
                                        char[] tempArray = inputRows.toCharArray(); //converting string to character array
                                        int cnt=0;
				
                                        for (char c : tempArray) //looping through the array
                                        {
                                            board[i][cnt]=c; //setting the input characters in the board 
                                            cnt++;
                                        }
                                        
                                    }
                                }
			}
			
                        else
                        {
				System.out.println("There is error in input length of list of characters");
			}
			
			
			
		}
                
                catch (NumberFormatException | IOException e) //catching exception
                { 
			e.printStackTrace();
		}
                
                finally{
                    
                        in.close(); //closing inputreader object
                }
	
        }
	
	public static boolean isValid(int row,int col,char sym) //method to check whether the character is already present in row, column or box
        {
		if(existInBox(row,col,sym)) 
                {
			return false;
		}
		
                if(existInRow(row,sym))
                {
			return false;
		}
		
                if(existInCol(col,sym))
                {
			return false;
		}
		
                return true;
	}
	
	public static boolean existInRow(int row,char sym) //method to check whether the character is present in row
        {
		for(int i=0;i<SIZE;i++) // looping through the rows
                {
			if(board[row][i]==sym) // check whether character is present in the row
                        {
				return true;
			}
		}
		return false;
	}
	
        public static boolean existInCol(int col,char sym) //method to check whether the character is present in column
        {
	
                for(int i=0;i<SIZE;i++) // looping through the column
                {
			
                        if(board[i][col]==sym) // check whether character is present in the column
                        {
				return true;
			}
		}
		
                return false;
	}
	
        public static boolean existInBox(int row,int col,char sym)  //method to check whether the character is present in the box
        {
		
                int sqrtOfSize= (int)(Math.sqrt(SIZE)); //taking square root of the size of the board
		int r= row-row%sqrtOfSize; //to get the starting value of row of the box
		int c= col-col%sqrtOfSize; //to get the starting value of column of the box
		
		for(int i=r;i<r+sqrtOfSize;i++) //looping through the row of the boz
                {    
			for(int j=c;j<c+sqrtOfSize;j++) //looping through the column of the box
			{
				if(board[i][j]==sym) //check if the character is present in the box
                                {
					return true; 
				}
			}
                }
		return false;
	}
        
	public static void printBoard() //function to print the board
        {
		
		for(int row=0;row<SIZE;row++) //looping through the rows of the board
                {
			
                    for(int col=0;col<SIZE;col++) //looping through the column of the board
                    {
			
				
                                System.out.print(board[row][col]); //print each value
                    }
			
                    System.out.println();
		
                }
		
               
	}
}