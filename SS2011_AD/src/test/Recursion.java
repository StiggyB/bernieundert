package test;
// Example of several very well-know recursive routines written 
//  in java

import java.util.*;

public class Recursion {
  public static void main ( String [] args ) 
  {
    int n,r, result;
    char choice;
    do {
      displaymenu();
      choice = getchoice();
      switch (choice)
        {
          case '1': n=getnumber("Enter number to get factorial of: ");
            result = fact(n);
            System.out.println(" the factorial of " + n + " is: " + result);
            break;
          case '2': n=getnumber("Enter which fibonacci term you want: ");
            result = fib(n);
            System.out.println(" fibonacci term # " + n + "is: " + result);
            break;
          case '3': n=getnumber("Enter n's value for triangle: "); 
            r=getnumber("Enter r's value for triangle");
            result = triangle(n,r);
            System.out.println("triangle (" + n + "," + r + ") = "+ result);
            break; 
          case '4': n=getnumber("Enter number of rings for hanoi: ");
            hanoi(n,'A', 'B', 'C');
            break;
          case 'X':
          case 'x':  System.out.println( "now exiting program \n");
            break;
          default:  System.out.println(" Invalid selection , try again.");
            
        }  /* end of switch */
    }
    while (choice !='X' && choice != 'x');
  }
  public static int fact (int n)
    {
      if (n < 0 ) 
        return -1; /* error trap */
      else if (n == 0)
        return 1; /* base case */
      else
        return n*fact(n-1);
    }
  
/* fibonacci sequence, returns the nth number in sequence that
   begins at 0,1,1,2,3,5,8,13...  */
  
  public static int fib (int n)
    {
      if (n < 0) return -1; // error trap
      if (n==1)
        return 0;
      else if (n==2)
        return 1;
      else
        return fib(n-1) + fib(n-2);
    }
  
/* Pascal's triangle using recursion */
  
  public static int triangle(int n, int r)
    {
      if( n<r || r<0)
        return 0;
      else if ( n==r || r==0)
        return 1;
      else
        return triangle(n-1,r) + triangle (n-1,r-1);
    }
  
/* towers of hanoi, prints the moves to make to move
   n rings from tower a to tower c */
  
  public static  void hanoi(int n, char a, char b, char c)
    {
      if (n==1)
        System.out.println( "Move ring " + n + " from " + a + " to " + c);
      else
        {
          hanoi(n-1, a,c,b);
          System.out.println( "Move ring " + n + " from " + a  + " to " + c);
          hanoi(n-1, b,a,c);
        }
    }
  
  public static int getnumber (String prompt)
    { 
      Scanner in = new Scanner(System.in);
      char ch;
      int  n;
      do{
        System.out.println(prompt);
        String result  = in.nextLine();
        n = Integer.parseInt(result);
        System.out.println(" You entered the value: " + n + " Correct? y or n: ");
        String answer = in.nextLine();
        ch = answer.charAt(0);
      }
      while (ch == 'N' || ch == 'n');
      return (n);
    }
  public static  void displaymenu()
    {
      System.out.println( "\n  1 - Factorial of a number "
                          +  "\n  2 - Fibonacci term in sequence"
                          +  "\n  3 - Pascal's triangle (n,r) "
                          +  "\n  4 - Towers of Hanoi"
                          +  "\n  X - Exit from program " );
    }
  
  
   public static  char getchoice()
    {
      Scanner in = new Scanner(System.in);
      char ch;
      System.out.print(" Please enter a selection from the menu : ");
      String answer = in.nextLine();
      ch = answer.charAt(0);
      
      return (ch);
    }    
  
}


