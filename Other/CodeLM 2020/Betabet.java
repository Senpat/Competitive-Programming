import java.util.ArrayList;
import java.util.Scanner;

public class Betabet {

   static Scanner s = new Scanner(System.in);

   public static void main(String[] args) {
      String[] input = s.nextLine().split(" ");        // A list of three-letter codes in betabetical order.
   	
   	// code to solve the problem.  You can write and call other methods as well.
      
      HashSet<Character> letters = new HashSet<Character>();
      
      for(int k = 0; k < input.length(); k++){
         letters.add(input[k].charAt(0));
         letters.add(input[k].charAt(2));
      }
      
      ArrayList<Character> alist = new ArrayList<Character
      
      
      System.out.println();                    // print your answer and just your answer.
   }
   
   public static 

}
