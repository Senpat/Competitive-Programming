import java.util.Scanner;
import java.io.*;
import java.util.*;
public class BasicOperations {

	static Scanner s = new Scanner(System.in);

   public static boolean found = false;

	public static void main(String[] args) {
		String digits = s.nextLine();                 // A string of digits.
		// code to solve the problem.  You can write and call other methods as well.
      
      int n = digits.length();
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(digits.charAt(k));
      }
      
      dothing(array,0,array[0]);
      
      
      
      
		System.out.println(found);                    // print your answer and just your answer.
	}
   
   public static void dothing(int[] array,int i, int sum){
   
      if(i == array.length-1){
         if(sum == 0){
            found = true;
         }
      } else {
         dothing(array,i+1,sum-array[i+1]);
         dothing(array,i+1,sum+array[i+1]);
      }
   }
      
      

}
