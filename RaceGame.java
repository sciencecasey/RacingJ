package totiseHareRace;
import java.util.Random;

public class RaceGame {
   int positionH;
   int positionT;

   //Create a location array
   
   /*
   * The goal of this method is to print the gameboard for Tortoise and Hare
   * race.  The game has 50 spaces.  If someone wins, the winner is declared.
   * If a tie, tie is declared.  If on same location, OUCH! is printed at the
   * position of the bump. Otherwise, the location of H & T are printed relative
   * to eachother on the board.
   * 
   *@param positionH: integer input position of Hare
   *@param positionT: integer input position of Tortoise
   *
   *precondition: input of Hare and Tortoise has been calculated to integer
   *postcondition: prints the gameboard on a single line
   *
   *@author: CaseyJayne
   */
   public void printLocation() {
      //Create a first array with 50 spaces
      char [] gameBoard = new char[50];
      char space = ' ';
      //use a for loop to put spaces in every index
      for (int index=0; index<49; index ++) {
         gameBoard[index] = space; 
      }
      
      //use the input values H and T to place on board
      //If H & T both win, print Tie
      if((this.positionH == 49) && (this.positionT == 50)) {
         System.out.println("It's a TIE!");
         
         //Break to location to end game
      } 
      else if ((this.positionH == 49) || (this.positionT ==49)){
         //one winner
         //print the winner
         if(this.positionH ==49) {
            System.out.println("Hare Wins!");
         }
         else {
            System.out.println("Tortoise Winse!");
         }
         //Break to location to end game
      }
      else if (this.positionH == positionT) {
         if(this.positionH>46) {
            //printing would go off board
            System.out.println("OUCH!! I see the finishline!");
         }
         else if(this.positionH<1) {
            //printing go off board
            System.out.println("OUCH!");//will be at initial location (0)
         }
         else {
            //not at beg or end
            gameBoard[this.positionH-2] = 'O';
            gameBoard[this.positionH-1] = 'U';
            gameBoard[this.positionH]   = 'C';
            gameBoard[this.positionH+1] = 'H';
            gameBoard[this.positionH+2] = '!';
            System.out.println('\n');
            for (int index = 0; index < 50; index ++) {
               System.out.print("" + gameBoard[index]);
            }
         }
         moveGenerator(); //continue play
      } 
      else { //normal play
         //insert locations of H & T on Board
         gameBoard[this.positionH] = 'H';
         gameBoard[this.positionT] = 'T';
         //print Board
         System.out.println('\n');
         for (int index = 0; index < 50; index ++) {
            System.out.print("" + gameBoard[index]);
         }
         moveGenerator();//continue play
      }//end print loop
   }
   
   /*generates the moves for Hare and Tortoise via a random number generator
   *
   *precondition: no input parameters, so must be called directly from 
   *RaceGame's printLocation method
   *postcondition: calculates the move number and sends the move to the position
   *calculator
   *
   *@author Casey Jayne  
   */
   public void moveGenerator(){
      int hareMove;
      int tortMove;
      
      //declare an instance of random
      Random rand = new Random();
      //set the value of the character moves 1-10. 
      hareMove = rand.nextInt(10)+1;
      tortMove = rand.nextInt(10)+1;
      
      //switch case to translate the moves
      switch(hareMove) {
         case 1:
         case 2:{
            //big hop
            hareMove = 9;
            break;
         }
         case 3:
         case 4:
         case 5:{
            //small hop
            hareMove = 1;
            break;
         }
         case 6:{
            //big slip
            hareMove = -12;
            break;
         }
         case 7:
         case 8:{
            //small slip
            hareMove = -2;
            break;
         }
         case 9:
         case 10:{
            //fall asleep
            hareMove = 0;
            break;
         }
      }
      switch(tortMove) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5: {
            //fast plod
            tortMove = 3;
            break;
         }
         case 6:
         case 7:
         case 8:{
            //slow plod
            tortMove = 1;
            break;
         }
         case 9:
         case 10:{
            //slip
            tortMove = -6;
            break;
         }
      }
      //send the moves to the next positionCalculator
      positionCalculator(hareMove, tortMove);
   }

   private void positionCalculator(int hMove, int tMove) {
      //recalculate moves!
      this.positionH+=hMove;
      this.positionT+=tMove;
      //change value to stay on board
      if (this.positionH>49) {
         this.positionH =49;
      }
      if (this.positionT>49) {
         this.positionT = 49;
      }
      if (this.positionH<0) {
         this.positionH=0;
      }
      if (this.positionT<0) {
         this.positionT = 0;
      }
      
      //call printLocation for GameBoard view
      printLocation();
   }
   
   public static void main(String [] args) {
      //let's play!
      //set start Position:
      System.out.println("Let's Play!");
      //print faux start-board (if really called, would output "OUCH"
      System.out.println("H\nT");
      
      //call to gameplay
      RaceGame race = new RaceGame();
      race.moveGenerator();
      
      //
   }
}
