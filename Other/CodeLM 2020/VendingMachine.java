import java.util.Scanner;
import java.io.*;
import java.util.*;
public class VendingMachine {
   static Scanner s = new Scanner(System.in);
	
   public static void main(String[] args) {
      String start = s.nextLine();            // The starting point.
      int n = Integer.parseInt(s.nextLine());
      String[][] path = new String[n][2];					// a nx2 array of connections.
   	
   	// The following code will read in the connections from the input stream and fill the array.
      
      HashMap<String,String> hmap = new HashMap<String,String>();
      
      for (int i = 0; i < n; i++) {
         path[i] = s.nextLine().split(" ");
         hmap.put(path[i][0],path[i][1]);
      }
      
      HashSet<String> seen = new HashSet<String>();
      
      while(true){
         if(start.length() < 2 || !Character.isDigit(start.charAt(1)) ){
            System.out.println(start);
            System.out.close();
            return;
         }
         
         if(seen.contains(start)){
            System.out.println("Loop");
            System.out.close();
            return;
         }
         
         seen.add(start);
         
         if(!hmap.containsKey(start)){
            break;
         }
         
         start = hmap.get(start);
      }
   	// code to solve the problem.  You can write and call other methods as well.
   	
      
      System.out.println("Nothing");                     // print your answer and just your answer.
   }
}
